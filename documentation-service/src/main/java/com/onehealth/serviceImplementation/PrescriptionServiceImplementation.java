package com.onehealth.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.Prescription;
import com.onehealth.repository.PrescriptionRepository;
import com.onehealth.service.PrescriptionService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service implementation class that handles operations related to Prescriptions.
 */
@Service
public class PrescriptionServiceImplementation implements PrescriptionService {

    // Logger for logging service actions.
    private static final Logger logger = Logger.getLogger(PrescriptionServiceImplementation.class.getName());

    // Autowired PrescriptionRepository to interact with the database.
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    /**
     * Stores a new Prescription in the repository.
     *
     * @param file         The MultipartFile representing the prescription file to be stored.
     * @param appointmentId The ID of the appointment associated with the prescription.
     * @param patientId     The ID of the patient associated with the prescription.
     * @return The ID of the newly stored Prescription.
     * @throws IOException If there is an I/O error while reading the file.
     */
    @Override
    public String storePrescription(MultipartFile file, long appointmentId, long patientId) throws IOException {
        try {
            Prescription prescription = new Prescription();
            prescription.setFilename(file.getOriginalFilename());
            prescription.setFileType(file.getContentType());
            prescription.setFileSize(Long.toString(file.getSize()));
            prescription.setFile(file.getBytes());
            prescription.setAppointmentId(appointmentId);
            prescription.setPatientId(patientId);
            prescriptionRepository.save(prescription);
            logger.log(Level.INFO, "Prescription stored successfully with ID: " + prescription.getId());
            return prescription.getId();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred while storing Prescription: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves all Prescriptions from the repository.
     *
     * @return A list of all Prescription objects in the repository.
     */
    @Override
    public List<Prescription> getAllPrescriptions() {
        try {
            logger.log(Level.INFO, "Retrieving all Prescriptions");
            List<Prescription> prescriptions = prescriptionRepository.findAll();
            logger.log(Level.INFO, "Retrieved " + prescriptions.size() + " Prescriptions");
            return prescriptions;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all Prescriptions");
            throw e;
        }
    }

    /**
     * Retrieves a Prescription with the given appointment ID from the repository.
     *
     * @param appointmentId The appointment ID associated with the Prescription to retrieve.
     * @return An Optional containing the Prescription if found, or an empty Optional if not found.
     */
    @Override
    public Optional<Prescription> getPrescriptionByAppointmentId(long appointmentId) {
        try {
            logger.log(Level.INFO, "Retrieving Prescription with Appointment ID: " + appointmentId);
            Optional<Prescription> prescriptionOptional = prescriptionRepository.findByAppointmentId(appointmentId);
            if (prescriptionOptional.isPresent()) {
                logger.log(Level.INFO, "Retrieved Prescription with Appointment ID: " + appointmentId);
            } else {
                logger.log(Level.INFO, "No Prescription found with Appointment ID: " + appointmentId);
            }
            return prescriptionOptional;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Prescription with Appointment ID: " + appointmentId);
            throw e;
        }
    }

    /**
     * Retrieves all Prescriptions associated with the given patient ID from the repository.
     *
     * @param patientId The patient ID associated with the Prescriptions to retrieve.
     * @return A list of Prescription objects associated with the given patient ID.
     */
    @Override
    public List<Prescription> getPrescriptionsByPatientId(long patientId) {
        try {
            logger.log(Level.INFO, "Retrieving Prescriptions with Patient ID: " + patientId);
            List<Prescription> prescriptions = prescriptionRepository.findByPatientId(patientId);
            logger.log(Level.INFO, "Retrieved " + prescriptions.size() + " Prescriptions with Patient ID: " + patientId);
            return prescriptions;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Prescriptions with Patient ID: " + patientId);
            throw e;
        }
    }

    /**
     * Retrieves a Prescription with the given ID from the repository.
     *
     * @param id The ID of the Prescription to retrieve.
     * @return An Optional containing the Prescription if found, or an empty Optional if not found.
     */
    @Override
    public Optional<Prescription> getPrescriptionById(String id) {
        try {
            logger.log(Level.INFO, "Retrieving Prescription with ID: " + id);
            Optional<Prescription> prescriptionOptional = prescriptionRepository.findById(id);
            if (prescriptionOptional.isPresent()) {
                logger.log(Level.INFO, "Retrieved Prescription with ID: " + id);
            } else {
                logger.log(Level.INFO, "No Prescription found with ID: " + id);
            }
            return prescriptionOptional;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Prescription with ID: " + id);
            throw e;
        }
    }

    /**
     * Updates the attributes of an existing Prescription in the repository.
     *
     * @param updatedPrescription The modified Prescription object to update.
     */
    @Override
    public void updatePrescription(Prescription updatedPrescription) {
        try {
            logger.log(Level.INFO, "Updating Prescription with ID: " + updatedPrescription.getId());
            prescriptionRepository.save(updatedPrescription);
            logger.log(Level.INFO, "Prescription updated successfully with ID: " + updatedPrescription.getId());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating Prescription: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Deletes a Prescription with the given ID from the repository.
     *
     * @param id The ID of the Prescription to delete.
     */
    @Override
    public void deletePrescriptionById(String id) {
        try {
            logger.log(Level.INFO, "Deleting Prescription with ID: " + id);
            prescriptionRepository.deleteById(id);
            logger.log(Level.INFO, "Prescription deleted successfully with ID: " + id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting Prescription: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Downloads the file associated with a Prescription with the given ID from the repository.
     *
     * @param id The ID of the Prescription to download.
     * @return The byte array representing the file associated with the Prescription, or null if not found.
     */
    @Override
    public byte[] downloadPrescription(String id) {
        try {
            logger.log(Level.INFO, "Downloading Prescription with ID: " + id);
            Optional<Prescription> prescriptionOptional = prescriptionRepository.findById(id);
            if (prescriptionOptional.isPresent()) {
                logger.log(Level.INFO, "Retrieved Prescription with ID: " + id);
                return prescriptionOptional.get().getFile();
            }
            logger.log(Level.INFO, "No Prescription found with ID: " + id);
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while downloading Prescription with ID: " + id);
            throw e;
        }
    }
}
