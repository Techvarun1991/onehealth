package com.onehealth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.onehealth.dto.LabOrderDetailsDto;
import com.onehealth.dto.LabTestOrderItemPair;
import com.onehealth.dto.OrderDto;
import com.onehealth.dto.OrderRequest;
import com.onehealth.dto.OrderUpdateDto;
import com.onehealth.dto.TestDateDto;
import com.onehealth.exception.ResourceNotFoundException;


public interface LabTestsOrderService {

	OrderDto placeOrder(OrderRequest orderRequest) throws ResourceNotFoundException;
	List<LabTestOrderItemPair> getAllOrderByPatientId(long patient_id);
//	List<OrderDto> getAllOrderByLabId(long lab_id);
	Optional<OrderDto> getOrderByTransactionId(long transaction_id);
	Optional<OrderDto> getOrderById(long order_id) throws ResourceNotFoundException;
	void deleteOrderById(long order_id) throws ResourceNotFoundException;
	boolean updateOrder(OrderUpdateDto orderDto) throws ResourceNotFoundException;
	List<LabOrderDetailsDto> findLabOrderDetailsByLabId(long labId);
	boolean updateTestDate(TestDateDto testDateDto) throws ResourceNotFoundException;
	Optional<LabOrderDetailsDto> getLabOrderItemDetails(long labId,long orderId ,long orderItemId);
}
