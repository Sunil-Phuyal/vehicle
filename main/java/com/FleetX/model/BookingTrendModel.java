package com.FleetX.model;
import java.sql.Date;

/**
 * BookingTrendModel represents booking trend data for analysis purposes.
 * This model stores information about booking dates and corresponding total bookings
 * to help track and visualize booking patterns over time.
 */
public class BookingTrendModel {
	// Date when the booking was made
	private Date bookingDate;
	// Total number of bookings for this date
	private int totalBookings;
	
	/**
	 * Constructor to create a new BookingTrendModel instance
	 * 
	 * @param bookingDate The date of the booking record
	 * @param totalBookings The total number of bookings on this date
	 */
	public BookingTrendModel(Date bookingDate, int totalBookings) {
		this.bookingDate = bookingDate;
		this.totalBookings = totalBookings;
	}
	
	/**
	 * Returns the booking date
	 * 
	 * @return The date associated with this booking record
	 */
	public Date getBookingDate() {
		return bookingDate;
	}
	
	/**
	 * Sets the booking date
	 * 
	 * @param bookingDate The date to set for this booking record
	 */
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	/**
	 * Returns the total number of bookings
	 * 
	 * @return The total bookings count for this date
	 */
	public int getTotalBookings() {
		return totalBookings;
	}
	
	/**
	 * Sets the total number of bookings
	 * 
	 * @param totalBookings The total bookings count to set
	 */
	public void setTotalBookings(int totalBookings) {
		this.totalBookings = totalBookings;
	}
}
