package com.FleetX.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

/**
 * Utility class providing various validation methods for input data.
 * This class contains methods to validate strings, dates, and other common input types.
 */
public class ValidationUtil {
	
	/**
	 * Validates if a field is null or empty.
	 * 
	 * @param value The string to check
	 * @return true if the string is null or contains only whitespace, false otherwise
	 */
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
    
    /**
     * Validates if a string contains only letters (a-z, A-Z).
     * 
     * @param value The string to check
     * @return true if the string contains only alphabetic characters, false otherwise
     */
    public static boolean isAlphabetic(String value) {
        return value != null && value.matches("^[a-zA-Z]+$");
    }
    
    /**
     * Validates if a string starts with a letter and contains only letters and numbers.
     * 
     * @param value The string to check
     * @return true if the string starts with a letter and contains only alphanumeric characters, false otherwise
     */
    public static boolean isAlphanumericStartingWithLetter(String value) {
        return value != null && value.matches("^[a-zA-Z][a-zA-Z0-9]*$");
    }
    
    /**
     * Validates if a person is at least 18 years old based on their date of birth.
     * 
     * @param dob The date of birth to check
     * @return true if the person is at least 18 years old, false otherwise
     */
    public static boolean isAgeAtLeast21(LocalDate dob) {
    	if(dob == null) {
    		return false;
    	}
    	LocalDate todayDate = LocalDate.now();
    	return Period.between(dob, todayDate).getYears() >= 18;
    }
    
    /**
     * Validates if a string is a valid email address format.
     * 
     * @param email The email address to validate
     * @return true if the string is a valid email address, false otherwise
     */
    public static boolean isValidEmail(String email) {
    	String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && Pattern.matches(emailRegex, email);
    }
    
    /**
     * Validates if a string is a valid phone number (10 digits starting with 98).
     * 
     * @param number The phone number to validate
     * @return true if the string is a valid phone number, false otherwise
     */
    public static boolean isValidPhoneNumber(String number) {
        return number != null && number.matches("^98\\d{8}$");
    }
    
    /**
     * Validates if a password meets security requirements:
     * - At least 1 capital letter
     * - At least 1 number
     * - At least 1 special symbol
     * - Minimum 8 characters
     * 
     * @param password The password to validate
     * @return true if the password meets all requirements, false otherwise
     */
    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password != null && password.matches(passwordRegex);
    }
    
    /**
     * Checks if the password and confirmation password match.
     * 
     * @param password The original password
     * @param repassword The confirmation password
     * @return true if passwords match, false otherwise
     */
    public static boolean doPasswordsMatch(String password, String repassword) {
    	return password != null && password.equals(repassword);
    }
}
