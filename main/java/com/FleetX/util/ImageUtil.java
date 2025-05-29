package com.FleetX.util;

import java.io.File;
import java.io.IOException;
import jakarta.servlet.http.Part;

/**
 * Utility class for handling image uploads in the FleetX application.
 * Provides methods for extracting image names and uploading image files.
 */
public class ImageUtil {
    
    /**
     * Extracts the original filename from a multipart request Part.
     * 
     * @param part The Part object containing the uploaded file
     * @return The original filename of the uploaded file, or "download.png" if filename cannot be determined
     */
    public String getImageNameFromPart(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        if (contentDisp != null) {
            for (String s : contentDisp.split(";")) {
                if (s.trim().startsWith("filename")) {
                    String fileName = s.substring(s.indexOf('=') + 1).trim().replace("\"", "");
                    if (!fileName.isEmpty()) {
                        return fileName;
                    }
                }
            }
        }
        return "download.png";
    }
    
    /**
     * Uploads an image file to the server's file system.
     * 
     * @param part The Part object containing the uploaded file
     * @param appPath The base application path on the server
     * @param categoryFolder The category subfolder where the image should be stored
     * @return The relative path to the uploaded image (for database storage), or null if upload fails
     */
    public String uploadImage(Part part, String appPath, String categoryFolder) {
        String imageName = getImageNameFromPart(part);
        // Construct the server-side upload path
        String uploadDirPath = appPath + "assets" + File.separator + "vehicle" + File.separator + categoryFolder;
        File uploadDir = new File(uploadDirPath);
        // Create directory if it doesn't exist
        if (!uploadDir.exists() && !uploadDir.mkdirs()) {
            System.err.println("Failed to create directory: " + uploadDirPath);
            return null;
        }
        // Full file path
        String fullPath = uploadDirPath + File.separator + imageName;
        try {
            part.write(fullPath);
            // Return relative path to be used in DB
            return categoryFolder + "/" + imageName;
        } catch (IOException e) {
            System.err.println("Image upload failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
