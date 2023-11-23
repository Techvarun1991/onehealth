package com.onehealth.entity;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class LabCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cart_id;
	@JsonManagedReference
	@OneToMany(mappedBy="labCart",cascade = CascadeType.ALL ,orphanRemoval = true , fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<LabCartItem> lab_cart_items = new HashSet<>();
	private long patientId;
	
	
	public long getCart_id() {
		return cart_id;
	}
	public void setCart_id(long cart_id) {
		this.cart_id = cart_id;
	}
	public Set<LabCartItem> getCart_items() {
		return lab_cart_items;
	}
	public void setCart_items(Set<LabCartItem> lab_cart_items) {
		this.lab_cart_items = lab_cart_items;
	}
	public long getPatient_id() {
		return patientId;
	}
	public void setPatient_id(long patient_id) {
		this.patientId = patient_id;
	}
	@Override
	public String toString() {
		return "LabCart [cart_id=" + cart_id + ", lab_cart_items=" + lab_cart_items + ", patient_id=" + patientId + "]";
	}
	public LabCart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LabCart(long cart_id, Set<LabCartItem> lab_cart_items, long patient_id) {
		super();
		this.cart_id = cart_id;
		this.lab_cart_items = lab_cart_items;
		this.patientId = patient_id;
	}
}

