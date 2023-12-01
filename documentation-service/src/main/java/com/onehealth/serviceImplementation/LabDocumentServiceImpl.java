package com.onehealth.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.LabDocument;
import com.onehealth.entity.LabDocument;
import com.onehealth.exception.DatabaseException;
import com.onehealth.repository.LabDocumentRepository;
import com.onehealth.service.LabDocumentService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class LabDocumentServiceImpl implements LabDocumentService {

    private final LabDocumentRepository labDocumentRepository;
    private static final Logger logger = Logger.getLogger(LabDocumentServiceImpl.class.getName());

    @Autowired
    public LabDocumentServiceImpl(LabDocumentRepository labDocumentRepository) {
        this.labDocumentRepository = labDocumentRepository;
    }

    /**
     * Stores a lab document.
     *
     * @param file      The file to be stored.
     * @param labId The ID of the lab associated with the document.
     * @return The ID of the stored document.
     * @throws IOException If there's an error reading or storing the file.
     */
    @Override
    public String storeLabDocument(MultipartFile file, long labId) throws IOException {
        try {
            LabDocument labDocument = new LabDocument();
            labDocument.setFilename(file.getOriginalFilename());
            labDocument.setFileType(file.getContentType());
            labDocument.setFileSize(Long.toString(file.getSize()));
            labDocument.setFile(file.getBytes());
            labDocument.setLabId(labId);
            labDocumentRepository.save(labDocument);
            logger.log(Level.INFO, "Stored Lab Document with ID: " + labDocument.getId() + " for lab ID: " + labId);
            return labDocument.getId();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error storing Lab Document for lab ID: " + labId, e);
            throw new IOException("Error storing Lab Document", e);
        }
    }

    /**
     * Retrieves all lab documents.
     *
     * @return A list of lab documents.
     * @throws DatabaseException If there's an error while retrieving lab documents from the repository.
     */
    @Override
    public List<LabDocument> getAllLabDocuments() throws DatabaseException {
        try {
            List<LabDocument> labDocuments = labDocumentRepository.findAll();
            logger.log(Level.INFO, "Retrieved " + labDocuments.size() + " LabDocuments");
            return labDocuments;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all LabDocuments", e);
            throw new DatabaseException("Error occurred while retrieving LabDocuments");
        }
    }

    /**
     * Retrieves lab documents associated with a specific lab ID.
     *
     * @param labId The ID of the lab.
     * @return A list of lab documents for the given lab ID.
     * @throws DatabaseException If there's an error while retrieving lab documents from the repository.
     */
    @Override
    public List<LabDocument> getLabDocumentsByLabId(long labId) throws DatabaseException {
        try {
            List<LabDocument> labDocuments = labDocumentRepository.findByLabId(labId);
            logger.log(Level.INFO, "Retrieved " + labDocuments.size() + " LabDocuments for lab ID: " + labId);
            return labDocuments;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving LabDocuments for lab ID: " + labId, e);
            throw new DatabaseException("Error occurred while retrieving LabDocuments");
        }
    }

    /**
     * Retrieves a lab document by its ID.
     *
     * @param id The ID of the lab document.
     * @return An optional containing the lab document if found.
     * @throws DatabaseException If there's an error while retrieving the lab document from the repository.
     */
    @Override
    public Optional<LabDocument> getLabDocumentById(String id) throws DatabaseException {
        try {
            Optional<LabDocument> labDocument = labDocumentRepository.findById(id);
            logger.log(Level.INFO, "Retrieved Lab Document with ID: " + id);
            return labDocument;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Lab Document with ID: " + id, e);
            throw new DatabaseException("Error occurred while retrieving Lab Document");
        }
    }

    /**
     * Updates a lab document.
     *
     * @param updatedLabDocument The updated lab document.
     * @throws DatabaseException If there's an error while updating the lab document in the repository.
     */
    @Override
    public void updateLabDocument(LabDocument updatedLabDocument) throws DatabaseException {
        try {
            labDocumentRepository.save(updatedLabDocument);
            logger.log(Level.INFO, "Updated Lab Document with ID: " + updatedLabDocument.getId());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating Lab Document with ID: " + updatedLabDocument.getId(), e);
            throw new DatabaseException("Error occurred while updating Lab Document");
        }
    }

    /**
     * Deletes a lab document by its ID.
     *
     * @param id The ID of the lab document to be deleted.
     * @throws DatabaseException If there's an error while deleting the lab document from the repository.
     */
    @Override
    public void deleteLabDocumentById(String id) throws DatabaseException {
        try {
            labDocumentRepository.deleteById(id);
            logger.log(Level.INFO, "Deleted Lab Document with ID: " + id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting Lab Document with ID: " + id, e);
            throw new DatabaseException("Error occurred while deleting Lab Document");
        }
    }

    /**
     * Deletes all lab documents associated with a specific lab ID.
     *
     * @param labId The ID of the lab.
     * @throws DatabaseException If there's an error while deleting lab documents from the repository.
     */
    @Override
    public void deleteLabDocumentsByLabId(long labId) throws DatabaseException {
        try {
            labDocumentRepository.deleteByLabId(labId);
            logger.log(Level.INFO, "Deleted all Lab Documents for lab ID: " + labId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting Lab Documents for lab ID: " + labId, e);
            throw new DatabaseException("Error occurred while deleting Lab Documents");
        }
    }
    
    
    /**
     * Downloads the content of a LabDocument with the given ID from the repository.
     *
     * @param id The ID of the LabDocument to download.
     * @return The byte array representing the content of the LabDocument, or null if not found.
     */
    @Override
    public byte[] downloadLabDocument(String id) {
        try {
            logger.log(Level.INFO, "Downloading LabDocument with ID: " + id);
            Optional<LabDocument> labDocumentOptional = labDocumentRepository.findById(id);
            if (labDocumentOptional.isPresent()) {
                logger.log(Level.INFO, "Retrieved LabDocument with ID: " + id);
                return labDocumentOptional.get().getFile();
            }
            logger.log(Level.INFO, "No LabDocument found with ID: " + id);
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while downloading LabDocument with ID: " + id);
            throw e;
        }
    }
}
