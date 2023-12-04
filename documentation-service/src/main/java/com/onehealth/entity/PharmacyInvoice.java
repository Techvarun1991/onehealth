package com.onehealth.entity;

import java.util.Arrays;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The PharmacyInvoice class represents a pharmacy invoice in the application.
 * It is annotated with @Document to specify the MongoDB collection it maps to.
 */
@Document(collection = "pharmacy_invoices")
public class PharmacyInvoice {

    @Id
    private String id;
    private String filename;
    private String fileType;
    private String fileSize;
    private byte[] file;
    private long orderId;
    private Date uploadDate;
    /**
     * Overrides the toString() method to provide a string representation of the PharmacyInvoice object.
     */
    @Override
    public String toString() {
        return "PharmacyInvoice [id=" + id + ", filename=" + filename + ", fileType=" + fileType + ", fileSize=" + fileSize
                + ", file=" + Arrays.toString(file) + ", orderId=" + orderId + "]";
    }

    /**
     * Getter for the ID of the pharmacy invoice.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the ID of the pharmacy invoice.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for the filename of the pharmacy invoice.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Setter for the filename of the pharmacy invoice.
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Getter for the file type of the pharmacy invoice.
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Setter for the file type of the pharmacy invoice.
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * Getter for the file size of the pharmacy invoice.
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * Setter for the file size of the pharmacy invoice.
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * Getter for the file data of the pharmacy invoice.
     */
    public byte[] getFile() {
        return file;
    }

    /**
     * Setter for the file data of the pharmacy invoice.
     */
    public void setFile(byte[] file) {
        this.file = file;
    }

    /**
     * Getter for the ID of the order associated with the pharmacy invoice.
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * Setter for the ID of the order associated with the pharmacy invoice.
     */
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    
    

    public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	/**
     * Default constructor for the PharmacyInvoice class.
     */
    public PharmacyInvoice() {
        super();
        // TODO Auto-generated constructor stub
        this.uploadDate = new Date();
    }

    /**
     * Parameterized constructor for the PharmacyInvoice class.
     */

	public PharmacyInvoice(String id, String filename, String fileType, String fileSize, byte[] file, long orderId,
			Date uploadDate) {
		super();
		this.id = id;
		this.filename = filename;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.file = file;
		this.orderId = orderId;
		this.uploadDate = uploadDate;
	}
    
    
}
