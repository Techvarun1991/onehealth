package com.onehealth.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.PharmacyInvoice;
import com.onehealth.repository.PharmacyInvoiceRepository;
import com.onehealth.service.PharmacyInvoiceService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service implementation class that handles operations related to PharmacyInvoices.
 */
@Service
public class PharmacyInvoiceServiceImplementation implements PharmacyInvoiceService {

    // Logger for logging service actions.
    private static final Logger logger = Logger.getLogger(PharmacyInvoiceServiceImplementation.class.getName());

    // Autowired PharmacyInvoiceRepository to interact with the database.
    @Autowired
    private PharmacyInvoiceRepository pharmacyInvoiceRepository;

    /**
     * Stores a new PharmacyInvoice in the repository.
     *
     * @param file    The MultipartFile representing the pharmacy invoice file to be stored.
     * @param orderId The ID of the order associated with the pharmacy invoice.
     * @return The ID of the newly stored PharmacyInvoice.
     * @throws IOException If there is an I/O error while reading the file.
     */
    @Override
    public String storePharmacyInvoice(MultipartFile file, long orderId) throws IOException {
        try {
            PharmacyInvoice pharmacyInvoice = new PharmacyInvoice();
            pharmacyInvoice.setFilename(file.getOriginalFilename());
            pharmacyInvoice.setFileType(file.getContentType());
            pharmacyInvoice.setFileSize(Long.toString(file.getSize()));
            pharmacyInvoice.setFile(file.getBytes());
            pharmacyInvoice.setOrderId(orderId);
            pharmacyInvoiceRepository.save(pharmacyInvoice);
            logger.log(Level.INFO, "PharmacyInvoice stored successfully with ID: " + pharmacyInvoice.getId());
            return pharmacyInvoice.getId();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred while storing PharmacyInvoice: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves all PharmacyInvoices from the repository.
     *
     * @return A list of all PharmacyInvoice objects in the repository.
     */
    @Override
    public List<PharmacyInvoice> getAllInvoices() {
        try {
            logger.log(Level.INFO, "Retrieving all PharmacyInvoices");
            List<PharmacyInvoice> pharmacyInvoices = pharmacyInvoiceRepository.findAll();
            logger.log(Level.INFO, "Retrieved " + pharmacyInvoices.size() + " PharmacyInvoices");
            return pharmacyInvoices;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all PharmacyInvoices");
            throw e;
        }
    }

    /**
     * Retrieves a PharmacyInvoice with the given order ID from the repository.
     *
     * @param orderId The order ID associated with the PharmacyInvoice to retrieve.
     * @return An Optional containing the PharmacyInvoice if found, or an empty Optional if not found.
     */
    @Override
    public Optional<PharmacyInvoice> getInvoiceByOrderId(long orderId) {
        try {
            logger.log(Level.INFO, "Retrieving PharmacyInvoice with Order ID: " + orderId);
            Optional<PharmacyInvoice> pharmacyInvoiceOptional = pharmacyInvoiceRepository.findByOrderId(orderId);
            if (pharmacyInvoiceOptional.isPresent()) {
                logger.log(Level.INFO, "Retrieved PharmacyInvoice with Order ID: " + orderId);
            } else {
                logger.log(Level.INFO, "No PharmacyInvoice found with Order ID: " + orderId);
            }
            return pharmacyInvoiceOptional;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving PharmacyInvoice with Order ID: " + orderId);
            throw e;
        }
    }

    /**
     * Retrieves a PharmacyInvoice with the given ID from the repository.
     *
     * @param id The ID of the PharmacyInvoice to retrieve.
     * @return An Optional containing the PharmacyInvoice if found, or an empty Optional if not found.
     */
    @Override
    public Optional<PharmacyInvoice> getInvoiceById(String id) {
        try {
            logger.log(Level.INFO, "Retrieving PharmacyInvoice with ID: " + id);
            Optional<PharmacyInvoice> pharmacyInvoiceOptional = pharmacyInvoiceRepository.findById(id);
            if (pharmacyInvoiceOptional.isPresent()) {
                logger.log(Level.INFO, "Retrieved PharmacyInvoice with ID: " + id);
            } else {
                logger.log(Level.INFO, "No PharmacyInvoice found with ID: " + id);
            }
            return pharmacyInvoiceOptional;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving PharmacyInvoice with ID: " + id);
            throw e;
        }
    }

    /**
     * Updates the attributes of an existing PharmacyInvoice in the repository.
     *
     * @param updatedPharmacyInvoice The modified PharmacyInvoice object to update.
     */
    @Override
    public void updatePharmacyInvoice(PharmacyInvoice updatedPharmacyInvoice) {
        try {
            logger.log(Level.INFO, "Updating PharmacyInvoice with ID: " + updatedPharmacyInvoice.getId());
            pharmacyInvoiceRepository.save(updatedPharmacyInvoice);
            logger.log(Level.INFO, "PharmacyInvoice updated successfully with ID: " + updatedPharmacyInvoice.getId());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating PharmacyInvoice: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Deletes a PharmacyInvoice with the given ID from the repository.
     *
     * @param id The ID of the PharmacyInvoice to delete.
     */
    @Override
    public void deletePharmacyInvoiceById(String id) {
        try {
            logger.log(Level.INFO, "Deleting PharmacyInvoice with ID: " + id);
            pharmacyInvoiceRepository.deleteById(id);
            logger.log(Level.INFO, "PharmacyInvoice deleted successfully with ID: " + id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting PharmacyInvoice: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Downloads the file associated with a PharmacyInvoice with the given ID from the repository.
     *
     * @param id The ID of the PharmacyInvoice to download.
     * @return The byte array representing the file associated with the PharmacyInvoice, or null if not found.
     */
    @Override
    public byte[] downloadPharmacyInvoice(String id) {
        try {
            logger.log(Level.INFO, "Downloading PharmacyInvoice with ID: " + id);
            Optional<PharmacyInvoice> pharmacyInvoiceOptional = pharmacyInvoiceRepository.findById(id);
            if (pharmacyInvoiceOptional.isPresent()) {
                logger.log(Level.INFO, "Retrieved PharmacyInvoice with ID: " + id);
                return pharmacyInvoiceOptional.get().getFile();
            }
            logger.log(Level.INFO, "No PharmacyInvoice found with ID: " + id);
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while downloading PharmacyInvoice with ID: " + id);
            throw e;
        }
    }
}
