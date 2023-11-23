package com.onehealth.dto;
/**
 * The TestsUpdateRequest class represents the data transfer object (DTO) for updating test details.
 * It contains the attributes required to update a test in the system.
 */
public class TestsUpdateRequest {
    private long test_id;                 // ID of the test
    private String testName;            // Name of the test
    private String home_sample;          // Flag indicating whether home sample collection is available for the test
    private String test_description;     // Description of the test
    private int price;                   // Price of the test
    private boolean test_approval;       // Flag indicating whether the test is approved or not
    private String gov_appro_cert_path;  // Path to the government approval certificate for the test
    private long labId;                  // ID of the lab where the test is conducted
    private String testCategory;
	
	/**
	 * Getter for the lab ID.
	 *
	 * @return The lab ID.
	 */
	public long getLabId() {
		return labId;
	}
	
	/**
	 * Setter for the lab ID.
	 *
	 * @param labId The lab ID to set.
	 */
	public void setLabId(long labId) {
		this.labId = labId;
	}
	
	/**
	 * Getter for the test ID.
	 *
	 * @return The test ID.
	 */
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
	 * @param test_name The test name to set.
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
	
	
	
	
	public String getTestCategory() {
		return testCategory;
	}

	public void setTestCategory(String testCategory) {
		this.testCategory = testCategory;
	}

	/**
	 * Override of the toString() method to display the entity's properties.
	 *
	 * @return A string representation of the entity.
	 */
	@Override
	public String toString() {
		return "TestsUpdateRequest [test_id=" + test_id + ", test_name=" + testName + ", home_sample=" + home_sample
				+ ", test_description=" + test_description + ", price=" + price + ", test_approval=" + test_approval
				+ ", gov_appro_cert_path=" + gov_appro_cert_path + ", labId=" + labId + ", testCategory=" +testCategory +"]";
	}
	
	/**
	 * Default constructor for the TestsUpdateRequest class.
	 */
	public TestsUpdateRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Parameterized constructor for the TestsUpdateRequest class.
	 *
	 * @param test_id            The test ID.
	 * @param test_name          The test name.
	 * @param home_sample        The home sample availability status.
	 * @param test_description   The test description.
	 * @param price              The test price.
	 * @param test_approval      The approval status of the test.
	 * @param gov_appro_cert_path The path to the government approval certificate.
	 * @param labId              The lab ID where the test is conducted.
	 */
	public TestsUpdateRequest(long test_id, String testName, String home_sample, String test_description, int price,
			boolean test_approval, String gov_appro_cert_path, long labId , String testCategory) {
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
		
