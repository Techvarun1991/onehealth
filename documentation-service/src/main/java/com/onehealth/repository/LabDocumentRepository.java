package com.onehealth.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.onehealth.entity.LabDocument;

public interface LabDocumentRepository extends MongoRepository<LabDocument, String> {

    /**
     * Finds lab documents by lab ID.
     *
     * @param labId The ID of the lab.
     * @return A list of lab documents for the given lab ID.
     */
    List<LabDocument> findByLabId(long labId);

    /**
     * Deletes lab documents by lab ID.
     *
     * @param labId The ID of the lab.
     */
    void deleteByLabId(long labId);
}

