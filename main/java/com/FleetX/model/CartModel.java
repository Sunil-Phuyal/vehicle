package com.FleetX.model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * CartModel represents a vehicle rental cart item in the FleetX system.
 * This class stores all information related to a vehicle rental including
 * vehicle details, pricing, date range, and pickup/dropoff locations.
 */
public class CartModel {
    // Vehicle information
    private int vehicleId;
    private String brand;
    private String model;
    private String imageUrl;
    
    // Pricing information
    private BigDecimal dailyRate;
    
    // Rental period
    private Date startDate;
    private Date endDate;
    
    // Location information
    private String pickupLocation;
    private String dropupLocation;

    /**
     * Default constructor
     */
    public CartModel() {
    }

    /**
     * Parameterized constructor to initialize all fields
     * 
     * @param vehicleId       Unique identifier for the vehicle
     * @param brand           Vehicle brand name
     * @param model           Vehicle model name
     * @param imageUrl        URL to the vehicle image
     * @param dailyRate       Daily rental rate
     * @param startDate       Rental start date
     * @param endDate         Rental end date
     * @param pickupLocation  Location where vehicle will be picked up
     * @param dropupLocation  Location where vehicle will be returned
     */
    public CartModel(int vehicleId, String brand, String model, String imageUrl, BigDecimal dailyRate, Date startDate, Date endDate, String pickupLocation, String dropupLocation) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.imageUrl = imageUrl;
        this.dailyRate = dailyRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pickupLocation = pickupLocation;
        this.dropupLocation = dropupLocation;
    }

    /**
     * Gets the vehicle ID
     * @return The vehicle ID
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * Sets the vehicle ID
     * @param vehicleId The vehicle ID to set
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Gets the vehicle brand
     * @return The vehicle brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the vehicle brand
     * @param brand The brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Gets the vehicle model
     * @return The vehicle model
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the vehicle model
     * @param model The model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets the image URL
     * @return The image URL
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image URL
     * @param imageUrl The image URL to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets the daily rental rate
     * @return The daily rate as BigDecimal
     */
    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    /**
     * Sets the daily rental rate
     * @param dailyRate The daily rate to set
     */
    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }

    /**
     * Gets the rental start date
     * @return The start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the rental start date
     * @param startDate The start date to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the rental end date
     * @return The end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the rental end date
     * @param endDate The end date to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the pickup location
     * @return The pickup location
     */
    public String getPickupLocation() {
        return pickupLocation;
    }

    /**
     * Sets the pickup location
     * @param pickupLocation The pickup location to set
     */
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    /**
     * Calculates the total number of rental days
     * @return The number of days between startDate and endDate (minimum 1)
     */
    public int getRentalDays() {
        long diffMillis = endDate.getTime() - startDate.getTime();
        return Math.max(1, (int) (diffMillis / (1000 * 60 * 60 * 24)));
    }

    /**
     * Calculates the total price for the rental period
     * @return The total price (daily rate × number of days)
     */
    public BigDecimal getTotalPrice() {
        BigDecimal rentalDays = new BigDecimal(getRentalDays());
        return dailyRate.multiply(rentalDays);  // Return total price as BigDecimal
    }

    /**
     * Gets the dropoff location
     * @return The dropoff location
     */
	public String getDropupLocation() {
		return dropupLocation;
	}

    /**
     * Sets the dropoff location
     * @param dropupLocation The dropoff location to set
     */
	public void setDropupLocationString(String dropupLocation) {
		this.dropupLocation = dropupLocation;
	}
}
