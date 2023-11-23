package com.onehealth.entity;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The LabInvoice class represents an invoice associated with a lab in the application.
 * It is annotated with @Document to specify the MongoDB collection it maps to.
 */
@Document(collection = "lab_invoices")
public class LabInvoice {

    @Id
    private String id;
    private String filename;
    private String fileType;
    private String fileSize;
    private byte[] file;
    private long orderId;

    /**
     * Getter for the ID of the lab invoice.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the ID of the lab invoice.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for the filename of the lab invoice.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Setter for the filename of the lab invoice.
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Getter for the file type of the lab invoice.
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Setter for the file type of the lab invoice.
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * Getter for the file size of the lab invoice.
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * Setter for the file size of the lab invoice.
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * Getter for the file data of the lab invoice.
     */
    public byte[] getFile() {
        return file;
    }

    /**
     * Setter for the file data of the lab invoice.
     */
    public void setFile(byte[] file) {
        this.file = file;
    }

    /**
     * Getter for the ID of the order associated with the lab invoice.
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * Setter for the ID of the order associated with the lab invoice.
     */
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    /**
     * Overrides the toString() method to provide a string representation of the LabInvoice object.
     */
    @Override
    public String toString() {
        return "LabInvoice [id=" + id + ", filename=" + filename + ", fileType=" + fileType + ", fileSize=" + fileSize
                + ", file=" + Arrays.toString(file) + ", orderId=" + orderId + "]";
    }

    /**
     * Default constructor for the LabInvoice class.
     */
    public LabInvoice() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Parameterized constructor for the LabInvoice class.
     */
    public LabInvoice(String id, String filename, String fileType, String fileSize, byte[] file, long orderId) {
        super();
        this.id = id;
        this.filename = filename;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.file = file;
        this.orderId = orderId;
    }
}
