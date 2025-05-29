// Privacy Controller
package com.FleetX.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class PrivacyController
 * This controller handles requests to the Privacy page
 * Maps to the URL: /Privacy
 */
@WebServlet("/Privacy")
public class PrivacyController extends HttpServlet {
	// Unique identifier for serialization
	private static final long serialVersionUID = 1L;
	
	/**
	 * Handles HTTP GET requests for the Privacy page
	 * Forwards the request to the Privacy.jsp view
	 * 
	 * @param request The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException If an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// Forward the request to the Privacy JSP page for rendering
		request.getRequestDispatcher("WEB-INF/Pages/Privacy.jsp").forward(request, response);
	}
}
