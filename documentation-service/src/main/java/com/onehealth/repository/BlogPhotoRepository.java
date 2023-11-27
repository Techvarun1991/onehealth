package com.onehealth.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.onehealth.entity.BlogPhoto;

/**
 * The BlogPhotoRepository interface extends the MongoRepository interface
 * to perform CRUD operations on the "blog_photos" collection in MongoDB.
 */
public interface BlogPhotoRepository extends MongoRepository<BlogPhoto, String> {

    /**
     * Finds and returns a list of BlogPhotos associated with the specified blogId.
     *
     * @param blogId The ID of the blog for which BlogPhotos are to be retrieved.
     * @return A list of BlogPhoto objects associated with the specified blogId.
     */
    List<BlogPhoto> findByBlogId(long blogId);
    
    /**
     * Deletes blog documents by blog ID.
     *
     * @param blogId The ID of the blog.
     */
    void deleteByBlogId(long blogId);
}
