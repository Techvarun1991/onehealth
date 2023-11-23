package com.onehealth.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.DoctorDocument;

/**
 * The DoctorDocumentService interface provides methods to perform CRUD operations
 * on DoctorDocument entities and retrieve DoctorDocuments based on doctorId.
 */
public interface DoctorDocumentService {

    /**
     * Stores a new DoctorDocument in the database.
     * 
     * @param file     The MultipartFile representing the document file to be stored as the DoctorDocument.
     * @param doctorId The ID of the doctor to whom the document belongs.
     * @return The ID of the newly stored DoctorDocument.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    String storeDoctorDocument(MultipartFile file, long doctorId) throws IOException;

    /**
     * Retrieves a list of DoctorDocuments associated with the specified doctorId.
     * 
     * @param doctorId The ID of the doctor for which DoctorDocuments are to be retrieved.
     * @return A List of DoctorDocuments associated with the specified doctorId.
     */
    List<DoctorDocument> getAllDoctorDocumentsByDoctorId(long doctorId);

    /**
     * Retrieves a DoctorDocument with the given ID.
     * 
     * @param id The ID of the DoctorDocument to be retrieved.
     * @return An Optional containing the DoctorDocument with the given ID, if present; otherwise, an empty Optional.
     */
    Optional<DoctorDocument> getDoctorDocumentById(String id);

    /**
     * Updates an existing DoctorDocument in the database.
     * 
     * @param updatedDoctorDocument The updated DoctorDocument object with modified attributes.
     */
    void updateDoctorDocument(DoctorDocument updatedDoctorDocument);

    /**
     * Deletes a DoctorDocument with the given ID from the database.
     * 
     * @param id The ID of the DoctorDocument to be deleted.
     */
    void deleteDoctorDocumentById(String id);

    /**
     * Downloads the file content of a DoctorDocument with the given ID.
     * 
     * @param id The ID of the DoctorDocument to be downloaded.
     * @return The byte array representing the file content of the DoctorDocument.
     */
    byte[] downloadDoctorDocument(String id);
}
