package com.onehealth.dto;

import java.sql.Date;
import java.sql.Timestamp;


public class LabOrderDetailsDto {
    private Long orderItemId;
    private Long test_id;
    private String test_name;
    private String labName;
    private Long labId;
    private String labAddress;
    private String testCategory;
    private Date test_date;
    private Double price;
    private Integer quantity;
    private Long order_id;
    private String order_status;
    private String payment_mode;
    private String payment_status;
    private Timestamp order_created;
    private Double total_amount;
    private Long patientId;
    private Long transactionId;
    private String patient_name;
	@Override
	public String toString() {
		return "LabOrderDetailsDto [orderItemId=" + orderItemId + ", test_id=" + test_id + ", test_name=" + test_name
				+ ", labName=" + labName + ", labId=" + labId + ", labAddress=" + labAddress + ", testCategory="
				+ testCategory + ", test_date=" + test_date + ", price=" + price + ", quantity=" + quantity
				+ ", order_id=" + order_id + ", order_status=" + order_status + ", payment_mode=" + payment_mode
				+ ", payment_status=" + payment_status + ", order_created=" + order_created + ", total_amount="
				+ total_amount + ", patientId=" + patientId + ", transactionId=" + transactionId + ", patient_name="
				+ patient_name + "]";
	}
	public LabOrderDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LabOrderDetailsDto(Long orderItemId, Long test_id, String test_name, String labName, Long labId,
			String labAddress, String testCategory, Date test_date, Double price, Integer quantity, Long order_id,
			String order_status, String payment_mode, String payment_status, Timestamp order_created, Double total_amount,
			Long patientId, Long transactionId, String patient_name) {
		super();
		this.orderItemId = orderItemId;
		this.test_id = test_id;
		this.test_name = test_name;
		this.labName = labName;
		this.labId = labId;
		this.labAddress = labAddress;
		this.testCategory = testCategory;
		this.test_date = test_date;
		this.price = price;
		this.quantity = quantity;
		this.order_id = order_id;
		this.order_status = order_status;
		this.payment_mode = payment_mode;
		this.payment_status = payment_status;
		this.order_created = order_created;
		this.total_amount = total_amount;
		this.patientId = patientId;
		this.transactionId = transactionId;
		this.patient_name = patient_name;
	}
	public Long getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}
	public Long getTest_id() {
		return test_id;
	}
	public void setTest_id(Long test_id) {
		this.test_id = test_id;
	}
	public String getTest_name() {
		return test_name;
	}
	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public Long getLabId() {
		return labId;
	}
	public void setLabId(Long labId) {
		this.labId = labId;
	}
	public String getLabAddress() {
		return labAddress;
	}
	public void setLabAddress(String labAddress) {
		this.labAddress = labAddress;
	}
	public String getTestCategory() {
		return testCategory;
	}
	public void setTestCategory(String testCategory) {
		this.testCategory = testCategory;
	}
	public Date getTest_date() {
		return test_date;
	}
	public void setTest_date(Date test_date) {
		this.test_date = test_date;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getPayment_mode() {
		return payment_mode;
	}
	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public Timestamp getOrder_created() {
		return order_created;
	}
	public void setOrder_created(Timestamp order_created) {
		this.order_created = order_created;
	}
	public Double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(Double total_amount) {
		this.total_amount = total_amount;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public LabOrderDetailsDto(Object[] row) {
	    this.orderItemId = (Long) row[0];
	    this.test_id = (Long) row[1];
	    this.test_name = (String) row[2];
	    this.labName = (String) row[3];
	    this.labId = (Long) row[4];
	    this.labAddress = (String) row[5];
	    this.testCategory = (String) row[6];
	    this.test_date = (Date) row[7];
	    this.price = (Double) row[8];
	    this.quantity = (Integer) row[9];
	    this.order_id = (Long) row[10];
	    this.order_status = (String) row[11];
	    this.payment_mode = (String) row[12];
	    this.payment_status = (String) row[13];
	    this.order_created = (Timestamp) row[14];
	    this.total_amount = (Double) row[15];
	    this.patientId = (Long) row[16];
	    this.transactionId = (Long) row[17];
	    this.patient_name = (String) row[18];
	}

}
