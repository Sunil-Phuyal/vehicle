package com.FleetX.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.math.BigDecimal;

import com.FleetX.model.VehicleModel;
import com.FleetX.service.VehicleService;
import com.FleetX.util.ImageUtil;

/**
 * Servlet implementation for updating vehicle information in the FleetX system.
 * This controller specifically handles direct update operations from the admin interface.
 * Unlike EditVehicleController, this servlet only processes POST requests for updates.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateVehicle" })
@MultipartConfig  // Annotation to support multipart/form-data requests (file uploads)
public class UpdateVehicleController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VehicleService vehicleService;  // Service class for vehicle database operations

    /**
     * Constructor - initializes the VehicleService for database interactions
     */
    public UpdateVehicleController() {
        vehicleService = new VehicleService();
    }

    /**
     * Handles POST requests to update vehicle information
     * No doGet method implemented as this controller only processes form submissions
     * 
     * @param request HTTP request object containing updated vehicle form data
     * @param response HTTP response object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set proper character encoding for handling non-ASCII characters
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            // === Collect vehicle form data ===
            // Extract and parse all form fields from the request
            int id = Integer.parseInt(request.getParameter("id"));
            String category = request.getParameter("category").toLowerCase();
            String brand = request.getParameter("brand");
            String model = request.getParameter("model");
            int year = Integer.parseInt(request.getParameter("year"));
            String registrationNumber = request.getParameter("registrationNumber");
            BigDecimal dailyRate = new BigDecimal(request.getParameter("dailyRate"));
            String fuelType = request.getParameter("fuelType");
            String transmission = request.getParameter("transmission");
            int capacity = Integer.parseInt(request.getParameter("capacity"));
            String status = request.getParameter("status");
            String location = request.getParameter("location");
            String description = request.getParameter("description");
            String features = request.getParameter("features");
            String existingImageUrl = request.getParameter("existingImageUrl");  // Get current image URL from form

            // === Image upload handling ===
            // Process image upload if provided
            Part imagePart = request.getPart("imageUrl");
            String appPath = request.getServletContext().getRealPath("");  // Get application's real path for file storage
            String imageUrl;

            if (imagePart != null && imagePart.getSize() > 0) {
                // New image uploaded - process and store it
                imageUrl = new ImageUtil().uploadImage(imagePart, appPath, category);
                if (imageUrl == null) {
                    // Throw exception if image upload fails
                    throw new IOException("Image upload failed.");
                }
            } else {
                // No new image uploaded - keep the existing image URL
                imageUrl = existingImageUrl;
            }

            // === Create updated vehicle object ===
            // Create a vehicle object with all the updated information
            VehicleModel vehicle = new VehicleModel(id,
                category, brand, model, year, registrationNumber,
                dailyRate, fuelType, transmission, capacity, status,
                imageUrl, location, description, features
            );

            // === Store updated vehicle in database ===
            // Update the vehicle information in the database
            boolean success = vehicleService.updateVehicle(vehicle);

            // Set appropriate session messages based on operation result
            if (success) {
                request.getSession().setAttribute("message", "Vehicle updated successfully!");
                System.out.println("Vehicle updated successfully!");  // Console log for successful update
            } else {
                request.getSession().setAttribute("error", "Failed to update vehicle.");
                System.err.println("Vehicle update failed.");  // Error log for failed update
            }

            // Redirect to dashboard after processing
            response.sendRedirect(request.getContextPath() + "/Dashboard");

        } catch (Exception e) {
            // Handle any exceptions that occur during the update process
            e.printStackTrace();  // Print stack trace to server logs
            request.getSession().setAttribute("error", "Unexpected error: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/Dashboard");  // Redirect to dashboard with error message
        }
    }
}
