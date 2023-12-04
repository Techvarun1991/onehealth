package com.onehealth.entity;

import java.util.Arrays;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The DoctorDocument class represents a document associated with a doctor in the application.
 * It is annotated with @Document to specify the MongoDB collection it maps to.
 */
@Document(collection = "doctor_documents")
public class DoctorDocument {
    @Id
    private String id;
    private String filename;
    private String fileType;
    private String fileSize;
    private byte[] file;
    private long doctorId;
    private Date uploadDate;
    /**
     * Getter for the ID of the doctor document.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the ID of the doctor document.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for the filename of the doctor document.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Setter for the filename of the doctor document.
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Getter for the file type of the doctor document.
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Setter for the file type of the doctor document.
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * Getter for the file size of the doctor document.
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * Setter for the file size of the doctor document.
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * Getter for the file data of the doctor document.
     */
    public byte[] getFile() {
        return file;
    }

    /**
     * Setter for the file data of the doctor document.
     */
    public void setFile(byte[] file) {
        this.file = file;
    }

    /**
     * Getter for the ID of the doctor associated with the document.
     */
    public long getDoctorId() {
        return doctorId;
    }

    /**
     * Setter for the ID of the doctor associated with the document.
     */
    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }
    
    public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	/**
     * Overrides the toString() method to provide a string representation of the DoctorDocument object.
     */
    @Override
    public String toString() {
        return "DoctorDocument [id=" + id + ", filename=" + filename + ", fileType=" + fileType + ", fileSize="
                + fileSize + ", file=" + Arrays.toString(file) + ", doctorId=" + doctorId + "]";
    }

    /**
     * Default constructor for the DoctorDocument class.
     */
    public DoctorDocument() {
        super();
        // TODO Auto-generated constructor stub
        this.uploadDate = new Date();
        
    }

    /**
     * Parameterized constructor for the DoctorDocument class.
     */
   

	public DoctorDocument(String id, String filename, String fileType, String fileSize, byte[] file, long doctorId,
			Date uploadDate) {
		super();
		this.id = id;
		this.filename = filename;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.file = file;
		this.doctorId = doctorId;
		this.uploadDate = uploadDate;
	}

    
}
