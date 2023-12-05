package com.onehealth.dto;

public class LabOrderItemRequest {

	private long labId;
	private long orderId;
	private long orderItemId;
	@Override
	public String toString() {
		return "LabOrderItemRequest [labId=" + labId + ", orderId=" + orderId + ", orderItemId=" + orderItemId + "]";
	}
	public LabOrderItemRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LabOrderItemRequest(long labId, long orderId, long orderItemId) {
		super();
		this.labId = labId;
		this.orderId = orderId;
		this.orderItemId = orderItemId;
	}
	public long getLabId() {
		return labId;
	}
	public void setLabId(long labId) {
		this.labId = labId;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}
	
	
	
	
}
