package com.onehealth.entity;

import java.util.Arrays;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The LabReport class represents a lab report in the application.
 * It is annotated with @Document to specify the MongoDB collection it maps to.
 */
@Document(collection = "lab_reports")
public class LabReport {

    @Id
    private String id;
    private String filename;
    private String fileType;
    private String fileSize;
    private byte[] file;
    private long orderId;
    private Date uploadDate;

    /**
     * Overrides the toString() method to provide a string representation of the LabReport object.
     */
    @Override
    public String toString() {
        return "LabReport [id=" + id + ", filename=" + filename + ", fileType=" + fileType + ", fileSize=" + fileSize
                + ", file=" + Arrays.toString(file) + ", orderId=" + orderId + "]";
    }

    /**
     * Getter for the ID of the lab report.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the ID of the lab report.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for the filename of the lab report.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Setter for the filename of the lab report.
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Getter for the file type of the lab report.
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Setter for the file type of the lab report.
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * Getter for the file size of the lab report.
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * Setter for the file size of the lab report.
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * Getter for the file data of the lab report.
     */
    public byte[] getFile() {
        return file;
    }

    /**
     * Setter for the file data of the lab report.
     */
    public void setFile(byte[] file) {
        this.file = file;
    }

    /**
     * Getter for the ID of the order associated with the lab report.
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * Setter for the ID of the order associated with the lab report.
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
     * Default constructor for the LabReport class.
     */
    public LabReport() {
        super();
        // TODO Auto-generated constructor stub
        this.uploadDate = new Date();
    }

    /**
     * Parameterized constructor for the LabReport class.
     */
	public LabReport(String id, String filename, String fileType, String fileSize, byte[] file, long orderId,
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
