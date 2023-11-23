package com.onehealth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.LabReport;
import com.onehealth.service.LabReportService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The LabReportController class handles HTTP requests related to lab reports.
 * It defines various endpoints for uploading, updating, and deleting lab reports,
 * as well as downloading the report files.
 */
@RestController
@RequestMapping("api/documentation/lab-reports")
public class LabReportController {

    private static final Logger logger = Logger.getLogger(LabReportController.class.getName());

    @Autowired
    private LabReportService labReportService;

    /**
     * Uploads a new lab report.
     *
     * @param file    The MultipartFile representing the report file to upload.
     * @param orderId The ID of the order associated with the report.
     * @return A ResponseEntity containing the success message and the file ID of the uploaded report.
     * @throws IOException If an I/O error occurs while storing the report.
     */
    @PostMapping
    public ResponseEntity<String> uploadLabReport(@RequestParam("file") MultipartFile file,
                                                  @RequestParam long orderId) throws IOException {
        try {
            String fileId = labReportService.storeLabReport(file, orderId);
            logger.log(Level.INFO, "Lab Report uploaded successfully. File ID: " + fileId);
            return ResponseEntity.ok("Lab Report uploaded successfully. File ID: " + fileId);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred while uploading Lab Report: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves all lab reports.
     *
     * @return A ResponseEntity containing the list of all LabReport objects.
     */
    @GetMapping
    public ResponseEntity<List<LabReport>> getAllReports() {
        try {
            List<LabReport> labReports = labReportService.getAllReports();
            logger.log(Level.INFO, "Retrieved " + labReports.size() + " Lab Reports");
            return ResponseEntity.ok(labReports);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Lab Reports");
            throw e;
        }
    }

    /**
     * Retrieves a lab report by its order ID.
     *
     * @param orderId The ID of the order to retrieve the report for.
     * @return A ResponseEntity containing the LabReport object if found, or a not found response if not found.
     */
    @GetMapping("/order/{orderId}")
    public ResponseEntity<LabReport> getReportByOrderId(@PathVariable long orderId) {
        try {
            Optional<LabReport> labReport = labReportService.getReportByOrderId(orderId);
            if (labReport.isPresent()) {
                logger.log(Level.INFO, "Retrieved Lab Report with Order ID: " + orderId);
                return ResponseEntity.ok(labReport.get());
            } else {
                logger.log(Level.INFO, "No Lab Report found with Order ID: " + orderId);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Lab Report with Order ID: " + orderId);
            throw e;
        }
    }

    /**
     * Retrieves a lab report by its ID.
     *
     * @param id The ID of the report to retrieve.
     * @return A ResponseEntity containing the LabReport object if found, or a not found response if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LabReport> getReportById(@PathVariable String id) {
        try {
            Optional<LabReport> labReport = labReportService.getReportById(id);
            if (labReport.isPresent()) {
                logger.log(Level.INFO, "Retrieved Lab Report with ID: " + id);
                return ResponseEntity.ok(labReport.get());
            } else {
                logger.log(Level.INFO, "No Lab Report found with ID: " + id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Lab Report with ID: " + id);
            throw e;
        }
    }

    /**
     * Updates an existing lab report.
     *
     * @param id                  The ID of the report to be updated.
     * @param updatedLabReport The updated LabReport object.
     * @return A ResponseEntity containing the success message upon successful update.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateLabReport(@PathVariable String id, @RequestBody LabReport updatedLabReport) {
        try {
            // Set the ID of the updatedLabReport to the path variable ID
            updatedLabReport.setId(id);
            labReportService.updateLabReport(updatedLabReport);
            logger.log(Level.INFO, "Lab Report updated successfully with ID: " + id);
            return ResponseEntity.ok("Lab Report updated successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating Lab Report: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Deletes a lab report by its ID.
     *
     * @param id The ID of the report to be deleted.
     * @return A ResponseEntity containing the success message upon successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLabReport(@PathVariable String id) {
        try {
            labReportService.deleteLabReportById(id);
            logger.log(Level.INFO, "Lab Report deleted successfully with ID: " + id);
            return ResponseEntity.ok("Lab Report deleted successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting Lab Report: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Downloads a lab report by its ID.
     *
     * @param id The ID of the report to download.
     * @return A ResponseEntity containing the report data as a byte array, or a not found response if the report is not found.
     */
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadLabReport(@PathVariable String id) {
        try {
            byte[] fileData = labReportService.downloadLabReport(id);
            if (fileData != null) {
                LabReport labReport = labReportService.getReportById(id).orElse(null);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType(labReport.getFileType()));
                headers.setContentDispositionFormData("attachment", labReport.getFilename());
                logger.log(Level.INFO, "Retrieved Lab Report with ID: " + id + " for downloading");
                return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
            } else {
                logger.log(Level.INFO, "No Lab Report found with ID: " + id + " for downloading");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while downloading Lab Report with ID: " + id);
            throw e;
        }
    }
}
