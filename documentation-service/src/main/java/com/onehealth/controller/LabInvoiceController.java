package com.onehealth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.LabInvoice;
import com.onehealth.service.LabInvoiceService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The LabInvoiceController class handles HTTP requests related to lab invoices.
 * It defines various endpoints for uploading, updating, and deleting lab invoices,
 * as well as downloading the invoice files.
 */
@RestController
@RequestMapping("api/documentation/lab-invoices")
public class LabInvoiceController {

    private static final Logger logger = Logger.getLogger(LabInvoiceController.class.getName());

    @Autowired
    private LabInvoiceService labInvoiceService;

    /**
     * Uploads a new lab invoice.
     *
     * @param file    The MultipartFile representing the invoice file to upload.
     * @param orderId The ID of the order associated with the invoice.
     * @return A ResponseEntity containing the success message and the file ID of the uploaded invoice.
     * @throws IOException If an I/O error occurs while storing the invoice.
     */
    @PostMapping
    public ResponseEntity<String> uploadLabInvoice(@RequestParam("file") MultipartFile file,
                                                   @RequestParam long orderId) throws IOException {
        try {
            String fileId = labInvoiceService.storeLabInvoice(file, orderId);
            logger.log(Level.INFO, "Lab Invoice uploaded successfully. File ID: " + fileId);
            return ResponseEntity.ok("Lab Invoice uploaded successfully. File ID: " + fileId);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred while uploading Lab Invoice: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves all lab invoices.
     *
     * @return A ResponseEntity containing the list of all LabInvoice objects.
     */
    @GetMapping
    public ResponseEntity<List<LabInvoice>> getAllInvoices() {
        try {
            List<LabInvoice> labInvoices = labInvoiceService.getAllInvoices();
            logger.log(Level.INFO, "Retrieved " + labInvoices.size() + " Lab Invoices");
            return ResponseEntity.ok(labInvoices);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Lab Invoices");
            throw e;
        }
    }

    /**
     * Retrieves a lab invoice by its order ID.
     *
     * @param orderId The ID of the order to retrieve the invoice for.
     * @return A ResponseEntity containing the LabInvoice object if found, or a not found response if not found.
     */
    @GetMapping("/order/{orderId}")
    public ResponseEntity<LabInvoice> getInvoiceByOrderId(@PathVariable long orderId) {
        try {
            Optional<LabInvoice> labInvoice = labInvoiceService.getInvoiceByOrderId(orderId);
            if (labInvoice.isPresent()) {
                logger.log(Level.INFO, "Retrieved Lab Invoice with Order ID: " + orderId);
                return ResponseEntity.ok(labInvoice.get());
            } else {
                logger.log(Level.INFO, "No Lab Invoice found with Order ID: " + orderId);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Lab Invoice with Order ID: " + orderId);
            throw e;
        }
    }

    /**
     * Retrieves a lab invoice by its ID.
     *
     * @param id The ID of the invoice to retrieve.
     * @return A ResponseEntity containing the LabInvoice object if found, or a not found response if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LabInvoice> getInvoiceById(@PathVariable String id) {
        try {
            Optional<LabInvoice> labInvoice = labInvoiceService.getInvoiceById(id);
            if (labInvoice.isPresent()) {
                logger.log(Level.INFO, "Retrieved Lab Invoice with ID: " + id);
                return ResponseEntity.ok(labInvoice.get());
            } else {
                logger.log(Level.INFO, "No Lab Invoice found with ID: " + id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Lab Invoice with ID: " + id);
            throw e;
        }
    }

    /**
     * Updates an existing lab invoice.
     *
     * @param id                  The ID of the invoice to be updated.
     * @param updatedLabInvoice The updated LabInvoice object.
     * @return A ResponseEntity containing the success message upon successful update.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateLabInvoice(@PathVariable String id, @RequestBody LabInvoice updatedLabInvoice) {
        try {
            // Set the ID of the updatedLabInvoice to the path variable ID
            updatedLabInvoice.setId(id);
            labInvoiceService.updateLabInvoice(updatedLabInvoice);
            logger.log(Level.INFO, "Lab Invoice updated successfully with ID: " + id);
            return ResponseEntity.ok("Lab Invoice updated successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating Lab Invoice: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Deletes a lab invoice by its ID.
     *
     * @param id The ID of the invoice to be deleted.
     * @return A ResponseEntity containing the success message upon successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLabInvoice(@PathVariable String id) {
        try {
            labInvoiceService.deleteLabInvoiceById(id);
            logger.log(Level.INFO, "Lab Invoice deleted successfully with ID: " + id);
            return ResponseEntity.ok("Lab Invoice deleted successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting Lab Invoice: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Downloads a lab invoice by its ID.
     *
     * @param id The ID of the invoice to download.
     * @return A ResponseEntity containing the invoice data as a byte array, or a not found response if the invoice is not found.
     */
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadLabInvoice(@PathVariable String id) {
        try {
            byte[] fileData = labInvoiceService.downloadLabInvoice(id);
            if (fileData != null) {
                LabInvoice labInvoice = labInvoiceService.getInvoiceById(id).orElse(null);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType(labInvoice.getFileType()));
                headers.setContentDispositionFormData("attachment", labInvoice.getFilename());
                logger.log(Level.INFO, "Retrieved Lab Invoice with ID: " + id + " for downloading");
                return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
            } else {
                logger.log(Level.INFO, "No Lab Invoice found with ID: " + id + " for downloading");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while downloading Lab Invoice with ID: " + id);
            throw e;
        }
    }
}
