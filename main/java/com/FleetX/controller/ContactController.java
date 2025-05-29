package com.FleetX.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.FleetX.model.UserModel;
import com.FleetX.service.ContactService;

/**
 * Servlet implementation class ContactController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/contact" })
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/Pages/contact.jsp").forward(request, response);
	}
}
