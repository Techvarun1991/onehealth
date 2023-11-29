package com.onehealth.service;

import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.LabDocument;
import com.onehealth.exception.DatabaseException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing lab documents.
 */
public interface LabDocumentService {

    /**
     * Stores a lab document in the system.
     *
     * @param file      The file to be stored.
     * @param labId The ID of the lab associated with the document.
     * @return The ID of the stored document.
     * @throws IOException If there is an error reading the file.
     */
    String storeLabDocument(MultipartFile file, long labId) throws IOException;

    /**
     * Retrieves all lab documents in the system.
     *
     * @return A list of lab documents.
     * @throws DatabaseException If there's an error while retrieving lab documents from the repository.
     */
    List<LabDocument> getAllLabDocuments() throws DatabaseException;

    /**
     * Retrieves lab documents associated with a specific lab.
     *
     * @param labId The ID of the lab.
     * @return A list of lab documents associated with the given lab.
     * @throws DatabaseException If there's an error while retrieving lab documents from the repository.
     */
    List<LabDocument> getLabDocumentsByLabId(long labId) throws DatabaseException;

    /**
     * Retrieves a lab document by its ID.
     *
     * @param id The ID of the lab document.
     * @return The lab document if found, otherwise empty.
     * @throws DatabaseException If there's an error while retrieving the lab document from the repository.
     */
    Optional<LabDocument> getLabDocumentById(String id) throws DatabaseException;

    /**
     * Updates a lab document in the system.
     *
     * @param updatedLabDocument The updated lab document.
     * @throws DatabaseException If there's an error while updating the lab document in the repository.
     */
    void updateLabDocument(LabDocument updatedLabDocument) throws DatabaseException;

    /**
     * Deletes a lab document by its ID.
     *
     * @param id The ID of the lab document to be deleted.
     * @throws DatabaseException If there's an error while deleting the lab document from the repository.
     */
    void deleteLabDocumentById(String id) throws DatabaseException;

    /**
     * Deletes all lab documents associated with a specific lab.
     *
     * @param labId The ID of the lab.
     * @throws DatabaseException If there's an error while deleting lab documents from the repository.
     */
    void deleteLabDocumentsByLabId(long labId) throws DatabaseException;
}