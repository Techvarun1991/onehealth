package com.onehealth.dto;

import java.sql.Time;
import java.sql.Timestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Represents a laboratory in the healthcare system.
 * Each laboratory has a unique identifier (lab_id) and holds information
 * such as name, address, registration timestamp, certifications, open and close times, etc.
 */
public class LabManagement {

    // Unique identifier for the laboratory
    private long lab_id;

    // Name of the laboratory
    private String lab_name;

    // Address of the laboratory
    private String address;

    // Postal code of the laboratory location
    private int pincode;

    // City where the laboratory is located
    private String city;

    // Timestamp representing the registration date and time of the laboratory
    private Timestamp registration_timestamp;

    // Certificate ID associated with the laboratory
    private String lab_cert_id;

    // Opening time of the laboratory
    private Time open_time;

    // Closing time of the laboratory
    private Time close_time;

    // License number of the laboratory
    private String licence_no;
    
    
    private boolean isActive;

    /**
     * Default constructor for the LabDto class.
     * Initializes all attributes to their default values.
     */
    public LabManagement() {
        super();
    }

    /**
     * Parameterized constructor for the LabDto class.
     * Initializes the attributes with the provided values.
     *
     * @param lab_id                  The unique identifier for the laboratory.
     * @param lab_name                The name of the laboratory.
     * @param address                 The address of the laboratory.
     * @param pincode                 The postal code of the laboratory location.
     * @param city                    The city where the laboratory is located.
     * @param registration_timestamp The timestamp representing the registration date and time of the laboratory.
     * @param lab_cert_id             The certificate ID associated with the laboratory.
     * @param open_time               The opening time of the laboratory.
     * @param close_time              The closing time of the laboratory.
     * @param licence_no              The license number of the laboratory.
     */
    public LabManagement(long lab_id, String lab_name, String address, int pincode, String city,
            Timestamp registration_timestamp, String lab_cert_id, Time open_time,
            Time close_time, String licence_no , boolean isActive) {
			this.lab_id = lab_id;
			this.lab_name = lab_name;
			this.address = address;
			this.pincode = pincode;
			this.city = city;
			this.registration_timestamp = registration_timestamp;
			this.lab_cert_id = lab_cert_id;
			this.open_time = open_time;
			this.close_time = close_time;
			this.licence_no = licence_no;
			this.isActive = isActive;
			}

    // Getter and Setter methods for all attributes

    /**
     * Get the unique identifier for the laboratory.
     *
     * @return The lab_id representing the primary key for the laboratory.
     */
    public long getLab_id() {
        return lab_id;
    }

    /**
     * Set the unique identifier for the laboratory.
     *
     * @param lab_id The lab_id to set as the primary key for the laboratory.
     */
    public void setLab_id(long lab_id) {
        this.lab_id = lab_id;
    }

    /**
     * Get the name of the laboratory.
     *
     * @return The lab_name representing the name of the laboratory.
     */
    public String getLab_name() {
        return lab_name;
    }

    /**
     * Set the name of the laboratory.
     *
     * @param lab_name The lab_name to set as the name of the laboratory.
     */
    public void setLab_name(String lab_name) {
        this.lab_name = lab_name;
    }

    /**
     * Get the address of the laboratory.
     *
     * @return The address representing the location of the laboratory.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the address of the laboratory.
     *
     * @param address The address to set as the location of the laboratory.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the postal code of the laboratory location.
     *
     * @return The pincode representing the postal code of the laboratory location.
     */
    public int getPincode() {
        return pincode;
    }

    /**
     * Set the postal code of the laboratory location.
     *
     * @param pincode The pincode to set as the postal code of the laboratory location.
     */
    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    /**
     * Get the city where the laboratory is located.
     *
     * @return The city representing the location city of the laboratory.
     */
    public String getCity() {
        return city;
    }

    /**
     * Set the city where the laboratory is located.
     *
     * @param city The city to set as the location city of the laboratory.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get the registration timestamp of the laboratory.
     *
     * @return The registration_timestamp representing the date and time of laboratory registration.
     */
    public Timestamp getRegistration_timestamp() {
        return registration_timestamp;
    }

    /**
     * Set the registration timestamp of the laboratory.
     *
     * @param registration_timestamp The registration_timestamp to set as the date and time of laboratory registration.
     */
    public void setRegistration_timestamp(Timestamp registration_timestamp) {
        this.registration_timestamp = registration_timestamp;
    }

    /**
     * Get the certificate ID associated with the laboratory.
     *
     * @return The lab_cert_id representing the certificate ID associated with the laboratory.
     */
    public String getLab_cert_id() {
        return lab_cert_id;
    }

    /**
     * Set the certificate ID associated with the laboratory.
     *
     * @param lab_cert_id The lab_cert_id to set as the certificate ID associated with the laboratory.
     */
    public void setLab_cert_id(String lab_cert_id) {
        this.lab_cert_id = lab_cert_id;
    }

    /**
     * Get the opening time of the laboratory.
     *
     * @return The open_time representing the opening time of the laboratory.
     */
    public Time getOpen_time() {
        return open_time;
    }

    /**
     * Set the opening time of the laboratory.
     *
     * @param open_time The open_time to set as the opening time of the laboratory.
     */
    public void setOpen_time(Time open_time) {
        this.open_time = open_time;
    }

    /**
     * Get the closing time of the laboratory.
     *
     * @return The close_time representing the closing time of the laboratory.
     */
    public Time getClose_time() {
        return close_time;
    }

    /**
     * Set the closing time of the laboratory.
     *
     * @param close_time The close_time to set as the closing time of the laboratory.
     */
    public void setClose_time(Time close_time) {
        this.close_time = close_time;
    }

    /**
     * Get the license number of the laboratory.
     *
     * @return The licence_no representing the license number of the laboratory.
     */
    public String getLicence_no() {
        return licence_no;
    }

    /**
     * Set the license number of the laboratory.
     *
     * @param licence_no The licence_no to set as the license number of the laboratory.
     */
    public void setLicence_no(String licence_no) {
        this.licence_no = licence_no;
    }
    public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
     * Returns a string representation of the LabManagement object.
     *
     * @return A string representing the LabManagement object's attributes.
     */
    @Override
	public String toString() {
		return "LabManagement [lab_id=" + lab_id + ", lab_name=" + lab_name + ", address=" + address + ", pincode="
				+ pincode + ", city=" + city + ", registration_timestamp=" + registration_timestamp + ", lab_cert_id="
				+ lab_cert_id + ", open_time=" + open_time + ", close_time=" + close_time + ", licence_no=" + licence_no
				+ ", isActive=" + isActive + "]";
	}
}

