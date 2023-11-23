package com.onehealth.serviceImplementation;


import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.onehealth.dto.CartItemRequest;
import com.onehealth.dto.Patient;
import com.onehealth.dto.Tests;
import com.onehealth.entity.LabCart;
import com.onehealth.entity.LabCartItem;
import com.onehealth.exception.ResourceNotFoundException;
import com.onehealth.exception.ServiceNotAvailableException;
import com.onehealth.repository.LabCartRepository;
import com.onehealth.service.LabCartService;

import jakarta.transaction.Transactional;


/**
 * Service implementation responsible for managing lab carts, including adding tests to carts,
 * retrieving cart details, removing items from carts, and clearing carts.
 */
@Service
@Transactional
public class LabCartServiceImplementation implements LabCartService {
	
	@Autowired
	private LabCartRepository labCartRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Value("${apiGatewayUrl}")
	private String apiGatewayUrl;
	
	
	private static final Logger logger = Logger.getLogger(LabCartServiceImplementation.class.getName());
	
	/**
	 * Adds a test to the lab cart for a specified patient.
	 *
	 * @param itemRequest The request containing information about the test to add.
	 * @throws ResourceNotFoundException If the patient or test is not found.
	 */
	@Override
	public void addToCart(CartItemRequest itemRequest) throws ResourceNotFoundException {
	    logger.info("Adding test to lab cart for patient with ID: " + itemRequest.getPatient_id());

	    // Fetch patient details from Patient Management service
	    logger.info("Fetching Patient Details With ID : "+itemRequest.getPatient_id());
	    Patient patientDto = webClientBuilder.build()
	            .get()
	            .uri(apiGatewayUrl+"/patientProfile/byPatientId/{patientId}",itemRequest.getPatient_id())
	            .retrieve()
	            .bodyToMono(Patient.class)
	            .block();
	    System.out.println(patientDto);
	    if (patientDto == null) {
	        logger.warning("Failed to add test to lab cart: Patient not found with ID " + itemRequest.getPatient_id());
	        throw new ResourceNotFoundException("Patient Not found with Patient id : " + itemRequest.getPatient_id());
	    }

	    // Fetch test details from Test Management service
	    logger.info("Fetching Test Details With ID : "+itemRequest.getTest_id());
	    Tests test = webClientBuilder.build()
	            .get()
	            .uri(apiGatewayUrl+"/api/test/singleTest",
	                    uriBuilder -> uriBuilder.queryParam("test_id", itemRequest.getTest_id()).build())
	            .retrieve()
	            .bodyToMono(Tests.class)
	            .block();
	    System.out.println(test);

	    if (test == null) {
	        logger.warning("Failed to add test to lab cart: Test not found with ID " + itemRequest.getTest_id());
	        throw new ResourceNotFoundException("Test Not found with Test id : " + itemRequest.getTest_id());
	    }

	    LabCartItem cartItem = new LabCartItem();
	    cartItem.setQuantity(itemRequest.getQuantity());
	    cartItem.setTest_id(itemRequest.getTest_id());
	    cartItem.setTotal_product_price(test.getPrice() * itemRequest.getQuantity());
	    cartItem.setLabAddress(test.getLabAddress());
	    cartItem.setLabName(test.getLabName());
	    cartItem.setLabId(test.getLabId());
	    cartItem.setTestCategory(test.getTestCategory());
	    
	    LabCart labCart = labCartRepository.findByPatientId(itemRequest.getPatient_id());
	    if (labCart == null) {
	        labCart = new LabCart();
	        labCart.setPatient_id(itemRequest.getPatient_id());
	    }
	    
	    cartItem.setLabCart(labCart);
	    Set<LabCartItem> items = labCart.getCart_items();
	    AtomicReference<Boolean> flag=new AtomicReference<>(false);
        Set<LabCartItem> newitem= items.stream().map((i) -> {
        	
        	if(i.getTest_id()==test.getTest_id()) {
        		
        		i.setQuantity(itemRequest.getQuantity());
        		i.setTotal_product_price(test.getPrice() * itemRequest.getQuantity());
        		flag.set(true);
        	}
        	
        	return i;
        }).collect(Collectors.toSet());
        
        if(flag.get()) {
        	items.clear();
        	items.addAll(newitem);
        	
        }
        else {
        	cartItem.setLabCart(labCart);
			items.add(cartItem);
        }

	    cartItem.setTest_name(test.getTestName());
	    cartItem.setTest_date(itemRequest.getTest_date());
	    labCartRepository.save(labCart);
	    

	    logger.info("Test added to lab cart successfully For patinet and test Id respectively : "+itemRequest.getPatient_id()+" , "+itemRequest.getTest_id());
	}

	
	/**
	 * Retrieves the lab cart for a patient.
	 *
	 * @param patientId The unique identifier of the patient.
	 * @return The lab cart for the specified patient.
	 * @throws ResourceNotFoundException If the patient or lab cart is not found.
	 * @throws ServiceNotAvailableException If the required service is not available.
	 */
	@Override
	public LabCart getCart(long patientId) throws ResourceNotFoundException, ServiceNotAvailableException {
		logger.info("Fetching Patient Details With ID : "+patientId);
	    Patient patientDto = webClientBuilder.build()
		    .get()
		    .uri(apiGatewayUrl+"/patientProfile/byPatientId/{patientId}", patientId)
		    .retrieve()
		    .bodyToMono(Patient.class)
		    .block();
	    

		if (patientDto == null) {
		    logger.warning("Failed to add test to lab cart: Patient not found with ID " + patientId);
		    throw new ResourceNotFoundException("Patient not found with Patient ID: " + patientId);
		}

		LabCart cart = labCartRepository.findByPatientId(patientId);

		return cart;
	}

	/**
	 * Removes a test from a patient's lab cart.
	 *
	 * @param test_id     The unique identifier of the test to remove.
	 * @param patient_id  The unique identifier of the patient.
	 * @throws ResourceNotFoundException If the patient or test is not found.
	 * @throws ServiceNotAvailableException If the required service is not available.
	 */
	@Override
	public void removeItem(long test_id,long patient_id) throws ResourceNotFoundException, ServiceNotAvailableException {
		logger.info("Removing Test "+test_id+" From Cart of Patient With ID : "+patient_id);
		LabCart labCart;
		try {
			labCart = this.mapper.map(getCart(patient_id),LabCart.class);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		Set<LabCartItem> set = labCart.getCart_items();
		
		set.removeIf((item) -> item.getTest_id() == test_id);
		labCartRepository.save(labCart);
		logger.info("Test "+test_id+" From Cart of Patient With ID : "+patient_id+"Removed Successfully");
	}



	/**
	 * Retrieves the lab cart by its unique identifier.
	 *
	 * @param cartId The unique identifier of the lab cart to retrieve.
	 * @return The lab cart with the specified ID.
	 * @throws ResourceNotFoundException If the lab cart is not found.
	 */
	@Override
	public LabCart getCartById(long cartId) throws ResourceNotFoundException {
		logger.info("retriving cart with cart id " + cartId);
		// TODO Auto-generated method stub
		return labCartRepository.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Lab Cart Not Found !!"));
	}	
	
	/**
	 * Clears a lab cart by removing all items from it.
	 *
	 * @param cartId The unique identifier of the lab cart to clear.
	 * @throws ResourceNotFoundException If the lab cart is not found.
	 */
	@Override
	public void clearCart(long cartId) throws ResourceNotFoundException {
		logger.info("Clearing cart with cart id " + cartId);
		LabCart cart = getCartById(cartId);
		
		cart.getCart_items().clear();
		labCartRepository.save(cart);
	}
	
}
