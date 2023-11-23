package com.onehealth.serviceImplementation;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.onehealth.dto.CityAndName;
import com.onehealth.dto.LabManagement;
import com.onehealth.dto.TestsDto;
import com.onehealth.dto.TestsUpdateRequest;
import com.onehealth.entity.Tests;
import com.onehealth.exception.DatabaseException;
import com.onehealth.exception.LabNotFoundException;
import com.onehealth.exception.TestNotFoundException;
import com.onehealth.repository.TestRepository;
import com.onehealth.service.TestsService;
/**
 * The TestsServiceImplementation class is an implementation of the TestsService interface.
 * It provides the business logic for handling test-related operations and interacts with the database through the TestRepository.
 */
@Service
public class TestsServiceImplementation implements TestsService{

    private static final Logger logger = LoggerFactory.getLogger(TestsService.class);
	
    @Autowired
    private TestRepository testRepository;
    
    @Autowired
    private WebClient.Builder webClientBuilder;
    
    @Value("${apiGatewayUrl}")
	private String apiGatewayUrl;
	
    /**
     * Retrieve a list of all tests.
     *
     * @return A list of all available tests.
     * @throws DatabaseException If there's an issue accessing the database.
     */
    @Override
    public List<Tests> getAllTests() throws DatabaseException {
        logger.info("Fetching all tests");
        return testRepository.findAll();
    }

    /**
     * Retrieve a test by its ID.
     *
     * @param test_id The ID of the test to retrieve.
     * @return The test with the provided ID.
     * @throws TestNotFoundException If the test with the given ID is not found.
     */
    @Override
    public Tests getTestById(long test_id) throws TestNotFoundException {
        logger.info("Fetching test with test_id: {}", test_id);
        try {
            Tests test = testRepository.findById(test_id)
                    .orElseThrow(() -> new TestNotFoundException("Test Not Found With test_id: " + test_id));
            logger.info("Returning test with test_id: {}", test_id);
            return test;
        } catch (TestNotFoundException ex) {
            logger.warn("Test with test_id {} not found.", test_id);
            throw ex;
        } 
    }

    /**
     * Delete a test by its ID.
     *
     * @param test_id The ID of the test to delete.
     * @throws TestNotFoundException If the test with the given ID is not found.
     * @throws DatabaseException If there's an issue accessing the database.
     */
    @Override
    public void deleteTest(long test_id) throws DatabaseException, TestNotFoundException {
        boolean isValidId = testRepository.existsById(test_id);
        if (isValidId) {
            logger.info("Deleting test with test_id: {}", test_id);
            testRepository.deleteById(test_id);
        } else {
            logger.warn("Test with test_id {} not found.", test_id);
            throw new TestNotFoundException("Test Not Found with test_id : " + test_id);
        }
    }

    /**
     * Update the details of a test.
     *
     * @param test The test with updated details to be saved.
     * @throws TestNotFoundException If the test with the given ID is not found.
     * @throws DatabaseException If there's an issue accessing the database.
     * @throws LabNotFoundException 
     */
    @Override
    public void updateTestDetails(TestsUpdateRequest test) throws DatabaseException, TestNotFoundException, LabNotFoundException {
        logger.info("Updating test with test_id: {}", test.getTest_id());
        boolean isValidId = testRepository.existsById(test.getTest_id());
        LabManagement labManagement = webClientBuilder.build()
			 	.get()
	            .uri(apiGatewayUrl+"/api/labs/{lab_id}",test.getLabId())
	            .retrieve()
	            .bodyToMono(LabManagement.class)
	            .block(); 
        if(labManagement == null) {
        	throw new LabNotFoundException("Lab Not Found With Id : "+test.getLabId() );
        }
        if (isValidId && labManagement!= null) {
            logger.info("Updating test with test_id: {}", test.getTest_id());
            Tests updateTest = new Tests();
            updateTest.setTest_id(test.getTest_id());
            updateTest.setTestName(test.getTestName());
            updateTest.setPrice(test.getPrice());
            updateTest.setGov_appro_cert_path(test.getGov_appro_cert_path());
            updateTest.setHome_sample(test.getHome_sample());
            updateTest.setTest_approval(test.isTest_approval());
            updateTest.setTest_description(test.getTest_description());
            updateTest.setLabId(test.getLabId());
            updateTest.setTestCategory(test.getTestCategory());
            updateTest.setLabActive(labManagement.isActive());
            updateTest.setLabAddress(labManagement.getAddress());
            updateTest.setLabCity(labManagement.getCity());
            updateTest.setLabName(labManagement.getLab_name());
            
            
            // TODO Auto-generated method stub
            testRepository.save(updateTest);
        } else {
            logger.warn("Test with test_id {} not found.", test.getTest_id());
            logger.error("Operation failed !!");
            throw new TestNotFoundException("Test Not Found with : " + test.getTest_id());
        }
    }

    /**
     * Retrieve a list of tests based on the lab ID.
     *
     * @param lab_id The ID of the lab for which tests need to be retrieved.
     * @return A list of tests associated with the provided lab ID.
     * @throws DatabaseException If there's an issue accessing the database.
     */
    @Override
    public List<Tests> getAllTestByLabId(long lab_id) throws DatabaseException {
        logger.info("Fetching all tests for lab with lab_id: {}", lab_id);
        return testRepository.findByLabId(lab_id);
    }

    /**
     * Add a new test to the database.
     *
     * @param test The new test to be added.
     * @throws DatabaseException If there's an issue accessing the database.
     * @throws LabNotFoundException 
     */
    @Override
    public void addNewTest(TestsDto testDto) throws DatabaseException, LabNotFoundException {
    	
    	logger.info("Fetching Lab lab with lab_id: {}", testDto.getLabId());
    	LabManagement labManagement = webClientBuilder.build()
				 	.get()
		            .uri(apiGatewayUrl+"/api/labs/{lab_id}",testDto.getLabId())
		            .retrieve()
		            .bodyToMono(LabManagement.class)
		            .block();
    	if(labManagement == null) {
    		throw new LabNotFoundException("Lab Not Found with Lab Id : "+testDto.getLabId());
    	}
    	
        Tests test = new Tests();
        test.setTestName(testDto.getTestName());
        test.setHome_sample(testDto.getHome_sample());
        test.setTest_description(testDto.getTest_description());
        test.setPrice(testDto.getPrice());
        test.setTest_approval(testDto.isTest_approval());
        test.setGov_appro_cert_path(testDto.getGov_appro_cert_path());
        test.setLabId(testDto.getLabId());

        // Map lab data from LabManagement
        test.setLabName(labManagement.getLab_name());
        test.setLabCity(labManagement.getCity());
        test.setLabAddress(labManagement.getAddress());
        test.setLabActive(labManagement.isActive());
        test.setTestCategory(testDto.getTestCategory());

        logger.info("Adding a new test");
        // Save the mapped Tests object
        testRepository.save(test);
    }
    
    
    /**
     * Retrieves a list of tests by their category.
     *
     * @param testCategory The category of tests to retrieve.
     * @return A list of tests in the specified category.
     */
    public List<Tests> getAllTestsByCatagory(String test_catagory){
		return testRepository.findByTestCategory(test_catagory);
    	
    }

    /**
     * Retrieves a list of tests by their laboratory city and test name.
     *
     * @param dto An object containing the test name and city for filtering tests.
     * @return A list of tests that match the specified test name and city.
     */
    @Override
    public List<Tests> getAllTestsByLabCityAndTestName(CityAndName dto) {
        logger.info("Fetching tests with testName: {} and city: {}", dto.getTestName(), dto.getCity());
        List<Tests> list = testRepository.findByLabCityAndTestName(dto.getTestName(), dto.getCity());
        logger.debug("Fetched {} tests", list.size());
        return list;
    }

    /**
     * Retrieves a list of tests by their laboratory city.
     *
     * @param city The city to filter tests by.
     * @return A list of tests located in the specified city.
     */
    @Override
    public List<Tests> getAllTestsByCity(String city) {
        logger.info("Fetching tests with city: {}", city);
        List<Tests> tests = testRepository.findByLabCity(city);
        logger.debug("Fetched {} tests", tests.size());
        return tests;
    }

    /**
     * Sets the active status of tests associated with a lab.
     *
     * @param labId  The ID of the lab.
     * @param status The new active status to set.
     * @throws DatabaseException If there is an issue with the database while updating.
     */
    @Override
    public void setLabActiveStatus(long labId, boolean status) throws DatabaseException {
        List<Tests> tests =  getAllTestByLabId(labId);

        tests.forEach(test -> test.setLabActive(status));

        testRepository.saveAll(tests);
        logger.info("Set lab active status to {} for tests associated with lab ID: {}", status, labId);
    }

    /**
     * Deletes all tests associated with a lab.
     *
     * @param labId The ID of the lab from which to delete tests.
     */
    @Override
    public void deleteAllTestsFromLabId(long labId) {
        testRepository.deleteByLabId(labId);
        logger.info("Deleted all tests associated with lab ID: {}", labId);
    }

    /**
     * Sets the approval status of a test.
     *
     * @param testId The ID of the test.
     * @param status The new approval status to set.
     * @throws TestNotFoundException If the test with the provided ID is not found.
     */
    @Override
    public void setTestApprovalStatus(long testId, boolean status) throws TestNotFoundException {
        Tests test = testRepository.findById(testId)
                .orElseThrow(() -> new TestNotFoundException("Test Not Found With Test Id: " + testId));
        test.setTest_approval(status);
        testRepository.save(test);
        logger.info("Set test approval status to {} for test with ID: {}", status, testId);
    }
}
