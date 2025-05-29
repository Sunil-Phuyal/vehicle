package com.FleetX.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import com.FleetX.model.UserModel;
import com.FleetX.service.RegisterService;
import com.FleetX.util.PasswordUtil;
import com.FleetX.util.ValidationUtil;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })

public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/Pages/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String errorMessageString = validateRegistrationForm(request);
		if (errorMessageString != null) {
			request.setAttribute("fname", request.getParameter("fname"));
			request.setAttribute("lname", request.getParameter("lname"));
			request.setAttribute("username", request.getParameter("username"));
			request.setAttribute("dob", request.getParameter("dob"));
			request.setAttribute("email", request.getParameter("email"));
			request.setAttribute("phone", request.getParameter("phone"));
			request.setAttribute("error", errorMessageString);
			request.getRequestDispatcher("/WEB-INF/Pages/register.jsp").forward(request, response);
			return;
		}

		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String uname = request.getParameter("username");
		String dob = request.getParameter("dob");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		password = PasswordUtil.encrypt(uname, password);

		// Set default role
		String role = "customer";

		UserModel user = new UserModel(fname, lname, uname, dob, email, phone, password, role);

		RegisterService registerService = new RegisterService();
		boolean success = registerService.addUser(user);

		if (success) {
			request.setAttribute("successMessage", "Registered successfully! Please login.");
		} else {
			request.setAttribute("error", "Registration failed. Please try again.");
		}

		request.getRequestDispatcher("/WEB-INF/Pages/register.jsp").forward(request, response);
	}

	private String validateRegistrationForm(HttpServletRequest request) {
	    // Retrieve form data
	    String fname = request.getParameter("fname");
	    String lname = request.getParameter("lname");
	    String uname = request.getParameter("username");
	    String dob = request.getParameter("dob");
	    String email = request.getParameter("email");
	    String phone = request.getParameter("phone");
	    String password = request.getParameter("password");
	    String repassword = request.getParameter("repassword");

	    // Validate fields
	    if (ValidationUtil.isNullOrEmpty(fname))
	        return "First name is empty";
	    if (!ValidationUtil.isAlphabetic(fname))
	        return "First name must contain only letters";

	    if (ValidationUtil.isNullOrEmpty(lname))
	        return "Last name is empty";
	    if (!ValidationUtil.isAlphabetic(lname))
	        return "Last name must contain only letters";

	    if (ValidationUtil.isNullOrEmpty(uname))
	        return "Username is empty";
	    if (!ValidationUtil.isAlphanumericStartingWithLetter(uname))
	        return "Username must start with a letter and contain only letters and numbers";

	    if (ValidationUtil.isNullOrEmpty(dob))
	        return "Date of birth is empty";
	    
	    LocalDate dobDate = null;
	    try {
	        dobDate = LocalDate.parse(dob);
	    } catch (Exception e) {
	        return "Invalid date format for DOB";
	    }

	    if (!ValidationUtil.isAgeAtLeast21(dobDate))
	        return "User age must be 18 or more";

	    if (ValidationUtil.isNullOrEmpty(email))
	        return "Email is empty";
	    if (!ValidationUtil.isValidEmail(email))
	        return "Invalid email format";

	    if (ValidationUtil.isNullOrEmpty(phone))
	        return "Phone number is empty";
	    if (!ValidationUtil.isValidPhoneNumber(phone))
	        return "Phone number must start with 98 and be 10 digits";

	    if (ValidationUtil.isNullOrEmpty(password))
	        return "Password is empty";
	    if (ValidationUtil.isNullOrEmpty(repassword))
	        return "Please retype the password";
	    if (!ValidationUtil.doPasswordsMatch(password, repassword))
	        return "Passwords do not match";

	    return null;
	}

}
