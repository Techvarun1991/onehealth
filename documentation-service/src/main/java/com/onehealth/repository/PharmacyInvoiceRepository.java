package com.onehealth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.onehealth.entity.PharmacyInvoice;

import java.util.Optional;

/**
 * The PharmacyInvoiceRepository interface extends the MongoRepository interface
 * to perform CRUD operations on the "pharmacy_invoices" collection in MongoDB.
 */
public interface PharmacyInvoiceRepository extends MongoRepository<PharmacyInvoice, String> {

    /**
     * Finds and returns an Optional containing a PharmacyInvoice associated with the specified orderId.
     * If a PharmacyInvoice with the given orderId exists, it will be present in the Optional.
     * Otherwise, the Optional will be empty.
     *
     * @param orderId The ID of the order for which the PharmacyInvoice is to be retrieved.
     * @return An Optional containing the PharmacyInvoice associated with the specified orderId,
     *         or an empty Optional if no PharmacyInvoice with the orderId exists.
     */
    Optional<PharmacyInvoice> findByOrderId(long orderId);
}
