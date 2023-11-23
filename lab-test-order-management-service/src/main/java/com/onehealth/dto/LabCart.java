package com.onehealth.dto;

import java.util.Set;

public class LabCart {

	private long cart_id;
	private Set<LabCartItem> cart_items;
	private long patient_id;
	private long lab_id;
	public long getCart_id() {
		return cart_id;
	}
	public void setCart_id(long cart_id) {
		this.cart_id = cart_id;
	}
	public Set<LabCartItem> getCart_items() {
		return cart_items;
	}
	public void setCart_items(Set<LabCartItem> cart_items) {
		this.cart_items = cart_items;
	}
	public long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(long patient_id) {
		this.patient_id = patient_id;
	}
	public long getLab_id() {
		return lab_id;
	}
	public void setLab_id(long lab_id) {
		this.lab_id = lab_id;
	}
	@Override
	public String toString() {
		return "CartDto [cart_id=" + cart_id + ", cart_items=" + cart_items + ", patient_id=" + patient_id + ", lab_id="
				+ lab_id + "]";
	}
	public LabCart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LabCart(long cart_id, Set<LabCartItem> cart_items, long patient_id, long lab_id) {
		super();
		this.cart_id = cart_id;
		this.cart_items = cart_items;
		this.patient_id = patient_id;
		this.lab_id = lab_id;
	}
}

