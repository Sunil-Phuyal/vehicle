package com.FleetX.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.FleetX.config.DbConfig;
import com.FleetX.model.UserModel;

/**
 * Service class that handles user registration for the FleetX application.
 * Manages the process of creating new user accounts in the database.
 * Provides methods to store user information securely.
 */
public class RegisterService {
	private Connection dbConnection;
	
	/**
	 * Constructor that initializes the database connection.
	 * Uses DbConfig to establish a connection to the database.
	 * Logs detailed error information if connection fails.
	 */
	public RegisterService() {
		try {
			this.dbConnection = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database Connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	/**
	 * Adds a new user to the database with all required user information.
	 * Inserts the user's personal details, contact information, credentials, and role.
	 * Performs a null check on the database connection before attempting insertion.
	 * 
	 * @param userModel The user model containing all registration information
	 * @return Boolean indicating registration result:
	 *         - true: registration successful (user added to database)
	 *         - false: registration failed (database error or connection issue)
	 */
	public Boolean addUser(UserModel userModel) {
		if (dbConnection == null) {
			System.err.println("Database connection is not available");
			return false;
		}
		
		String sql = "INSERT INTO users (FirstName, LastName, UserName, DOB, Number, Password, Email, Role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pst = dbConnection.prepareStatement(sql)) {
			// Set all user properties in the prepared statement
			pst.setString(1, userModel.getFname());
			pst.setString(2, userModel.getLname());
			pst.setString(3, userModel.getuName());
			pst.setString(4, userModel.getDob());
			pst.setString(5, userModel.getPhone());
			pst.setString(6, userModel.getPassword());  // Note: Password should be encrypted before this point
			pst.setString(7, userModel.getEmail());
			pst.setString(8, userModel.getRole());
			
			// Execute the insert and return true if at least one row was affected
			return pst.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
