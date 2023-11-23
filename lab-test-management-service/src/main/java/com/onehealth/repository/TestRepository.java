package com.onehealth.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onehealth.entity.Tests;

import jakarta.transaction.Transactional;

/**
 * The TestRepository interface provides CRUD operations for the Tests entity.
 * It extends the JpaRepository interface, which provides standard JPA methods for data access.
 */
public interface TestRepository extends JpaRepository<Tests, Long> {
	
	/**
	 * Custom query to find tests by lab ID.
	 *
	 * @param lab_id The ID of the lab for which tests need to be retrieved.
	 * @return A list of Tests matching the provided lab ID.
	 */
	List<Tests> findByLabId(long lab_id);
	
	/**
     * Retrieves a list of tests by their category.
     *
     * @param testCategory The category of tests to retrieve.
     * @return A list of tests in the specified category.
     */
    List<Tests> findByTestCategory(String testCategory);

    /**
     * Retrieves a list of tests by laboratory city and test name, filtering by active labs and approved tests.
     *
     * @param testName The name of the test.
     * @param city     The city where the laboratory is located.
     * @return A list of tests that match the specified test name and city and meet the active and approval criteria.
     */
    @Query("SELECT t FROM Tests t WHERE t.labCity = :city AND t.testName = :testName AND t.isLabActive = true AND t.test_approval = true")
    List<Tests> findByLabCityAndTestName(@Param("testName") String testName, @Param("city") String city);

    /**
     * Retrieves a list of tests by laboratory city, filtering by active labs and approved tests.
     *
     * @param labCity The city where the laboratory is located.
     * @return A list of tests located in the specified city that meet the active and approval criteria.
     */
    @Query("SELECT t FROM Tests t WHERE t.labCity = :labCity AND t.isLabActive = true AND t.test_approval = true")
    List<Tests> findByLabCity(@Param("labCity") String labCity);

    /**
     * Deletes all tests associated with a lab by lab ID.
     *
     * @param labId The ID of the lab from which to delete tests.
     */
    @Transactional
    void deleteByLabId(long labId);

	

}