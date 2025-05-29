package com.FleetX.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import com.FleetX.model.UserModel;
import com.FleetX.service.UserService;

/**
 * Servlet implementation class UpdatePassword
 * 
 * This controller handles password updates for logged-in users.
 * It verifies the old password and updates with a new one if valid.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/updatePassword" })
public class UpdatePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserService userService = new UserService(); // Service class for user operations

	/**
	 * Handles POST requests for password updates
	 * 
	 * @param request HTTP request containing username, old password, and new password
	 * @param response HTTP response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // Get form parameters
	    String uName = request.getParameter("username");
	    String oldPassword = request.getParameter("oldPassword");
	    String newPassword = request.getParameter("newPassword");

	    // Validate all required fields are present
	    if (uName == null || oldPassword == null || newPassword == null ||
	        uName.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty()) {
	        request.setAttribute("error", "All fields are required.");
	        request.getRequestDispatcher("/WEB-INF/Pages/userProfilePage.jsp").forward(request, response);
	        return;
	    }

	    // Attempt to update password in the database
	    boolean success = userService.updateUserPassword(uName, oldPassword, newPassword);
	    
	    if (success) {
	        // If successful, invalidate session for security reasons
	        request.getSession().invalidate();
	        // Redirect to login page to re-authenticate with new password
	        response.sendRedirect("login");
	        return;
	    } else {
	        // If failed (likely due to incorrect old password), show error message
	        request.setAttribute("passwordStatus", "Failed to update password. Check old password.");
	        request.getRequestDispatcher("/WEB-INF/Pages/userProfilePage.jsp").forward(request, response);
	        return;
	    }
	}
}
