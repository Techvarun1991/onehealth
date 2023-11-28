package com.onehealth.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.onehealth.entity.MedicinePhoto;

/**
 * The MedicinePhotoRepository interface extends the MongoRepository interface provided by Spring Data MongoDB.
 * It is responsible for handling database operations related to the MedicinePhoto entity.
 * The repository allows CRUD operations (Create, Read, Update, Delete) and other custom queries for the MedicinePhoto collection in MongoDB.
 */
public interface MedicinePhotoRepository extends MongoRepository<MedicinePhoto, String>{

    /**
     * Custom query to find all medicine photos associated with a specific medicine ID.
     *
     * @param medicineId The ID of the medicine to retrieve photos for.
     * @return A list of MedicinePhoto objects associated with the given medicine ID.
     */
    List<MedicinePhoto> findByMedicineId(long medicineId);
    
    
    /**
     * Deletes Medicine documents by Medicine ID.
     *
     * @param medId The ID of the Medicine.
     */
    void deleteByMedicineId(long medId);
}
