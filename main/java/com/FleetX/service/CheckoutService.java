package com.FleetX.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.FleetX.config.DbConfig;
import com.FleetX.model.RentalModel;
import com.FleetX.model.BookingTrendModel;
import com.FleetX.model.PaymentModel;

/**
 * Service class that handles checkout operations in the FleetX application.
 * Manages rental bookings, payments, and provides analytics for booking trends.
 * Interacts with the database to persist and retrieve rental and payment information.
 */
public class CheckoutService {
	private Connection dbConnection;

	/**
	 * Constructor that initializes the database connection.
	 * Uses DbConfig to establish a connection to the database.
	 */
	public CheckoutService() {
		try {
			dbConnection = DbConfig.getDbConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inserts a new rental record into the database.
	 * Sets the initial status of the rental to 'booked'.
	 * 
	 * @param rentalModel The rental data to be inserted
	 * @return The generated rental ID if successful
	 * @throws SQLException If a database error occurs
	 */
	public int insertRental(RentalModel rentalModel) throws SQLException {
		String sql = "INSERT INTO rental (user_id, vehicle_id, start_date, end_date, address, dropLocation, status) VALUES (?, ?, ?, ?, ?, ?, 'booked')";
		try (PreparedStatement pst = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pst.setInt(1, rentalModel.getUserId());
			pst.setInt(2, rentalModel.getVehicleId());
			pst.setDate(3, rentalModel.getStartDate());
			pst.setDate(4, rentalModel.getEndDate());
			pst.setString(5, rentalModel.getAddress());
			pst.setString(6, rentalModel.getDropAddress());

			int affectedRows = pst.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Creating rental failed, no rows affected.");
			}

			try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					int rentalId = generatedKeys.getInt(1);
					System.out.println("Rental inserted with ID: " + rentalId);
					return rentalId;
				} else {
					throw new SQLException("Creating rental failed, no ID obtained.");
				}
			}
		}
	}

	/**
	 * Records a payment for a rental in the database.
	 * 
	 * @param paymentModel The payment data to be inserted
	 * @return true if payment was successfully inserted, false otherwise
	 * @throws SQLException If a database error occurs
	 */
	public boolean insertPayment(PaymentModel paymentModel) throws SQLException {
		String sql = "INSERT INTO payment (rental_id, amount, payment_date) VALUES (?, ?, ?)";
		try (PreparedStatement pst = dbConnection.prepareStatement(sql)) {
			pst.setInt(1, paymentModel.getRentalId());
			pst.setBigDecimal(2, paymentModel.getAmount());
			pst.setTimestamp(3, paymentModel.getPaymentDate());

			int affectedRows = pst.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("Payment done");
			}
			return affectedRows > 0;
		}
	}

	/**
	 * Retrieves all rental records from the database with their payment information.
	 * Uses a left join to include rentals that may not have payments yet.
	 * 
	 * @return List of rental models, ordered by rental ID in descending order
	 */
	public List<RentalModel> getAllRental() {
		List<RentalModel> rentalList = new ArrayList<>();
		
		String sqlString = """
			SELECT r.*, p.amount AS amount
			FROM rental r
			LEFT JOIN payment p ON r.rental_id = p.rental_id ORDER BY r.rental_id DESC
		""";

		try (PreparedStatement pst = dbConnection.prepareStatement(sqlString)) {
			ResultSet resultSet = pst.executeQuery();

			while (resultSet.next()) {
				rentalList.add(addRentalDetail(resultSet));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rentalList;
	}

	/**
	 * Helper method to convert a result set row to a RentalModel object.
	 * Extracts all fields from the database and constructs a RentalModel instance.
	 * 
	 * @param rs The result set containing rental data
	 * @return A populated RentalModel object
	 */
	public RentalModel addRentalDetail(ResultSet rs) {
		try {
			return new RentalModel(
				rs.getInt("rental_id"),
				rs.getInt("user_id"),
				rs.getInt("vehicle_id"),
				rs.getDate("start_date"),
				rs.getDate("end_date"),
				rs.getString("address"),
				rs.getString("dropLocation"),
				rs.getString("status"),
				rs.getDouble("amount")
			);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Updates the status of a vehicle in the database.
	 * Used to mark vehicles as booked, available, etc.
	 * 
	 * @param vehicleId The ID of the vehicle to update
	 * @param status The new status value
	 * @return true if update was successful, false otherwise
	 * @throws SQLException If a database error occurs
	 */
	public boolean updateVehicleStatus(int vehicleId, String status) throws SQLException {
	    String sql = "UPDATE vehicle SET status = ? WHERE id = ?";
	    try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
	        stmt.setString(1, status);
	        stmt.setInt(2, vehicleId);
	        int rowsUpdated = stmt.executeUpdate();
	        return rowsUpdated > 0;
	    }
	}
	
	/**
	 * Counts the total number of bookings in the system.
	 * Used for dashboard statistics.
	 * 
	 * @return Total count of rental records
	 */
	public int totalBookingCount() {
		int count = 0;
		String sqlString = "SELECT COUNT(*) FROM rental";
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
	 * Retrieves booking trend data for the last 7 days.
	 * Groups bookings by date and counts the total for each day.
	 * Used for dashboard analytics and reporting.
	 * 
	 * @return List of booking trend models with date and count information
	 */
	public List<BookingTrendModel> getBookingTrend() {
		List<BookingTrendModel> trendList = new ArrayList<>();
		String sql = """
			SELECT start_date AS bookingDate, COUNT(*) AS totalBookings
			FROM rental
			GROUP BY start_date
			ORDER BY start_date DESC
			LIMIT 7
		""";

		try (PreparedStatement pst = dbConnection.prepareStatement(sql);
		     ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				BookingTrendModel trend = new BookingTrendModel(
					rs.getDate("bookingDate"),
					rs.getInt("totalBookings")
				);
				trendList.add(trend);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trendList;
	}
}
