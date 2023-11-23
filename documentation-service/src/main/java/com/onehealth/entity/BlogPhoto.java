package com.onehealth.entity;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The BlogPhoto class represents a blog photo entity in the application.
 * It is annotated with @Document to specify the MongoDB collection it maps to.
 */
@Document(collection = "blog_photos")
public class BlogPhoto {
    @Id
    private String id;
    private String filename;
    private String fileType;
    private String fileSize;
    private byte[] file;
    private long blogId;

    /**
     * Getter for the ID of the blog photo.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the ID of the blog photo.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for the filename of the blog photo.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Setter for the filename of the blog photo.
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Getter for the file type of the blog photo.
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Setter for the file type of the blog photo.
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * Getter for the file size of the blog photo.
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * Setter for the file size of the blog photo.
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * Getter for the file data of the blog photo.
     */
    public byte[] getFile() {
        return file;
    }

    /**
     * Setter for the file data of the blog photo.
     */
    public void setFile(byte[] file) {
        this.file = file;
    }

    /**
     * Getter for the ID of the blog associated with the photo.
     */
    public long getBlogId() {
        return blogId;
    }

    /**
     * Setter for the ID of the blog associated with the photo.
     */
    public void setBlogId(long blogId) {
        this.blogId = blogId;
    }

    /**
     * Overrides the toString() method to provide a string representation of the BlogPhoto object.
     */
    @Override
    public String toString() {
        return "BlogPhoto [id=" + id + ", filename=" + filename + ", fileType=" + fileType + ", fileSize=" + fileSize
                + ", file=" + Arrays.toString(file) + ", blogId=" + blogId + "]";
    }

    /**
     * Default constructor for the BlogPhoto class.
     */
    public BlogPhoto() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Parameterized constructor for the BlogPhoto class.
     */
    public BlogPhoto(String id, String filename, String fileType, String fileSize, byte[] file, long blogId) {
        super();
        this.id = id;
        this.filename = filename;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.file = file;
        this.blogId = blogId;
    }
}
