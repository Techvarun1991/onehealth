package com.onehealth.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.LabInvoice;
import com.onehealth.repository.LabInvoiceRepository;
import com.onehealth.service.LabInvoiceService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service implementation class that handles operations related to LabInvoices.
 */
@Service
public class LabInvoiceServiceImplementation implements LabInvoiceService {

    // Logger for logging service actions.
    private static final Logger logger = Logger.getLogger(LabInvoiceServiceImplementation.class.getName());

    // Autowired LabInvoiceRepository to interact with the database.
    @Autowired
    private LabInvoiceRepository labInvoiceRepository;

    /**
     * Stores a new LabInvoice in the repository.
     *
     * @param file    The MultipartFile representing the lab invoice file to be stored.
     * @param orderId The ID of the order associated with the lab invoice.
     * @return The ID of the newly stored LabInvoice.
     * @throws IOException If there is an I/O error while reading the file.
     */
    @Override
    public String storeLabInvoice(MultipartFile file, long orderId) throws IOException {
        try {
            LabInvoice labInvoice = new LabInvoice();
            labInvoice.setFilename(file.getOriginalFilename());
            labInvoice.setFileType(file.getContentType());
            labInvoice.setFileSize(Long.toString(file.getSize()));
            labInvoice.setFile(file.getBytes());
            labInvoice.setOrderId(orderId);
            labInvoiceRepository.save(labInvoice);
            logger.log(Level.INFO, "LabInvoice stored successfully with ID: " + labInvoice.getId());
            return labInvoice.getId();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred while storing LabInvoice: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves all LabInvoices from the repository.
     *
     * @return A list of all LabInvoice objects in the repository.
     */
    @Override
    public List<LabInvoice> getAllInvoices() {
        try {
            logger.log(Level.INFO, "Retrieving all LabInvoices");
            List<LabInvoice> labInvoices = labInvoiceRepository.findAll();
            logger.log(Level.INFO, "Retrieved " + labInvoices.size() + " LabInvoices");
            return labInvoices;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all LabInvoices");
            throw e;
        }
    }

    /**
     * Retrieves a LabInvoice with the given order ID from the repository.
     *
     * @param orderId The order ID associated with the LabInvoice to retrieve.
     * @return An Optional containing the LabInvoice if found, or an empty Optional if not found.
     */
    @Override
    public Optional<LabInvoice> getInvoiceByOrderId(long orderId) {
        try {
            logger.log(Level.INFO, "Retrieving LabInvoice with Order ID: " + orderId);
            Optional<LabInvoice> labInvoiceOptional = labInvoiceRepository.findByOrderId(orderId);
            if (labInvoiceOptional.isPresent()) {
                logger.log(Level.INFO, "Retrieved LabInvoice with Order ID: " + orderId);
            } else {
                logger.log(Level.INFO, "No LabInvoice found with Order ID: " + orderId);
            }
            return labInvoiceOptional;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving LabInvoice with Order ID: " + orderId);
            throw e;
        }
    }

    /**
     * Retrieves a LabInvoice with the given ID from the repository.
     *
     * @param id The ID of the LabInvoice to retrieve.
     * @return An Optional containing the LabInvoice if found, or an empty Optional if not found.
     */
    @Override
    public Optional<LabInvoice> getInvoiceById(String id) {
        try {
            logger.log(Level.INFO, "Retrieving LabInvoice with ID: " + id);
            Optional<LabInvoice> labInvoiceOptional = labInvoiceRepository.findById(id);
            if (labInvoiceOptional.isPresent()) {
                logger.log(Level.INFO, "Retrieved LabInvoice with ID: " + id);
            } else {
                logger.log(Level.INFO, "No LabInvoice found with ID: " + id);
            }
            return labInvoiceOptional;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving LabInvoice with ID: " + id);
            throw e;
        }
    }

    /**
     * Updates the attributes of an existing LabInvoice in the repository.
     *
     * @param updatedLabInvoice The modified LabInvoice object to update.
     */
    @Override
    public void updateLabInvoice(LabInvoice updatedLabInvoice) {
        try {
            logger.log(Level.INFO, "Updating LabInvoice with ID: " + updatedLabInvoice.getId());
            labInvoiceRepository.save(updatedLabInvoice);
            logger.log(Level.INFO, "LabInvoice updated successfully with ID: " + updatedLabInvoice.getId());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating LabInvoice: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Deletes a LabInvoice with the given ID from the repository.
     *
     * @param id The ID of the LabInvoice to delete.
     */
    @Override
    public void deleteLabInvoiceById(String id) {
        try {
            logger.log(Level.INFO, "Deleting LabInvoice with ID: " + id);
            labInvoiceRepository.deleteById(id);
            logger.log(Level.INFO, "LabInvoice deleted successfully with ID: " + id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting LabInvoice: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Downloads the content of a LabInvoice with the given ID from the repository.
     *
     * @param id The ID of the LabInvoice to download.
     * @return The byte array representing the content of the LabInvoice, or null if not found.
     */
    @Override
    public byte[] downloadLabInvoice(String id) {
        try {
            logger.log(Level.INFO, "Downloading LabInvoice with ID: " + id);
            Optional<LabInvoice> labInvoiceOptional = labInvoiceRepository.findById(id);
            if (labInvoiceOptional.isPresent()) {
                logger.log(Level.INFO, "Retrieved LabInvoice with ID: " + id);
                return labInvoiceOptional.get().getFile();
            }
            logger.log(Level.INFO, "No LabInvoice found with ID: " + id);
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while downloading LabInvoice with ID: " + id);
            throw e;
        }
    }
}
