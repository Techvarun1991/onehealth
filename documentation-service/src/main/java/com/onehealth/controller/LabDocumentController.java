package com.onehealth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.LabDocument;
import com.onehealth.exception.DatabaseException;
import com.onehealth.service.LabDocumentService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/documentation/lab-documents")
public class LabDocumentController {

    private final LabDocumentService labDocumentService;
    private static final Logger logger = Logger.getLogger(LabDocumentController.class.getName());

    @Autowired
    public LabDocumentController(LabDocumentService labDocumentService) {
        this.labDocumentService = labDocumentService;
    }

    /**
     * Uploads a lab document.
     *
     * @param file      The file to be uploaded.
     * @param labId The ID of the lab associated with the document.
     * @return ResponseEntity with a success message and status 200 if successful.
     */
    @PostMapping
    public ResponseEntity<String> uploadLabDocument(@RequestParam("file") MultipartFile file,@RequestParam long labId) {
        try {
            String fileId = labDocumentService.storeLabDocument(file, labId);
            logger.log(Level.INFO, "Lab Document uploaded successfully. File ID: " + fileId);
            return ResponseEntity.ok("Lab Document uploaded successfully. File ID: " + fileId);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred while uploading Lab Document", e);
            return ResponseEntity.status(500).body("Error occurred while uploading Lab Document");
        }
    }

    /**
     * Retrieves all lab documents.
     *
     * @return ResponseEntity with a list of lab documents and status 200 if successful.
     */
    @GetMapping
    public ResponseEntity<?> getAllLabDocuments() {
        try {
            List<LabDocument> labDocuments = labDocumentService.getAllLabDocuments();
            logger.log(Level.INFO, "Retrieved " + labDocuments.size() + " LabDocuments");
            return ResponseEntity.ok(labDocuments);
        } catch (DatabaseException e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all LabDocuments", e);
            return ResponseEntity.status(500).body("Error occurred while retrieving Lab Documents");
        }
    }

    /**
     * Retrieves lab documents associated with a specific lab ID.
     *
     * @param labId The ID of the lab.
     * @return ResponseEntity with a list of lab documents and status 200 if successful.
     */
    @GetMapping("/{labId}")
    public ResponseEntity<?> getLabDocumentsByLabId(@PathVariable long labId) {
        try {
            List<LabDocument> labDocuments = labDocumentService.getLabDocumentsByLabId(labId);
            logger.log(Level.INFO, "Retrieved " + labDocuments.size() + " LabDocuments for lab ID: " + labId);
            return ResponseEntity.ok(labDocuments);
        } catch (DatabaseException e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving LabDocuments for lab ID: " + labId, e);
            return ResponseEntity.status(500).body("Error occurred while retrieving Lab Documents");
        }
    }

    /**
     * Retrieves a lab document by its ID.
     *
     * @param id The ID of the lab document.
     * @return ResponseEntity with a lab document and status 200 if successful.
     */
    @GetMapping("/document/{id}")
    public ResponseEntity<?> getLabDocumentById(@PathVariable String id) {
        try {
            Optional<LabDocument> labDocument = labDocumentService.getLabDocumentById(id);
            if (labDocument.isPresent()) {
                logger.log(Level.INFO, "Retrieved Lab Document with ID: " + id);
                return ResponseEntity.ok(labDocument.get());
            } else {
                logger.log(Level.INFO, "Lab Document with ID: " + id + " not found");
                return ResponseEntity.notFound().build();
            }
        } catch (DatabaseException e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Lab Document with ID: " + id, e);
            return ResponseEntity.status(500).body("Error occurred while retrieving Lab Document");
        }
    }

    /**
     * Updates a lab document.
     *
     * @param updatedLabDocument The updated lab document.
     * @return ResponseEntity with a success message and status 200 if successful.
     */
    @PutMapping("/update")
    public ResponseEntity<String> updateLabDocument(@RequestBody LabDocument updatedLabDocument) {
        try {
            labDocumentService.updateLabDocument(updatedLabDocument);
            logger.log(Level.INFO, "Updated Lab Document with ID: " + updatedLabDocument.getId());
            return ResponseEntity.ok("Lab Document updated successfully.");
        } catch (DatabaseException e) {
            logger.log(Level.SEVERE, "Error occurred while updating Lab Document", e);
            return ResponseEntity.status(500).body("Error occurred while updating Lab Document");
        }
    }

    /**
     * Deletes a lab document by its ID.
     *
     * @param id The ID of the lab document to be deleted.
     * @return ResponseEntity with a success message and status 200 if successful.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLabDocumentById(@PathVariable String id) {
        try {
            labDocumentService.deleteLabDocumentById(id);
            logger.log(Level.INFO, "Deleted Lab Document with ID: " + id);
            return ResponseEntity.ok("Lab Document deleted successfully.");
        } catch (DatabaseException e) {
            logger.log(Level.SEVERE, "Error occurred while deleting Lab Document with ID: " + id, e);
            return ResponseEntity.status(500).body("Error occurred while deleting Lab Document");
        }
    }

    /**
     * Deletes all lab documents associated with a specific lab ID.
     *
     * @param labId The ID of the lab.
     * @return ResponseEntity with a success message and status 200 if successful.
     */
    @DeleteMapping("/delete-all/{labId}")
    public ResponseEntity<String> deleteLabDocumentsByLabId(@PathVariable long labId) {
        try {
            labDocumentService.deleteLabDocumentsByLabId(labId);
            logger.log(Level.INFO, "Deleted all Lab Documents for lab ID: " + labId);
            return ResponseEntity.ok("All Lab Documents deleted successfully.");
        } catch (DatabaseException e) {
            logger.log(Level.SEVERE, "Error occurred while deleting Lab Documents for lab ID: " + labId, e);
            return ResponseEntity.status(500).body("Error occurred while deleting Lab Documents");
        }
    }
    
    /**
     * Downloads a lab document by its ID.
     *
     * @param id The ID of the document to download.
     * @return A ResponseEntity containing the document data as a byte array, or a not found response if the document is not found.
     * @throws Exception 
     */
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadLabDocument(@PathVariable String id) throws Exception {
        try {
            byte[] fileData = labDocumentService.downloadLabDocument(id);
            if (fileData != null) {
                LabDocument labDocument = labDocumentService.getLabDocumentById(id).orElse(null);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType(labDocument.getFileType()));
                headers.setContentDispositionFormData("attachment", labDocument.getFilename());
                logger.log(Level.INFO, "Retrieved Lab Document with ID: " + id + " for downloading");
                return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
            } else {
                logger.log(Level.INFO, "No Lab Document found with ID: " + id + " for downloading");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while downloading Lab Document with ID: " + id);
            throw e;
        }
    }
    
}
