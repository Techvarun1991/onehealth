package com.onehealth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.onehealth.entity.LabCart;

/**
 * The LabCartRepository interface defines database operations for managing lab carts,
 * including retrieving a lab cart by patient ID.
 */
public interface LabCartRepository extends JpaRepository<LabCart, Long> {

    /**
     * Retrieves a lab cart by the patient's unique identifier.
     *
     * @param patientId The ID of the patient for whom to retrieve the lab cart.
     * @return The lab cart associated with the specified patient ID, or null if not found.
     */
    LabCart findByPatientId(long patientId);
}
