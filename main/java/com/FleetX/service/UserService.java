package com.FleetX.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.FleetX.config.DbConfig;
import com.FleetX.model.RentalModel;
import com.FleetX.model.UserModel;
import com.FleetX.util.PasswordUtil;

/**
 * Service class for managing user-related operations in the FleetX application.
 * Handles database operations for user management including CRUD operations.
 */
public class UserService {
	// Database connection instance
	private Connection dbConnection;

	/**
	 * Constructor - initializes the database connection
	 */
	public UserService() {
		try {
			this.dbConnection = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database Connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Retrieves a user's ID by their username
	 * 
	 * @param username The username to search for
	 * @return The user ID if found, 0 otherwise
	 */
	public int getUserIdByUsername(String username) {
		int userId = 0;
		String sqlString = "SELECT UserID from users WHERE username = ?";
		try (PreparedStatement pst = dbConnection.prepareStatement(sqlString)) {
			pst.setString(1, username);
			ResultSet resultSet = pst.executeQuery();
			if (resultSet.next()) {
				userId = resultSet.getInt("UserId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;

	}

	/**
	 * Retrieves all users with the role of "customer"
	 * 
	 * @return List of UserModel objects
	 */
	public List<UserModel> getAllUser() {
		String role = "customer";
		List<UserModel> usersList = new ArrayList<>();
		String sqlString = "SELECT * FROM users WHERE role = ? ORDER BY UserID DESC";

		try (PreparedStatement pst = dbConnection.prepareStatement(sqlString)) {
			pst.setString(1, role);
			ResultSet resultSet = pst.executeQuery();

			while (resultSet.next()) {
				usersList.add(addUserDetail(resultSet));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return usersList;
	}

	/**
	 * Helper method to create a UserModel object from database result set
	 * 
	 * @param rs ResultSet containing user data
	 * @return Populated UserModel object
	 */
	public UserModel addUserDetail(ResultSet rs) {
		UserModel userModel = new UserModel();

		try {
			userModel.setId(rs.getInt("UserID"));
			userModel.setFname(rs.getString("FirstName"));
			userModel.setLname(rs.getString("LastName"));
			userModel.setuName(rs.getString("UserName"));
			userModel.setDob(rs.getString("DOB"));
			userModel.setPhone(rs.getString("Number"));
			userModel.setPassword(rs.getString("Password"));
			userModel.setEmail(rs.getString("Email"));
			userModel.setRole(rs.getString("role"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userModel;
	}

	/**
	 * Retrieves a user by their username
	 * 
	 * @param username The username to search for
	 * @return UserModel if found, null otherwise
	 */
	public UserModel getUserByUsername(String username) {
		UserModel user = null;
		String sqlString = "SELECT * FROM users WHERE Username = ?";

		try (PreparedStatement pst = dbConnection.prepareStatement(sqlString)) {
			pst.setString(1, username);
			ResultSet rSet = pst.executeQuery();

			if (rSet.next()) {
				user = new UserModel();
				user.setFname(rSet.getString("FirstName"));
				user.setLname(rSet.getString("LastName"));
				user.setuName(rSet.getString("UserName"));
				user.setPhone(rSet.getString("Number"));
				user.setEmail(rSet.getString("Email"));
				user.setPassword(rSet.getString("Password"));
			}
		} catch (SQLException e) {
			System.err.println("Error fetching user: " + e.getMessage());
		}

		return user;
	}

	/**
	 * Updates a user's profile information
	 * 
	 * @param user UserModel containing updated information
	 * @return true if update successful, false otherwise
	 */
	public boolean updateUserProfile(UserModel user) {
		String sql = "UPDATE users SET FirstName=?, LastName=?, Email=?, Number=? WHERE UserName=?";

		try (PreparedStatement pst = dbConnection.prepareStatement(sql)) {
			pst.setString(1, user.getFname());
			pst.setString(2, user.getLname());
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getPhone());
			pst.setString(5, user.getuName()); // WHERE clause

			int rows = pst.executeUpdate();
			return rows > 0;
		} catch (Exception e) {
			System.err.println("Error updating profile: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Updates a user's password after verifying the old password
	 * 
	 * @param uName       The username
	 * @param oldPassword Current password
	 * @param newPassword New password to set
	 * @return true if update successful, false otherwise
	 */
	public boolean updateUserPassword(String uName, String oldPassword, String newPassword) {
		String sqlSelect = "SELECT Password FROM users WHERE UserName = ?";
		String sqlUpdate = "UPDATE users SET Password = ? WHERE UserName = ?";

		try (PreparedStatement pst = dbConnection.prepareStatement(sqlSelect);
				PreparedStatement pstUpdate = dbConnection.prepareStatement(sqlUpdate)) {

			// First verify the old password
			pst.setString(1, uName);
			ResultSet rSet = pst.executeQuery();

			if (rSet.next()) {
				String regPasswordString = rSet.getString("Password");
				String decrypted = PasswordUtil.decrypt(regPasswordString, uName);

				if (decrypted == null || !oldPassword.equals(decrypted)) {
					return false;
				}
			} else {
				return false;
			}

			// Then update with the new password
			String newHashPassword = PasswordUtil.encrypt(uName, newPassword);
			pstUpdate.setString(1, newHashPassword);
			pstUpdate.setString(2, uName);

			int rows = pstUpdate.executeUpdate();
			return rows > 0;

		} catch (SQLException e) {
			System.err.println("Error updating password: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Gets a username by email address
	 * 
	 * @param email The email to search for
	 * @return Username if found, null otherwise
	 * @throws SQLException if database error occurs
	 */
	public String getUsernameByEmail(String email) throws SQLException {
		String sql = "SELECT Username FROM users WHERE Email = ?";
		try (PreparedStatement pst = dbConnection.prepareStatement(sql)) {
			pst.setString(1, email);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					return rs.getString("Username");
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving username by email: " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Updates a user's password using their email address
	 * Used for password reset functionality
	 * 
	 * @param email             The user's email
	 * @param encryptedPassword The new encrypted password
	 * @return true if update successful, false otherwise
	 */
	public boolean updateUserPasswordByEmail(String email, String encryptedPassword) {
		String sql = "UPDATE users SET Password = ? WHERE Email = ?";
		try (PreparedStatement pst = dbConnection.prepareStatement(sql)) {
			pst.setString(1, encryptedPassword);
			pst.setString(2, email);
			int rows = pst.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			System.err.println("Error updating password: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Deletes a user from the database
	 * 
	 * @param userId ID of the user to delete
	 * @return true if deletion successful, false otherwise
	 */
	public boolean deleteUser(int userId) {
		String query = "DELETE FROM users WHERE UserID = ?";
		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, userId);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Retrieves rental data for a specific user
	 * 
	 * @param username The username to get rentals for
	 * @return List of RentalModel objects
	 */
	public List<RentalModel> getRentalData(String username) {
		List<RentalModel> rentList = new ArrayList<>();
		String sqlString = """
				    SELECT
				        r.rental_id,
				        v.model,
				        p.amount AS amount,
				        r.status
				    FROM rental r
				    JOIN vehicle v ON r.vehicle_id = v.id
				    LEFT JOIN payment p ON r.rental_id = p.rental_id
				    JOIN users u ON r.user_id = u.userID
				    WHERE u.UserName = ?
				""";

		try (PreparedStatement pStatement = dbConnection.prepareStatement(sqlString)) {
			pStatement.setString(1, username);
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				System.out.println("Row found:");
				System.out.println("Rental ID: " + rs.getInt("rental_id"));
				System.out.println("Model: " + rs.getString("model"));
				System.out.println("Amount: " + rs.getDouble("amount"));
				System.out.println("Status: " + rs.getString("status"));
				RentalModel rentalModel = new RentalModel(rs.getInt("rental_id"), rs.getString("status"),
						rs.getDouble("amount"), rs.getString("model"));
				rentList.add(rentalModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Total rentals fetched: " + rentList.size());
		return rentList;
	}

	/**
	 * Counts the total number of users in the database
	 * 
	 * @return Total count of users
	 */
	public int totalUserCount() {
		int count = 0;
		String sqlString = "SELECT COUNT(*) FROM users";
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

	/**
	 * Gets the 5 most recently added customers
	 * 
	 * @return List of up to 5 UserModel objects
	 */
	public List<UserModel> getOnly5User() {
		String role = "customer";
		List<UserModel> usersList = new ArrayList<>();
		String sqlString = "SELECT * FROM users WHERE role = ? ORDER BY UserID DESC LIMIT 5";

		try (PreparedStatement pst = dbConnection.prepareStatement(sqlString)) {
			pst.setString(1, role);
			ResultSet resultSet = pst.executeQuery();

			while (resultSet.next()) {
				usersList.add(addUserDetail(resultSet));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return usersList;
	}

}
