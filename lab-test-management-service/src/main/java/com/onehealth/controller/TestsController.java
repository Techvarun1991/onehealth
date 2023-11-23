package com.onehealth.controller;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onehealth.dto.CityAndName;
import com.onehealth.dto.TestsDto;
import com.onehealth.dto.TestsUpdateRequest;
import com.onehealth.entity.Tests;
import com.onehealth.exception.DatabaseException;
import com.onehealth.exception.LabNotFoundException;
import com.onehealth.exception.TestNotFoundException;
import com.onehealth.service.TestsService;




/**
 * The TestsController class handles HTTP requests related to tests.
 * It defines various endpoints for CRUD operations and interacts with the TestsService.
 */

@RestController
@RequestMapping("/api/test")
public class TestsController {
	
	 private static final Logger logger = LoggerFactory.getLogger(TestsController.class);

	    @Autowired
	    private TestsService testsService;
	    
	    /**
	     * Endpoint to fetch all tests.
	     *
	     * @return ResponseEntity containing a list of all available tests and HTTP status.
	     * @throws DatabaseException If there's an issue accessing the database.
	     */
	    @GetMapping("/allTest")
	    public ResponseEntity<List<Tests>> getAllTest() throws DatabaseException {
	        logger.info("Received request to fetch all tests");
	        List<Tests> tests = testsService.getAllTests();
	        logger.info("Returning {} tests", tests.size());
	        return new ResponseEntity<>(tests, HttpStatus.OK);
	    }

	    /**
	     * Endpoint to fetch a single test by its ID.
	     *
	     * @param test_id The ID of the test to retrieve.
	     * @return ResponseEntity containing the test with the provided ID and HTTP status.
	     * @throws TestNotFoundException If the test with the given ID is not found.
	     * @throws DatabaseException If there's an issue accessing the database.
	     */
	    @GetMapping("/singleTest")
	    public ResponseEntity<?> getTestById(@RequestParam("test_id") long test_id) throws TestNotFoundException, DatabaseException {
	    	try {
	        logger.info("Received request to fetch test with test_id: {}", test_id);
	        Tests test = testsService.getTestById(test_id);
	        logger.info("Returning test with test_id: {}", test_id);
	        
	        return new ResponseEntity<>(test, HttpStatus.OK);
	    	}
	    	catch (TestNotFoundException e) {
	    		logger.warn(e.getMessage());
				// TODO: handle exception
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
	    }

	    /**
	     * Endpoint to update the details of a test.
	     *
	     * @param request The request containing the updated test details.
	     * @return ResponseEntity with a success message and HTTP status.
	     * @throws DatabaseException If there's an issue accessing the database.
	     * @throws TestNotFoundException If the test with the given ID is not found.
	     * @throws LabNotFoundException 
	     */
	    @PutMapping("/updateTest")
	    public ResponseEntity<String> updateTestDetails(@RequestBody TestsUpdateRequest request) throws DatabaseException, TestNotFoundException, LabNotFoundException {
	    	try {
	        logger.info("Received request to update test with test_id: {}", request.getTest_id());
	        testsService.updateTestDetails(request);
	        logger.info("Test with test_id {} updated successfully.", request.getTest_id());
	        return new ResponseEntity<>("Test having test_id : " + request.getTest_id() + " Updated Successfully !!", HttpStatus.OK);
	    	}catch(TestNotFoundException | LabNotFoundException e) {
	    		logger.warn(e.getMessage());
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    	}
	    }

	    /**
	     * Endpoint to delete a test by its ID.
	     *
	     * @param test_id The ID of the test to delete.
	     * @return ResponseEntity with a success message and HTTP status.
	     * @throws DatabaseException If there's an issue accessing the database.
	     * @throws TestNotFoundException If the test with the given ID is not found.
	     */
	    @DeleteMapping("/deleteTest")
	    public ResponseEntity<String> deleteTest(@RequestParam("test_id") long test_id) throws DatabaseException, TestNotFoundException {
	    	try {
	        logger.info("Received request to delete test with test_id: {}", test_id);
	        testsService.deleteTest(test_id);
	        logger.info("Test with test_id {} deleted successfully.", test_id);
	        return new ResponseEntity<>("Test having test_id : " + test_id + " Deleted Successfully !!", HttpStatus.OK);
	    	}catch(TestNotFoundException e) {
	    		logger.warn(e.getMessage());
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());	
	    	}
	    }

	    /**
	     * Endpoint to fetch all tests based on the lab ID.
	     *
	     * @param lab_id The ID of the lab for which tests need to be retrieved.
	     * @return ResponseEntity containing a list of tests associated with the provided lab ID and HTTP status.
	     * @throws DatabaseException If there's an issue accessing the database.
	     */
	    @GetMapping("/inLab")
	    public ResponseEntity<?> getAllTestByLabId(@RequestParam("lab_id") long lab_id) throws DatabaseException {
	        logger.info("Received request to fetch tests for lab with lab_id: {}", lab_id);
	        List<Tests> tests = testsService.getAllTestByLabId(lab_id);
	        logger.info("Returning {} tests for lab with lab_id: {}", tests.size(), lab_id);
	        return new ResponseEntity<>(tests, HttpStatus.OK);
	    }

	    /**
	     * Endpoint to add a new test to the database.
	     *
	     * @param test The new test to be added.
	     * @return ResponseEntity with a success message and HTTP status.
	     * @throws DatabaseException If there's an issue accessing the database.
	     */
	    @PostMapping("/addNewTest")
	    public ResponseEntity<String> addNewTest(@RequestBody TestsDto test) throws DatabaseException {
	        logger.info("Received request to add a new test");
	        try {
				testsService.addNewTest(test);
				logger.info("New test added successfully.");
		        return new ResponseEntity<>("Test Added Successfully !!", HttpStatus.CREATED);
			} catch (LabNotFoundException e) {
				// TODO Auto-generated catch block
				logger.warn(e.getMessage());
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
	        
	    }
	    
	    
	    
	    
	    
	    /**
	     * Retrieves a list of tests by their category.
	     *
	     * @param testCategory The category of tests to retrieve.
	     * @return A list of tests in the specified category.
	     */
	    @GetMapping("/tests/category/{testCategory}")
	    public List<Tests> getAllTestsByCategory(@PathVariable String testCategory) {
	        logger.info("Received request to fetch all tests using testCategory: {}", testCategory);
	        return testsService.getAllTestsByCatagory(testCategory);
	    }

	    /**
	     * Retrieves a list of tests by laboratory city and test name.
	     *
	     * @param dto An object containing the test name and city for filtering tests.
	     * @return A list of tests that match the specified test name and city.
	     */
	    @PostMapping("/tests/city-and-name")
	    public List<Tests> getAllTestsByLabCityAndTestName(@RequestBody CityAndName dto) {
	        logger.info("Received request to fetch all tests using city: {} and testName: {}", dto.getCity(), dto.getTestName());
	        return testsService.getAllTestsByLabCityAndTestName(dto);
	    }

	    /**
	     * Retrieves a list of tests by laboratory city.
	     *
	     * @param city The city to filter tests by.
	     * @return A list of tests located in the specified city.
	     */
	    @GetMapping("/test/city/{city}")
	    public List<Tests> getAllTestsByCity(@PathVariable String city) {
	        logger.info("Received request to fetch all tests using city: {}", city);
	        return testsService.getAllTestsByCity(city);
	    }

	    /**
	     * Updates the active status of a lab.
	     *
	     * @param labId  The ID of the lab.
	     * @param status The new active status to set.
	     * @return A ResponseEntity with a success message if the update was successful.
	     * @throws DatabaseException If there is an issue with the database while updating.
	     */
	    @PostMapping("/updateLabActiveStatus/{labId}/{status}")
	    public ResponseEntity<String> updateLabActiveStatus(@PathVariable(value = "labId") long labId, @PathVariable(value = "status") boolean status) throws DatabaseException {
	        testsService.setLabActiveStatus(labId, status);
	        logger.info("Updated lab active status to {} for labId: {}", status, labId);
	        return ResponseEntity.ok("Lab active status updated for labId: " + labId);
	    }

	    /**
	     * Deletes all tests associated with a lab.
	     *
	     * @param labId The ID of the lab from which to delete tests.
	     * @return A ResponseEntity with a success message if the deletion was successful.
	     */
	    @DeleteMapping("/deleteAllTest/{labId}")
	    public ResponseEntity<String> deleteAllTestsByLabId(@PathVariable long labId) {
	        testsService.deleteAllTestsFromLabId(labId);
	        logger.info("Deleted all tests for labId: {}", labId);
	        return ResponseEntity.ok("Deleted All tests for labId: " + labId);
	    }

	    /**
	     * Sets the approval status of a test.
	     *
	     * @param testId The ID of the test.
	     * @param status The new approval status to set.
	     * @return A ResponseEntity with a success message if the update was successful.
	     * @throws TestNotFoundException If the test with the provided ID is not found.
	     */
	    @PutMapping("/updateTestApproval/{test_id}/{status}")
	    public ResponseEntity<String> SetTestApprovalStatus(@PathVariable(value = "test_id") long test_id, @PathVariable(value = "status") boolean status) throws TestNotFoundException {
	        try {
	    	testsService.setTestApprovalStatus(test_id, status);
	        logger.info("Updated Test Approval status to {} for test Id: {}", status, test_id);
	        return ResponseEntity.ok("Updated Test Approval status to " + status + " for test Id: " + test_id);
	        }
	        catch(TestNotFoundException e) {
	        	logger.warn(e.getMessage());
	        	
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        }
	    }

}
