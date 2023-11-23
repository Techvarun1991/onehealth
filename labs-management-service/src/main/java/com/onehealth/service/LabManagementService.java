package com.onehealth.service;

import java.util.List;
import java.util.Optional;

import com.onehealth.entity.LabManagement;
import com.onehealth.exception.DatabaseException;
import com.onehealth.exception.LabNotFoundException;

/**
 * This interface defines the contract for the LabManagementService, which provides methods to interact with LabManagement entities.
 */
public interface LabManagementService {

    /**
     * Saves a new LabManagement entity in the database.
     *
     * @param lab The LabManagement object to be saved.
     * @return The saved LabManagement object.
     * @throws DatabaseException If there is an error while interacting with the database.
     */
    LabManagement saveLab(LabManagement lab) throws DatabaseException;

    /**
     * Retrieves a list of all LabManagement entities from the database.
     *
     * @return A list of all LabManagement entities.
     * @throws DatabaseException If there is an error while interacting with the database.
     */
    List<LabManagement> getAllLabs() throws DatabaseException;

    /**
     * Retrieves a LabManagement entity by its ID.
     *
     * @param labId The ID of the LabManagement entity to retrieve.
     * @return An Optional containing the retrieved LabManagement entity, or empty if not found.
     * @throws DatabaseException If there is an error while interacting with the database.
     * @throws LabNotFoundException If the LabManagement entity with the given ID is not found.
     */
    Optional<LabManagement> getLabById(long labId) throws DatabaseException, LabNotFoundException;

    /**
     * Updates an existing LabManagement entity in the database.
     *
     * @param lab The updated LabManagement object to be saved.
     * @throws DatabaseException If there is an error while interacting with the database.
     * @throws LabNotFoundException If the LabManagement entity with the given ID is not found.
     */
    void updateLab(LabManagement lab) throws DatabaseException, LabNotFoundException;

    /**
     * Deletes a LabManagement entity from the database by its ID.
     *
     * @param labId The ID of the LabManagement entity to be deleted.
     * @throws DatabaseException If there is an error while interacting with the database.
     * @throws LabNotFoundException If the LabManagement entity with the given ID is not found.
     */
    void deleteLabById(long labId) throws DatabaseException, LabNotFoundException;

    /**
     * Retrieves a list of LabManagement entities by city.
     *
     * @param city The city to search for.
     * @return A list of LabManagement entities with the given city.
     * @throws DatabaseException If there is an error while interacting with the database.
     */
    List<LabManagement> getLabsByCity(String city) throws DatabaseException;

    
    /**
     * This method deactivates a lab by setting its active status to false.
     *
     * @param labId The unique identifier of the lab to deactivate.
     * @return A ResponseEntity with a success message if deactivation is successful.
     * @throws DatabaseException   If there is an issue with the database while updating.
     * @throws LabNotFoundException If the lab with the provided labId is not found.
     */
	void setLabInactive(long labId , boolean status) throws DatabaseException, LabNotFoundException;
}
