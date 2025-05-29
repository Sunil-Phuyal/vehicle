package com.FleetX.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.FleetX.service.UserService;

/**
 * Controller for handling user account deletion requests
 * Maps to the /deleteAccount URL pattern
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/deleteAccount" })
public class DeleteAccountController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService; // Service class to handle user-related operations

    /**
     * Constructor - initializes the UserService
     */
    public DeleteAccountController() {
        userService = new UserService();
    }

    /**
     * Handles POST requests for account deletion
     * 
     * @param request HTTP request containing the username to delete
     * @param response HTTP response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get username from request parameters
        String username = request.getParameter("username");

        // Validate username presence
        if (username == null || username.isEmpty()) {
            System.out.println("Username not provided.");
            response.sendRedirect("userprofile");
            return;
        }

        // Get user ID from username
        int userId = userService.getUserIdByUsername(username);
        
        // Validate user ID
        if (userId <= 0) {
            System.out.println("Invalid user ID.");
            response.sendRedirect("userprofile");
            return;
        }

        // Attempt to delete the user
        boolean isDeleted = userService.deleteUser(userId);
        
        if (isDeleted) {
            System.out.println("User deleted successfully.");
            // Invalidate the user's session for security
            request.getSession().invalidate();
            // Redirect to login page
            response.sendRedirect("login");
        } else {
            System.out.println("Failed to delete user.");
            // Redirect back to profile page if deletion fails
            response.sendRedirect("userprofile");
        }
    }
}
