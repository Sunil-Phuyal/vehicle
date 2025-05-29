package com.FleetX.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.FleetX.model.UserModel;
import com.FleetX.service.LoginService;
import com.FleetX.util.CookieUtil;
import com.FleetX.util.SessionUtil;

/**
 * Controller for handling user login functionality
 * Manages authentication and role-based redirection
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public LoginController() {
        super();
    }

    /**
     * Handles GET requests to the login page
     * Displays the login form
     * 
     * @param request HTTP request object
     * @param response HTTP response object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to login page JSP
        request.getRequestDispatcher("/WEB-INF/Pages/login.jsp").forward(request, response);
    }

    /**
     * Handles POST requests for user authentication
     * Validates credentials and manages role-based redirection
     * 
     * @param request HTTP request containing username and password
     * @param response HTTP response object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get login credentials from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Create user model with credentials
        UserModel userModel = new UserModel(username, password);
        
        // Authenticate user through service
        LoginService loginService = new LoginService();
        Boolean loginStatus = loginService.loginUser(userModel);

        if (loginStatus != null && loginStatus) {
            // Set session attributes for authenticated user
            SessionUtil.setAttribute(request, "username", username);

            // Handle redirect based on role
            String redirectUrl = "/"; // Default redirection URL
            
            // Simple role determination logic (admin or customer)
            String role = username.equals("admin") ? "admin" : "customer"; // Role logic
            
            // Store role in session and cookie
            SessionUtil.setAttribute(request, "role", role);
            CookieUtil.addCookie(response, "role", role, 5 * 30); // Cookie expires in 150 days (5*30)

            // Redirect to the appropriate page based on role
            if ("admin".equals(role)) {
                redirectUrl = "/Dashboard"; // Admin dashboard
            } else {
                redirectUrl = "/home"; // Customer home page
            }
            
            // Perform the redirection
            response.sendRedirect(request.getContextPath() + redirectUrl); 
        } else {
            // Error handling for failed login
            request.setAttribute("error", "User credential mismatch or Server is under maintenance");
            // Return to login page with error message
            request.getRequestDispatcher("/WEB-INF/Pages/login.jsp").forward(request, response);
        }
    }
}
