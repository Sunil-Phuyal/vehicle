
package com.FleetX.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.FleetX.model.UserModel;
import com.FleetX.service.UserService;

/**
 * Servlet implementation class UserProfileController
 * 
 * This controller handles requests to display user profile information.
 * It retrieves user data and rental history from the database and forwards to the profile page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/userprofile" })
public class UserProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	/**
	 * Handles GET requests for user profile viewing
	 * Retrieves user data and rental history for display
	 * 
	 * @param request HTTP request object
	 * @param response HTTP response object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Get username from session (authentication check)
	    String username = (String) request.getSession().getAttribute("username");
	    
	    // Redirect to login if no user is logged in
	    if (username == null) {
	        response.sendRedirect(request.getContextPath() + "/login");
	        return;
	    }

	    // Create service to fetch user data
	    UserService userService = new UserService();
	    
	    // Retrieve user information from database
	    UserModel user = userService.getUserByUsername(username);
	    
	    // Handle case where user might be in session but not in database
	    if (user == null) {
	        // If no user found, redirect to error page or show an appropriate message
	        response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
	        return;
	    }

	    // Set attributes for the JSP view
	    request.setAttribute("user", user);
	    // Get and set rental history for the user
	    request.setAttribute("rentList", userService.getRentalData(username));
	    
	    // Forward to profile page JSP
	    request.getRequestDispatcher("WEB-INF/Pages/userProfilePage.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests to user profile page
	 * Currently forwards to doGet method
	 * 
	 * @param request HTTP request object
	 * @param response HTTP response object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
