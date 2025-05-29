package com.FleetX.controller.admin;

import java.io.IOException;
import java.math.BigDecimal;
import com.FleetX.model.VehicleModel;
import com.FleetX.service.VehicleService;
import com.FleetX.util.ImageUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Controller for adding new vehicles to the system
 * Handles processing of form data and image uploads for new vehicles
 */
@SuppressWarnings("serial")
@WebServlet("/AddVehicle")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
    maxFileSize = 1024 * 1024 * 10,       // 10MB
    maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class AddVehicleController extends HttpServlet {
    // Service to handle database operations for vehicles
    private VehicleService vehicleService;

    /**
     * Initialize the controller by creating a VehicleService instance
     */
    @Override
    public void init() {
        vehicleService = new VehicleService();
    }

    /**
     * Process POST requests containing vehicle form data
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set character encoding to handle non-ASCII characters
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            // === Collect vehicle form data ===
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

            // === Image upload handling ===
            Part imagePart = request.getPart("imageUrl");
            String appPath = request.getServletContext().getRealPath("");
            String imageUrl = new ImageUtil().uploadImage(imagePart, appPath, category);

            if (imageUrl == null) {
                throw new IOException("Image upload failed.");
            }

            // === Create vehicle object ===
            VehicleModel vehicle = new VehicleModel(
                category, brand, model, year, registrationNumber,
                dailyRate, fuelType, transmission, capacity, status,
                imageUrl, location, description, features
            );

            // === Store vehicle in database ===
            boolean success = vehicleService.insertVehicle(vehicle);
            if (success) {
            	request.getSession().setAttribute("message", "Vehicle addition successful.");
            } else {
                request.getSession().setAttribute("message", "Failed to add vehicle.");
            }

            // Redirect to dashboard in both cases
            response.sendRedirect(request.getContextPath() + "/Dashboard");
        } catch (Exception e) {
            // Log and handle any exceptions that occur during processing
            e.printStackTrace();
            request.setAttribute("error", "Unexpected error occurred: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/Dashboard");
        }
    }
}
