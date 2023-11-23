package com.onehealth.service;

import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.Prescription;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * The PrescriptionService interface provides methods to perform CRUD operations
 * on Prescription entities and retrieve prescriptions based on appointmentId and patientId.
 */
public interface PrescriptionService {

    /**
     * Stores a new Prescription in the database.
     * 
     * @param file         The MultipartFile representing the prescription file to be stored as the Prescription.
     * @param appointmentId The ID of the appointment for which the prescription is being created.
     * @param patientId     The ID of the patient to whom the prescription belongs.
     * @return The ID of the newly stored Prescription.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    String storePrescription(MultipartFile file, long appointmentId, long patientId) throws IOException;

    /**
     * Retrieves a list of all Prescriptions from the database.
     * 
     * @return A List of all Prescriptions.
     */
    List<Prescription> getAllPrescriptions();

    /**
     * Retrieves a Prescription with the given appointmentId.
     * 
     * @param appointmentId The ID of the appointment for which the Prescription is to be retrieved.
     * @return An Optional containing the Prescription with the given appointmentId, if present; otherwise, an empty Optional.
     */
    Optional<Prescription> getPrescriptionByAppointmentId(long appointmentId);

    /**
     * Retrieves a list of Prescriptions belonging to a patient with the given patientId.
     * 
     * @param patientId The ID of the patient for which the Prescriptions are to be retrieved.
     * @return A List of Prescriptions belonging to the patient with the given patientId.
     */
    List<Prescription> getPrescriptionsByPatientId(long patientId);

    /**
     * Retrieves a Prescription with the given ID.
     * 
     * @param id The ID of the Prescription to be retrieved.
     * @return An Optional containing the Prescription with the given ID, if present; otherwise, an empty Optional.
     */
    Optional<Prescription> getPrescriptionById(String id);

    /**
     * Updates an existing Prescription in the database.
     * 
     * @param updatedPrescription The updated Prescription object with modified attributes.
     */
    void updatePrescription(Prescription updatedPrescription);

    /**
     * Deletes a Prescription with the given ID from the database.
     * 
     * @param id The ID of the Prescription to be deleted.
     */
    void deletePrescriptionById(String id);

    /**
     * Downloads the file content of a Prescription with the given ID.
     * 
     * @param id The ID of the Prescription to be downloaded.
     * @return The byte array representing the file content of the Prescription.
     */
    byte[] downloadPrescription(String id);
}
