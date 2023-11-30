package com.onehealth.serviceImplementation;

import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.onehealth.controller.LabTestsOrderController;
import com.onehealth.dto.LabCart;
import com.onehealth.dto.LabCartItem;
import com.onehealth.dto.LabOrderDetailsDto;
import com.onehealth.dto.LabTestOrderItemPair;
import com.onehealth.dto.OrderDto;
import com.onehealth.dto.OrderRequest;
import com.onehealth.dto.OrderUpdateDto;
import com.onehealth.dto.Patient;
import com.onehealth.entity.LabOrderItem;
import com.onehealth.entity.LabTestsOrder;
import com.onehealth.exception.ResourceNotFoundException;
import com.onehealth.repository.LabTestsOrderRepository;
import com.onehealth.service.LabTestsOrderService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LabTestsOrderServiceImplementation implements LabTestsOrderService{
	
	 @Autowired
	 private ModelMapper mapper;
	 
	 @Autowired
	 private LabTestsOrderRepository labTestsOrderRepository;
	 
	 @Autowired
	 private WebClient.Builder webClientBuilder;

	 @Value("${apiGatewayUrl}")
	 private String apiGatewayUrl;
	 
	 private static final Logger logger = LoggerFactory.getLogger(LabTestsOrderServiceImplementation.class);
	 
	 /**
	  * Places an order for laboratory tests.
	  *
	  * @param orderRequest The order request containing patient and cart details.
	  * @return An OrderDto representing the placed order.
	  * @throws ResourceNotFoundException If patient or cart details are not found.
	  */
	 @Override
	 public OrderDto placeOrder(OrderRequest orderRequest) throws ResourceNotFoundException {
	     // Fetch patient details from Patient Management service
	     logger.info("Fetching patient details with patient_id: " + orderRequest.getPatient_id());
	     Patient patientDto = webClientBuilder.build()
	             .get()
	             .uri(apiGatewayUrl + "/patientProfile/byPatientId/{patient_id}", orderRequest.getPatient_id())
	             .retrieve()
	             .bodyToMono(Patient.class)
	             .block();
	     logger.info("Patient details fetched successfully.");

	     // Fetch cart details from Lab Cart Management service
	     logger.info("Fetching cart details with cart_id: " + orderRequest.getCart_id());
	     LabCart labCart = webClientBuilder.build()
	             .get()
	             .uri(apiGatewayUrl + "/api/lab-carts/cart/{cart_id}", orderRequest.getCart_id())
	             .retrieve()
	             .bodyToMono(LabCart.class)
	             .block();
	     logger.info("Cart details fetched successfully.");

	     // Retrieve cart items and create LabOrderItems
	     Set<LabCartItem> cartItems = labCart.getCart_items();
	     LabTestsOrder labTestsOrder = new LabTestsOrder();
	     labTestsOrder.setItem(new HashSet<>());
	     AtomicReference<Double> totalOrderPrice = new AtomicReference<>(0.0);

	     Set<LabOrderItem> labOrderItems = cartItems.stream().map(cItems -> {
	         LabOrderItem labOrderItem = new LabOrderItem();
	         labOrderItem.setTest_id(cItems.getTest_id());
	         labOrderItem.setQuantity(cItems.getQuantity());
	         labOrderItem.setPrice(cItems.getTotal_product_price());
	         labOrderItem.setTest_name(cItems.getTest_name());
	         labOrderItem.setTest_date(cItems.getTest_date());
	         totalOrderPrice.set(totalOrderPrice.get() + labOrderItem.getPrice());
	         labOrderItem.setLab_name(cItems.getLabName());
	         labOrderItem.setLabAddress(cItems.getLabAddress());
	         labOrderItem.setLabId(cItems.getLabId());
	         labOrderItem.setLabTestsOrder(labTestsOrder);
	         labOrderItem.setTestCategory(cItems.getTestCategory());
	         labOrderItem.setPayment_mode("None");
	         labOrderItem.setPayment_status("Not Paid");
	         labOrderItem.setOrder_status("Received");
	         return labOrderItem;
	     }).collect(Collectors.toSet());

	     labTestsOrder.setItem(labOrderItems);

	     // Set order details
	     labTestsOrder.setOrder_created(new java.util.Date());
	     labTestsOrder.setPatientId(orderRequest.getPatient_id());
	     labTestsOrder.setPatient_name(patientDto.getFirstName() + " " + patientDto.getLastName());
	     labTestsOrder.setTransactionId(0);
	     labTestsOrder.setTotal_amount(totalOrderPrice.get());

	     // Check if the order total amount is greater than zero
	     if (labTestsOrder.getTotal_amount() > 0) {
	         logger.info("Order total amount: " + labTestsOrder.getTotal_amount());
	         LabTestsOrder order = labTestsOrderRepository.save(labTestsOrder);

	         // Clear the cart after placing the order
	         logger.info("Clearing the cart with cart_id: " + orderRequest.getCart_id());
	         String cartClearResponse = webClientBuilder.build()
	                 .post()
	                 .uri(apiGatewayUrl + "/api/lab-carts/clearCart/{cartId}", orderRequest.getCart_id())
	                 .retrieve()
	                 .bodyToMono(String.class)
	                 .block();
	         logger.info("Cart cleared successfully.");

	         return this.mapper.map(order, OrderDto.class);
	     } else {
	         throw new ResourceNotFoundException("Please add items to the cart before placing an order.");
	     }
	 }

	 
	 
	 /**
	  * Retrieves all orders associated with a patient by their patient ID.
	  *
	  * @param patient_id The patient's unique identifier.
	  * @return A list of LabTestOrderItemPair representing the orders and their associated items.
	  */
	 @Override
	 public List<LabTestOrderItemPair> getAllOrderByPatientId(long patient_id) {
	     logger.info("Fetching all orders by patientId: {}", patient_id);
	     List<LabTestsOrder> orders = labTestsOrderRepository.findByPatientId(patient_id);
	     List<LabTestOrderItemPair> result = new ArrayList<>();

	     // Flatten the result by iterating through each order and its associated items
	     for (LabTestsOrder order : orders) {
	         for (LabOrderItem item : order.getItem()) {
	             result.add(new LabTestOrderItemPair(order, item));
	         }
	     }

	     logger.info("Found {} orders for patientId: {}", result.size(), patient_id);
	     return result;
	 }

	 /**
	 * Retrieves an order by its transaction ID.
	 *
	 * @param transaction_id The unique transaction ID.
	 * @return An Optional containing an OrderDto if found, or empty if not found.
	 */
	 @Override
	 public Optional<OrderDto> getOrderByTransactionId(long transaction_id) {
	     logger.info("Fetching order by transactionId: {}", transaction_id);
	     // Implement your logic here and return the order if found, or Optional.empty() if not found
	     return Optional.empty();
	 }

	 /**
     * Retrieves an order by its order ID.
     *
     * @param order_id The unique order ID.
     * @return An Optional containing an OrderDto if found, or empty if not found.
     * @throws ResourceNotFoundException If the order with the specified ID is not found.
     */
	 @Override
	 public Optional<OrderDto> getOrderById(long order_id) throws ResourceNotFoundException {
	     logger.info("Fetching order by orderId: {}", order_id);
	     LabTestsOrder order = labTestsOrderRepository.findById(order_id)
	             .orElseThrow(() -> new ResourceNotFoundException("Order not found with orderId: " + order_id));

	     // Initialize the collections to avoid proxy issues
	     order.getItem().size(); // This initializes the collection

	     OrderDto orderDto = mapper.map(order, OrderDto.class);
	     logger.info("Found order by orderId: {}", order_id);
	     return Optional.of(orderDto);
	 }

	 /**
	     * Deletes an order by its order ID.
	     *
	     * @param order_id The unique order ID to delete.
	     * @throws ResourceNotFoundException If the order with the specified ID is not found.
	     */
	 @Override
	 public void deleteOrderById(long order_id) throws ResourceNotFoundException {
	     logger.info("Deleting order by orderId: {}", order_id);
	     boolean exist = labTestsOrderRepository.existsById(order_id);
	     if (exist) {
	         labTestsOrderRepository.deleteById(order_id);
	         logger.info("Deleted order with orderId: {}", order_id);
	     } else {
	         logger.warn("Order not found with orderId: {}", order_id);
	         throw new ResourceNotFoundException("Order Not Found With Order Id : " + order_id);
	     }
	 }

	 /**
	     * Updates an order's status, payment status, and payment mode.
	     *
	     * @param dto The OrderUpdateDto containing the updated order information.
	     * @return True if the update is successful, false otherwise.
	     */
	 public boolean updateOrder(OrderUpdateDto dto) {
	     logger.info("Updating order with orderId: {} and orderItemId: {}", dto.getOrder_id(), dto.getOrder_item_id());
	     // Retrieve the LabTestsOrder by orderId
	     LabTestsOrder order = labTestsOrderRepository.findById(dto.getOrder_id()).orElse(null);

	     if (order != null) {
	         // Find the LabOrderItem within the LabTestsOrder by orderItemId
	         LabOrderItem itemToUpdate = order.getItem()
	                 .stream()
	                 .filter(item -> item.getOrderItemId() == dto.getOrder_item_id())
	                 .findFirst()
	                 .orElse(null);

	         if (itemToUpdate != null) {
	             // Update the item's details
	             itemToUpdate.setOrder_status(dto.getOrder_status());
	             itemToUpdate.setPayment_status(dto.getPayment_status());
	             itemToUpdate.setPayment_mode(dto.getPayment_mode());
	             itemToUpdate.setTest_date(dto.getTest_date());	  
	             System.out.println("ITEM TO UPDATE : " +itemToUpdate.getTest_date());
	             labTestsOrderRepository.save(order);

	             logger.info("Updated order with orderId: {} and orderItemId: {}", dto.getOrder_id(), dto.getOrder_item_id());
	             return true; // Update successful
	         }
	     }

	     logger.warn("Order or item not found with orderId: {} and orderItemId: {}", dto.getOrder_id(), dto.getOrder_item_id());
	     return false; // Order or item not found, update failed
	 }

	 /**
	     * Retrieves laboratory order details by lab ID.
	     *
	     * @param labId The unique lab ID.
	     * @return A list of LabOrderDetailsDto containing order details for the specified lab.
	     */
	 @Override
	 public List<LabOrderDetailsDto> findLabOrderDetailsByLabId(long labId) {
	     logger.info("Fetching lab order details by labId: {}", labId);
	     List<Object[]> labOrderDetails = labTestsOrderRepository.findLabOrderDetailsByLabId(labId);
	     List<LabOrderDetailsDto> labOrderDetailsDtoList = convertToDto(labOrderDetails);

	     logger.info("Found {} lab order details by labId: {}", labOrderDetailsDtoList.size(), labId);
	     return labOrderDetailsDtoList;
	 }

	 
	 /**
	     * Converts a list of raw data to LabOrderDetailsDto objects.
	     *
	     * @param originalData The original data obtained from a custom repository query.
	     * @return A list of LabOrderDetailsDto objects.
	     */
	 public List<LabOrderDetailsDto> convertToDto(List<Object[]> originalData) {
	        List<LabOrderDetailsDto> result = new ArrayList<>();

	        for (Object[] row : originalData) {
	            LabOrderDetailsDto dto = new LabOrderDetailsDto();
	            dto.setOrderItemId(((Integer) row[0]).longValue());
	            dto.setTest_id(((Long) row[1]));
	            dto.setTest_name((String) row[2]);
	            dto.setLabName((String) row[3]);
	            dto.setLabId(((Long) row[4]));
	            dto.setLabAddress((String) row[5]);
	            dto.setTestCategory((String) row[6]);
	            dto.setTest_date((Date) row[7]);
	            dto.setPrice((Double) row[8]);
	            dto.setQuantity((Integer) row[9]);
	            dto.setOrder_id(((Long) row[10]));
	            dto.setOrder_status((String) row[11]);
	            dto.setPayment_mode((String) row[12]);
	            dto.setPayment_status((String) row[13]);
	            dto.setOrder_created((Timestamp) row[14]);
	            dto.setTotal_amount((Double) row[15]);
	            dto.setPatientId(((Long) row[16]));
	            dto.setTransactionId(((Long) row[17]));
	            dto.setPatient_name((String) row[18]);

	            result.add(dto);
	        }

	        return result;
	    }

}
