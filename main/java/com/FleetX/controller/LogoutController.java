package com.FleetX.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.FleetX.util.CookieUtil;
import com.FleetX.util.SessionUtil;

/**
 * Servlet implementation class LogoutController
 * 
 * This controller handles user logout operations by clearing cookies,
 * invalidating the session, and redirecting to the login page.
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles POST requests for user logout
	 * 
	 * @param request HTTP request object
	 * @param response HTTP response object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Remove role cookie
		CookieUtil.deleteCookie(response, "role");
		
		// Invalidate session to remove all session attributes
		SessionUtil.invalidateSession(request);
		
		// Redirect user to login page
		response.sendRedirect(request.getContextPath() + "/login");
	}
}
