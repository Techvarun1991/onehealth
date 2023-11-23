package com.onehealth.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.onehealth.entity.LabManagement;
import com.onehealth.exception.DatabaseException;
import com.onehealth.exception.LabNotFoundException;
import com.onehealth.service.LabManagementService;

import jakarta.ws.rs.core.Response;


/**
 * REST Controller for managing LabManagement entities.
 * This controller handles HTTP requests related to LabManagement operations, such as
 * creating, retrieving, updating, and deleting LabManagement objects. It provides
 * endpoints to interact with the LabManagement service and perform various actions.
 */
@RestController
@RequestMapping("/api/labs")
public class LabManagementController {

    @Autowired
    private LabManagementService labManagementService;
    
    private static final Logger logger = Logger.getLogger(LabManagementController.class.getName());

    /**
     * Creates and saves a new LabManagement object in the database.
     *
     * @param lab The LabManagement object to be saved.
     * @return The saved LabManagement object as a ResponseEntity.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    @PostMapping
    public ResponseEntity<?> saveLab(@RequestBody LabManagement lab) throws DatabaseException {
        try {
            // Logging: Log the attempt to save a new LabManagement.
            logger.info("Attempting to save a new LabManagement: " + lab.toString());

            LabManagement savedLab = labManagementService.saveLab(lab);

            // Logging: Log the successful saving of the LabManagement.
            logger.info("LabManagement saved successfully: " + savedLab.toString());

            return ResponseEntity.ok(savedLab);
        } catch (DatabaseException e) {
            logger.warning("Error occurred while saving LabManagement: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
         }
    }

    /**
     * Retrieves a list of all LabManagement objects from the database.
     *
     * @return A list of all LabManagement objects as a ResponseEntity.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    @GetMapping
    public ResponseEntity<?> getAllLabs() throws DatabaseException {
        try {
            // Logging: Log the attempt to retrieve all LabManagement objects.
            logger.info("Attempting to retrieve all LabManagement objects.");

            List<LabManagement> labs = labManagementService.getAllLabs();

            // Logging: Log the successful retrieval of all LabManagement objects.
            logger.info("Successfully retrieved all LabManagement objects.");

            return ResponseEntity.ok(labs);
        } catch (DatabaseException e) {
            logger.warning("Error occurred while retrieving LabManagement objects: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());        }
    }

    /**
     * Retrieves a specific LabManagement object identified by its ID from the database.
     *
     * @param id The ID of the LabManagement object to retrieve.
     * @return The LabManagement object as a ResponseEntity if found, or a not found response if not found.
     * @throws DatabaseException   If an error occurs while accessing the database.
     * @throws LabNotFoundException If the LabManagement object with the given ID is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getLabById(@PathVariable long id) throws DatabaseException, LabNotFoundException {
        try {
            // Logging: Log the attempt to retrieve a LabManagement object by ID.
            logger.info("Attempting to retrieve LabManagement object with ID: " + id);
            Optional<LabManagement> lab = labManagementService.getLabById(id);
            return ResponseEntity.ok(lab.get());
        } catch (LabNotFoundException e) {
            logger.warning("LabManagement object with ID " + id + " not found: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());  
        }
    }

    /**
     * Updates an existing LabManagement object in the database.
     *
     * @param id         The ID of the LabManagement object to update.
     * @param updatedLab The updated LabManagement object.
     * @return A ResponseEntity with a success message if the update was successful.
     * @throws DatabaseException   If an error occurs while accessing the database.
     * @throws LabNotFoundException If the LabManagement object with the given ID is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateLab(@PathVariable long id, @RequestBody LabManagement updatedLab)
            throws DatabaseException, LabNotFoundException {
        try {
            // Logging: Log the attempt to update a LabManagement object by ID.
            logger.info("Attempting to update LabManagement object with ID: " + id);

            labManagementService.updateLab(updatedLab);

            // Logging: Log the successful update of the LabManagement object.
            logger.info("LabManagement object with ID " + id + " updated successfully.");

            return ResponseEntity.ok("Lab updated successfully.");
        } catch (LabNotFoundException e) {
            logger.warning("LabManagement object with ID " + id + " not found: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());   
        }
    }

    /**
     * Deletes a specific LabManagement object identified by its ID from the database.
     *
     * @param id The ID of the LabManagement object to delete.
     * @return A ResponseEntity with a success message if the deletion was successful.
     * @throws DatabaseException   If an error occurs while accessing the database.
     * @throws LabNotFoundException If the LabManagement object with the given ID is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLabById(@PathVariable long id) throws DatabaseException, LabNotFoundException {
        try {
            // Logging: Log the attempt to delete a LabManagement object by ID.
            logger.info("Attempting to delete LabManagement object with ID: " + id);

            labManagementService.deleteLabById(id);

            // Logging: Log the successful deletion of the LabManagement object.
            logger.info("LabManagement object with ID " + id + " deleted successfully.");

            return ResponseEntity.ok("Lab deleted successfully.");
        } catch (LabNotFoundException e) {
            logger.warning("LabManagement object with ID " + id + " not found: ");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());              
        }
    }

    /**
     * Retrieves a list of LabManagement objects associated with a specific city from the database.
     *
     * @param city The city for which to retrieve LabManagement objects.
     * @return A list of LabManagement objects as a ResponseEntity.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    @GetMapping("/labs")
    public ResponseEntity<?> getLabsByCity(@RequestParam("city") String city) throws DatabaseException {
        try {
            // Logging: Log the attempt to retrieve LabManagement objects by city.
            logger.info("Attempting to retrieve LabManagement objects for city: " + city);

            List<LabManagement> labs = labManagementService.getLabsByCity(city);

            // Logging: Log the successful retrieval of LabManagement objects by city.
            logger.info("Successfully retrieved LabManagement objects for city: " + city);

            return ResponseEntity.ok(labs);
        } catch (DatabaseException e) {
            logger.warning("Error occurred while retrieving LabManagement objects by city: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    /**
     * This endpoint deactivates a lab by setting its active status to false.
     *
     * @param labId The unique identifier of the lab to deactivate.
     * @return A ResponseEntity with a success message if deactivation is successful.
     * @throws DatabaseException   If there is an issue with the database while updating.
     * @throws LabNotFoundException If the lab with the provided labId is not found.
     */
    @PostMapping("/active/{labId}/{status}")
    public ResponseEntity<String> setLabInactive(@PathVariable(value = "labId") long labId , @PathVariable(value = "status") boolean status) throws DatabaseException, LabNotFoundException {
        // Log that we are setting the lab's active status to false for the given labId.
    	
    	try {
    		  
    		logger.info("Setting Lab Active Status to "+status+" for labId : " + labId);
    		// Call the service to set the lab's active status to false.
    	    labManagementService.setLabInactive(labId , status);
    	    //Log that the active status has been updated to false for the given labId.
    	    logger.info("Active status updated to "+status+" for labId : " + labId);
    	    // Return a success response.
    	    return ResponseEntity.ok("Lab Status Updated Successfully !!");
		} catch (LabNotFoundException e) {
			logger.warning("LabManagement object with ID " + labId + " not found: ");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			
		}
      

     }

}
