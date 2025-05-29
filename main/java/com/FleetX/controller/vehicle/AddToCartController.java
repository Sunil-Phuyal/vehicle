package com.FleetX.controller.vehicle;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;

import com.FleetX.model.CartModel;
import com.FleetX.model.VehicleModel;
import com.FleetX.service.CartService;
import com.FleetX.service.VehicleService;

/**
 * Servlet implementation class AddToCartController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddToCart" })
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VehicleService vehicleService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCartController() {
		vehicleService = new VehicleService();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
		Date startDate = Date.valueOf(request.getParameter("startDate"));
		Date endDate = Date.valueOf(request.getParameter("endDate"));
		String pickupLocation = request.getParameter("pickupLocation");
		String dropupLocation = request.getParameter("dropupLocation");

		VehicleModel vehicle = vehicleService.getVehicleById(vehicleId);
		if (vehicle == null) {
			response.sendRedirect("vehicle");
			return;
		}

		CartModel cart = new CartModel(vehicle.getId(), vehicle.getBrand(), vehicle.getModel(), vehicle.getImageUrl(),
				vehicle.getDailyRate(), startDate, endDate, pickupLocation, dropupLocation);

		HttpSession session = request.getSession();
		CartService service = new CartService(session);
		boolean added = service.addItem(cart);

		if (!added) {
			request.getSession().setAttribute("messageStatus", "Vehicle already in cart!");
		} else {
			request.getSession().setAttribute("messageStatus", "Vehicle added to cart successfully.");
		}

		response.sendRedirect("vehicle");
	}

}