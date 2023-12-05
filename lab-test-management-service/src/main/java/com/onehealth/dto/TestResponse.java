package com.onehealth.dto;

public class TestResponse{
	private long test_id;
	private String testName;
	private String home_sample;
	private String test_description;
	private int price;
	private boolean test_approval;
	private String gov_appro_cert_path;
	private String labarea;
	private int pincode;
	private long labId;
	private String labName;
	private String labCity;
	private String labAddress;
	private boolean isLabActive;
	private String testCategory;
	public TestResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TestResponse [gov_appro_cert_path=" + gov_appro_cert_path + ", home_sample=" + home_sample
				+ ", isLabActive=" + isLabActive + ", labAddress=" + labAddress + ", labarea=" + labarea + ", labCity="
				+ labCity + ", labId=" + labId + ", labName=" + labName + ", pincode=" + pincode + ", price=" + price
				+ ", test_approval=" + test_approval + ", test_description=" + test_description + ", test_id=" + test_id
				+ ", testCategory=" + testCategory + ", testName=" + testName + "]";
	}
	public TestResponse(long test_id, String testName, String home_sample, String test_description, int price,
			boolean test_approval, String gov_appro_cert_path, String labarea, int pincode, long labId, String labName,
			String labCity, String labAddress, boolean isLabActive, String testCategory) {
		super();
		this.test_id = test_id;
		this.testName = testName;
		this.home_sample = home_sample;
		this.test_description = test_description;
		this.price = price;
		this.test_approval = test_approval;
		this.gov_appro_cert_path = gov_appro_cert_path;
		this.labarea = labarea;
		this.pincode = pincode;
		this.labId = labId;
		this.labName = labName;
		this.labCity = labCity;
		this.labAddress = labAddress;
		this.isLabActive = isLabActive;
		this.testCategory = testCategory;
	}
	public long getTest_id() {
		return test_id;
	}
	public void setTest_id(long test_id) {
		this.test_id = test_id;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getHome_sample() {
		return home_sample;
	}
	public void setHome_sample(String home_sample) {
		this.home_sample = home_sample;
	}
	public String getTest_description() {
		return test_description;
	}
	public void setTest_description(String test_description) {
		this.test_description = test_description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isTest_approval() {
		return test_approval;
	}
	public void setTest_approval(boolean test_approval) {
		this.test_approval = test_approval;
	}
	public String getGov_appro_cert_path() {
		return gov_appro_cert_path;
	}
	public void setGov_appro_cert_path(String gov_appro_cert_path) {
		this.gov_appro_cert_path = gov_appro_cert_path;
	}
	public String getLabarea() {
		return labarea;
	}
	public void setLabarea(String labarea) {
		this.labarea = labarea;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public long getLabId() {
		return labId;
	}
	public void setLabId(long labId) {
		this.labId = labId;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getLabCity() {
		return labCity;
	}
	public void setLabCity(String labCity) {
		this.labCity = labCity;
	}
	public String getLabAddress() {
		return labAddress;
	}
	public void setLabAddress(String labAddress) {
		this.labAddress = labAddress;
	}
	public boolean isLabActive() {
		return isLabActive;
	}
	public void setLabActive(boolean isLabActive) {
		this.isLabActive = isLabActive;
	}
	public String getTestCategory() {
		return testCategory;
	}
	public void setTestCategory(String testCategory) {
		this.testCategory = testCategory;
	}
	
	
	
	
	
}
