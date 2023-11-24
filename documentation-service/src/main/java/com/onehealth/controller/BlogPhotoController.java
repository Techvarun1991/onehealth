package com.onehealth.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

//import com.google.common.net.MediaType;
import com.onehealth.entity.BlogPhoto;
import com.onehealth.exception.DatabaseException;
import com.onehealth.service.BlogPhotoService;

import jakarta.servlet.MultipartConfigElement;

import java.util.logging.Logger;


/**
 * Controller class to handle HTTP requests related to blog photos.
 * This class maps incoming requests to the specified URL path "/blog_photo"
 * and delegates the request handling to the BlogPhotoService.
 */
@RestController
//@CrossOrigin("*")
@RequestMapping("api/documentation/blog_photo")
public class BlogPhotoController {
	
	
    @Autowired
    private BlogPhotoService blogPhotoService;

    private static final Logger logger = Logger.getLogger(BlogPhotoController.class.getName());


    

    /**
     * Deletes a BlogPhoto with the given ID from the repository.
     *
     * @param id The ID of the BlogPhoto to delete.
     * @return ResponseEntity with a success message and a 200 OK status if deletion is successful.
     * @throws DatabaseException If there's an error while deleting the BlogPhoto from the repository.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlogPhoto(@PathVariable String id) throws DatabaseException {
        try {
            blogPhotoService.deleteBlogPhoto(id);
            logger.log(Level.INFO, "BlogPhoto deleted successfully with ID: " + id);
            return ResponseEntity.ok("Blog Photo deleted successfully.");
        } catch (DatabaseException e) {
            logger.log(Level.SEVERE, "Error occurred while deleting BlogPhoto: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Stores/uploads a new BlogPhoto in the repository.
     *
     * @param file The MultipartFile representing the photo to upload.
     * @return ResponseEntity with a success message and a 200 OK status if upload is successful.
     * @throws IOException       If there's an error while handling the uploade d file.
     * @throws DatabaseException If there's an error while storing the BlogPhoto in the repository.
     */
    @PostMapping
    public ResponseEntity<String> storeBlogPhoto(@RequestPart MultipartFile file) throws IOException, DatabaseException {
        try {
            // Your existing logic to handle the file
            String fileId = blogPhotoService.storeBlogPhoto(file);
            logger.log(Level.INFO, "BlogPhoto uploaded successfully with ID: " + fileId);
            return ResponseEntity.ok("Blog Photo uploaded successfully. File ID: " + fileId);
        } catch (IOException | DatabaseException e) {
            logger.log(Level.SEVERE, "Error occurred while uploading BlogPhoto: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Updates an existing BlogPhoto in the repository.
     *
     * @param id        The ID of the BlogPhoto to update.
     * @param blogPhoto The BlogPhoto object containing updated data.
     * @return ResponseEntity with a success message and a 200 OK status if update is successful.
     * @throws DatabaseException If there's an error while updating the BlogPhoto in the repository.
     */
    @PutMapping
    public ResponseEntity<String> updateBlogPhoto(@PathVariable String id, @RequestBody BlogPhoto blogPhoto)
            throws DatabaseException {
        try {
            blogPhoto.setId(id);
            blogPhotoService.updateBlogPhoto(blogPhoto);
            logger.log(Level.INFO, "BlogPhoto updated successfully with ID: " + id);
            return ResponseEntity.ok("Blog Photo updated successfully.");
        } catch (DatabaseException e) {
            logger.log(Level.SEVERE, "Error occurred while updating BlogPhoto: " + e.getMessage());
            throw e;
        }
    }
}
