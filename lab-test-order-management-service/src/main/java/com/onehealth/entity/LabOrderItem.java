package com.onehealth.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LabOrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderItemId;
	private long test_id;
	private String test_name;
	private String lab_name;
	private long labId;
	private String labAddress;
	private String testCategory;
	private Date test_date;
	private double price;
	private int quantity;
	private String order_status;  // Item-level status
    private String payment_mode;  // Item-level payment mode
    private String payment_status; // Item-level payment status
	
	@JsonBackReference
	@ManyToOne
    private	LabTestsOrder labTestsOrder;
	public int getOrderItemId() {
		return orderItemId;
	}
	
	
	public String getTestCategory() {
		return testCategory;
	}


	public void setTestCategory(String testCategory) {
		this.testCategory = testCategory;
	}


	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public long getTest_id() {
		return test_id;
	}
	public void setTest_id(long test_id) {
		this.test_id = test_id;
	}
	public String getTest_name() {
		return test_name;
	}
	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}
	public Date getTest_date() {
		return test_date;
	}
	public void setTest_date(Date test_date) {
		this.test_date = test_date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LabTestsOrder getLabTestsOrder() {
		return labTestsOrder;
	}
	public void setLabTestsOrder(LabTestsOrder labTestsOrder) {
		this.labTestsOrder = labTestsOrder;
	}
	public String getLab_name() {
		return lab_name;
	}
	public void setLab_name(String lab_name) {
		this.lab_name = lab_name;
	}
	public long getLabId() {
		return labId;
	}
	public void setLabId(long labId) {
		this.labId = labId;
	}
	public String getLabAddress() {
		return labAddress;
	}
	public void setLabAddress(String labAddress) {
		this.labAddress = labAddress;
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


	public LabOrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "LabOrderItem [orderItemId=" + orderItemId + ", test_id=" + test_id + ", test_name=" + test_name
				+ ", lab_name=" + lab_name + ", labId=" + labId + ", labAddress=" + labAddress + ", testCategory="
				+ testCategory + ", test_date=" + test_date + ", price=" + price + ", quantity=" + quantity
				+ ", order_status=" + order_status + ", payment_mode=" + payment_mode + ", payment_status="
				+ payment_status + ", labTestsOrder=" + labTestsOrder + "]";
	}


	public LabOrderItem(int orderItemId, long test_id, String test_name, String lab_name, long labId, String labAddress,
			String testCategory, Date test_date, double price, int quantity, String order_status, String payment_mode,
			String payment_status, LabTestsOrder labTestsOrder) {
		super();
		this.orderItemId = orderItemId;
		this.test_id = test_id;
		this.test_name = test_name;
		this.lab_name = lab_name;
		this.labId = labId;
		this.labAddress = labAddress;
		this.testCategory = testCategory;
		this.test_date = test_date;
		this.price = price;
		this.quantity = quantity;
		this.order_status = order_status;
		this.payment_mode = payment_mode;
		this.payment_status = payment_status;
		this.labTestsOrder = labTestsOrder;
	}
	
}
