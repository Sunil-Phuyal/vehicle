package com.FleetX.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.FleetX.service.UserService;
import com.FleetX.util.PasswordUtil;

/**
 * Servlet implementation class ForgetPasswordController
 * 
 * This controller handles password reset functionality for users who have
 * forgotten their passwords. It validates the email, checks password match,
 * and updates the password in the database.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/forgetPassword" })
public class ForgetPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService; // Service class for user-related operations
	private String loginUrl = "WEB-INF/Pages/login.jsp"; // JSP page for login/reset form

	/**
	 * Constructor - initializes the UserService
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgetPasswordController() {
		userService = new UserService();
	}

	/**
	 * Handles POST requests for password reset
	 * 
	 * @param request HTTP request containing email and new password details
	 * @param response HTTP response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get form parameters
		String email = request.getParameter("email");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		try {
			// Lookup username by email
			String username = userService.getUsernameByEmail(email);
			
			// Validate email exists in system
			if (username == null) {
				request.setAttribute("error", "Email not registered.");
				request.getRequestDispatcher(loginUrl).forward(request, response);
				return;
			}

			// Validate password confirmation
			if (!newPassword.equals(confirmPassword)) {
				request.setAttribute("error", "Passwords do not match.");
				request.getRequestDispatcher(loginUrl).forward(request, response);
				return;
			}

			// Encrypt the new password
			String encryptPass = PasswordUtil.encrypt(username, newPassword);
			
			// Update password in database
			boolean success = userService.updateUserPasswordByEmail(email, encryptPass);
			
			// Set appropriate message based on result
			if (success) {
				request.setAttribute("success", "Password reset successful. Please log in.");
			} else {
				request.setAttribute("error", "Failed to reset password. Try again.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			// Note: Exception handling is incomplete in original code
		}

		// Forward back to login page with result message
		request.getRequestDispatcher(loginUrl).forward(request, response);
	}
}
