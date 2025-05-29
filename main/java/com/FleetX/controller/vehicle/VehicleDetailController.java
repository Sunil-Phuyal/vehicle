package com.FleetX.controller.vehicle;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.FleetX.model.VehicleModel;
import com.FleetX.service.VehicleService;

/**
 * Servlet implementation class VehicleDetailController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/vehicleDetail" })
public class VehicleDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private VehicleService vehicleService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehicleDetailController() {
       vehicleService = new VehicleService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		String idString = request.getParameter("vehicleId");
    		if (idString == null) {
    			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing vehicleId");
    			return;
    		}

    		int vehicleId = Integer.parseInt(idString);
    		VehicleModel vehicle = vehicleService.getVehicleById(vehicleId);

    		if (vehicle == null) {
    			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Vehicle not found");
    			return;
    		}

    		request.setAttribute("vehicle", vehicle);
    		request.getRequestDispatcher("/WEB-INF/Pages/Vehicle-Detail.jsp").forward(request, response);

    	} catch (NumberFormatException e) {
    		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid vehicleId");
    	}
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
