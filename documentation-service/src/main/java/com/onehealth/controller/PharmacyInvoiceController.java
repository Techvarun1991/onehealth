package com.onehealth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.PharmacyInvoice;
import com.onehealth.service.PharmacyInvoiceService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The PharmacyInvoiceController class handles HTTP requests related to pharmacy invoices.
 * It defines various endpoints for uploading, updating, and deleting pharmacy invoices,
 * as well as downloading the invoice files.
 */
@RestController
@RequestMapping("api/documentation/pharmacy-invoices")
public class PharmacyInvoiceController {

    private static final Logger logger = Logger.getLogger(PharmacyInvoiceController.class.getName());

    @Autowired
    private PharmacyInvoiceService pharmacyInvoiceService;

    /**
     * Uploads a new pharmacy invoice.
     *
     * @param file    The MultipartFile representing the invoice file to upload.
     * @param orderId The ID of the order associated with the invoice.
     * @return A ResponseEntity containing the success message and the file ID of the uploaded invoice.
     * @throws IOException If an I/O error occurs while storing the invoice.
     */
    @PostMapping
    public ResponseEntity<String> uploadPharmacyInvoice(@RequestParam("file") MultipartFile file,
                                                        @RequestParam long orderId) throws IOException {
        try {
            String fileId = pharmacyInvoiceService.storePharmacyInvoice(file, orderId);
            logger.log(Level.INFO, "Pharmacy Invoice uploaded successfully. File ID: " + fileId);
            return ResponseEntity.ok("Pharmacy Invoice uploaded successfully. File ID: " + fileId);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred while uploading Pharmacy Invoice: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves all pharmacy invoices.
     *
     * @return A ResponseEntity containing the list of all PharmacyInvoice objects.
     */
    @GetMapping
    public ResponseEntity<List<PharmacyInvoice>> getAllInvoices() {
        try {
            List<PharmacyInvoice> pharmacyInvoices = pharmacyInvoiceService.getAllInvoices();
            logger.log(Level.INFO, "Retrieved " + pharmacyInvoices.size() + " Pharmacy Invoices");
            return ResponseEntity.ok(pharmacyInvoices);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Pharmacy Invoices");
            throw e;
        }
    }

    /**
     * Retrieves a pharmacy invoice by its order ID.
     *
     * @param orderId The ID of the order to retrieve the invoice for.
     * @return A ResponseEntity containing the PharmacyInvoice object if found, or a not found response if not found.
     */
    @GetMapping("/order/{orderId}")
    public ResponseEntity<PharmacyInvoice> getInvoiceByOrderId(@PathVariable long orderId) {
        try {
            Optional<PharmacyInvoice> pharmacyInvoice = pharmacyInvoiceService.getInvoiceByOrderId(orderId);
            if (pharmacyInvoice.isPresent()) {
                logger.log(Level.INFO, "Retrieved Pharmacy Invoice with Order ID: " + orderId);
                return ResponseEntity.ok(pharmacyInvoice.get());
            } else {
                logger.log(Level.INFO, "No Pharmacy Invoice found with Order ID: " + orderId);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Pharmacy Invoice with Order ID: " + orderId);
            throw e;
        }
    }

    /**
     * Retrieves a pharmacy invoice by its ID.
     *
     * @param id The ID of the invoice to retrieve.
     * @return A ResponseEntity containing the PharmacyInvoice object if found, or a not found response if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PharmacyInvoice> getInvoiceById(@PathVariable String id) {
        try {
            Optional<PharmacyInvoice> pharmacyInvoice = pharmacyInvoiceService.getInvoiceById(id);
            if (pharmacyInvoice.isPresent()) {
                logger.log(Level.INFO, "Retrieved Pharmacy Invoice with ID: " + id);
                return ResponseEntity.ok(pharmacyInvoice.get());
            } else {
                logger.log(Level.INFO, "No Pharmacy Invoice found with ID: " + id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Pharmacy Invoice with ID: " + id);
            throw e;
        }
    }

    /**
     * Updates an existing pharmacy invoice.
     *
     * @param id                  The ID of the invoice to be updated.
     * @param updatedPharmacyInvoice The updated PharmacyInvoice object.
     * @return A ResponseEntity containing the success message upon successful update.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePharmacyInvoice(@PathVariable String id, @RequestBody PharmacyInvoice updatedPharmacyInvoice) {
        try {
            // Set the ID of the updatedPharmacyInvoice to the path variable ID
            updatedPharmacyInvoice.setId(id);
            pharmacyInvoiceService.updatePharmacyInvoice(updatedPharmacyInvoice);
            logger.log(Level.INFO, "Pharmacy Invoice updated successfully with ID: " + id);
            return ResponseEntity.ok("Pharmacy Invoice updated successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating Pharmacy Invoice: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Deletes a pharmacy invoice by its ID.
     *
     * @param id The ID of the invoice to be deleted.
     * @return A ResponseEntity containing the success message upon successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePharmacyInvoice(@PathVariable String id) {
        try {
            pharmacyInvoiceService.deletePharmacyInvoiceById(id);
            logger.log(Level.INFO, "Pharmacy Invoice deleted successfully with ID: " + id);
            return ResponseEntity.ok("Pharmacy Invoice deleted successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting Pharmacy Invoice: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Downloads a pharmacy invoice by its ID.
     *
     * @param id The ID of the invoice to download.
     * @return A ResponseEntity containing the invoice data as a byte array, or a not found response if the invoice is not found.
     */
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadPharmacyInvoice(@PathVariable String id) {
        try {
            byte[] fileData = pharmacyInvoiceService.downloadPharmacyInvoice(id);
            if (fileData != null) {
                PharmacyInvoice pharmacyInvoice = pharmacyInvoiceService.getInvoiceById(id).orElse(null);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType(pharmacyInvoice.getFileType()));
                headers.setContentDispositionFormData("attachment", pharmacyInvoice.getFilename());
                logger.log(Level.INFO, "Retrieved Pharmacy Invoice with ID: " + id + " for downloading");
                return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
            } else {
                logger.log(Level.INFO, "No Pharmacy Invoice found with ID: " + id + " for downloading");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while downloading Pharmacy Invoice with ID: " + id);
            throw e;
        }
    }
}
