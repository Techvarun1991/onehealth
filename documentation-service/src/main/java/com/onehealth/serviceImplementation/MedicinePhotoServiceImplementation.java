package com.onehealth.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.onehealth.entity.MedicinePhoto;
import com.onehealth.exception.DatabaseException;
import com.onehealth.repository.MedicinePhotoRepository;
import com.onehealth.service.MedicinePhotoService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The MedicinePhotoServiceImplementation class implements the MedicinePhotoService interface and provides the actual
 * implementation for service operations related to the MedicinePhoto entity.
 */
@Service
public class MedicinePhotoServiceImplementation implements MedicinePhotoService {

    private static final Logger logger = Logger.getLogger(MedicinePhotoServiceImplementation.class.getName());

    @Autowired
    private MedicinePhotoRepository medicinePhotoRepository;

    /**
     * Stores a new MedicinePhoto object in the database.
     *
     * @param file         The MultipartFile representing the photo file to store.
     * @param medicineName The name of the medicine associated with the photo.
     * @param medicineId   The ID of the medicine associated with the photo.
     * @return The ID of the stored MedicinePhoto object.
     * @throws IOException       If an I/O error occurs while processing the file.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    @Override
    public String storeMedicinePhoto(MultipartFile file, String medicineName, long medicineId) throws IOException, DatabaseException {
        try {
            MedicinePhoto medicinePhoto = new MedicinePhoto();
            medicinePhoto.setFilename(file.getOriginalFilename());
            medicinePhoto.setFileType(file.getContentType());
            medicinePhoto.setFileSize(Long.toString(file.getSize()));
            medicinePhoto.setFile(file.getBytes());
            medicinePhoto.setMedicine_name(medicineName);
            medicinePhoto.setMedicineId(medicineId);
            medicinePhotoRepository.save(medicinePhoto);
            logger.log(Level.INFO, "MedicinePhoto stored successfully with ID: " + medicinePhoto.getId());
            return medicinePhoto.getId();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred while storing MedicinePhoto: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Updates an existing MedicinePhoto object in the database.
     *
     * @param updatedMedicinePhoto The updated MedicinePhoto object.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    @Override
    public void updateMedicinePhoto(MedicinePhoto updatedMedicinePhoto) throws DatabaseException {
        medicinePhotoRepository.save(updatedMedicinePhoto);
        logger.log(Level.INFO, "MedicinePhoto updated successfully with ID: " + updatedMedicinePhoto.getId());
    }

    /**
     * Deletes a MedicinePhoto object by its ID.
     *
     * @param id The ID of the MedicinePhoto to delete.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    @Override
    public void deleteMedicinePhotoById(String id) throws DatabaseException {
        medicinePhotoRepository.deleteById(id);
        logger.log(Level.INFO, "MedicinePhoto deleted successfully with ID: " + id);
    }

    /**
     * Retrieves all MedicinePhoto objects associated with a specific medicine ID.
     *
     * @param medicineId The ID of the medicine to retrieve photos for.
     * @return A list of MedicinePhoto objects associated with the given medicine ID.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    @Override
    public List<MedicinePhoto> getMedicinePhotosByMedicineId(long medicineId) throws DatabaseException {
        List<MedicinePhoto> medicinePhotos = medicinePhotoRepository.findByMedicineId(medicineId);
        logger.log(Level.INFO, "Retrieved " + medicinePhotos.size() + " MedicinePhotos for medicine ID: " + medicineId);
        return medicinePhotos;
    }
    /**
     * Retrieves a specific MedicinePhoto object identified by its ID from the database.
     *
     * @param id The ID of the MedicinePhoto to retrieve.
     * @return An Optional containing the MedicinePhoto object if found, or an empty Optional if not found.
     */
    public Optional<MedicinePhoto> getMedicinePhotoById(String id) {
        // Logging: Log the attempt to retrieve a MedicinePhoto by ID.
        logger.info("Attempting to retrieve MedicinePhoto with ID: " + id);
        
        // Call the repository method to retrieve the MedicinePhoto.
        Optional<MedicinePhoto> medicinePhotoOptional = medicinePhotoRepository.findById(id);

        // Logging: Log the result of the retrieval attempt.
        if (medicinePhotoOptional.isPresent()) {
            logger.info("MedicinePhoto with ID " + id + " found in the database.");
        } else {
            logger.info("MedicinePhoto with ID " + id + " not found in the database.");
        }

        return medicinePhotoOptional;
    }

    /**
     * Downloads the file data of a specific MedicinePhoto identified by its ID.
     *
     * @param id The ID of the MedicinePhoto for which the file is to be downloaded.
     * @return The file data as a byte array for successful download, or null if the MedicinePhoto with the given ID is not found.
     */
    public byte[] downloadMedicinePhoto(String id) {
        // Logging: Log the attempt to download the file of a MedicinePhoto by ID.
        logger.info("Attempting to download file for MedicinePhoto with ID: " + id);
        
        // Call the repository method to retrieve the MedicinePhoto.
        Optional<MedicinePhoto> medicinePhotoOptional = medicinePhotoRepository.findById(id);

        if (medicinePhotoOptional.isPresent()) {
            // Logging: Log the successful download of the file.
            logger.log(Level.INFO,"File for MedicinePhoto with ID " + id + " downloaded successfully.");
            
            // Return the file data as a byte array.
            return medicinePhotoOptional.get().getFile();
        } else {
            // Logging: Log that the MedicinePhoto with the given ID was not found.
            logger.log(Level.SEVERE,"File for MedicinePhoto with ID " + id + " not found.");
            
            // Return null as the file could not be found.
            return null;
        }
    }

}

