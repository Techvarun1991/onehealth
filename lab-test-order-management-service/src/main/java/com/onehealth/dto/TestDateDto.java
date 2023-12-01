package com.onehealth.dto;

import java.sql.Date;

public class TestDateDto {

	private long order_id;
	private long order_item_id;
	private Date test_date;
	public TestDateDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TestDateDto(long order_id, long order_item_id, Date test_date) {
		super();
		this.order_id = order_id;
		this.order_item_id = order_item_id;
		this.test_date = test_date;
	}
	@Override
	public String toString() {
		return "TestDateDto [order_id=" + order_id + ", order_item_id=" + order_item_id + ", test_date=" + test_date
				+ "]";
	}
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
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
	
	
	
}
