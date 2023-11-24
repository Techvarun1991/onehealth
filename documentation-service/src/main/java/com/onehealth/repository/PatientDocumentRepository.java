package com.onehealth.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.onehealth.entity.PatientDocument;

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
}

