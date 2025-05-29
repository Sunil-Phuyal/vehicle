package com.FleetX.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.math.BigDecimal;

import com.FleetX.model.VehicleModel;
import com.FleetX.service.VehicleService;
import com.FleetX.util.ImageUtil;

/**
 * Servlet implementation class EditPageController
 * 
 * This servlet handles the editing of vehicle information in the FleetX system.
 * It provides functionality for both viewing vehicle details (GET request) and 
 * updating vehicle information (POST request).
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/EditVehicle" })
public class EditVehicleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VehicleService vehicleService; // Service class to handle vehicle-related database operations

	/**
	 * Constructor - initializes the VehicleService
	 */
	public EditVehicleController() {
		vehicleService = new VehicleService();
	}

	/**
	 * Handles GET requests to display the vehicle edit form
	 * 
	 * @param request HTTP request object containing the vehicleId parameter
	 * @param response HTTP response object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Get the vehicle ID from the request parameters
			String idString = request.getParameter("vehicleId");
			if (idString == null) {
				// Return a 400 error if the vehicleId parameter is missing
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing vehicleId");
				return;
			}

			// Parse the vehicle ID to an integer
			int vehicleId = Integer.parseInt(idString);
			// Retrieve the vehicle data from the database
			VehicleModel vehicle = vehicleService.getVehicleById(vehicleId);

			if (vehicle == null) {
				// Return a 404 error if the vehicle is not found
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Vehicle not found");
				return;
			}

			// Set the vehicle object as a request attribute
			request.setAttribute("vehicle", vehicle);
			// Forward to the edit vehicle JSP page
			request.getRequestDispatcher("/WEB-INF/Pages/Admin/EditVehicle.jsp").forward(request, response);

		} catch (NumberFormatException e) {
			// Return a 400 error if the vehicleId parameter is not a valid number
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid vehicleId");
		}
	}

	/**
	 * Handles POST requests to update vehicle information
	 * 
	 * @param request HTTP request object containing updated vehicle data
	 * @param response HTTP response object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Set character encoding to handle UTF-8 characters properly
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		try {
			// Extract all form fields from the request
			int id = Integer.parseInt(request.getParameter("id")); // Hidden input from form
			String category = request.getParameter("category").toLowerCase();
			String brand = request.getParameter("brand");
			String model = request.getParameter("model");
			int year = Integer.parseInt(request.getParameter("year"));
			String registrationNumber = request.getParameter("registrationNumber");
			BigDecimal dailyRate = new BigDecimal(request.getParameter("dailyRate"));
			String fuelType = request.getParameter("fuelType");
			String transmission = request.getParameter("transmission");
			int capacity = Integer.parseInt(request.getParameter("capacity"));
			String status = request.getParameter("status");
			String location = request.getParameter("location");
			String description = request.getParameter("description");
			String features = request.getParameter("features");

			// Handle the image upload
			Part imagePart = request.getPart("imageUrl");
			String appPath = request.getServletContext().getRealPath("");
			String imageUrl;

			// Check if a new image was uploaded
			if (imagePart != null && imagePart.getSize() > 0) {
				// Upload the new image and get its URL
				imageUrl = new ImageUtil().uploadImage(imagePart, appPath, category);
			} else {
				// Keep the existing image URL if no new image was uploaded
				VehicleModel existingVehicle = vehicleService.getVehicleById(id);
				imageUrl = existingVehicle.getImageUrl();
			}

			// Create a vehicle object with the updated information
			VehicleModel updatedVehicle = new VehicleModel(id, category, brand, model, year, registrationNumber,
					dailyRate, fuelType, transmission, capacity, status, imageUrl, location, description, features);

			// Update the vehicle in the database
			boolean success = vehicleService.updateVehicle(updatedVehicle);

			// Set a message based on the update operation result
			if (success) {
				request.getSession().setAttribute("message", "Vehicle updated successfully.");
			} else {
				request.getSession().setAttribute("message", "Failed to update vehicle.");
			}
			// Redirect to the Dashboard page
			response.sendRedirect("Dashboard");

		} catch (Exception e) {
			// Handle any exceptions that occur during the update process
			e.printStackTrace();
			request.setAttribute("error", "Unexpected error: " + e.getMessage());
			// Redirect to the Dashboard page in case of error
			response.sendRedirect(request.getContextPath() + "/Dashboard");
		}
	}

}
