package com.FleetX.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.FleetX.service.CheckoutService;
import com.FleetX.service.ContactService;
import com.FleetX.service.UserService;
import com.FleetX.service.VehicleService;

/**
 * Main dashboard controller for the admin panel
 * Retrieves and aggregates data from various services to display on the dashboard
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Dashboard" })
public class Dashboard extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// Services to handle different types of data for the dashboard
	private UserService userService;
	private VehicleService vehicleService;
	private ContactService contactService;
	private CheckoutService checkoutService;

	/**
	 * Constructor - initializes all required services
	 */
	public Dashboard() {
		userService = new UserService();
		vehicleService = new VehicleService();
		contactService = new ContactService();
		checkoutService = new CheckoutService();
	}

	/**
	 * Process GET requests to load the admin dashboard
	 * Fetches data from all services and forwards to the dashboard JSP
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Update vehicle availability status based on completed rentals
		    vehicleService.updateVehicleAvailabilityBasedOnRentals();
			
			// Fetch and set vehicle-related data
			request.setAttribute("VehicleList", vehicleService.getAllVehicles());
			request.setAttribute("totalVehicle", vehicleService.totalVehicleCount());
			
			// Fetch and set user-related data
			request.setAttribute("UserList", userService.getAllUser());
			request.setAttribute("Only5User", userService.getOnly5User());
			request.setAttribute("totalUser", userService.totalUserCount());
			
			// Fetch and set message/contact-related data
			request.setAttribute("MessageList", contactService.getAllMessages());
			request.setAttribute("Only5Message", contactService.getOnly5Messages());
			request.setAttribute("totalMessage", contactService.totalMessageCount());
			
			// Fetch and set booking/rental-related data
			request.setAttribute("BookingList", checkoutService.getAllRental());
			request.setAttribute("totalBooking", checkoutService.totalBookingCount());
			request.setAttribute("bookingTrend", checkoutService.getBookingTrend());
			
			// Forward the request to the dashboard JSP
			request.getRequestDispatcher("/WEB-INF/Pages/Admin/Dashboard.jsp").forward(request, response);
		} catch (Exception e) {
			// Log and handle any exceptions
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error: " + e.getMessage());
		}
	}
}
