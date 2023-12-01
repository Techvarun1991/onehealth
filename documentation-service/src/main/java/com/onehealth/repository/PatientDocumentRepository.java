package com.onehealth.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.onehealth.entity.PatientDocument;

/**
 * Repository interface for interacting with patient documents in the MongoDB database.
 * Extends the MongoRepository interface to leverage Spring Data MongoDB capabilities.
 * Provides methods to query and manipulate patient documents, including custom queries.
 */
public interface PatientDocumentRepository extends MongoRepository<PatientDocument, String> {

    /**
     * Finds patient documents by patient ID.
     *
     * @param patientId The ID of the patient.
     * @return A list of patient documents for the given patient ID.
     */
    List<PatientDocument> findByPatientId(long patientId);

    /**
     * Deletes patient documents by patient ID.
     *
     * @param patientId The ID of the patient.
     */
    void deleteByPatientId(long patientId);
    
    /**
     * Retrieves a list of patient documents based on the patient ID and record type.
     *
     * @param patientId  The ID of the patient.
     * @param recordType The record type of the documents.
     * @return A list of patient documents matching the given criteria.
     */
    List<PatientDocument> findByPatientIdAndRecordType(long patientId, String recordType);

}

