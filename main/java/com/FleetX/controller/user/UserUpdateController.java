package com.FleetX.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.FleetX.model.UserModel;
import com.FleetX.service.UserService;
import com.FleetX.util.PasswordUtil;

/**
 * Servlet implementation class UserUpdateController
 * 
 * This controller handles updates to user profile information
 * such as name, email, and phone number.
 */
@WebServlet("/userUpdate")
public class UserUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles POST requests for updating user profile information
	 * 
	 * @param request HTTP request with updated user details
	 * @param response HTTP response object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Extract user information from form submission
		String uName = request.getParameter("username");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");

		// Create service instance to handle database operations
		UserService userService = new UserService();
		
		// Create user model with updated information
		UserModel userModel = new UserModel(fname, lname, uName, email, phone);
		
		// Update user profile in database
		boolean success = userService.updateUserProfile(userModel);
		
		// Retrieve the fully updated user data (including any fields not updated via form)
		UserModel updatedUser = userService.getUserByUsername(uName);
		
		// Update the user object in session with refreshed data
		request.getSession().setAttribute("user", updatedUser);

		// Set status attribute based on update result
		if (success) {
			request.setAttribute("status", "updated");
		} else {
			request.setAttribute("status", "failed");
		}

		// Forward back to profile page with status message
		request.getRequestDispatcher("WEB-INF/Pages/userProfilePage.jsp").forward(request, response);
	}
}
