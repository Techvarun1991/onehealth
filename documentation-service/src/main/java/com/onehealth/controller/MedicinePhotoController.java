package com.onehealth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.onehealth.entity.MedicinePhoto;
import com.onehealth.exception.DatabaseException;
import com.onehealth.service.MedicinePhotoService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The MedicinePhotoController class is a REST controller that handles HTTP requests related to MedicinePhoto entity.
 * It provides endpoints to upload, update, and delete MedicinePhotos and retrieve photos by medicine ID.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("api/documentation/medicine-photos")
public class MedicinePhotoController {

    private static final Logger logger = Logger.getLogger(MedicinePhotoController.class.getName());

    @Autowired
    private MedicinePhotoService medicinePhotoService;

    @GetMapping
    public String show() {
    	return "Hello Medicine photos domentatio controller";
    }
    
    /**
     * Endpoint to upload a new MedicinePhoto.
     *
     * @param file         The MultipartFile representing the photo file to upload.
     * @param medicineName The name of the medicine associated with the photo.
     * @param medicineId   The ID of the medicine associated with the photo.
     * @return A ResponseEntity with a success message and the ID of the stored MedicinePhoto.
     * @throws IOException       If an I/O error occurs while processing the file.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadMedicinePhoto(@RequestParam("mfile") MultipartFile mfile,
                                                      @RequestParam("medicine_name") String medicineName,
                                                      @RequestParam("medicine_id") long medicineId) throws IOException, DatabaseException {
    	System.out.println("Inside the uploadmedicine api");
        try {
            String fileId = medicinePhotoService.storeMedicinePhoto(mfile, medicineName, medicineId);
            logger.log(Level.INFO, "MedicinePhoto uploaded successfully with ID: " + fileId);
            return ResponseEntity.ok("Medicine Photo uploaded successfully. File ID: " + fileId);
        } catch (IOException | DatabaseException e) {
            logger.log(Level.SEVERE, "Error occurred while uploading MedicinePhoto: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Endpoint to update an existing MedicinePhoto.
     *
     * @param id                  The ID of the MedicinePhoto to update.
     * @param updatedMedicinePhoto The updated MedicinePhoto object.
     * @return A ResponseEntity with a success message.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMedicinePhoto(@PathVariable String id, @RequestBody MedicinePhoto updatedMedicinePhoto) throws DatabaseException {
        try {
            updatedMedicinePhoto.setId(id);
            medicinePhotoService.updateMedicinePhoto(updatedMedicinePhoto);
            logger.log(Level.INFO, "MedicinePhoto updated successfully with ID: " + id);
            return ResponseEntity.ok("Medicine Photo updated successfully.");
        } catch (DatabaseException e) {
            logger.log(Level.SEVERE, "Error occurred while updating MedicinePhoto: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Endpoint to delete a MedicinePhoto by its ID.
     *
     * @param id The ID of the MedicinePhoto to delete.
     * @return A ResponseEntity with a success message.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedicinePhoto(@PathVariable String id) throws DatabaseException {
        try {
            medicinePhotoService.deleteMedicinePhotoById(id);
            logger.log(Level.INFO, "MedicinePhoto deleted successfully with ID: " + id);
            return ResponseEntity.ok("Medicine Photo deleted successfully.");
        } catch (DatabaseException e) {
            logger.log(Level.SEVERE, "Error occurred while deleting MedicinePhoto: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Endpoint to retrieve all MedicinePhotos associated with a specific medicine ID.
     *
     * @param medicineId The ID of the medicine to retrieve photos for.
     * @return A ResponseEntity with a list of MedicinePhotos and a success message.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    @GetMapping("/medicine/{medicineId}")
    public ResponseEntity<List<MedicinePhoto>> getMedicinePhotosByMedicineId(@PathVariable long medicineId) throws DatabaseException {
        try {
            List<MedicinePhoto> medicinePhotos = medicinePhotoService.getMedicinePhotosByMedicineId(medicineId);
            logger.log(Level.INFO, "Retrieved " + medicinePhotos.size() + " MedicinePhotos for medicine ID: " + medicineId);
            return ResponseEntity.ok(medicinePhotos);
        } catch (DatabaseException e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving MedicinePhotos for medicine ID: " + medicineId);
            throw e;
        }
    }
    
    /**
     * Endpoint to download the file of a specific MedicinePhoto identified by its ID.
     * This endpoint allows users to download the medicine photo file associated with the provided ID.
     *
     * @param id The ID of the MedicinePhoto for which the file is to be downloaded.
     * @return A ResponseEntity containing the file data and headers for successful download,
     *         or a not found response if the MedicinePhoto with the given ID is not found.
     * @throws Exception If an error occurs during the download process.
     */
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadMedicinePhoto(@PathVariable String id) {
        try {
            // Retrieve the medicine photo file data using the provided ID
            byte[] fileData = medicinePhotoService.downloadMedicinePhoto(id);
            
            if (fileData != null) {
                // If the file data is not null, retrieve the MedicinePhoto object for the provided ID
                MedicinePhoto medicinePhoto = medicinePhotoService.getMedicinePhotoById(id).orElse(null);
                HttpHeaders headers = new HttpHeaders();
                // Set the content type of the response to match the file's media type
                headers.setContentType(MediaType.parseMediaType(medicinePhoto.getFileType()));
                // Set the content disposition header to prompt download with the original filename
                headers.setContentDispositionFormData("attachment", medicinePhoto.getFilename());
                logger.log(Level.INFO, "Medicine photo download successful for ID: " + id);
                return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
            } else {
                // If the file data is null, return a not found response
                logger.log(Level.INFO, "No medicine photo found for download with ID: " + id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle any exceptions that may occur during the download process
            logger.log(Level.SEVERE, "Error occurred while downloading medicine photo with ID: " + id);
            throw e;
        }
    }

}
