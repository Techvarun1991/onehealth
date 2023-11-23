package com.onehealth.dto;

import java.sql.Date;

public class OrderUpdateDto {

	private long order_id;
	private long order_item_id;
	private String order_status;
	private String payment_mode;
	private String payment_status;
	private Date test_date;
	
	
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
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
	public long getOrder_item_id() {
		return order_item_id;
	}
	public void setOrder_item_id(long order_item_id) {
		this.order_item_id = order_item_id;
	}
	public Date getTest_date() {
		return test_date;
	}
	public void setTest_date(Date test_date) {
		this.test_date = test_date;
	}
	@Override
	public String toString() {
		return "OrderUpdateDto [order_id=" + order_id + ", order_item_id=" + order_item_id + ", order_status="
				+ order_status + ", payment_mode=" + payment_mode + ", payment_status=" + payment_status
				+ ", test_date=" + test_date + "]";
	}
	public OrderUpdateDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderUpdateDto(long order_id, long order_item_id, String order_status, String payment_mode,
			String payment_status, Date test_date) {
		super();
		this.order_id = order_id;
		this.order_item_id = order_item_id;
		this.order_status = order_status;
		this.payment_mode = payment_mode;
		this.payment_status = payment_status;
		this.test_date = test_date;
	}
	
	
}
