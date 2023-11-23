package com.onehealth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.onehealth.entity.LabInvoice;

/**
 * The LabInvoiceRepository interface extends the MongoRepository interface
 * to perform CRUD operations on the "lab_invoices" collection in MongoDB.
 */
public interface LabInvoiceRepository extends MongoRepository<LabInvoice, String> {

    /**
     * Finds and returns an Optional containing a LabInvoice associated with the specified orderId.
     * If a LabInvoice with the given orderId exists, it will be present in the Optional.
     * Otherwise, the Optional will be empty.
     *
     * @param orderId The ID of the order for which the LabInvoice is to be retrieved.
     * @return An Optional containing the LabInvoice associated with the specified orderId,
     *         or an empty Optional if no LabInvoice with the orderId exists.
     */
    Optional<LabInvoice> findByOrderId(long orderId);
}
