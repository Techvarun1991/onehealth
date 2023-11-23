package com.onehealth.service;

import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.PharmacyInvoice;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * The PharmacyInvoiceService interface provides methods to perform CRUD operations
 * on PharmacyInvoice entities and retrieve PharmacyInvoices based on orderId.
 */
public interface PharmacyInvoiceService {

    /**
     * Stores a new PharmacyInvoice in the database.
     * 
     * @param file    The MultipartFile representing the invoice file to be stored as the PharmacyInvoice.
     * @param orderId The ID of the order to which the invoice belongs.
     * @return The ID of the newly stored PharmacyInvoice.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    String storePharmacyInvoice(MultipartFile file, long orderId) throws IOException;

    /**
     * Retrieves a list of all PharmacyInvoices from the database.
     * 
     * @return A List of all PharmacyInvoices.
     */
    List<PharmacyInvoice> getAllInvoices();

    /**
     * Retrieves a PharmacyInvoice with the given orderId.
     * 
     * @param orderId The ID of the order for which the PharmacyInvoice is to be retrieved.
     * @return An Optional containing the PharmacyInvoice with the given orderId, if present; otherwise, an empty Optional.
     */
    Optional<PharmacyInvoice> getInvoiceByOrderId(long orderId);

    /**
     * Retrieves a PharmacyInvoice with the given ID.
     * 
     * @param id The ID of the PharmacyInvoice to be retrieved.
     * @return An Optional containing the PharmacyInvoice with the given ID, if present; otherwise, an empty Optional.
     */
    Optional<PharmacyInvoice> getInvoiceById(String id);

    /**
     * Updates an existing PharmacyInvoice in the database.
     * 
     * @param updatedPharmacyInvoice The updated PharmacyInvoice object with modified attributes.
     */
    void updatePharmacyInvoice(PharmacyInvoice updatedPharmacyInvoice);

    /**
     * Deletes a PharmacyInvoice with the given ID from the database.
     * 
     * @param id The ID of the PharmacyInvoice to be deleted.
     */
    void deletePharmacyInvoiceById(String id);

    /**
     * Downloads the file content of a PharmacyInvoice with the given ID.
     * 
     * @param id The ID of the PharmacyInvoice to be downloaded.
     * @return The byte array representing the file content of the PharmacyInvoice.
     */
    byte[] downloadPharmacyInvoice(String id);
}
