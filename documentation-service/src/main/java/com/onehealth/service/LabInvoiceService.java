package com.onehealth.service;

import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.LabInvoice;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * The LabInvoiceService interface provides methods to perform CRUD operations
 * on LabInvoice entities and retrieve LabInvoices based on orderId.
 */
public interface LabInvoiceService {

    /**
     * Stores a new LabInvoice in the database.
     * 
     * @param file    The MultipartFile representing the invoice file to be stored as the LabInvoice.
     * @param orderId The ID of the order to which the invoice belongs.
     * @return The ID of the newly stored LabInvoice.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    String storeLabInvoice(MultipartFile file, long orderId) throws IOException;

    /**
     * Retrieves a list of all LabInvoices from the database.
     * 
     * @return A List of all LabInvoices.
     */
    List<LabInvoice> getAllInvoices();

    /**
     * Retrieves a LabInvoice with the given orderId.
     * 
     * @param orderId The ID of the order for which the LabInvoice is to be retrieved.
     * @return An Optional containing the LabInvoice with the given orderId, if present; otherwise, an empty Optional.
     */
    Optional<LabInvoice> getInvoiceByOrderId(long orderId);

    /**
     * Retrieves a LabInvoice with the given ID.
     * 
     * @param id The ID of the LabInvoice to be retrieved.
     * @return An Optional containing the LabInvoice with the given ID, if present; otherwise, an empty Optional.
     */
    Optional<LabInvoice> getInvoiceById(String id);

    /**
     * Updates an existing LabInvoice in the database.
     * 
     * @param updatedLabInvoice The updated LabInvoice object with modified attributes.
     */
    void updateLabInvoice(LabInvoice updatedLabInvoice);

    /**
     * Deletes a LabInvoice with the given ID from the database.
     * 
     * @param id The ID of the LabInvoice to be deleted.
     */
    void deleteLabInvoiceById(String id);

    /**
     * Downloads the file content of a LabInvoice with the given ID.
     * 
     * @param id The ID of the LabInvoice to be downloaded.
     * @return The byte array representing the file content of the LabInvoice.
     */
    byte[] downloadLabInvoice(String id);
}
