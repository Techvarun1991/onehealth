package com.onehealth.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.DoctorDocument;
import com.onehealth.repository.DoctorDocumentRepository;
import com.onehealth.service.DoctorDocumentService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service implementation class that handles operations related to DoctorDocuments.
 */
@Service
public class DoctorDocumentServiceImplementation implements DoctorDocumentService {

    // Logger for logging service actions.
    private static final Logger logger = Logger.getLogger(DoctorDocumentServiceImplementation.class.getName());

    // Autowired DoctorDocumentRepository to interact with the database.
    @Autowired
    private DoctorDocumentRepository doctorDocumentRepository;

    /**
     * Stores a new DoctorDocument in the repository.
     *
     * @param file      The MultipartFile representing the doctor document file to be stored.
     * @param doctorId  The ID of the doctor associated with the document.
     * @return The ID of the newly stored DoctorDocument.
     * @throws IOException If there is an I/O error while reading the file.
     */
    @Override
    public String storeDoctorDocument(MultipartFile file, long doctorId) throws IOException {
        try {
            DoctorDocument doctorDocument = new DoctorDocument();
            doctorDocument.setFilename(file.getOriginalFilename());
            doctorDocument.setFileType(file.getContentType());
            doctorDocument.setFileSize(Long.toString(file.getSize()));
            doctorDocument.setFile(file.getBytes());
            doctorDocument.setDoctorId(doctorId);
            doctorDocumentRepository.save(doctorDocument);
            logger.log(Level.INFO, "DoctorDocument stored successfully with ID: " + doctorDocument.getId());
            return doctorDocument.getId();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred while storing DoctorDocument: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves all DoctorDocuments associated with a given doctor ID from the repository.
     *
     * @param doctorId The ID of the doctor to get DoctorDocuments for.
     * @return A list of DoctorDocument objects associated with the specified doctor ID.
     */
    @Override
    public List<DoctorDocument> getAllDoctorDocumentsByDoctorId(long doctorId) {
        try {
            logger.log(Level.INFO, "Retrieving DoctorDocuments for doctor ID: " + doctorId);
            List<DoctorDocument> doctorDocuments = doctorDocumentRepository.findByDoctorId(doctorId);
            logger.log(Level.INFO, "Retrieved " + doctorDocuments.size() + " DoctorDocuments for doctor ID: " + doctorId);
            return doctorDocuments;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving DoctorDocuments for doctor ID: " + doctorId);
            throw e;
        }
    }

    /**
     * Retrieves a DoctorDocument with the given ID from the repository.
     *
     * @param id The ID of the DoctorDocument to retrieve.
     * @return An Optional containing the DoctorDocument if found, or an empty Optional if not found.
     */
    @Override
    public Optional<DoctorDocument> getDoctorDocumentById(String id) {
        try {
            logger.log(Level.INFO, "Retrieving DoctorDocument with ID: " + id);
            Optional<DoctorDocument> doctorDocumentOptional = doctorDocumentRepository.findById(id);
            if (doctorDocumentOptional.isPresent()) {
                logger.log(Level.INFO, "Retrieved DoctorDocument with ID: " + id);
            } else {
                logger.log(Level.INFO, "No DoctorDocument found with ID: " + id);
            }
            return doctorDocumentOptional;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving DoctorDocument with ID: " + id);
            throw e;
        }
    }

    /**
     * Updates the attributes of an existing DoctorDocument in the repository.
     *
     * @param updatedDoctorDocument The modified DoctorDocument object to update.
     */
    @Override
    public void updateDoctorDocument(DoctorDocument updatedDoctorDocument) {
        try {
            logger.log(Level.INFO, "Updating DoctorDocument with ID: " + updatedDoctorDocument.getId());
            doctorDocumentRepository.save(updatedDoctorDocument);
            logger.log(Level.INFO, "DoctorDocument updated successfully with ID: " + updatedDoctorDocument.getId());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating DoctorDocument: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Deletes a DoctorDocument with the given ID from the repository.
     *
     * @param id The ID of the DoctorDocument to delete.
     */
    @Override
    public void deleteDoctorDocumentById(String id) {
        try {
            logger.log(Level.INFO, "Deleting DoctorDocument with ID: " + id);
            doctorDocumentRepository.deleteById(id);
            logger.log(Level.INFO, "DoctorDocument deleted successfully with ID: " + id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting DoctorDocument: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Downloads the content of a DoctorDocument with the given ID from the repository.
     *
     * @param id The ID of the DoctorDocument to download.
     * @return The byte array representing the content of the DoctorDocument, or null if not found.
     */
    @Override
    public byte[] downloadDoctorDocument(String id) {
        try {
            logger.log(Level.INFO, "Downloading DoctorDocument with ID: " + id);
            Optional<DoctorDocument> doctorDocumentOptional = doctorDocumentRepository.findById(id);
            if (doctorDocumentOptional.isPresent()) {
                logger.log(Level.INFO, "Retrieved DoctorDocument with ID: " + id);
                return doctorDocumentOptional.get().getFile();
            }
            logger.log(Level.INFO, "No DoctorDocument found with ID: " + id);
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while downloading DoctorDocument with ID: " + id);
            throw e;
        }
    }
}
