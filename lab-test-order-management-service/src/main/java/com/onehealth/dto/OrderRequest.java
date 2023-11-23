package com.onehealth.dto;

public class OrderRequest {

	private long cart_id;
//	private long lab_id;
	private long patient_id;
	
	
	
	public long getCart_id() {
		return cart_id;
	}
	public void setCart_id(long cart_id) {
		this.cart_id = cart_id;
	}
//	public long getLab_id() {
//		return lab_id;
//	}
//	public void setLab_id(long lab_id) {
//		this.lab_id = lab_id;
//	}
	public long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(long patient_id) {
		this.patient_id = patient_id;
	}
	@Override
	public String toString() {
		return "OrderRequest [cart_id=" + cart_id + ", patient_id=" + patient_id + "]";
	}
	public OrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderRequest(long cart_id, long patient_id) {
		super();
		this.cart_id = cart_id;
//		this.lab_id = lab_id;
		this.patient_id = patient_id;
	}

	


	
	
	
}
