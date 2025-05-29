package com.FleetX.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.FleetX.config.DbConfig;
import com.FleetX.model.UserModel;
import com.FleetX.util.PasswordUtil;

/**
 * Service class that handles user authentication for the FleetX application.
 * Manages the login process by validating user credentials against the database.
 * Uses password encryption/decryption for secure authentication.
 */
public class LoginService {
	private Connection dbConnection;
	private boolean isConnectionError = false;
	
	/**
	 * Constructor that initializes the database connection.
	 * Sets an error flag if the connection cannot be established.
	 * Uses DbConfig to establish a connection to the database.
	 */
	public LoginService() {
		try {
			dbConnection = DbConfig.getDbConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			isConnectionError = true;
		}
	}
	
	/**
	 * Authenticates a user by comparing provided credentials with database records.
	 * Queries the database for the user and delegates to validatePassword for comparison.
	 * 
	 * @param userModel The user model containing login credentials
	 * @return Boolean indicating authentication result:
	 *         - true: authentication successful
	 *         - false: invalid credentials
	 *         - null: error occurred during authentication
	 */
	public Boolean loginUser(UserModel userModel) {
		if (isConnectionError) {
			System.out.println("Connection error!");
			return null;
		}
		String loginQuery = "SELECT UserName, Password FROM users WHERE UserName = ?";
		try (PreparedStatement pst = dbConnection.prepareStatement(loginQuery)) {
			pst.setString(1, userModel.getuName());
			ResultSet resultSet = pst.executeQuery();
			if (resultSet.next()) {
				return validatePassword(resultSet, userModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return false;
	}
	
	/**
	 * Helper method to validate the user's password against the stored encrypted password.
	 * Decrypts the password from the database using the username as the key.
	 * Performs comparison with the password provided by the user.
	 * 
	 * @param resultSet The result set containing the stored credentials
	 * @param userModel The user model containing the login attempt credentials
	 * @return true if passwords match, false otherwise
	 * @throws SQLException If a database error occurs
	 */
	private boolean validatePassword(ResultSet resultSet, UserModel userModel) throws SQLException {
		String dbUsername = resultSet.getString("username");
		String dbPassword = resultSet.getString("password");
		// Decrypt stored password using username as key
		String decryptedPassword = PasswordUtil.decrypt(dbPassword, dbUsername);
		// Null check and comparison
		return dbUsername.equals(userModel.getuName())
				&& decryptedPassword != null
				&& decryptedPassword.equals(userModel.getPassword());
	}
}
