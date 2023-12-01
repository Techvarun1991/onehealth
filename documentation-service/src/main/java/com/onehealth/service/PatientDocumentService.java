package com.onehealth.service;

import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.PatientDocument;
import com.onehealth.exception.DatabaseException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing patient documents.
 * Provides methods to retrieve a list of patient documents based on patient ID and record type.
 * Additional methods can be added for other document-related operations.
 */
public interface PatientDocumentService {

    /**
     * Stores a patient document in the system.
     *
     * @param file      The file to be stored.
     * @param patientId The ID of the patient associated with the document.
     * @return The ID of the stored document.
     * @throws IOException If there is an error reading the file.
     */
    String storePatientDocument(MultipartFile file, long patientId,String recordType) throws IOException;

    /**
     * Retrieves all patient documents in the system.
     *
     * @return A list of patient documents.
     * @throws DatabaseException If there's an error while retrieving patient documents from the repository.
     */
    List<PatientDocument> getAllPatientDocuments() throws DatabaseException;

    /**
     * Retrieves patient documents associated with a specific patient.
     *
     * @param patientId The ID of the patient.
     * @return A list of patient documents associated with the given patient.
     * @throws DatabaseException If there's an error while retrieving patient documents from the repository.
     */
    List<PatientDocument> getPatientDocumentsByPatientId(long patientId) throws DatabaseException;

    /**
     * Retrieves a patient document by its ID.
     *
     * @param id The ID of the patient document.
     * @return The patient document if found, otherwise empty.
     * @throws DatabaseException If there's an error while retrieving the patient document from the repository.
     */
    Optional<PatientDocument> getPatientDocumentById(String id) throws DatabaseException;

    /**
     * Updates a patient document in the system.
     *
     * @param updatedPatientDocument The updated patient document.
     * @throws DatabaseException If there's an error while updating the patient document in the repository.
     */
    void updatePatientDocument(PatientDocument updatedPatientDocument) throws DatabaseException;

    /**
     * Deletes a patient document by its ID.
     *
     * @param id The ID of the patient document to be deleted.
     * @throws DatabaseException If there's an error while deleting the patient document from the repository.
     */
    void deletePatientDocumentById(String id) throws DatabaseException;

    /**
     * Deletes all patient documents associated with a specific patient.
     *
     * @param patientId The ID of the patient.
     * @throws DatabaseException If there's an error while deleting patient documents from the repository.
     */
    void deletePatientDocumentsByPatientId(long patientId) throws DatabaseException;
    
    
    /**
     * Downloads the file content of a PatientDocument with the given ID.
     * 
     * @param id The ID of the PatientDocument to be downloaded.
     * @return The byte array representing the file content of the PatientDocument.
     */
    byte[] downloadPatientDocument(String id);
    
    
    /**
     * Retrieves all patient documents for a given patient ID and record type.
     *
     * @param patientId  The ID of the patient.
     * @param recordType The record type of the documents.
     * @return A list of patient documents.
     * @throws RuntimeException If an error occurs while retrieving documents.
     */
    List<PatientDocument> getAllPatientDocuments(long patientId, String recordType);
}