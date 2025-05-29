package com.FleetX.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.FleetX.service.VehicleService;

/**
 * Controller for deleting vehicles from the system
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/deleteVehicle" })
public class DeleteVehicleController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// Service to handle database operations for vehicles
	private VehicleService vehicleService;
	
	/**
	 * Constructor - initializes the VehicleService
	 */
	public DeleteVehicleController() {
		vehicleService = new VehicleService();
	}

	/**
	 * Process POST requests to delete a vehicle
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Extract vehicle ID from the request
		String id = request.getParameter("vehicleID");
		
		// Check if the ID is valid
		if (id == null || id.isEmpty()) {
            System.out.println("No vehicle ID provided.");
            response.sendRedirect("Dashboard");
            return;
        }
		
        // Attempt to delete the vehicle and set appropriate message
        boolean isDeleted = vehicleService.deleteVehicle(Integer.parseInt(id));
        if (isDeleted) {
        	request.getSession().setAttribute("message", "Vehicle Deleted Successfully.");
        } else {
        	request.getSession().setAttribute("message", "Failed to delete vehicle.");
        }
		
        // Redirect back to the dashboard
		response.sendRedirect("Dashboard");
	}
}
