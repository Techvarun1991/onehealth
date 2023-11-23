package com.onehealth.entity;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The MedicinePhoto class represents a document in the "medicine_photos" collection of the MongoDB database.
 * It stores information about medicine photos, including the image file, metadata, and associated medicine details.
 */
@Document(collection = "medicine_photos")
public class MedicinePhoto {
    @Id
    private String id;              // The unique ID of the medicine photo document
    private String filename;        // The filename of the uploaded image
    private String fileType;        // The file type (MIME type) of the uploaded image
    private String fileSize;        // The size of the uploaded image file
    private byte[] file;            // The byte array representing the uploaded image data
    private String medicine_name;   // The name of the medicine associated with the photo
    private long medicineId;        // The ID of the medicine associated with the photo

    /**
     * Override of the toString() method to display the entity's properties.
     *
     * @return A string representation of the entity.
     */
    @Override
    public String toString() {
        return "MedicinePhoto [id=" + id + ", filename=" + filename + ", fileType=" + fileType + ", fileSize="
                + fileSize + ", file=" + Arrays.toString(file) + ", medicine_name=" + medicine_name + ", medicineId="
                + medicineId + "]";
    }

    /**
     * Default constructor for the MedicinePhoto class.
     */
    public MedicinePhoto() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Parameterized constructor for the MedicinePhoto class.
     *
     * @param id            The unique ID of the medicine photo document.
     * @param filename      The filename of the uploaded image.
     * @param fileType      The file type (MIME type) of the uploaded image.
     * @param fileSize      The size of the uploaded image file.
     * @param file          The byte array representing the uploaded image data.
     * @param medicine_name The name of the medicine associated with the photo.
     * @param medicineId    The ID of the medicine associated with the photo.
     */
    public MedicinePhoto(String id, String filename, String fileType, String fileSize, byte[] file,
            String medicine_name, long medicineId) {
        super();
        this.id = id;
        this.filename = filename;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.file = file;
        this.medicine_name = medicine_name;
        this.medicineId = medicineId;
    }

    /**
     * Getter for the ID of the medicine photo document.
     *
     * @return The ID of the medicine photo document.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the ID of the medicine photo document.
     *
     * @param id The ID of the medicine photo document to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for the filename of the uploaded image.
     *
     * @return The filename of the uploaded image.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Setter for the filename of the uploaded image.
     *
     * @param filename The filename of the uploaded image to set.
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Getter for the file type (MIME type) of the uploaded image.
     *
     * @return The file type (MIME type) of the uploaded image.
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Setter for the file type (MIME type) of the uploaded image.
     *
     * @param fileType The file type (MIME type) of the uploaded image to set.
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * Getter for the size of the uploaded image file.
     *
     * @return The size of the uploaded image file.
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * Setter for the size of the uploaded image file.
     *
     * @param fileSize The size of the uploaded image file to set.
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * Getter for the byte array representing the uploaded image data.
     *
     * @return The byte array representing the uploaded image data.
     */
    public byte[] getFile() {
        return file;
    }

    /**
     * Setter for the byte array representing the uploaded image data.
     *
     * @param file The byte array representing the uploaded image data to set.
     */
    public void setFile(byte[] file) {
        this.file = file;
    }

    /**
     * Getter for the name of the medicine associated with the photo.
     *
     * @return The name of the medicine associated with the photo.
     */
    public String getMedicine_name() {
        return medicine_name;
    }

    /**
     * Setter for the name of the medicine associated with the photo.
     *
     * @param medicine_name The name of the medicine associated with the photo to set.
     */
    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    /**
     * Getter for the ID of the medicine associated with the photo.
     *
     * @return The ID of the medicine associated with the photo.
     */
    public long getMedicineId() {
        return medicineId;
    }

    /**
     * Setter for the ID of the medicine associated with the photo.
     *
     * @param medicineId The ID of the medicine associated with the photo to set.
     */
    public void setMedicineId(long medicineId) {
        this.medicineId = medicineId;
    }
}
