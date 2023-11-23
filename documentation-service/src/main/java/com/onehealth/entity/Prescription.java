package com.onehealth.entity;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Prescription class represents a prescription in the application.
 * It is annotated with @Document to specify the MongoDB collection it maps to.
 */
@Document(collection = "prescriptions")
public class Prescription {

    @Id
    private String id;
    private String filename;
    private String fileType;
    private String fileSize;
    private byte[] file;
    private long appointmentId;
    private long patientId;

    /**
     * Overrides the toString() method to provide a string representation of the Prescription object.
     */
    @Override
    public String toString() {
        return "Prescription [id=" + id + ", filename=" + filename + ", fileType=" + fileType + ", fileSize=" + fileSize
                + ", file=" + Arrays.toString(file) + ", appointmentId=" + appointmentId + ", patientId=" + patientId
                + "]";
    }

    /**
     * Getter for the ID of the prescription.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the ID of the prescription.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for the filename of the prescription.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Setter for the filename of the prescription.
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Getter for the file type of the prescription.
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Setter for the file type of the prescription.
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * Getter for the file size of the prescription.
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * Setter for the file size of the prescription.
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * Getter for the file data of the prescription.
     */
    public byte[] getFile() {
        return file;
    }

    /**
     * Setter for the file data of the prescription.
     */
    public void setFile(byte[] file) {
        this.file = file;
    }

    /**
     * Getter for the ID of the appointment associated with the prescription.
     */
    public long getAppointmentId() {
        return appointmentId;
    }

    /**
     * Setter for the ID of the appointment associated with the prescription.
     */
    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Getter for the ID of the patient associated with the prescription.
     */
    public long getPatientId() {
        return patientId;
    }

    /**
     * Setter for the ID of the patient associated with the prescription.
     */
    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    /**
     * Default constructor for the Prescription class.
     */
    public Prescription() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Parameterized constructor for the Prescription class.
     */
    public Prescription(String id, String filename, String fileType, String fileSize, byte[] file, long appointmentId,
            long patientId) {
        super();
        this.id = id;
        this.filename = filename;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.file = file;
        this.appointmentId = appointmentId;
        this.patientId = patientId;
    }
}
