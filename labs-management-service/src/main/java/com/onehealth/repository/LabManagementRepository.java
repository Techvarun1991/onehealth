package com.onehealth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onehealth.entity.LabManagement;

/**
 * This interface extends JpaRepository, providing CRUD and pagination operations for the LabManagement entity.
 * It allows easy interaction with the underlying database table for LabManagement.
 */
public interface LabManagementRepository extends JpaRepository<LabManagement, Long>{

    /**
     * Custom method to find LabManagement entities by city.
     *
     * @param city The city to search for.
     * @return A list of LabManagement entities with the given city.
     */
    List<LabManagement> findByCity(String city);
}
