package com.onehealth.service;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.MedicinePhoto;
import com.onehealth.exception.DatabaseException;

/**
 * The MedicinePhotoService interface defines the contract for service operations related to the MedicinePhoto entity.
 * It declares methods to retrieve, delete, store, and update MedicinePhoto objects.
 */
public interface MedicinePhotoService {

    /**
     * Retrieves all MedicinePhoto objects associated with a specific medicine ID.
     *
     * @param medicineId The ID of the medicine to retrieve photos for.
     * @return A list of MedicinePhoto objects associated with the given medicine ID.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    List<MedicinePhoto> getMedicinePhotosByMedicineId(long medicineId) throws DatabaseException;

    /**
     * Deletes a MedicinePhoto object by its ID.
     *
     * @param id The ID of the MedicinePhoto to delete.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    void deleteMedicinePhotoById(String id) throws DatabaseException;

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
    String storeMedicinePhoto(MultipartFile file, String medicineName, long medicineId) throws IOException, DatabaseException;

    /**
     * Updates an existing MedicinePhoto object in the database.
     *
     * @param updatedMedicinePhoto The updated MedicinePhoto object.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    void updateMedicinePhoto(MedicinePhoto updatedMedicinePhoto) throws DatabaseException;
    
    /**
     * Downloads the file data of a specific MedicinePhoto identified by its ID.
     *
     * @param id The ID of the MedicinePhoto for which the file is to be downloaded.
     * @return The file data as a byte array for successful download, or null if the MedicinePhoto with the given ID is not found.
     */
    byte[] downloadMedicinePhoto(String id);
    
    /**
     * Retrieves a specific MedicinePhoto object identified by its ID.
     *
     * @param id The ID of the MedicinePhoto to retrieve.
     * @return An Optional containing the MedicinePhoto object if found, or an empty Optional if not found.
     */
    Optional<MedicinePhoto> getMedicinePhotoById(String id);	
}
