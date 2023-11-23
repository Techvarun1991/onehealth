package com.onehealth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.DoctorDocument;
import com.onehealth.service.DoctorDocumentService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The DoctorDocumentController class handles HTTP requests related to doctor documents.
 * It defines various endpoints for uploading, updating, and deleting doctor documents,
 * as well as downloading the document files.
 */
@RestController
@RequestMapping("api/documentation/doctor-documents")
public class DoctorDocumentController {
    
    private static final Logger logger = Logger.getLogger(DoctorDocumentController.class.getName());

    @Autowired
    private DoctorDocumentService doctorDocumentService;

    /**
     * Uploads a new document for a doctor.
     *
     * @param file     The MultipartFile representing the document file to upload.
     * @param doctorId The ID of the doctor associated with the document.
     * @return A ResponseEntity containing the success message and the file ID of the uploaded document.
     * @throws IOException If an I/O error occurs while storing the document.
     */
    @PostMapping
    public ResponseEntity<String> uploadDoctorDocument(@RequestParam("file") MultipartFile file,
                                                       @RequestParam long doctorId) throws IOException {
        try {
            String fileId = doctorDocumentService.storeDoctorDocument(file, doctorId);
            logger.log(Level.INFO, "Doctor Document uploaded successfully. File ID: " + fileId);
            return ResponseEntity.ok("Doctor Document uploaded successfully. File ID: " + fileId);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred while uploading Doctor Document: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves all documents associated with a specific doctor ID.
     *
     * @param doctorId The ID of the doctor to retrieve documents for.
     * @return A ResponseEntity containing the list of DoctorDocument objects associated with the given doctor ID.
     */
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<DoctorDocument>> getAllDoctorDocumentsByDoctorId(@PathVariable long doctorId) {
        try {
            List<DoctorDocument> doctorDocuments = doctorDocumentService.getAllDoctorDocumentsByDoctorId(doctorId);
            logger.log(Level.INFO, "Retrieved " + doctorDocuments.size() + " Doctor Documents for Doctor ID: " + doctorId);
            return ResponseEntity.ok(doctorDocuments);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Doctor Documents for Doctor ID: " + doctorId);
            throw e;
        }
    }

    /**
     * Retrieves a document by its ID.
     *
     * @param id The ID of the document to retrieve.
     * @return A ResponseEntity containing the DoctorDocument object if found, or a not found response if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDocument> getDoctorDocumentById(@PathVariable String id) {
        try {
            Optional<DoctorDocument> doctorDocument = doctorDocumentService.getDoctorDocumentById(id);
            if (doctorDocument.isPresent()) {
                logger.log(Level.INFO, "Retrieved Doctor Document with ID: " + id);
                return ResponseEntity.ok(doctorDocument.get());
            } else {
                logger.log(Level.INFO, "No Doctor Document found with ID: " + id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Doctor Document with ID: " + id);
            throw e;
        }
    }

    /**
     * Updates an existing document.
     *
     * @param id                  The ID of the document to be updated.
     * @param updatedDoctorDocument The updated DoctorDocument object.
     * @return A ResponseEntity containing the success message upon successful update.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateDoctorDocument(@PathVariable String id, @RequestBody DoctorDocument updatedDoctorDocument) {
        try {
            // Set the ID of the updatedDoctorDocument to the path variable ID
            updatedDoctorDocument.setId(id);
            doctorDocumentService.updateDoctorDocument(updatedDoctorDocument);
            logger.log(Level.INFO, "Doctor Document updated successfully with ID: " + id);
            return ResponseEntity.ok("Doctor Document updated successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating Doctor Document: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Deletes a document by its ID.
     *
     * @param id The ID of the document to be deleted.
     * @return A ResponseEntity containing the success message upon successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctorDocument(@PathVariable String id) {
        try {
            doctorDocumentService.deleteDoctorDocumentById(id);
            logger.log(Level.INFO, "Doctor Document deleted successfully with ID: " + id);
            return ResponseEntity.ok("Doctor Document deleted successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting Doctor Document: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Downloads a document by its ID.
     *
     * @param id The ID of the document to download.
     * @return A ResponseEntity containing the document data as a byte array, or a not found response if the document is not found.
     */
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadDoctorDocument(@PathVariable String id) {
        try {
            byte[] fileData = doctorDocumentService.downloadDoctorDocument(id);
            if (fileData != null) {
                DoctorDocument doctorDocument = doctorDocumentService.getDoctorDocumentById(id).orElse(null);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType(doctorDocument.getFileType()));
                headers.setContentDispositionFormData("attachment", doctorDocument.getFilename());
                logger.log(Level.INFO, "Retrieved Doctor Document with ID: " + id + " for downloading");
                return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
            } else {
                logger.log(Level.INFO, "No Doctor Document found with ID: " + id + " for downloading");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while downloading Doctor Document with ID: " + id);
            throw e;
        }
    }
}
