package com.onehealth.dto;

import java.sql.Date;


public class LabCartItem {

	
	private long cart_item_id;
	private int quantity;
	private double total_product_price;
	private long test_id;
	private Date test_date;
	private String test_name;
	private String labName;
	private String labAddress;
	private long labId;
	private String testCategory;
	
//	@JsonBackReference
//	@ManyToOne
//    private	LabCart labCart;
	
	
	public String getTest_name() {
		return test_name;
	}
	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}
	public long getCart_item_id() {
		return cart_item_id;
	}
	public void setCart_item_id(long cart_item_id) {
		this.cart_item_id = cart_item_id;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal_product_price() {
		return total_product_price;
	}
	public void setTotal_product_price(double total_product_price) {
		this.total_product_price = total_product_price;
	}
	public long getTest_id() {
		return test_id;
	}
	public void setTest_id(long test_id) {
		this.test_id = test_id;
	}
	public Date getTest_date() {
		return test_date;
	}
	public void setTest_date(Date test_date) {
		this.test_date = test_date;
	}
	
	
	
	
	public String getTestCategory() {
		return testCategory;
	}
	public void setTestCategory(String testCategory) {
		this.testCategory = testCategory;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getLabAddress() {
		return labAddress;
	}
	public void setLabAddress(String labAddress) {
		this.labAddress = labAddress;
	}
	public long getLabId() {
		return labId;
	}
	public void setLabId(long labId) {
		this.labId = labId;
	}
	public LabCartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "LabCartItem [cart_item_id=" + cart_item_id + ", quantity=" + quantity + ", total_product_price="
				+ total_product_price + ", test_id=" + test_id + ", test_date=" + test_date + ", test_name=" + test_name
				+ ", labName=" + labName + ", labAddress=" + labAddress + ", labId=" + labId + ", testCategory="
				+ testCategory + "]";
	}
	public LabCartItem(long cart_item_id, int quantity, double total_product_price, long test_id, Date test_date,
			String test_name, String labName, String labAddress, long labId, String testCategory) {
		super();
		this.cart_item_id = cart_item_id;
		this.quantity = quantity;
		this.total_product_price = total_product_price;
		this.test_id = test_id;
		this.test_date = test_date;
		this.test_name = test_name;
		this.labName = labName;
		this.labAddress = labAddress;
		this.labId = labId;
		this.testCategory = testCategory;
	}
	
	
	
	
	
	
}
