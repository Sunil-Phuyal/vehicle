package com.FleetX.controller.vehicle;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.FleetX.service.VehicleService;
import java.io.IOException;

@WebServlet(asyncSupported = true, urlPatterns = { "/vehicle" })
public class VehicleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private VehicleService vehicleService;

	public VehicleController() {
		vehicleService = new VehicleService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String fuel = request.getParameter("fuel");
	    String gear = request.getParameter("gear");
	    String type = request.getParameter("type");
	    String location = request.getParameter("location");
	    
	    // Call a new service method that handles filtering
	    request.setAttribute("vehicleList", vehicleService.getFilteredVehicles(fuel, gear, type, location));
	    request.getRequestDispatcher("/WEB-INF/Pages/vehicle.jsp").forward(request, response);
	}



	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response); // This handles POST same as GET for now
	}
}
