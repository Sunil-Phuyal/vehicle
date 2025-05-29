package com.FleetX.controller.admin;

import java.io.IOException;
import com.FleetX.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller for deleting users from the system
 */
@SuppressWarnings("serial")
@WebServlet("/deleteUser")
public class DeleteUserController extends HttpServlet {

	/**
	 * Process POST requests to delete a user
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Extract the user ID from the request
		String userId = request.getParameter("userId");
		System.out.println("User ID to delete: " + userId); // Debugging line
		
		UserService userService = new UserService();
		
		// If userId is null or empty, handle it as an error
		if (userId == null || userId.isEmpty()) {
			System.out.println("No user ID provided.");
			response.sendRedirect("Dashboard");
			return;
		}
		
		// Attempt to delete the user and set appropriate message
		boolean isDeleted = userService.deleteUser(Integer.parseInt(userId));
		if (isDeleted) {
			request.getSession().setAttribute("message", "User deleted successfully.");
		} else {
			request.getSession().setAttribute("message", "Failed to delete user.");
		}
		
		// Redirect back to the dashboard
		response.sendRedirect("Dashboard");
	}
}
