package com.onehealth.service;

import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.LabReport;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * The LabReportService interface provides methods to perform CRUD operations
 * on LabReport entities and retrieve LabReports based on orderId.
 */
public interface LabReportService {

    /**
     * Stores a new LabReport in the database.
     * 
     * @param file    The MultipartFile representing the report file to be stored as the LabReport.
     * @param orderId The ID of the order to which the report belongs.
     * @return The ID of the newly stored LabReport.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    String storeLabReport(MultipartFile file, long orderId) throws IOException;

    /**
     * Retrieves a list of all LabReports from the database.
     * 
     * @return A List of all LabReports.
     */
    List<LabReport> getAllReports();

    /**
     * Retrieves a LabReport with the given orderId.
     * 
     * @param orderId The ID of the order for which the LabReport is to be retrieved.
     * @return An Optional containing the LabReport with the given orderId, if present; otherwise, an empty Optional.
     */
    Optional<LabReport> getReportByOrderId(long orderId);

    /**
     * Retrieves a LabReport with the given ID.
     * 
     * @param id The ID of the LabReport to be retrieved.
     * @return An Optional containing the LabReport with the given ID, if present; otherwise, an empty Optional.
     */
    Optional<LabReport> getReportById(String id);

    /**
     * Updates an existing LabReport in the database.
     * 
     * @param updatedLabReport The updated LabReport object with modified attributes.
     */
    void updateLabReport(LabReport updatedLabReport);

    /**
     * Deletes a LabReport with the given ID from the database.
     * 
     * @param id The ID of the LabReport to be deleted.
     */
    void deleteLabReportById(String id);

    /**
     * Downloads the file content of a LabReport with the given ID.
     * 
     * @param id The ID of the LabReport to be downloaded.
     * @return The byte array representing the file content of the LabReport.
     */
    byte[] downloadLabReport(String id);
}
