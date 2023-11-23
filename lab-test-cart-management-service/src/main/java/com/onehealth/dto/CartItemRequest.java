package com.onehealth.dto;

import java.sql.Date;

public class CartItemRequest {

	private long test_id;
	private long patient_id;
	private int quantity;
	private Date test_date;
	
	
	
	public Date getTest_date() {
		return test_date;
	}
	public void setTest_date(Date test_date) {
		this.test_date = test_date;
	}
	public long getTest_id() {
		return test_id;
	}
	public void setTest_id(long test_id) {
		this.test_id = test_id;
	}
	public long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(long patient_id) {
		this.patient_id = patient_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public CartItemRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CartItemRequest [test_id=" + test_id + ", patient_id=" + patient_id + ", quantity=" + quantity
				+ ", test_date=" + test_date + "]";
	}
	public CartItemRequest(long test_id, long patient_id, int quantity, Date test_date) {
		super();
		this.test_id = test_id;
		this.patient_id = patient_id;
		this.quantity = quantity;
		this.test_date = test_date;
	}
}
