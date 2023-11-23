package com.onehealth.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.onehealth.entity.LabManagement;
import com.onehealth.exception.DatabaseException;
import com.onehealth.exception.LabNotFoundException;
import com.onehealth.repository.LabManagementRepository;
import com.onehealth.service.LabManagementService;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * This class implements the LabManagementService interface to provide
 * service methods for managing LabManagement entities. It interacts with
 * the LabManagementRepository to perform CRUD operations on the database.
 */
@Service
public class LabManagementServiceImplementation implements LabManagementService {

    @Autowired
    private LabManagementRepository labManagementRepository;

    @Autowired
    private WebClient.Builder builder;
    
    @Value("${apiGatewayUrl}")
	private String apiGatewayUrl;
    
    private static final Logger logger = Logger.getLogger(LabManagementServiceImplementation.class.getName());

    /**
     * Saves a new LabManagement object in the database.
     *
     * @param lab The LabManagement object to be saved.
     * @return The saved LabManagement object.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    @Override
    public LabManagement saveLab(LabManagement lab) throws DatabaseException {
        // Logging: Log the attempt to save a new LabManagement.
        logger.info("Attempting to save a new Lab: " + lab.toString());

        LabManagement savedLab = labManagementRepository.save(lab);

        // Logging: Log the successful saving of the LabManagement.
        logger.info("Lab saved successfully: " + savedLab.toString());

        return savedLab;
    }

    /**
     * Retrieves a list of all LabManagement objects from the database.
     *
     * @return A list of all LabManagement objects.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    @Override
    public List<LabManagement> getAllLabs() throws DatabaseException {
        // Logging: Log the attempt to retrieve all LabManagement objects.
        logger.info("Attempting to retrieve all Lab objects.");

        List<LabManagement> labs = labManagementRepository.findAll();

        // Logging: Log the successful retrieval of all LabManagement objects.
        logger.info("Successfully retrieved all Lab objects.");

        return labs;
    }

    /**
     * Retrieves a specific LabManagement object identified by its ID from the database.
     *
     * @param labId The ID of the LabManagement object to retrieve.
     * @return The LabManagement object if found.
     * @throws DatabaseException   If an error occurs while accessing the database.
     * @throws LabNotFoundException If the LabManagement object with the given ID is not found.
     */
    @Override
    public Optional<LabManagement> getLabById(long labId) throws DatabaseException, LabNotFoundException {
        // Logging: Log the attempt to retrieve a LabManagement object by ID.
        logger.info("Attempting to retrieve LabManagement object with ID: " + labId);

        Optional<LabManagement> lab = labManagementRepository.findById(labId);

        if (lab.isPresent()) {
            // Logging: Log the successful retrieval of the LabManagement object by ID.
            logger.info("LabManagement object with ID " + labId + " found in the database.");
            return lab;
        } else {
            // Logging: Log that the LabManagement object with the given ID was not found.
            logger.warning("LabManagement object with ID " + labId + " not found in the database.");
            throw new LabNotFoundException("Lab Not Found with lab_id : " + labId);
        }
    }

    /**
     * Updates an existing LabManagement object in the database.
     *
     * @param lab The updated LabManagement object.
     * @throws DatabaseException   If an error occurs while accessing the database.
     * @throws LabNotFoundException If the LabManagement object with the given ID is not found.
     */
    @Override
    public void updateLab(LabManagement lab) throws DatabaseException, LabNotFoundException {
        boolean isValidId = labManagementRepository.existsById(lab.getLab_id());
        if (isValidId) {
            // Logging: Log the attempt to update a LabManagement object by ID.
            logger.info("Attempting to update LabManagement object with ID: " + lab.getLab_id());

            labManagementRepository.save(lab);

            // Logging: Log the successful update of the LabManagement object.
            logger.info("LabManagement object with ID " + lab.getLab_id() + " updated successfully.");
        } else {
            // Logging: Log that the LabManagement object with the given ID was not found.
            logger.warning("LabManagement object with ID " + lab.getLab_id() + " not found in the database.");
            throw new LabNotFoundException("Lab Not Found with lab_id : " + lab.getLab_id());
        }
    }

    /**
     * Deletes a specific LabManagement object identified by its ID from the database.
     *
     * @param labId The ID of the LabManagement object to delete.
     * @throws DatabaseException   If an error occurs while accessing the database.
     * @throws LabNotFoundException If the LabManagement object with the given ID is not found.
     */
    @Override
	    public void deleteLabById(long labId) throws DatabaseException, LabNotFoundException {
	        boolean isValidId = labManagementRepository.existsById(labId);
	        if (isValidId) {
	            // Logging: Log the attempt to delete a LabManagement object by ID.
	            logger.info("Attempting to delete LabManagement object with ID: " + labId);
	            logger.info("Attempting to delete all Tests from lab with ID: " + labId);
	            String response = builder.build()
	                    .delete()
	                    .uri(apiGatewayUrl+"/api/test/deleteAllTest/{labId}",labId)
	                    .retrieve()
	                    .bodyToMono(String.class)
	                    .block();
	            
	        labManagementRepository.deleteById(labId);
            
            
            
            logger.info(response);
            // Logging: Log the successful deletion of the LabManagement object.
            logger.info("LabManagement object with ID " + labId + " deleted successfully.");
        } else {
            // Logging: Log that the LabManagement object with the given ID was not found.
            logger.warning("LabManagement object with ID " + labId + " not found in the database.");
            throw new LabNotFoundException("Lab Not Found with lab_id : " + labId);
        }
    }

    /**
     * Retrieves a list of LabManagement objects associated with a specific city from the database.
     *
     * @param city The city for which to retrieve LabManagement objects.
     * @return A list of LabManagement objects.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    @Override
    public List<LabManagement> getLabsByCity(String city) throws DatabaseException {
        // Logging: Log the attempt to retrieve LabManagement objects by city.
        logger.info("Attempting to retrieve LabManagement objects for city: " + city);

        List<LabManagement> labs = labManagementRepository.findByCity(city);

        // Logging: Log the successful retrieval of LabManagement objects by city.
        logger.info("Successfully retrieved LabManagement objects for city: " + city);

        return labs;
    }

    /**
     * This method deactivates a lab by setting its active status to false.
     *
     * @param labId The unique identifier of the lab to deactivate.
     * @return A ResponseEntity with a success message if deactivation is successful.
     * @throws DatabaseException   If there is an issue with the database while updating.
     * @throws LabNotFoundException If the lab with the provided labId is not found.
     */
    @Override
    public void setLabInactive(long labId , boolean status) throws DatabaseException, LabNotFoundException {
        // Log a message to indicate that we are setting the lab's active status to false for the specified labId.
        logger.info("Setting Lab Active Status to "+status+" for labId : " + labId);

        // Attempt to retrieve the lab with the provided labId from the database.
        LabManagement lab = getLabById(labId).orElseThrow(() -> new LabNotFoundException("Lab Not Found With Lab Id : " + labId));

        // Set the lab's active status to false, effectively marking it as inactive.
        lab.setActive(status);
        
        String s = builder.build()
        			.post()
        			.uri(apiGatewayUrl+"/api/test/updateLabActiveStatus/{labId}/{status}",labId,status)
        			.retrieve()
        			.bodyToMono(String.class)
        			.block();
        
        // Save the updated lab information back to the database.
        labManagementRepository.save(lab);
         
        logger.info(s); 
        // Log a message to confirm that the active status has been successfully updated to false for the specified labId.
        logger.info("Active status updated to "+status+" for labId : " + labId);
    }

}
