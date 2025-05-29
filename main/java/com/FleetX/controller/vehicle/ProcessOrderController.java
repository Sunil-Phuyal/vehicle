package com.FleetX.controller.vehicle;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.FleetX.model.CartModel;
import com.FleetX.model.PaymentModel;
import com.FleetX.model.RentalModel;
import com.FleetX.service.CartService;
import com.FleetX.service.CheckoutService;
import com.FleetX.service.UserService;

/**
 * Servlet implementation class ProcessOrderController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ProcessOrder" })
public class ProcessOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private CheckoutService checkoutService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessOrderController() {
		userService = new UserService();
		checkoutService = new CheckoutService();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String username = (String) request.getSession().getAttribute("username");
		if (username == null) {
			response.sendRedirect("login");
			return;
		}

		int userId = userService.getUserIdByUsername(username);
		if (userId == -1) {
			request.setAttribute("error", "User not found.");
			request.getRequestDispatcher("/WEB-INF/Pages/checkout.jsp").forward(request, response);
			return;
		}

		CartService cartService = new CartService(request.getSession());
		List<CartModel> cartItems = cartService.getCartItems();

		// Debug: print cart size
		System.out.println("Cart size: " + cartItems.size());

		// Debug: print each cart item
		for (int i = 0; i < cartItems.size(); i++) {
			CartModel item = cartItems.get(i);
			System.out.println("Item " + i + ": VehicleID=" + item.getVehicleId() + ", StartDate=" + item.getStartDate()
					+ ", EndDate=" + item.getEndDate() + ", PickupLocation=" + item.getPickupLocation()
					+ ", TotalPrice=" + item.getTotalPrice());
		}

		if (cartItems.isEmpty()) {
			request.setAttribute("error", "Your cart is empty.");
			request.getRequestDispatcher("/WEB-INF/Pages/checkout.jsp").forward(request, response);
			return;
		}

		boolean allSuccess = true;

		for (CartModel item : cartItems) {
			// Create and insert rental
			RentalModel rental = new RentalModel();
			rental.setUserId(userId);
			rental.setVehicleId(item.getVehicleId());
			rental.setStartDate(item.getStartDate());
			rental.setEndDate(item.getEndDate());
			rental.setAddress(item.getPickupLocation());
			rental.setDropAddress(item.getDropupLocation());

			int rentalId = 0;
			try {
				rentalId = checkoutService.insertRental(rental);
				System.out.println("Rental ID returned: " + rentalId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (rentalId == -1) {
				allSuccess = false;
				break;
			}

			// Create and insert payment
			PaymentModel payment = new PaymentModel();
			payment.setRentalId(rentalId);
			payment.setAmount(item.getTotalPrice());
			payment.setPaymentDate(new Timestamp(System.currentTimeMillis()));

			boolean paymentInserted = false;
			try {
				paymentInserted = checkoutService.insertPayment(payment);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (!paymentInserted) {
				allSuccess = false;
				break;
			}
			String  vehilceStatus = "rented";
			boolean isStatusUpdate = false;
			try {
				 isStatusUpdate = checkoutService.updateVehicleStatus(item.getVehicleId(), vehilceStatus);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(!isStatusUpdate) {
				allSuccess = false;
				break;
			}
		}

		if (allSuccess) {
			httpRequest.setAttribute("cartSize", 0);
			cartService.clearCart();
			request.getSession().setAttribute("messageStatus", "Order placed successfully!");
			response.sendRedirect("vehicle");
		} else {
			request.getSession().setAttribute("messageStatus", "Something went wrong while processing your order.");
			request.getRequestDispatcher("/WEB-INF/Pages/checkout.jsp").forward(request, response);
		}

	}
}