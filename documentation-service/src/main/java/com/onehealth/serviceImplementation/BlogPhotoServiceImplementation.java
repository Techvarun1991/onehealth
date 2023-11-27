package com.onehealth.serviceImplementation;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onehealth.entity.BlogPhoto;
import com.onehealth.exception.DatabaseException;
import com.onehealth.repository.BlogPhotoRepository;
import com.onehealth.service.BlogPhotoService;

/**
 * Service implementation class that handles operations related to BlogPhotos.
 */
@Service
public class BlogPhotoServiceImplementation implements BlogPhotoService {

    // Autowired BlogPhotoRepository to interact with the database.
    @Autowired
    private BlogPhotoRepository blogPhotoRepository;

    // Logger for logging service actions.
    private static final Logger logger = Logger.getLogger(BlogPhotoServiceImplementation.class.getName());

    /**
     * Retrieves all BlogPhotos associated with a given blog ID from the repository.
     *
     * @param blog_id The ID of the blog to get BlogPhotos for.
     * @return A list of BlogPhoto objects associated with the specified blog ID.
     * @throws DatabaseException If there is an error while accessing the database.
     */
    @Override
    public List<BlogPhoto> getAllByBlogId(long blog_id) throws DatabaseException {
        List<BlogPhoto> blogPhotos = blogPhotoRepository.findByBlogId(blog_id);
        logger.log(Level.INFO, "Retrieved " + blogPhotos.size() + " BlogPhotos for blog ID: " + blog_id);
        return blogPhotos;
    }

    /**
     * Updates the attributes of an existing BlogPhoto in the repository.
     *
     * @param blogPhoto The modified BlogPhoto object to update.
     * @throws DatabaseException If there is an error while accessing the database.
     */
    @Override
    public void updateBlogPhoto(BlogPhoto blogPhoto) throws DatabaseException {
        blogPhotoRepository.save(blogPhoto);
        logger.log(Level.INFO, "BlogPhoto updated successfully with ID: " + blogPhoto.getId());
    }

    /**
     * Stores a new BlogPhoto in the repository.
     *
     * @param file The MultipartFile representing the blog photo file to be stored.
     * @return The ID of the newly stored BlogPhoto.
     * @throws IOException If there is an I/O error while reading the file.
     * @throws DatabaseException If there is an error while accessing the database.
     */
    @Override
    public String storeBlogPhoto(MultipartFile file,long blogId) throws IOException, DatabaseException {
        try {
            BlogPhoto blogPhoto = new BlogPhoto();
            blogPhoto.setFilename(file.getOriginalFilename());
            blogPhoto.setFileType(file.getContentType());
            blogPhoto.setFileSize(Long.toString(file.getSize()));
            blogPhoto.setFile(file.getBytes());
            blogPhoto.setBlogId(blogId);
            blogPhotoRepository.save(blogPhoto);
            logger.log(Level.INFO, "BlogPhoto stored successfully with ID: " + blogPhoto.getId());
            return blogPhoto.getId();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred while storing BlogPhoto: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Deletes a BlogPhoto with the given ID from the repository.
     *
     * @param id The ID of the BlogPhoto to delete.
     * @throws DatabaseException If there is an error while accessing the database.
     */
    @Override
    public void deleteBlogPhoto(String id) throws DatabaseException {
        blogPhotoRepository.deleteById(id);
        logger.log(Level.INFO, "BlogPhoto deleted successfully with ID: " + id);
    }
    
    /**
     * Deletes all blog photos associated with a specific blog ID.
     *
     * @param blogId The ID of the blog.
     * @return ResponseEntity with a success message and status 200 if successful.
     */
    @Override
    public void deleteBlogPhotosByBlogId(long blogId) throws DatabaseException {
        try {
        	blogPhotoRepository.deleteByBlogId(blogId);
            logger.log(Level.INFO, "Deleted all Patient Documents for patient ID: " + blogId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting Patient Documents for patient ID: " + blogId, e);
            throw new DatabaseException("Error occurred while deleting Patient Documents");
        }
    }
}
