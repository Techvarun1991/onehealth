package com.onehealth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.onehealth.entity.LabReport;

import java.util.Optional;

/**
 * The LabReportRepository interface extends the MongoRepository interface
 * to perform CRUD operations on the "lab_reports" collection in MongoDB.
 */
public interface LabReportRepository extends MongoRepository<LabReport, String> {

    /**
     * Finds and returns an Optional containing a LabReport associated with the specified orderId.
     * If a LabReport with the given orderId exists, it will be present in the Optional.
     * Otherwise, the Optional will be empty.
     *
     * @param orderId The ID of the order for which the LabReport is to be retrieved.
     * @return An Optional containing the LabReport associated with the specified orderId,
     *         or an empty Optional if no LabReport with the orderId exists.
     */
    Optional<LabReport> findByOrderId(long orderId);
}
