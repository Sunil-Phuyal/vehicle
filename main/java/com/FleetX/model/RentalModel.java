package com.FleetX.model;
import java.sql.Date;

/**
 * RentalModel represents a vehicle rental transaction in the FleetX system.
 * This class contains all details related to a rental including user information,
 * vehicle details, rental period, locations, status, and financial information.
 */
public class RentalModel {
	// Unique identifier for the rental transaction
	private int rentalId;
	// User ID of the customer who rented the vehicle
	private int userId;
	// ID of the vehicle being rented
	private int vehicleId;
	// Date when the rental period begins
	private Date startDate;
	// Date when the rental period ends
	private Date endDate;
	// Pickup location address
	private String address;
	// Drop-off location address
	private String dropAddress;
	// Current status of the rental (e.g., pending, active, completed, cancelled)
	private String status;
	// Total cost of the rental
	private double amount;
	// Name/model of the rented vehicle
	private String vehicleName;
	
	/**
	 * Default constructor for creating an empty RentalModel instance
	 */
	public RentalModel() {
	}
	
	/**
	 * Constructor for minimal rental information, typically used for summary views
	 * 
	 * @param rentalId Unique identifier for the rental
	 * @param status Current status of the rental
	 * @param amount Total cost of the rental
	 * @param vehicleName Name/model of the rented vehicle
	 */
	public RentalModel(int rentalId, String status, double amount, String vehicleName) {
		this.rentalId = rentalId;
		this.status = status;
		this.amount = amount;
		this.vehicleName = vehicleName;
	}
	
	/**
	 * Constructor without financial information, typically used during rental creation
	 * 
	 * @param rentalId Unique identifier for the rental
	 * @param userId User ID of the customer
	 * @param vehicleId ID of the rented vehicle
	 * @param startDate Beginning date of rental period
	 * @param endDate Ending date of rental period
	 * @param address Pickup location address
	 * @param dropAddress Drop-off location address
	 * @param status Current status of the rental
	 */
	public RentalModel(int rentalId, int userId, int vehicleId, Date startDate, Date endDate, String address,
			String dropAddress, String status) {
		this.rentalId = rentalId;
		this.userId = userId;
		this.vehicleId = vehicleId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.address = address;
		this.dropAddress = dropAddress;
		this.status = status;
	}
	
	/**
	 * Complete constructor with all rental details including financial information
	 * 
	 * @param rentalId Unique identifier for the rental
	 * @param userId User ID of the customer
	 * @param vehicleId ID of the rented vehicle
	 * @param startDate Beginning date of rental period
	 * @param endDate Ending date of rental period
	 * @param address Pickup location address
	 * @param dropAddress Drop-off location address
	 * @param status Current status of the rental
	 * @param amount Total cost of the rental
	 */
	public RentalModel(int rentalId, int userId, int vehicleId, Date startDate, Date endDate, String address, String dropAddress,
			String status, double amount) {
		this.rentalId = rentalId;
		this.userId = userId;
		this.vehicleId = vehicleId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.address = address;
		this.dropAddress = dropAddress;
		this.status = status;
		this.amount = amount;
	}
	
	/**
	 * Returns the unique identifier for this rental
	 * 
	 * @return The rental ID
	 */
	public int getRentalId() {
		return rentalId;
	}
	
	/**
	 * Sets the unique identifier for this rental
	 * 
	 * @param rentalId The rental ID to set
	 */
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}
	
	/**
	 * Returns the user ID of the customer who rented the vehicle
	 * 
	 * @return The user ID
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * Sets the user ID of the customer who rented the vehicle
	 * 
	 * @param userId The user ID to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * Returns the ID of the vehicle being rented
	 * 
	 * @return The vehicle ID
	 */
	public int getVehicleId() {
		return vehicleId;
	}
	
	/**
	 * Sets the ID of the vehicle being rented
	 * 
	 * @param vehicleId The vehicle ID to set
	 */
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	/**
	 * Returns the date when the rental period begins
	 * 
	 * @return The start date
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * Sets the date when the rental period begins
	 * 
	 * @param startDate The start date to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Returns the date when the rental period ends
	 * 
	 * @return The end date
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Sets the date when the rental period ends
	 * 
	 * @param endDate The end date to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Returns the pickup location address
	 * 
	 * @return The pickup address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the pickup location address
	 * 
	 * @param address The pickup address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Returns the current status of the rental
	 * 
	 * @return The rental status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets the current status of the rental
	 * 
	 * @param status The rental status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Returns the drop-off location address
	 * 
	 * @return The drop-off address
	 */
	public String getDropAddress() {
		return dropAddress;
	}
	
	/**
	 * Sets the drop-off location address
	 * 
	 * @param dropAddress The drop-off address to set
	 */
	public void setDropAddress(String dropAddress) {
		this.dropAddress = dropAddress;
	}
	
	/**
	 * Returns the total cost of the rental
	 * 
	 * @return The rental amount
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * Sets the total cost of the rental
	 * 
	 * @param amount The rental amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	/**
	 * Returns the name/model of the rented vehicle
	 * 
	 * @return The vehicle name
	 */
	public String getVehicleName() {
		return vehicleName;
	}
	
	/**
	 * Sets the name/model of the rented vehicle
	 * 
	 * @param vehicleName The vehicle name to set
	 */
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	
	/**
	 * Calculates the total number of days in the rental period
	 * Returns at least 1 day even for same-day rentals
	 * 
	 * @return The number of rental days or 0 if dates are not set
	 */
	public int getRentalDays() {
		if (startDate == null || endDate == null) {
			return 0;
		}
		long diffMillis = endDate.getTime() - startDate.getTime();
		return Math.max(1, (int) (diffMillis / (1000 * 60 * 60 * 24)));
	}
}
