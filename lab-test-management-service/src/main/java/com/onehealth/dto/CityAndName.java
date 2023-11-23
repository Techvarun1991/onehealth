package com.onehealth.dto;

public class CityAndName {

	private String city;
	private String testName;
	@Override
	public String toString() {
		return "CityAndName [city=" + city + ", testName=" + testName + "]";
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public CityAndName() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CityAndName(String city, String testName) {
		super();
		this.city = city;
		this.testName = testName;
	}
	
	
	
	
}
