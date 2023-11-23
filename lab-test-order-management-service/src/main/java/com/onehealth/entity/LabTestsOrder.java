package com.onehealth.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class LabTestsOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long order_id;
//	private String order_status;
//	private String payment_mode;
//	private String payment_status;
	private Date order_created;
	private Double total_amount;
	private long patientId;
	private long transactionId;
	private String patient_name;
	@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy="labTestsOrder",cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private	Set<LabOrderItem> item = new HashSet<>();
	
	
	public long getOrder_id() {
		return order_id;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
//	public String getOrder_status() {
//		return order_status;
//	}
//	public void setOrder_status(String order_status) {
//		this.order_status = order_status;
//	}
//	public String getPayment_mode() {
//		return payment_mode;
//	}
//	public void setPayment_mode(String payment_mode) {
//		this.payment_mode = payment_mode;
//	}
//	public String getPayment_status() {
//		return payment_status;
//	}
//	public void setPayment_status(String payment_status) {
//		this.payment_status = payment_status;
//	}
	public Date getOrder_created() {
		return order_created;
	}
	public void setOrder_created(Date order_created) {
		this.order_created = order_created;
	}
	public Double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(Double total_amount) {
		this.total_amount = total_amount;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public Set<LabOrderItem> getItem() {
		return item;
	}
	public void setItem(Set<LabOrderItem> item) {
		this.item = item;
	}
	@Override
	public String toString() {
		return "LabTestsOrder [order_id=" + order_id + ", order_created=" + order_created + ", total_amount="
				+ total_amount + ", patientId=" + patientId + ", transactionId=" + transactionId + ", patient_name="
				+ patient_name + ", item=" + item + "]";
	}
	public LabTestsOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LabTestsOrder(long order_id, Date order_created, Double total_amount, long patientId, long transactionId,
			String patient_name, Set<LabOrderItem> item) {
		super();
		this.order_id = order_id;
		this.order_created = order_created;
		this.total_amount = total_amount;
		this.patientId = patientId;
		this.transactionId = transactionId;
		this.patient_name = patient_name;
		this.item = item;
	}
		
}
