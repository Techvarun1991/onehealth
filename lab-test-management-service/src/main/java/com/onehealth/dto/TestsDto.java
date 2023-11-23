package com.onehealth.dto;

public class TestsDto {

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
	
	private String testCategory;

	

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

	public long getLabId() {
		return labId;
	}

	public void setLabId(long labId) {
		this.labId = labId;
	}

	public TestsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTestCategory() {
		return testCategory;
	}

	public void setTestCategory(String testCategory) {
		this.testCategory = testCategory;
	}

	@Override
	public String toString() {
		return "TestsDto [test_id=" + test_id + ", testName=" + testName + ", home_sample=" + home_sample
				+ ", test_description=" + test_description + ", price=" + price + ", test_approval=" + test_approval
				+ ", gov_appro_cert_path=" + gov_appro_cert_path + ", labId=" + labId + ", testCategory=" + testCategory
				+ "]";
	}

	public TestsDto(long test_id, String testName, String home_sample, String test_description, int price,
			boolean test_approval, String gov_appro_cert_path, long labId, String testCategory) {
		super();
		this.test_id = test_id;
		this.testName = testName;
		this.home_sample = home_sample;
		this.test_description = test_description;
		this.price = price;
		this.test_approval = test_approval;
		this.gov_appro_cert_path = gov_appro_cert_path;
		this.labId = labId;
		this.testCategory = testCategory;
	}

	
	
	
	
	

	
}
