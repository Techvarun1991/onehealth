package com.onehealth.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.onehealth.entity.LabCartItem;

public class LabCartDTO {
	private long cart_id;
	private Set<LabCartItem> lab_cart_items = new HashSet<>();
	private long patientId;
	public long getCart_id() {
		return cart_id;
	}
	public void setCart_id(long cart_id) {
		this.cart_id = cart_id;
	}
	public Set<LabCartItem> getLab_cart_items() {
		return lab_cart_items;
	}
	public void setLab_cart_items(Set<LabCartItem> lab_cart_items) {
		this.lab_cart_items = lab_cart_items;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	@Override
	public String toString() {
		return "LabCartDTO [cart_id=" + cart_id + ", lab_cart_items=" + lab_cart_items + ", patientId=" + patientId
				+ "]";
	}
	public LabCartDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LabCartDTO(long cart_id, Set<LabCartItem> lab_cart_items, long patientId) {
		super();
		this.cart_id = cart_id;
		this.lab_cart_items = lab_cart_items;
		this.patientId = patientId;
	}
	
	
	
}
