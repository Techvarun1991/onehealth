package com.onehealth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.onehealth.entity.Prescription;

import java.util.List;
import java.util.Optional;

/**
 * The PrescriptionRepository interface extends the MongoRepository interface
 * to perform CRUD operations on the "prescriptions" collection in MongoDB.
 */
public interface PrescriptionRepository extends MongoRepository<Prescription, String> {

    /**
     * Finds and returns an Optional containing a Prescription associated with the specified appointmentId.
     * If a Prescription with the given appointmentId exists, it will be present in the Optional.
     * Otherwise, the Optional will be empty.
     *
     * @param appointmentId The ID of the appointment for which the Prescription is to be retrieved.
     * @return An Optional containing the Prescription associated with the specified appointmentId,
     *         or an empty Optional if no Prescription with the appointmentId exists.
     */
    Optional<Prescription> findByAppointmentId(long appointmentId);

    /**
     * Finds and returns a list of Prescriptions associated with the specified patientId.
     * If one or more Prescriptions are associated with the patientId, they will be returned as a List.
     * If no Prescriptions are associated with the patientId, the returned List will be empty.
     *
     * @param patientId The ID of the patient for whom the Prescriptions are to be retrieved.
     * @return A List containing all Prescriptions associated with the specified patientId,
     *         or an empty List if no Prescriptions are associated with the patientId.
     */
    List<Prescription> findByPatientId(long patientId);
}
