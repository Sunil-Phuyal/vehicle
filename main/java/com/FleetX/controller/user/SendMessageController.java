package com.FleetX.controller.user;

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
 * Servlet implementation class SendMessageController
 * This servlet handles sending messages from users through the contact form.
 * It processes the submitted email, subject, and message, validates the user,
 * and stores the message in the database.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/SendMessage" })
public class SendMessageController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Service class to handle contact-related operations
    private ContactService contactService;
    
    /**
     * Constructor - initializes the ContactService
     */
    public SendMessageController() {
        contactService = new ContactService();
    }
    
    /**
     * Handles POST requests from the contact form
     * @param request The HTTP request containing form parameters
     * @param response The HTTP response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException If an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract form data from the request
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        
        try {
            // Verify if user exists with the provided email
            UserModel user = contactService.getUserDetailByEmail(email);
            
            if (user != null) {
                // If user exists, attempt to insert the message
                boolean success = contactService.insertMessage(user.getId(), subject, message);
                
                if (success) {
                    // Set success message if message was inserted
                    request.getSession().setAttribute("messageStatus", "Message sent successfully!");
                } else {
                    // Set error message if insertion failed
                    request.getSession().setAttribute("messageStatus", "Failed to send message. Please try again.");
                }
            } else {
                // Set error message if user not found
                request.getSession().setAttribute("messageStatus", "User not found with provided email.");
            }
        } catch (SQLException e) {
            // Handle database exceptions
            e.printStackTrace();
            request.setAttribute("messageStatus", "Server error occurred.");
        }
        
        // Forward the request to the contact page with appropriate status
        request.getRequestDispatcher("/WEB-INF/Pages/contact.jsp").forward(request, response);
    }
}
