package com.onehealth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The TestDto entity class represents the structure of a test in the system.
 * It is annotated with @Entity to indicate that it's a JPA entity mapped to a database table.
 */
@Entity
public class Tests {

	/**
	 * Primary key for the test entity.
	 *
	 * @return The test ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long test_id;

	/**
	 * Getter for the test name.
	 *
	 * @return The test name.
	 */
	private String testName;

	/**
	 * Getter for the availability of home sample collection for the test.
	 *
	 * @return The home sample availability status.
	 */
	private String home_sample;

	/**
	 * Getter for the description of the test.
	 *
	 * @return The test description.
	 */
	private String test_description;

	/**
	 * Getter for the price of the test.
	 *
	 * @return The test price.
	 */
	private int price;

	/**
	 * Getter for the approval status of the test.
	 *
	 * @return The approval status of the test.
	 */
	private boolean test_approval;

	/**
	 * Getter for the path to the government approval certificate for the test.
	 *
	 * @return The path to the government approval certificate.
	 */
	private String gov_appro_cert_path;

	/**
	 * Getter for the ID of the lab where the test is conducted.
	 *
	 * @return The lab ID.
	 */
	private long labId;

	/**
	 * Getter for the test ID.
	 *
	 * @return The test ID.
	 */
	
	private String labName;
	private String labCity;
	private String labAddress;
	private boolean isLabActive;
	private String labArea;
	private int pincode;
	private String testCategory;
	
	
	
	
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

	public String getTestCategory() {
		return testCategory;
	}

	public void setTestCategory(String testCategory) {
		this.testCategory = testCategory;
	}

	public long getTest_id() {
		return test_id;
	}

	/**
	 * Setter for the test ID.
	 *
	 * @param test_id The test ID to set.
	 */
	public void setTest_id(long test_id) {
		this.test_id = test_id;
	}

	/**
	 * Getter for the test name.
	 *
	 * @return The test name.
	 */
	public String getTestName() {
		return testName;
	}

	/**
	 * Setter for the test name.
	 *
	 * @param testName The test name to set.
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}

	/**
	 * Getter for the availability of home sample collection for the test.
	 *
	 * @return The home sample availability status.
	 */
	public String getHome_sample() {
		return home_sample;
	}

	/**
	 * Setter for the availability of home sample collection for the test.
	 *
	 * @param home_sample The home sample availability status to set.
	 */
	public void setHome_sample(String home_sample) {
		this.home_sample = home_sample;
	}

	/**
	 * Getter for the description of the test.
	 *
	 * @return The test description.
	 */
	public String getTest_description() {
		return test_description;
	}

	/**
	 * Setter for the description of the test.
	 *
	 * @param test_description The test description to set.
	 */
	public void setTest_description(String test_description) {
		this.test_description = test_description;
	}

	/**
	 * Getter for the price of the test.
	 *
	 * @return The test price.
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Setter for the price of the test.
	 *
	 * @param price The test price to set.
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * Getter for the approval status of the test.
	 *
	 * @return The approval status of the test.
	 */
	public boolean isTest_approval() {
		return test_approval;
	}

	/**
	 * Setter for the approval status of the test.
	 *
	 * @param test_approval The approval status of the test to set.
	 */
	public void setTest_approval(boolean test_approval) {
		this.test_approval = test_approval;
	}

	/**
	 * Getter for the path to the government approval certificate for the test.
	 *
	 * @return The path to the government approval certificate.
	 */
	public String getGov_appro_cert_path() {
		return gov_appro_cert_path;
	}

	/**
	 * Setter for the path to the government approval certificate for the test.
	 *
	 * @param gov_appro_cert_path The path to the government approval certificate to set.
	 */
	public void setGov_appro_cert_path(String gov_appro_cert_path) {
		this.gov_appro_cert_path = gov_appro_cert_path;
	}

	/**
	 * Getter for the ID of the lab where the test is conducted.
	 *
	 * @return The lab ID.
	 */
	public long getLabId() {
		return labId;
	}

	/**
	 * Setter for the ID of the lab where the test is conducted.
	 *
	 * @param labId The lab ID to set.
	 */
	public void setLabId(long labId) {
		this.labId = labId;
	}
	
	public boolean isLabActive() {
		return isLabActive;
	}

	public void setLabActive(boolean isLabActive) {
		this.isLabActive = isLabActive;
	}
	
	public String getLabArea() {
		return labArea;
	}

	public void setLabArea(String labArea) {
		this.labArea = labArea;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	/**
	 * Override of the toString() method to display the entity's properties.
	 *
	 * @return A string representation of the entity.
	 */
	
	@Override
	public String toString() {
		return "Tests [test_id=" + test_id + ", testName=" + testName + ", home_sample=" + home_sample
				+ ", test_description=" + test_description + ", price=" + price + ", test_approval=" + test_approval
				+ ", gov_appro_cert_path=" + gov_appro_cert_path + ", labId=" + labId + ", labName=" + labName
				+ ", labCity=" + labCity + ", labAddress=" + labAddress + ", isLabActive=" + isLabActive + ", labArea="
				+ labArea + ", pincode=" + pincode + ", testCategory=" + testCategory + "]";
	}


	
	


	/**

	 * Default constructor for the TestDto class.
	 */
	public Tests() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	/**
	 * Parameterized constructor for the TestDto class.
	 *
	 * @param test_id            The test ID.
	 * @param testName          The test name.
	 * @param home_sample        The home sample availability status.
	 * @param test_description   The test description.
	 * @param price              The test price.
	 * @param test_approval      The approval status of the test.
	 * @param gov_appro_cert_path The path to the government approval certificate.
	 * @param labId              The lab ID where the test is conducted.
	 */
	public Tests(long test_id, String testName, String home_sample, String test_description, int price,
			boolean test_approval, String gov_appro_cert_path, long labId, String labName, String labCity,
			String labAddress, boolean isLabActive, String labArea, int pincode, String testCategory) {
		super();
		this.test_id = test_id;
		this.testName = testName;
		this.home_sample = home_sample;
		this.test_description = test_description;
		this.price = price;
		this.test_approval = test_approval;
		this.gov_appro_cert_path = gov_appro_cert_path;
		this.labId = labId;
		this.labName = labName;
		this.labCity = labCity;
		this.labAddress = labAddress;
		this.isLabActive = isLabActive;
		this.labArea = labArea;
		this.pincode = pincode;
		this.testCategory = testCategory;
	}

	
	
	

}
