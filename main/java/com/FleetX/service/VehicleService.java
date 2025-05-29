package com.FleetX.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.FleetX.config.DbConfig;
import com.FleetX.model.VehicleModel;

/**
 * Service class for managing vehicle-related operations in the FleetX application.
 * Handles database operations for vehicle management including CRUD operations.
 */
public class VehicleService {
	// Database connection instance
	private Connection dbConnection;

	/**
	 * Constructor - initializes the database connection
	 */
	public VehicleService() {
		try {
			this.dbConnection = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database Connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Inserts a new vehicle into the database
	 * 
	 * @param vehicle The VehicleModel object containing vehicle details
	 * @return true if insertion successful, false otherwise
	 */
	public boolean insertVehicle(VehicleModel vehicle) {
		String sql = "INSERT INTO vehicle (" + "category, brand, model, year, registration_number, "
				+ "daily_rate, fuel_type, transmission, capacity, status, "
				+ "image_url, location, description, features" + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
			// Set all parameters for the prepared statement
			stmt.setString(1, vehicle.getCategory());
			stmt.setString(2, vehicle.getBrand());
			stmt.setString(3, vehicle.getModel());
			stmt.setInt(4, vehicle.getYear());
			stmt.setString(5, vehicle.getRegistrationNumber());
			stmt.setBigDecimal(6, vehicle.getDailyRate());
			stmt.setString(7, vehicle.getFuelType());
			stmt.setString(8, vehicle.getTransmission());
			stmt.setInt(9, vehicle.getCapacity());
			stmt.setString(10, vehicle.getStatus());
			stmt.setString(11, vehicle.getImageUrl());
			stmt.setString(12, vehicle.getLocation());
			stmt.setString(13, vehicle.getDescription());
			stmt.setString(14, vehicle.getFeatures());

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Retrieves all vehicles from the database
	 * 
	 * @return List of VehicleModel objects
	 */
	public List<VehicleModel> getAllVehicles() {
		List<VehicleModel> vehicles = new ArrayList<>();
		String sql = "SELECT * FROM vehicle ORDER BY id DESC";

		try (PreparedStatement stmt = dbConnection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				vehicles.add(setVehicleResult(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicles;
	}

	/**
	 * Retrieves vehicles filtered by various criteria
	 * 
	 * @param fuel     Fuel type filter
	 * @param gear     Transmission type filter
	 * @param type     Vehicle category filter
	 * @param location Vehicle location filter
	 * @return List of filtered VehicleModel objects
	 */
	public List<VehicleModel> getFilteredVehicles(String fuel, String gear, String type, String location) {
		List<VehicleModel> vehicles = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM vehicle WHERE 1=1");
		List<Object> params = new ArrayList<>();

		// Dynamically build the query based on the provided filters
		if (fuel != null && !fuel.isEmpty()) {
			sql.append(" AND fuel_type = ?");
			params.add(fuel);
		}
		if (gear != null && !gear.isEmpty()) {
			sql.append(" AND transmission = ?");
			params.add(gear);
		}
		if (type != null && !type.isEmpty()) {
			sql.append(" AND category = ?");
			params.add(type);
		}
		if (location != null && !location.isEmpty()) {
			sql.append(" AND location = ?");
			params.add(location);
		}

		try (PreparedStatement stmt = dbConnection.prepareStatement(sql.toString())) {
			// Set all parameters for the prepared statement
			for (int i = 0; i < params.size(); i++) {
				stmt.setObject(i + 1, params.get(i));
			}

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					vehicles.add(setVehicleResult(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vehicles;
	}

	/**
	 * Retrieves a vehicle by its ID
	 * 
	 * @param id The vehicle ID to search for
	 * @return VehicleModel if found, null otherwise
	 */
	public VehicleModel getVehicleById(int id) {
		VehicleModel vehicle = null;
		String sql = "SELECT * FROM vehicle WHERE id = ?";
		try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				vehicle = setVehicleResult(rs); // Use helper method
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicle;
	}

	/**
	 * Helper method to create a VehicleModel object from database result set
	 * 
	 * @param rs ResultSet containing vehicle data
	 * @return Populated VehicleModel object
	 * @throws SQLException if database error occurs
	 */
	public VehicleModel setVehicleResult(ResultSet rs) throws SQLException {
		VehicleModel v = new VehicleModel();
		v.setId(rs.getInt("id"));
		v.setCategory(rs.getString("category"));
		v.setBrand(rs.getString("brand"));
		v.setModel(rs.getString("model"));
		v.setYear(rs.getInt("year"));
		v.setRegistrationNumber(rs.getString("registration_number"));
		v.setDailyRate(rs.getBigDecimal("daily_rate"));
		v.setFuelType(rs.getString("fuel_type"));
		v.setTransmission(rs.getString("transmission"));
		v.setCapacity(rs.getInt("capacity"));
		v.setStatus(rs.getString("status"));
		v.setImageUrl(rs.getString("image_url"));
		v.setLocation(rs.getString("location"));
		v.setDescription(rs.getString("description"));
		v.setFeatures(rs.getString("features"));
		return v;
	}

	/**
	 * Retrieves the 4 most recently added available vehicles
	 * 
	 * @return List of up to 4 VehicleModel objects
	 */
	public List<VehicleModel> getNewAddedVehicle() {
		List<VehicleModel> vehicles = new ArrayList<>();
		String sql = "SELECT * FROM vehicle WHERE status='available' ORDER BY id DESC LIMIT 4";

		try (PreparedStatement stmt = dbConnection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				// Create a simplified VehicleModel with only necessary fields
				vehicles.add(new VehicleModel(rs.getInt("id"), rs.getString("brand"), rs.getString("model"),
						rs.getBigDecimal("daily_rate"), rs.getString("transmission"), rs.getInt("capacity"),
						rs.getString("image_url")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicles;

	}

	/**
	 * Updates an existing vehicle in the database
	 * 
	 * @param vehicle The VehicleModel object containing updated details
	 * @return true if update successful, false otherwise
	 */
	public boolean updateVehicle(VehicleModel vehicle) {
		String sql = "UPDATE vehicle SET category=?, brand=?, model=?, year=?, registration_number=?, "
				+ "daily_rate=?, fuel_type=?, transmission=?, capacity=?, status=?, image_url=?, "
				+ "location=?, description=?, features=? WHERE id=?";

		try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
			// Set all parameters for the prepared statement
			stmt.setString(1, vehicle.getCategory());
			stmt.setString(2, vehicle.getBrand());
			stmt.setString(3, vehicle.getModel());
			stmt.setInt(4, vehicle.getYear());
			stmt.setString(5, vehicle.getRegistrationNumber());
			stmt.setBigDecimal(6, vehicle.getDailyRate());
			stmt.setString(7, vehicle.getFuelType());
			stmt.setString(8, vehicle.getTransmission());
			stmt.setInt(9, vehicle.getCapacity());
			stmt.setString(10, vehicle.getStatus());
			stmt.setString(11, vehicle.getImageUrl());
			stmt.setString(12, vehicle.getLocation());
			stmt.setString(13, vehicle.getDescription());
			stmt.setString(14, vehicle.getFeatures());
			stmt.setInt(15, vehicle.getId());

			int affectedRows = stmt.executeUpdate();
			return affectedRows > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error updating profile: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Deletes a vehicle from the database
	 * 
	 * @param vehicleId ID of the vehicle to delete
	 * @return true if deletion successful, false otherwise
	 */
	public boolean deleteVehicle(int vehicleId) {
		String query = "DELETE FROM vehicle WHERE id = ?";
		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, vehicleId);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Updates vehicle availability status based on rental end dates
	 * Marks rentals as 'completed' when end date is reached and updates vehicle status to 'available'
	 */
	public void updateVehicleAvailabilityBasedOnRentals() {
		// First update rentals to 'completed' status when end date is reached
		String updateRentalSql = """
				    UPDATE rental
				    SET status = 'completed'
				    WHERE end_date <= CURRENT_DATE AND status = 'booked'
				""";

		// Then update vehicle status and location based on completed rentals
		String updateVehicleSql = """
				    UPDATE vehicle
				    SET status = 'available', location = (
				        SELECT r.dropLocation
				        FROM rental r
				        WHERE r.vehicle_id = vehicle.id
				          AND r.end_date <= CURRENT_DATE
				          AND r.status = 'completed'
				        ORDER BY r.end_date DESC
				        LIMIT 1
				    )
				    WHERE id IN (
				        SELECT vehicle_id
				        FROM rental
				        WHERE end_date <= CURRENT_DATE AND status = 'completed'
				    )
				""";

		try (PreparedStatement ps1 = dbConnection.prepareStatement(updateRentalSql);
				PreparedStatement ps2 = dbConnection.prepareStatement(updateVehicleSql)) {
			ps1.executeUpdate();
			ps2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Counts the total number of vehicles in the database
	 * 
	 * @return Total count of vehicles
	 */
	public int totalVehicleCount() {
		int count = 0;
		String sqlString = "SELECT COUNT(*) FROM vehicle";
		try {
			PreparedStatement pStatement = dbConnection.prepareStatement(sqlString);
			ResultSet rs = pStatement.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return count;
	}

}
