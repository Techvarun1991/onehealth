 package com.onehealth.service;

import java.util.List;

import com.onehealth.dto.CityAndName;
import com.onehealth.dto.TestsDto;
import com.onehealth.dto.TestsUpdateRequest;
import com.onehealth.entity.Tests;
import com.onehealth.exception.DatabaseException;
import com.onehealth.exception.LabNotFoundException;
import com.onehealth.exception.TestNotFoundException;

/**
 * The TestsService interface provides methods to interact with the Tests entity.
 * It defines the service layer operations for managing tests.
 */
public interface TestsService {
	
	/**
	 * Retrieve a list of all tests.
	 *
	 * @return A list of all available tests.
	 * @throws DatabaseException If there's an issue accessing the database.
	 */
	List<Tests> getAllTests() throws DatabaseException;

	/**
	 * Retrieve a test by its ID.
	 *
	 * @param test_id The ID of the test to retrieve.
	 * @return The test with the provided ID.
	 * @throws TestNotFoundException If the test with the given ID is not found.
	 * @throws DatabaseException     If there's an issue accessing the database.
	 */
	Tests getTestById(long test_id) throws TestNotFoundException, DatabaseException;

	/**
	 * Delete a test by its ID.
	 *
	 * @param test_id The ID of the test to delete.
	 * @throws TestNotFoundException If the test with the given ID is not found.
	 * @throws DatabaseException     If there's an issue accessing the database.
	 */
	void deleteTest(long test_id) throws DatabaseException, TestNotFoundException;

	/**
	 * Update the details of a test.
	 *
	 * @param test The test with updated details to be saved.
	 * @throws TestNotFoundException If the test with the given ID is not found.
	 * @throws DatabaseException     If there's an issue accessing the database.
	 * @throws LabNotFoundException 
	 */
	void updateTestDetails(TestsUpdateRequest test) throws DatabaseException, TestNotFoundException, LabNotFoundException;

	/**
	 * Retrieve a list of tests based on the lab ID.
	 *
	 * @param lab_id The ID of the lab for which tests need to be retrieved.
	 * @return A list of tests associated with the provided lab ID.
	 * @throws DatabaseException If there's an issue accessing the database.
	 */
	List<Tests> getAllTestByLabId(long lab_id) throws DatabaseException;

	/**
	 * Add a new test to the database.
	 *
	 * @param test The new test to be added.
	 * @throws DatabaseException If there's an issue accessing the database.
	 * @throws LabNotFoundException 
	 */
	void addNewTest(TestsDto test) throws DatabaseException, LabNotFoundException;
	
	
	

    /**
     * Retrieves all tests in a specified category.
     *
     * @param testCategory The category of tests to retrieve.
     * @return A list of tests in the specified category.
     */
    List<Tests> getAllTestsByCatagory(String testCategory);

    /**
     * Retrieves all tests in a specific laboratory city with a given test name.
     *
     * @param dto An object containing the test name and laboratory city.
     * @return A list of tests that match the specified test name and laboratory city.
     */
    List<Tests> getAllTestsByLabCityAndTestName(CityAndName dto);

    /**
     * Retrieves all tests in a specified city.
     *
     * @param city The city for which to retrieve tests.
     * @return A list of tests located in the specified city.
     */
    List<Tests> getAllTestsByCity(String city);

    /**
     * Sets the active status for a laboratory.
     *
     * @param labId  The ID of the laboratory to update.
     * @param status The status to set (true for active, false for inactive).
     * @throws DatabaseException If there is an issue with the database while updating.
     */
    void setLabActiveStatus(long labId, boolean status) throws DatabaseException;

    /**
     * Deletes all tests associated with a laboratory by lab ID.
     *
     * @param labId The ID of the lab from which to delete tests.
     */
    void deleteAllTestsFromLabId(long labId);

    /**
     * Sets the approval status for a test.
     *
     * @param test_id The ID of the test to update.
     * @param status  The approval status to set (true for approved, false for unapproved).
     * @throws TestNotFoundException If the test with the provided test_id is not found.
     */
    void setTestApprovalStatus(long test_id, boolean status) throws TestNotFoundException;

}

