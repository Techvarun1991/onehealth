package com.onehealth.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.PatientDocument;
import com.onehealth.entity.PatientDocument;
import com.onehealth.exception.DatabaseException;
import com.onehealth.repository.PatientDocumentRepository;
import com.onehealth.service.PatientDocumentService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PatientDocumentServiceImpl implements PatientDocumentService {

    private final PatientDocumentRepository patientDocumentRepository;
    private static final Logger logger = Logger.getLogger(PatientDocumentServiceImpl.class.getName());

    @Autowired
    public PatientDocumentServiceImpl(PatientDocumentRepository patientDocumentRepository) {
        this.patientDocumentRepository = patientDocumentRepository;
    }

    /**
     * Stores a patient document.
     *
     * @param file      The file to be stored.
     * @param patientId The ID of the patient associated with the document.
     * @return The ID of the stored document.
     * @throws IOException If there's an error reading or storing the file.
     */
    @Override
    public String storePatientDocument(MultipartFile file, long patientId,String recordType) throws IOException {
        try {
            PatientDocument patientDocument = new PatientDocument();
            patientDocument.setFilename(file.getOriginalFilename());
            patientDocument.setFileType(file.getContentType());
            patientDocument.setFileSize(Long.toString(file.getSize()));
            patientDocument.setFile(file.getBytes());
            patientDocument.setPatientId(patientId);
            patientDocument.setRecordType(recordType);
            patientDocumentRepository.save(patientDocument);
            logger.log(Level.INFO, "Stored Patient Document with ID: " + patientDocument.getId() + " for patient ID: " + patientId);
            return patientDocument.getId();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error storing Patient Document for patient ID: " + patientId, e);
            throw new IOException("Error storing Patient Document", e);
        }
    }

    /**
     * Retrieves all patient documents.
     *
     * @return A list of patient documents.
     * @throws DatabaseException If there's an error while retrieving patient documents from the repository.
     */
    @Override
    public List<PatientDocument> getAllPatientDocuments() throws DatabaseException {
        try {
            List<PatientDocument> patientDocuments = patientDocumentRepository.findAll();
            logger.log(Level.INFO, "Retrieved " + patientDocuments.size() + " PatientDocuments");
            return patientDocuments;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all PatientDocuments", e);
            throw new DatabaseException("Error occurred while retrieving PatientDocuments");
        }
    }

    /**
     * Retrieves patient documents associated with a specific patient ID.
     *
     * @param patientId The ID of the patient.
     * @return A list of patient documents for the given patient ID.
     * @throws DatabaseException If there's an error while retrieving patient documents from the repository.
     */
    @Override
    public List<PatientDocument> getPatientDocumentsByPatientId(long patientId) throws DatabaseException {
        try {
            List<PatientDocument> patientDocuments = patientDocumentRepository.findByPatientId(patientId);
            logger.log(Level.INFO, "Retrieved " + patientDocuments.size() + " PatientDocuments for patient ID: " + patientId);
            return patientDocuments;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving PatientDocuments for patient ID: " + patientId, e);
            throw new DatabaseException("Error occurred while retrieving PatientDocuments");
        }
    }

    /**
     * Retrieves a patient document by its ID.
     *
     * @param id The ID of the patient document.
     * @return An optional containing the patient document if found.
     * @throws DatabaseException If there's an error while retrieving the patient document from the repository.
     */
    @Override
    public Optional<PatientDocument> getPatientDocumentById(String id) throws DatabaseException {
        try {
            Optional<PatientDocument> patientDocument = patientDocumentRepository.findById(id);
            logger.log(Level.INFO, "Retrieved Patient Document with ID: " + id);
            return patientDocument;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving Patient Document with ID: " + id, e);
            throw new DatabaseException("Error occurred while retrieving Patient Document");
        }
    }

    /**
     * Updates a patient document.
     *
     * @param updatedPatientDocument The updated patient document.
     * @throws DatabaseException If there's an error while updating the patient document in the repository.
     */
    @Override
    public void updatePatientDocument(PatientDocument updatedPatientDocument) throws DatabaseException {
        try {
            patientDocumentRepository.save(updatedPatientDocument);
            logger.log(Level.INFO, "Updated Patient Document with ID: " + updatedPatientDocument.getId());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating Patient Document with ID: " + updatedPatientDocument.getId(), e);
            throw new DatabaseException("Error occurred while updating Patient Document");
        }
    }

    /**
     * Deletes a patient document by its ID.
     *
     * @param id The ID of the patient document to be deleted.
     * @throws DatabaseException If there's an error while deleting the patient document from the repository.
     */
    @Override
    public void deletePatientDocumentById(String id) throws DatabaseException {
        try {
            patientDocumentRepository.deleteById(id);
            logger.log(Level.INFO, "Deleted Patient Document with ID: " + id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting Patient Document with ID: " + id, e);
            throw new DatabaseException("Error occurred while deleting Patient Document");
        }
    }

    /**
     * Deletes all patient documents associated with a specific patient ID.
     *
     * @param patientId The ID of the patient.
     * @throws DatabaseException If there's an error while deleting patient documents from the repository.
     */
    @Override
    public void deletePatientDocumentsByPatientId(long patientId) throws DatabaseException {
        try {
            patientDocumentRepository.deleteByPatientId(patientId);
            logger.log(Level.INFO, "Deleted all Patient Documents for patient ID: " + patientId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting Patient Documents for patient ID: " + patientId, e);
            throw new DatabaseException("Error occurred while deleting Patient Documents");
        }
    }
    
    
    /**
     * Downloads the content of a PatientDocument with the given ID from the repository.
     *
     * @param id The ID of the PatientDocument to download.
     * @return The byte array representing the content of the PatientDocument, or null if not found.
     */
    @Override
    public byte[] downloadPatientDocument(String id) {
        try {
            logger.log(Level.INFO, "Downloading PatientDocument with ID: " + id);
            Optional<PatientDocument> patientDocumentOptional = patientDocumentRepository.findById(id);
            if (patientDocumentOptional.isPresent()) {
                logger.log(Level.INFO, "Retrieved PatientDocument with ID: " + id);
                return patientDocumentOptional.get().getFile();
            }
            logger.log(Level.INFO, "No PatientDocument found with ID: " + id);
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while downloading PatientDocument with ID: " + id);
            throw e;
        }
 
    }
    
    
    /**
     * Retrieves all patient documents for a given patient ID and record type.
     *
     * @param patientId  The ID of the patient.
     * @param recordType The record type of the documents.
     * @return A list of patient documents.
     * @throws RuntimeException If an error occurs while retrieving documents.
     */
    @Override
    public List<PatientDocument> getAllPatientDocuments(long patientId, String recordType) {
        try {
            List<PatientDocument> patientDocuments = patientDocumentRepository.findByPatientIdAndRecordType(patientId, recordType);
            logger.log(Level.INFO, "Retrieved " + patientDocuments.size() + " PatientDocuments for patient ID: " + patientId
                    + " and record type: " + recordType);
            return patientDocuments;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving PatientDocuments for patient ID: " + patientId
                    + " and record type: " + recordType, e);
            throw new RuntimeException("Error occurred while retrieving Patient Documents", e);
        }
    }
}
