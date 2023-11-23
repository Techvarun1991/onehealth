package com.onehealth.service;


import com.onehealth.dto.CartItemRequest;
import com.onehealth.entity.LabCart;
import com.onehealth.exception.ResourceNotFoundException;
import com.onehealth.exception.ServiceNotAvailableException;


/**
 * The LabCartService interface defines operations for managing lab carts, including adding tests to a cart,
 * retrieving a patient's cart, removing items from a cart, retrieving carts by ID, and clearing a cart.
 */
public interface LabCartService {

	/**
	 * Adds a test specified in the CartItemRequest to the lab cart for the given patient.
	 *
	 * @param itemRequest The request containing details about the test to add to the cart.
	 * @throws ResourceNotFoundException If the requested patient or test is not found.
	 */
	void addToCart(CartItemRequest itemRequest) throws ResourceNotFoundException;

	/**
	 * Retrieves the lab cart for a specific patient.
	 *
	 * @param patientId The unique identifier of the patient.
	 * @return The lab cart associated with the specified patient.
	 * @throws ResourceNotFoundException    If the patient is not found.
	 * @throws ServiceNotAvailableException If the lab cart service is unavailable.
	 */
	LabCart getCart(long patientId) throws ResourceNotFoundException, ServiceNotAvailableException;

	/**
	 * Removes a specific test item from the lab cart of a patient.
	 *
	 * @param test_id    The unique identifier of the test to be removed.
	 * @param patient_id The unique identifier of the patient's cart.
	 * @throws ResourceNotFoundException    If the test or patient's cart is not found.
	 * @throws ServiceNotAvailableException If the lab cart service is unavailable.
	 */
	void removeItem(long test_id, long patient_id) throws ResourceNotFoundException, ServiceNotAvailableException;

	/**
	 * Retrieves the lab cart by its unique identifier.
	 *
	 * @param cartId The unique identifier of the lab cart.
	 * @return The lab cart with the specified ID.
	 * @throws ResourceNotFoundException If the lab cart is not found.
	 */
	LabCart getCartById(long cartId) throws ResourceNotFoundException;

	/**
	 * Clears all items from the lab cart specified by its unique identifier.
	 *
	 * @param cartId The unique identifier of the lab cart to be cleared.
	 * @throws ResourceNotFoundException If the lab cart is not found.
	 */
	void clearCart(long cartId) throws ResourceNotFoundException;

}
