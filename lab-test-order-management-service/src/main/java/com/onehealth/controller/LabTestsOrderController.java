package com.onehealth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.onehealth.dto.LabOrderDetailsDto;
import com.onehealth.dto.LabOrderItemRequest;
import com.onehealth.dto.LabTestOrderItemPair;
import com.onehealth.dto.OrderDto;
import com.onehealth.dto.OrderRequest;
import com.onehealth.dto.OrderUpdateDto;
import com.onehealth.dto.TestDateDto;
import com.onehealth.exception.ResourceNotFoundException;
import com.onehealth.repository.LabTestsOrderRepository;
import com.onehealth.service.LabTestsOrderService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lab-tests-order")
public class LabTestsOrderController {

	@Autowired
    private LabTestsOrderService labTestsOrderService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(LabTestsOrderController.class);

	/**
     * Places an order for lab tests.
     *
     * @param orderRequest The order request containing test details.
     * @return A ResponseEntity with the created order.
     * @throws ResourceNotFoundException If a requested resource is not found.
     */
	@PostMapping("/place-order")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest) throws ResourceNotFoundException {
        try {
            logger.info("Placing an order.");
            OrderDto order = labTestsOrderService.placeOrder(orderRequest);
            logger.info("Order placed successfully.");
            return ResponseEntity.ok(order);
        } catch (ResourceNotFoundException e) {
            logger.warn("Failed to place an order: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            
        }
    }

	/**
     * Retrieves all orders for a specific patient.
     *
     * @param patientId The ID of the patient.
     * @return A ResponseEntity with a list of orders for the patient.
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<LabTestOrderItemPair>> getAllOrderByPatientId(@PathVariable long patientId) {
        logger.info("Fetching all orders for patient with ID: " + patientId);
        List<LabTestOrderItemPair> orders = labTestsOrderService.getAllOrderByPatientId(patientId);
        return ResponseEntity.ok(orders);
    }

    
    /**
     * Retrieves all orders for a specific lab.
     *
     * @param labId The ID of the lab.
     * @return A list of order details for the lab.
     */
    @GetMapping("/lab/{labId}")
    public List<LabOrderDetailsDto> getAllOrderByLabId(@PathVariable long labId) {
        logger.info("Fetching all orders for lab with ID: " + labId);
        List<LabOrderDetailsDto> resultObjects = labTestsOrderService.findLabOrderDetailsByLabId(labId);
        return resultObjects;
    }

    /**
     * Retrieves an order by its transaction ID.
     *
     * @param transactionId The transaction ID of the order.
     * @return A ResponseEntity with the retrieved order or not found if not found.
     */
    @GetMapping("/{transactionId}")
    public ResponseEntity<OrderDto> getOrderByTransactionId(@PathVariable long transactionId) {
        logger.info("Fetching order by transaction ID: " + transactionId);
        Optional<OrderDto> order = labTestsOrderService.getOrderByTransactionId(transactionId);
        return order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves an order by its order ID.
     *
     * @param orderId The ID of the order to retrieve.
     * @return A ResponseEntity with the retrieved order or not found if not found.
     * @throws ResourceNotFoundException If a requested resource is not found.
     */
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable long orderId) throws ResourceNotFoundException {
        try {
            logger.info("Fetching order by ID: " + orderId);
            Optional<OrderDto> order = labTestsOrderService.getOrderById(orderId);
            return order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        } catch (ResourceNotFoundException e) {
            logger.warn("Order not found: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Deletes an order by its order ID.
     *
     * @param orderId The ID of the order to delete.
     * @return A ResponseEntity with a success message or not found if the order doesn't exist.
     * @throws ResourceNotFoundException If a requested resource is not found.
     */
    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable long orderId) throws ResourceNotFoundException {
        try {
            logger.info("Deleting order by ID: " + orderId);
            labTestsOrderService.deleteOrderById(orderId);
            logger.info("Order deleted successfully.");
            return ResponseEntity.ok("Order deleted successfully.");
        } catch (ResourceNotFoundException e) {
            logger.warn("Failed to delete order: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    
    /**
     * Updates an existing order.
     *
     * @param orderDto The updated order details.
     * @return A ResponseEntity with true if the order was successfully updated, or not found if the order doesn't exist.
     * @throws ResourceNotFoundException If a requested resource is not found.
     */
    @PutMapping("/update-order")
    public ResponseEntity<?> updateOrder(@RequestBody OrderUpdateDto orderDto) throws ResourceNotFoundException {
        try {
            logger.info("Updating order with ID: " + orderDto.getOrder_id());
            boolean updatedOrder = labTestsOrderService.updateOrder(orderDto);
            if (updatedOrder) {
                logger.info("Order updated successfully.");
                return ResponseEntity.ok(updatedOrder);
            } else {
                logger.warn("Order not found for update.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found for update.");
            }
        } catch (ResourceNotFoundException e) {
            logger.warn("Failed to update order: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
   
    /**
     * Updates an existing order.
     *
     * @param orderDto The updated order details.
     * @return A ResponseEntity with true if the order was successfully updated, or not found if the order doesn't exist.
     * @throws ResourceNotFoundException If a requested resource is not found.
     */
    @PutMapping("/update-testDate")
    public ResponseEntity<?> updateOrderDate(@RequestBody TestDateDto orderDto) throws ResourceNotFoundException {
        try {
            logger.info("Updating order with ID: " + orderDto.getOrder_id());
            boolean updatedOrder = labTestsOrderService.updateTestDate(orderDto);
            if (updatedOrder) {
                logger.info("Order updated successfully.");
                return ResponseEntity.ok(updatedOrder);
            } else {
                logger.warn("Order not found for update.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found for update.");
            }
        } catch (ResourceNotFoundException e) {
            logger.warn("Failed to update order: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @GetMapping("/lab-order-item")
    public ResponseEntity<?> getLabOrderItemDetails(@RequestBody LabOrderItemRequest labOrderItemRequest){
    	 try {
             // Log the request details
             logger.info("Received request for lab order details: {}", labOrderItemRequest);

             // Call the service to get lab order details
             Optional<LabOrderDetailsDto> labOrderDetailsDto = labTestsOrderService.getLabOrderItemDetails(
                     labOrderItemRequest.getLabId(),
                     labOrderItemRequest.getOrderId(),
                     labOrderItemRequest.getOrderItemId());

             // Log the result
             if (labOrderDetailsDto.isPresent()) {
                 logger.info("Lab order details found: {}", labOrderDetailsDto.get());
                 return new ResponseEntity<>(labOrderDetailsDto.get(), HttpStatus.OK);
             } else {
            	 String notFoundMessage = "Lab order details not found for LabId: "
                         + labOrderItemRequest.getLabId()
                         + ", OrderId: "
                         + labOrderItemRequest.getOrderId()
                         + ", OrderItemId: "
                         + labOrderItemRequest.getOrderItemId();
                 logger.info("No lab order item details found for LabId: {}, OrderId: {}, OrderItemId: {}",
                         labOrderItemRequest.getLabId(),
                         labOrderItemRequest.getOrderId(),
                         labOrderItemRequest.getOrderItemId());
                 return new ResponseEntity<>(notFoundMessage ,HttpStatus.NOT_FOUND);
             }
         } catch (Exception e) {
             // Log and handle any unexpected exceptions
             logger.error("Error occurred while processing lab order details request", e);
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }
}
