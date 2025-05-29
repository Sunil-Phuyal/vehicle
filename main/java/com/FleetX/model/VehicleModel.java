package com.FleetX.model;

import java.math.BigDecimal;

/**
 * VehicleModel represents a vehicle entity in the FleetX rental system.
 * This class stores comprehensive information about vehicles available for rent,
 * including specifications, pricing, status, and descriptive details.
 */
public class VehicleModel {

    // Unique identifier for the vehicle
    private int id;
    // Vehicle category (e.g., sedan, SUV, truck, van)
    private String category;
    // Manufacturer brand (e.g., Toyota, Honda, Ford)
    private String brand;
    // Specific model name of the vehicle
    private String model;
    // Manufacturing year of the vehicle
    private int year;
    // Official registration/license plate number
    private String registrationNumber;
    // Daily rental rate in currency (using BigDecimal for financial precision)
    private BigDecimal dailyRate;
    // Type of fuel the vehicle uses (e.g., gasoline, diesel, electric)
    private String fuelType;
    // Transmission type (e.g., automatic, manual)
    private String transmission;
    // Passenger capacity of the vehicle
    private int capacity;
    // Current status of the vehicle (e.g., available, rented, maintenance)
    private String status;
    // URL to the vehicle's image for display purposes
    private String imageUrl;
    // Current location of the vehicle (city or specific address)
    private String location;
    // Detailed description of the vehicle
    private String description;
    // Special features or amenities of the vehicle (e.g., GPS, bluetooth, sunroof)
    private String features;

    /**
     * Default constructor for creating an empty VehicleModel instance
     */
    public VehicleModel() {
        // Default constructor
    }

    /**
     * Complete constructor with all vehicle properties including ID
     * Typically used when retrieving existing vehicle data from database
     * 
     * @param id Unique vehicle identifier
     * @param category Vehicle category
     * @param brand Manufacturer brand
     * @param model Vehicle model name
     * @param year Manufacturing year
     * @param registrationNumber Registration/license plate number
     * @param dailyRate Daily rental cost
     * @param fuelType Type of fuel used
     * @param transmission Transmission type
     * @param capacity Passenger capacity
     * @param status Current availability status
     * @param imageUrl URL to vehicle image
     * @param location Current vehicle location
     * @param description Detailed vehicle description
     * @param features Special vehicle features
     */
    public VehicleModel(int id, String category, String brand, String model, int year, String registrationNumber,
            BigDecimal dailyRate, String fuelType, String transmission, int capacity, String status, String imageUrl,
            String location, String description, String features) {
        this.id = id;
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.dailyRate = dailyRate;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.capacity = capacity;
        this.status = status;
        this.imageUrl = imageUrl;
        this.location = location;
        this.description = description;
        this.features = features;
    }

    /**
     * Constructor for creating a new vehicle without ID (not yet stored in database)
     * Typically used when adding a new vehicle to the system
     * 
     * @param category Vehicle category
     * @param brand Manufacturer brand
     * @param model Vehicle model name
     * @param year Manufacturing year
     * @param registrationNumber Registration/license plate number
     * @param dailyRate Daily rental cost
     * @param fuelType Type of fuel used
     * @param transmission Transmission type
     * @param capacity Passenger capacity
     * @param status Current availability status
     * @param imageUrl URL to vehicle image
     * @param location Current vehicle location
     * @param description Detailed vehicle description
     * @param features Special vehicle features
     */
    public VehicleModel(String category, String brand, String model, int year, String registrationNumber, BigDecimal dailyRate,
            String fuelType, String transmission, int capacity, String status, String imageUrl, String location,
            String description, String features) {
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.dailyRate = dailyRate;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.capacity = capacity;
        this.status = status;
        this.imageUrl = imageUrl;
        this.location = location;
        this.description = description;
        this.features = features;
    }

    /**
     * Simplified constructor with only essential properties for vehicle listings
     * Typically used for summary displays in search results or browsing views
     * 
     * @param id Unique vehicle identifier
     * @param brand Manufacturer brand
     * @param model Vehicle model name
     * @param dailyRate Daily rental cost
     * @param transmission Transmission type
     * @param capacity Passenger capacity
     * @param imageUrl URL to vehicle image
     */
    public VehicleModel(int id, String brand, String model, BigDecimal dailyRate, String transmission, int capacity,
            String imageUrl) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.dailyRate = dailyRate;
        this.transmission = transmission;
        this.capacity = capacity;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    
    /**
     * Returns the vehicle's unique identifier
     * 
     * @return The vehicle ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * Sets the vehicle's unique identifier
     * 
     * @param id The vehicle ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the vehicle's category
     * 
     * @return The vehicle category
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * Sets the vehicle's category
     * 
     * @param category The vehicle category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the manufacturer brand
     * 
     * @return The vehicle brand
     */
    public String getBrand() {
        return brand;
    }
    
    /**
     * Sets the manufacturer brand
     * 
     * @param brand The vehicle brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Returns the vehicle's model name
     * 
     * @return The vehicle model
     */
    public String getModel() {
        return model;
    }
    
    /**
     * Sets the vehicle's model name
     * 
     * @param model The vehicle model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Returns the manufacturing year of the vehicle
     * 
     * @return The vehicle year
     */
    public int getYear() {
        return year;
    }
    
    /**
     * Sets the manufacturing year of the vehicle
     * 
     * @param year The vehicle year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns the vehicle's registration/license plate number
     * 
     * @return The registration number
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    
    /**
     * Sets the vehicle's registration/license plate number
     * 
     * @param registrationNumber The registration number to set
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Returns the daily rental rate for the vehicle
     * 
     * @return The daily rate in BigDecimal for financial precision
     */
    public BigDecimal getDailyRate() {
        return dailyRate;
    }
    
    /**
     * Sets the daily rental rate for the vehicle
     * 
     * @param dailyRate The daily rate in BigDecimal for financial precision
     */
    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }

    /**
     * Returns the type of fuel the vehicle uses
     * 
     * @return The fuel type
     */
    public String getFuelType() {
        return fuelType;
    }
    
    /**
     * Sets the type of fuel the vehicle uses
     * 
     * @param fuelType The fuel type to set
     */
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * Returns the vehicle's transmission type
     * 
     * @return The transmission type
     */
    public String getTransmission() {
        return transmission;
    }
    
    /**
     * Sets the vehicle's transmission type
     * 
     * @param transmission The transmission type to set
     */
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    /**
     * Returns the passenger capacity of the vehicle
     * 
     * @return The passenger capacity
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Sets the passenger capacity of the vehicle
     * 
     * @param capacity The passenger capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Returns the current availability status of the vehicle
     * 
     * @return The vehicle status
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * Sets the current availability status of the vehicle
     * 
     * @param status The vehicle status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the URL to the vehicle's image
     * 
     * @return The image URL
     */
    public String getImageUrl() {
        return imageUrl;
    }
    
    /**
     * Sets the URL to the vehicle's image
     * 
     * @param imageUrl The image URL to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Returns the current location of the vehicle
     * 
     * @return The vehicle location
     */
    public String getLocation() {
        return location;
    }
    
    /**
     * Sets the current location of the vehicle
     * 
     * @param location The vehicle location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns the detailed description of the vehicle
     * 
     * @return The vehicle description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the detailed description of the vehicle
     * 
     * @param description The vehicle description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the special features and amenities of the vehicle
     * 
     * @return The vehicle features
     */
    public String getFeatures() {
        return features;
    }
    
    /**
     * Sets the special features and amenities of the vehicle
     * 
     * @param features The vehicle features to set
     */
    public void setFeatures(String features) {
        this.features = features;
    }
}
