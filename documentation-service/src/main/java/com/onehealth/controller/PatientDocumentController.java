package com.onehealth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.PatientDocument;
import com.onehealth.entity.PatientDocument;
import com.onehealth.exception.DatabaseException;
import com.onehealth.service.PatientDocumentService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/documentation/patient-documents")
public class PatientDocumentController {

    private final PatientDocumentService patientDocumentService;
    private static final Logger logger = Logger.getLogger(PatientDocumentController.class.getName());

    @Autowired
    public PatientDocumentController(PatientDocumentService patientDocumentService) {
        this.patientDocumentService = patientDocumentService;
    }

    /**
     * Uploads a patient document.
     *
     * @param file      The file to be uploaded.
     * @param patientId The ID of the patient associated with the document.
     * @return ResponseEntity with a success message and status 200 if successful.
     */
    @PostMapping
    public ResponseEntity<String> uploadPatientDocument(@RequestParam("file") MultipartFile file,
                                                       @RequestParam long patientId) {
        try {
            String fileId = patientDocumentService.storePatientDocument(file, patientId);
            logger.log(Level.INFO, "Patient Document uploaded successfully. File ID: " + fileId);
            return ResponseEntity.ok("Patient Document uploaded successfully. File ID: " + fileId);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred while uploading Patient Document", e);
            return ResponseEntity.status(500).body("Error occurred while uploading Patient Document");
        }
    }

    /**
     * Retrieves all patient documents.
     *
     * @return ResponseEntity with a list of patient documents and status 200 if successful.
     */
    @GetMapping
    public ResponseEntity<?> getAllPatientDocuments() {
        try {
            List<PatientDocument> patientDocuments = patientDocumentService.getAllPatientDocuments();
            logger.log(Level.INFO, "Retrieved " + patientDocuments.size() + " PatientDocuments");
            return ResponseEntity.ok(patientDocuments);
        } catch (DatabaseException e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all PatientDocuments", e);
            return ResponseEntity.status(500).body("Error occurred while retrieving Patient Documents");
        }
    }

    /**
     * Retrieves patient documents associated with a specific patient ID.
     *
     * @param patientId The ID of the patient.
     * @return ResponseEntity with a list of patient documents and status 200 if successful.
     */
    @GetMapping("/{patientId}")
    public ResponseEntity<?> getPatientDocumentsByPatientId(@PathVariable long patientId) {
        try {
            List<PatientDocument> patientDocuments = patientDocumentService.getPatientDocumentsByPatientId(patientId);
            logger.log(Level.INFO, "Retrieved " + patientDocuments.size() + " PatientDocuments for patient ID: " + patientId);
            return ResponseEntity.ok(patientDocuments);
        } catch (DatabaseException e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving PatientDocuments for patient ID: " + patientId, e);
            return ResponseEntity.status(500).body("Error occurred while retrieving Patient Documents");
        }
    }

    /**
     * Retrieves a patient document by its ID.
     *
     * @param id The ID of the patient document.
     * @return ResponseEntity with a patient document and status 200 if successful.
     */
    @GetMapping("/document/{id}")
    public ResponseEntity<?> getPatientDocumentById(@PathVariable String id) {
        try {
            Optional<PatientDocument> patientDocument = patientDocumentService.getPatientDocumentById(id);
            if (patientDocument.isPresent()) {
                logger.log(Level.INFO, "Retrieved Patient Document with ID: " + id);
                return ResponseEntity.ok(patientDocument.get());
            } else {
                logger.log(Level.INFO, "Patient Document with ID: " + id + " not found");
                return ResponseEntity.notFound().build();
            }
        } catch (DatabaseException e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Patient Document with ID: " + id, e);
            return ResponseEntity.status(500).body("Error occurred while retrieving Patient Document");
        }
    }

    /**
     * Updates a patient document.
     *
     * @param updatedPatientDocument The updated patient document.
     * @return ResponseEntity with a success message and status 200 if successful.
     */
    @PutMapping("/update")
    public ResponseEntity<String> updatePatientDocument(@RequestBody PatientDocument updatedPatientDocument) {
        try {
            patientDocumentService.updatePatientDocument(updatedPatientDocument);
            logger.log(Level.INFO, "Updated Patient Document with ID: " + updatedPatientDocument.getId());
            return ResponseEntity.ok("Patient Document updated successfully.");
        } catch (DatabaseException e) {
            logger.log(Level.SEVERE, "Error occurred while updating Patient Document", e);
            return ResponseEntity.status(500).body("Error occurred while updating Patient Document");
        }
    }

    /**
     * Deletes a patient document by its ID.
     *
     * @param id The ID of the patient document to be deleted.
     * @return ResponseEntity with a success message and status 200 if successful.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatientDocumentById(@PathVariable String id) {
        try {
            patientDocumentService.deletePatientDocumentById(id);
            logger.log(Level.INFO, "Deleted Patient Document with ID: " + id);
            return ResponseEntity.ok("Patient Document deleted successfully.");
        } catch (DatabaseException e) {
            logger.log(Level.SEVERE, "Error occurred while deleting Patient Document with ID: " + id, e);
            return ResponseEntity.status(500).body("Error occurred while deleting Patient Document");
        }
    }

    /**
     * Deletes all patient documents associated with a specific patient ID.
     *
     * @param patientId The ID of the patient.
     * @return ResponseEntity with a success message and status 200 if successful.
     */
    @DeleteMapping("/delete-all/{patientId}")
    public ResponseEntity<String> deletePatientDocumentsByPatientId(@PathVariable long patientId) {
        try {
            patientDocumentService.deletePatientDocumentsByPatientId(patientId);
            logger.log(Level.INFO, "Deleted all Patient Documents for patient ID: " + patientId);
            return ResponseEntity.ok("All Patient Documents deleted successfully.");
        } catch (DatabaseException e) {
            logger.log(Level.SEVERE, "Error occurred while deleting Patient Documents for patient ID: " + patientId, e);
            return ResponseEntity.status(500).body("Error occurred while deleting Patient Documents");
        }
    }
    
    
    /**
     * Downloads a patient document by its ID.
     *
     * @param id The ID of the document to download.
     * @return A ResponseEntity containing the document data as a byte array, or a not found response if the document is not found.
     * @throws Exception 
     */
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadPatientDocument(@PathVariable String id) throws Exception {
        try {
            byte[] fileData = patientDocumentService.downloadPatientDocument(id);
            if (fileData != null) {
                PatientDocument patientDocument = patientDocumentService.getPatientDocumentById(id).orElse(null);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType(patientDocument.getFileType()));
                headers.setContentDispositionFormData("attachment", patientDocument.getFilename());
                logger.log(Level.INFO, "Retrieved Patient Document with ID: " + id + " for downloading");
                return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
            } else {
                logger.log(Level.INFO, "No Patient Document found with ID: " + id + " for downloading");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while downloading Patient Document with ID: " + id);
            throw e;
        }
    }
}
