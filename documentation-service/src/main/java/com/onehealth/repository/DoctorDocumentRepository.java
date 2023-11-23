package com.onehealth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.onehealth.entity.DoctorDocument;
import java.util.List;

/**
 * The DoctorDocumentRepository interface extends the MongoRepository interface
 * to perform CRUD operations on the "doctor_documents" collection in MongoDB.
 */
public interface DoctorDocumentRepository extends MongoRepository<DoctorDocument, String> {

    /**
     * Finds and returns a list of DoctorDocuments associated with the specified doctorId.
     *
     * @param doctorId The ID of the doctor for which DoctorDocuments are to be retrieved.
     * @return A list of DoctorDocument objects associated with the specified doctorId.
     */
    List<DoctorDocument> findByDoctorId(long doctorId);
}
