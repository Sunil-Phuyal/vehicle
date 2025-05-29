package com.FleetX.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class TermsController
 * This controller handles requests to the Terms page
 * Maps to the URL: /Terms
 */
@WebServlet("/Terms")
public class TermsController extends HttpServlet {
	// Unique identifier for serialization
	private static final long serialVersionUID = 1L;
             
    /**
     * Default constructor for TermsController
     * Calls the parent HttpServlet constructor
     */
    public TermsController() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	/**
	 * Handles HTTP GET requests for the Terms page
	 * Forwards the request to the Terms.jsp view
	 * 
	 * @param request The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException If an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
		// Forward the request to the Terms JSP page for rendering
		request.getRequestDispatcher("WEB-INF/Pages/Terms.jsp").forward(request, response);
	}
}
