package com.onehealth.entity;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lab_documents")
public class LabDocument {
    
    @Id
    private String id;
    private String filename;
    private String fileType;
    private String fileSize;
    private byte[] file;
    private long labId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public long getLabId() {
		return labId;
	}
	public void setLabId(long labId) {
		this.labId = labId;
	}
	@Override
	public String toString() {
		return "LabDocument [id=" + id + ", filename=" + filename + ", fileType=" + fileType + ", fileSize="
				+ fileSize + ", file=" + Arrays.toString(file) + ", labId=" + labId + "]";
	}
	public LabDocument() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LabDocument(String id, String filename, String fileType, String fileSize, byte[] file, long labId) {
		super();
		this.id = id;
		this.filename = filename;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.file = file;
		this.labId = labId;
	}

    // Constructors, getters, setters
    
    
}