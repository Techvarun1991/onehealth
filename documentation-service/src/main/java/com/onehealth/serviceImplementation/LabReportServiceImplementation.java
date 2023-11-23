package com.onehealth.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.LabReport;
import com.onehealth.repository.LabReportRepository;
import com.onehealth.service.LabReportService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service implementation class that handles operations related to LabReports.
 */
@Service
public class LabReportServiceImplementation implements LabReportService {

    // Logger for logging service actions.
    private static final Logger logger = Logger.getLogger(LabReportServiceImplementation.class.getName());

    // Autowired LabReportRepository to interact with the database.
    @Autowired
    private LabReportRepository labReportRepository;

    /**
     * Stores a new LabReport in the repository.
     *
     * @param file    The MultipartFile representing the lab report file to be stored.
     * @param orderId The ID of the order associated with the lab report.
     * @return The ID of the newly stored LabReport.
     * @throws IOException If there is an I/O error while reading the file.
     */
    @Override
    public String storeLabReport(MultipartFile file, long orderId) throws IOException {
        try {
            LabReport labReport = new LabReport();
            labReport.setFilename(file.getOriginalFilename());
            labReport.setFileType(file.getContentType());
            labReport.setFileSize(Long.toString(file.getSize()));
            labReport.setFile(file.getBytes());
            labReport.setOrderId(orderId);
            labReportRepository.save(labReport);
            logger.log(Level.INFO, "LabReport stored successfully with ID: " + labReport.getId());
            return labReport.getId();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred while storing LabReport: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves all LabReports from the repository.
     *
     * @return A list of all LabReport objects in the repository.
     */
    @Override
    public List<LabReport> getAllReports() {
        try {
            logger.log(Level.INFO, "Retrieving all LabReports");
            List<LabReport> labReports = labReportRepository.findAll();
            logger.log(Level.INFO, "Retrieved " + labReports.size() + " LabReports");
            return labReports;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all LabReports");
            throw e;
        }
    }

    /**
     * Retrieves a LabReport with the given order ID from the repository.
     *
     * @param orderId The order ID associated with the LabReport to retrieve.
     * @return An Optional containing the LabReport if found, or an empty Optional if not found.
     */
    @Override
    public Optional<LabReport> getReportByOrderId(long orderId) {
        try {
            logger.log(Level.INFO, "Retrieving LabReport with Order ID: " + orderId);
            Optional<LabReport> labReportOptional = labReportRepository.findByOrderId(orderId);
            if (labReportOptional.isPresent()) {
                logger.log(Level.INFO, "Retrieved LabReport with Order ID: " + orderId);
            } else {
                logger.log(Level.INFO, "No LabReport found with Order ID: " + orderId);
            }
            return labReportOptional;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving LabReport with Order ID: " + orderId);
            throw e;
        }
    }

    /**
     * Retrieves a LabReport with the given ID from the repository.
     *
     * @param id The ID of the LabReport to retrieve.
     * @return An Optional containing the LabReport if found, or an empty Optional if not found.
     */
    @Override
    public Optional<LabReport> getReportById(String id) {
        try {
            logger.log(Level.INFO, "Retrieving LabReport with ID: " + id);
            Optional<LabReport> labReportOptional = labReportRepository.findById(id);
            if (labReportOptional.isPresent()) {
                logger.log(Level.INFO, "Retrieved LabReport with ID: " + id);
            } else {
                logger.log(Level.INFO, "No LabReport found with ID: " + id);
            }
            return labReportOptional;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving LabReport with ID: " + id);
            throw e;
        }
    }

    /**
     * Updates the attributes of an existing LabReport in the repository.
     *
     * @param updatedLabReport The modified LabReport object to update.
     */
    @Override
    public void updateLabReport(LabReport updatedLabReport) {
        try {
            logger.log(Level.INFO, "Updating LabReport with ID: " + updatedLabReport.getId());
            labReportRepository.save(updatedLabReport);
            logger.log(Level.INFO, "LabReport updated successfully with ID: " + updatedLabReport.getId());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating LabReport: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Deletes a LabReport with the given ID from the repository.
     *
     * @param id The ID of the LabReport to delete.
     */
    @Override
    public void deleteLabReportById(String id) {
        try {
            logger.log(Level.INFO, "Deleting LabReport with ID: " + id);
            labReportRepository.deleteById(id);
            logger.log(Level.INFO, "LabReport deleted successfully with ID: " + id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting LabReport: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Downloads the file associated with a LabReport with the given ID from the repository.
     *
     * @param id The ID of the LabReport to download.
     * @return The byte array representing the file associated with the LabReport, or null if not found.
     */
    @Override
    public byte[] downloadLabReport(String id) {
        try {
            logger.log(Level.INFO, "Downloading LabReport with ID: " + id);
            Optional<LabReport> labReportOptional = labReportRepository.findById(id);
            if (labReportOptional.isPresent()) {
                logger.log(Level.INFO, "Retrieved LabReport with ID: " + id);
                return labReportOptional.get().getFile();
            }
            logger.log(Level.INFO, "No LabReport found with ID: " + id);
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while downloading LabReport with ID: " + id);
            throw e;
        }
    }
}
