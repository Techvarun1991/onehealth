package com.onehealth.entity;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "patient_documents")
public class PatientDocument {
    
    @Id
    private String id;
    private String filename;
    private String fileType;
    private String fileSize;
    private byte[] file;
    private long patientId;
    private String recordType;
	@Override
	public String toString() {
		return "PatientDocument [id=" + id + ", filename=" + filename + ", fileType=" + fileType + ", fileSize="
				+ fileSize + ", file=" + Arrays.toString(file) + ", patientId=" + patientId + ", recordType="
				+ recordType + "]";
	}
	public PatientDocument() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PatientDocument(String id, String filename, String fileType, String fileSize, byte[] file, long patientId,
			String recordType) {
		super();
		this.id = id;
		this.filename = filename;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.file = file;
		this.patientId = patientId;
		this.recordType = recordType;
	}
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
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
    

    
    
}