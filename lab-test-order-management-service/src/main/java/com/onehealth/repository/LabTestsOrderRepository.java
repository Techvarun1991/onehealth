package com.onehealth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onehealth.dto.LabOrderDetailsDto;
import com.onehealth.entity.LabTestsOrder;

public interface LabTestsOrderRepository extends JpaRepository<LabTestsOrder, Long>{

//	List<LabTestsOrder> findByLabId(long labId);
	List<LabTestsOrder> findByPatientId(long patientId);
	
	 @Query(value = "SELECT i.order_item_id, i.test_id, i.test_name, i.lab_name, i.lab_id, i.lab_address, i.test_category, i.test_date, i.price, i.quantity, " +
             "o.order_id, i.order_status, i.payment_mode, i.payment_status, o.order_created, o.total_amount, o.patient_id, o.transaction_id, o.patient_name " +
             "FROM lab_order_item i " +
             "JOIN lab_tests_order o ON i.lab_tests_order_order_id = o.order_id " +
             "WHERE i.lab_id = :labId", nativeQuery = true)
	 List<Object[]> findLabOrderDetailsByLabId(@Param("labId") Long labId);
	 
	 @Query(value = "SELECT i.order_item_id, i.test_id, i.test_name, i.lab_name, i.lab_id, i.lab_address, i.test_category, i.test_date, i.price, i.quantity, " +
             "o.order_id, i.order_status, i.payment_mode, i.payment_status, o.order_created, o.total_amount, o.patient_id, o.transaction_id, o.patient_name " +
             "FROM lab_order_item i " +
             "JOIN lab_tests_order o ON i.lab_tests_order_order_id = o.order_id " +
             "WHERE i.lab_id = :labId AND o.order_id = :orderId AND i.order_item_id = :orderItemId", nativeQuery = true)
	 Object findLabOrderItemDetailsByLabId(@Param("labId") Long labId , @Param("orderId") Long orderId , @Param("orderItemId") Long orderItemId);
	
}
