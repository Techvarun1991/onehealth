package com.onehealth.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.Prescription;
import com.onehealth.service.PrescriptionService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/documentation/prescriptions")
public class PrescriptionController {

    private static final Logger logger = Logger.getLogger(PrescriptionController.class.getName());

    @Autowired
    private PrescriptionService prescriptionService;

    /**
     * Uploads a new prescription file for a given appointment and patient.
     *
     * @param file          The MultipartFile representing the prescription file to upload.
     * @param appointmentId The ID of the appointment associated with the prescription.
     * @param patientId     The ID of the patient associated with the prescription.
     * @return A ResponseEntity containing the success message and the file ID of the uploaded prescription.
     * @throws IOException If an I/O error occurs while storing the prescription.
     */
    @PostMapping
    public ResponseEntity<String> uploadPrescription(@RequestParam("file") MultipartFile file,
                                                     @RequestParam long appointmentId,
                                                     @RequestParam long patientId) throws IOException {
        try {
            String fileId = prescriptionService.storePrescription(file, appointmentId, patientId);
            logger.log(Level.INFO, "Prescription uploaded successfully. File ID: " + fileId);
            return ResponseEntity.ok("Prescription uploaded successfully. File ID: " + fileId);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred while uploading Prescription: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves all prescriptions.
     *
     * @return A ResponseEntity containing the list of all Prescription objects.
     */
    @GetMapping
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        try {
            List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
            logger.log(Level.INFO, "Retrieved " + prescriptions.size() + " Prescriptions");
            return ResponseEntity.ok(prescriptions);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Prescriptions");
            throw e;
        }
    }

    /**
     * Retrieves a prescription by its appointment ID.
     *
     * @param appointmentId The ID of the appointment to retrieve the prescription for.
     * @return A ResponseEntity containing the Prescription object if found, or a not found response if not found.
     */
    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<Prescription> getPrescriptionByAppointmentId(@PathVariable long appointmentId) {
        try {
            Optional<Prescription> prescription = prescriptionService.getPrescriptionByAppointmentId(appointmentId);
            if (prescription.isPresent()) {
                logger.log(Level.INFO, "Retrieved Prescription with Appointment ID: " + appointmentId);
                return ResponseEntity.ok(prescription.get());
            } else {
                logger.log(Level.INFO, "No Prescription found with Appointment ID: " + appointmentId);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Prescription with Appointment ID: " + appointmentId);
            throw e;
        }
    }

    /**
     * Retrieves all prescriptions associated with a patient.
     *
     * @param patientId The ID of the patient to retrieve prescriptions for.
     * @return A ResponseEntity containing the list of Prescription objects for the patient.
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Prescription>> getPrescriptionsByPatientId(@PathVariable long patientId) {
        try {
            List<Prescription> prescriptions = prescriptionService.getPrescriptionsByPatientId(patientId);
            logger.log(Level.INFO, "Retrieved " + prescriptions.size() + " Prescriptions for Patient ID: " + patientId);
            return ResponseEntity.ok(prescriptions);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Prescriptions for Patient ID: " + patientId);
            throw e;
        }
    }

    /**
     * Retrieves a prescription by its ID.
     *
     * @param id The ID of the prescription to retrieve.
     * @return A ResponseEntity containing the Prescription object if found, or a not found response if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable String id) {
        try {
            Optional<Prescription> prescription = prescriptionService.getPrescriptionById(id);
            if (prescription.isPresent()) {
                logger.log(Level.INFO, "Retrieved Prescription with ID: " + id);
                return ResponseEntity.ok(prescription.get());
            } else {
                logger.log(Level.INFO, "No Prescription found with ID: " + id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Prescription with ID: " + id);
            throw e;
        }
    }

    /**
     * Updates an existing prescription.
     *
     * @param id                  The ID of the prescription to be updated.
     * @param updatedPrescription The updated Prescription object.
     * @return A ResponseEntity containing the success message upon successful update.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePrescription(@PathVariable String id, @RequestBody Prescription updatedPrescription) {
        try {
            // Set the ID of the updatedPrescription to the path variable ID
            updatedPrescription.setId(id);
            prescriptionService.updatePrescription(updatedPrescription);
            logger.log(Level.INFO, "Prescription updated successfully with ID: " + id);
            return ResponseEntity.ok("Prescription updated successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating Prescription: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Deletes a prescription by its ID.
     *
     * @param id The ID of the prescription to be deleted.
     * @return A ResponseEntity containing the success message upon successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePrescription(@PathVariable String id) {
        try {
            prescriptionService.deletePrescriptionById(id);
            logger.log(Level.INFO, "Prescription deleted successfully with ID: " + id);
            return ResponseEntity.ok("Prescription deleted successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting Prescription: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Downloads a prescription by its ID.
     *
     * @param id The ID of the prescription to download.
     * @return A ResponseEntity containing the prescription file data as a byte array if found, or a not found response if not found.
     */
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadPrescription(@PathVariable String id) {
        try {
            byte[] fileData = prescriptionService.downloadPrescription(id);
            if (fileData != null) {
                Prescription prescription = prescriptionService.getPrescriptionById(id).orElse(null);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType(prescription.getFileType()));
                headers.setContentDispositionFormData("attachment", prescription.getFilename());
                return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
            } else {
                logger.log(Level.INFO, "No Prescription file found for download with ID: " + id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while downloading Prescription with ID: " + id);
            throw e;
        }
    }
}
