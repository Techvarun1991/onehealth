package com.onehealth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.onehealth.dto.CartItemRequest;
import com.onehealth.entity.LabCart;
import com.onehealth.exception.ResourceNotFoundException;
import com.onehealth.exception.ServiceNotAvailableException;
import com.onehealth.repository.LabCartRepository;
import com.onehealth.service.LabCartService;
import java.util.List;
import java.util.logging.Logger;

/**
 * The `LabCartController` class handles HTTP requests related to lab carts.
 * It provides endpoints for managing lab carts such as retrieving, clearing, and listing lab carts.
 */
@RestController
@RequestMapping("/api/lab-carts")
public class LabCartController {

    @Autowired
    private LabCartService labCartService;
    
    @Autowired 
    private LabCartRepository repository;

    private static final Logger logger = Logger.getLogger(LabCartController.class.getName());
    
    
    /**
     * Adds a test to the lab cart for a specific patient.
     *
     * @param itemRequest The CartItemRequest containing the details of the test to be added.
     * @return A ResponseEntity indicating the success of the operation.
     * @throws ResourceNotFoundException If the requested patient or test is not found.
     */
    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody CartItemRequest itemRequest) throws ResourceNotFoundException {
        logger.info("Adding test to lab cart for patient with ID: " + itemRequest.getPatient_id());

        try {
            labCartService.addToCart(itemRequest);
            logger.info("Test added to lab cart successfully.");
            return ResponseEntity.ok("Test added to lab cart successfully.");
        } catch (ResourceNotFoundException e) {
            logger.warning("Failed to add test to lab cart: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Retrieves the lab cart for a specific patient.
     *
     * @param patientId The ID of the patient.
     * @return The LabCart for the specified patient.
     * @throws ServiceNotAvailableException 
     * @throws ResourceNotFoundException
     */
    @GetMapping("/{patientId}")
    public ResponseEntity<?> getCart(@PathVariable long patientId) throws ResourceNotFoundException , ServiceNotAvailableException{
        logger.info("Retrieving lab cart for patient with ID: " + patientId);
        try {
        	LabCart cart = labCartService.getCart(patientId);
        	return ResponseEntity.ok(cart);
		} catch (ResourceNotFoundException e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
    }

    /**
     * Removes a specific test item from the lab cart for a specific patient.
     *
     * @param test_id    The ID of the test to be removed.
     * @param patient_id The ID of the patient.
     * @return A ResponseEntity indicating the success of the operation.
     * @throws ResourceNotFoundException 
     * @throws ServiceNotAvailableException 
     */
    @DeleteMapping("/remove-item/{test_id}/{patient_id}")
    public ResponseEntity<?> removeItem(@PathVariable(value = "test_id") long test_id, @PathVariable(value = "patient_id") long patient_id) throws Exception {
        logger.info("Removing test with ID " + test_id + " from lab cart for patient with ID: " + patient_id);

        try {
			labCartService.removeItem(test_id, patient_id);
			logger.info("Test removed from lab cart successfully.");
	        return ResponseEntity.ok("Test removed from lab cart successfully.");
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

        
    }
    
    /**
     * Retrieves a LabCart by its unique identifier (cartId).
     *
     * @param cartId The unique identifier of the LabCart to retrieve.
     * @return A ResponseEntity containing the LabCart if found.
     * @throws ResourceNotFoundException If the LabCart with the provided cartId is not found.
     */
    @GetMapping("/cart/{cartId}")
    public ResponseEntity<?> getCartById(@PathVariable long cartId) throws ResourceNotFoundException {
        try {
            // Log the attempt to retrieve a cart by cartId.
            logger.info("Getting cart with cart id " + cartId);

            // Attempt to retrieve the LabCart with the provided cartId.
            LabCart cart = labCartService.getCartById(cartId);

            // Return a ResponseEntity with the LabCart if found.
            return ResponseEntity.ok(cart);
        } catch (ResourceNotFoundException e) {
            // Handle the exception by returning a ResponseEntity with a not found status and an error message.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Clears the LabCart with the specified cartId.
     *
     * @param cartId The unique identifier of the LabCart to clear.
     * @return A ResponseEntity with a success message if clearing is successful.
     * @throws ResourceNotFoundException If the LabCart with the provided cartId is not found.
     */
    @PostMapping("/clearCart/{cartId}")
    public ResponseEntity<?> clearCart(@PathVariable long cartId) throws ResourceNotFoundException {
        try {
            // Clear the LabCart with the provided cartId.
            labCartService.clearCart(cartId);

            // Return a ResponseEntity with a success message.
            return ResponseEntity.ok("Cart cleared successfully!!");
        } catch (ResourceNotFoundException e) {
            // Handle the exception by returning a ResponseEntity with a not found status and an error message.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Retrieves a list of all LabCarts.
     *
     * @return A list of LabCarts as a ResponseEntity.
     */
    @GetMapping
    public List<LabCart> getAllCart() {
        // Retrieve and return a list of all LabCarts.
        return repository.findAll();
    }

}
