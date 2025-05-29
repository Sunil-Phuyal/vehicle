package com.FleetX.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.FleetX.config.DbConfig;
import com.FleetX.model.MessageModel;
import com.FleetX.model.UserModel;

/**
 * Service class that handles contact and messaging functionality for the FleetX application.
 * Manages user inquiries, message storage, and retrieval operations.
 * Provides methods to interact with users and messages in the database.
 */
public class ContactService {
	private Connection dConnection;
	
	/**
	 * Constructor that initializes the database connection.
	 * Uses DbConfig to establish a connection to the database.
	 */
	public ContactService() {
		try {
			dConnection = DbConfig.getDbConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves a user's details from the database using their email address.
	 * Populates a UserModel object with all user attributes.
	 * 
	 * @param email The email address to search for
	 * @return UserModel containing user details if found, null otherwise
	 * @throws SQLException If a database error occurs
	 */
	public UserModel getUserDetailByEmail(String email) throws SQLException {
		UserModel user = null;
		String sql = "SELECT * FROM users WHERE Email = ?";
		try (PreparedStatement pst = dConnection.prepareStatement(sql)) {
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				user = new UserModel();
				user.setId(rs.getInt("UserID"));
				user.setFname(rs.getString("FirstName"));
				user.setLname(rs.getString("LastName"));
				user.setuName(rs.getString("UserName"));
				user.setDob(rs.getString("DOB"));
				user.setPhone(rs.getString("Number"));
				user.setPassword(rs.getString("Password"));
				user.setEmail(rs.getString("Email"));
				user.setRole(rs.getString("role"));
			}
		}
		return user;
	}
	
	/**
	 * Stores a new message from a user in the database.
	 * Records the user ID, subject, and message content.
	 * 
	 * @param id The user ID of the message sender
	 * @param subject The subject line of the message
	 * @param message The content of the message
	 * @return true if message was successfully inserted, false otherwise
	 */
	public boolean insertMessage(int id, String subject, String message) {
		String sql = "INSERT INTO message (user_id, subject, content) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = dConnection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.setString(2, subject);
			stmt.setString(3, message);
			int rowsInserted = stmt.executeUpdate();
			return rowsInserted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Retrieves all messages from the database along with the sender's email.
	 * Joins the message table with users table to get the email address.
	 * Orders messages by sent time in descending order (newest first).
	 * 
	 * @return List of MessageModel objects containing all messages
	 */
	//  New method to fetch all messages with user email
	public List<MessageModel> getAllMessages() {
		List<MessageModel> messages = new ArrayList<>();
		String sql = "SELECT m.message_id, m.subject, m.content, m.sent_at, u.email "
				+ "FROM message m JOIN users u ON m.user_id = u.UserID " + "ORDER BY m.sent_at DESC";
		try (PreparedStatement stmt = dConnection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				MessageModel message = new MessageModel();
				message.setMessageId(rs.getInt("message_id"));
				message.setSubject(rs.getString("subject"));
				message.setContent(rs.getString("content"));
				message.setSentAt(rs.getTimestamp("sent_at"));
				message.setEmail(rs.getString("email"));
				messages.add(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}
	
	/**
	 * Counts the total number of messages in the system.
	 * Used for dashboard statistics and reporting.
	 * 
	 * @return Total count of message records
	 */
	public int totalMessageCount() {
		int count = 0;
		String sqlString = "SELECT COUNT(*) FROM message";
		try {
			PreparedStatement pStatement = dConnection.prepareStatement(sqlString);
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
	 * Retrieves the 5 most recent messages from the database.
	 * Only includes content and timestamp, not subject or sender information.
	 * Used for dashboard preview of recent communications.
	 * 
	 * @return List containing up to 5 of the most recent MessageModel objects
	 */
	public List<MessageModel> getOnly5Messages() {
		List<MessageModel> messages = new ArrayList<>();
		String sql = "SELECT content, sent_at FROM message ORDER BY sent_at DESC LIMIT 5";
		try (PreparedStatement stmt = dConnection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				MessageModel message = new MessageModel();
				message.setContent(rs.getString("content"));
				message.setSentAt(rs.getTimestamp("sent_at"));
				messages.add(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}
}
