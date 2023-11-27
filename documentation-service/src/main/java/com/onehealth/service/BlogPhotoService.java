package com.onehealth.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.BlogPhoto;
import com.onehealth.exception.DatabaseException;

/**
 * The BlogPhotoService interface provides methods to perform CRUD operations
 * on BlogPhoto entities and retrieve BlogPhotos based on blog_id.
 */
public interface BlogPhotoService {

    /**
     * Retrieves a list of BlogPhotos associated with the specified blog_id.
     * 
     * @param blog_id The ID of the blog for which BlogPhotos are to be retrieved.
     * @return A List of BlogPhotos associated with the specified blog_id.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    List<BlogPhoto> getAllByBlogId(long blog_id) throws DatabaseException;

    /**
     * Deletes a BlogPhoto with the given ID from the database.
     * 
     * @param id The ID of the BlogPhoto to be deleted.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    void deleteBlogPhoto(String id) throws DatabaseException;

    /**
     * Stores a new BlogPhoto in the database.
     * 
     * @param file The MultipartFile representing the image file to be stored as the BlogPhoto.
     * @param blog Id
     * @return The ID of the newly stored BlogPhoto.
     * @throws IOException      If an I/O error occurs while reading the file.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    String storeBlogPhoto(MultipartFile file,long blogId) throws IOException, DatabaseException;

    /**
     * Updates an existing BlogPhoto in the database.
     * 
     * @param blogPhoto The updated BlogPhoto object with modified attributes.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    void updateBlogPhoto(BlogPhoto blogPhoto) throws DatabaseException;

    /**
     * Deletes all blog photos associated with a specific blog ID.
     *
     * @param blogId The ID of the blog.
     * @return ResponseEntity with a success message and status 200 if successful.
     * @throws DatabaseException 
     */
	void deleteBlogPhotosByBlogId(long blogId) throws DatabaseException;
}
