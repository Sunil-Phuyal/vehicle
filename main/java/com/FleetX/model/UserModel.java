package com.FleetX.model;

/**
 * UserModel represents a user entity within the FleetX application.
 * This class stores all user-related information including personal details,
 * authentication credentials, and system role assignments.
 */
public class UserModel {
    
    // Unique identifier for the user
	private int id;
    // User's first name
    private String fname;
    // User's last name
    private String lname;
    // Username for login purposes
    private String uName;
    // Date of birth (stored as String)
    private String dob; 
    // Email address for contact and notifications
    private String email;
    // Phone number for contact purposes
    private String phone;
    // User's password (should be stored securely)
    private String password;
    // System role (e.g., customer, admin, manager)
    private String role;
    
    /**
     * Default constructor for creating an empty UserModel instance
     */
    public UserModel() {
		// TODO Auto-generated constructor stub
	}
    
    /**
     * Authentication constructor with minimal login credentials
     * Typically used for authentication processes
     * 
     * @param uName Username for login
     * @param password User's password
     */
    public UserModel(String uName, String password) {
        this.uName = uName;
        this.password = password;
    }
    
    /**
     * Profile constructor with basic user information without security details
     * Typically used for user profile displays or updates
     * 
     * @param fname User's first name
     * @param lname User's last name
     * @param uName Username
     * @param email User's email address
     * @param phone User's phone number
     */
	public UserModel(String fname, String lname, String uName, String email, String phone) {
		this.fname = fname;
		this.lname = lname;
		this.uName = uName;
		this.email = email;
		this.phone = phone;
	}
    
    /**
     * Registration constructor with all user details except ID
     * Typically used when creating new user accounts
     * 
     * @param fname User's first name
     * @param lname User's last name
     * @param uName Username
     * @param dob Date of birth
     * @param email User's email address
     * @param phone User's phone number
     * @param password User's password
     * @param role User's system role
     */
    public UserModel(String fname, String lname, String uName, String dob, String email, String phone,
                     String password, String role) {
        this.fname = fname;
        this.lname = lname;
        this.uName = uName;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }
    
    /**
     * Complete constructor with all user properties including ID
     * Typically used when retrieving existing user data from database
     * 
     * @param id Unique user identifier
     * @param fname User's first name
     * @param lname User's last name
     * @param uName Username
     * @param dob Date of birth
     * @param email User's email address
     * @param phone User's phone number
     * @param password User's password
     * @param role User's system role
     */
    public UserModel(int id, String fname, String lname, String uName, String dob, String email, String phone,
                     String password, String role) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.uName = uName;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }
    
    /**
     * Returns the user's unique identifier
     * 
     * @return The user ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * Sets the user's unique identifier
     * 
     * @param id The user ID to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Returns the user's first name
     * 
     * @return The first name
     */
    public String getFname() {
        return fname;
    }
    
    /**
     * Sets the user's first name
     * 
     * @param fname The first name to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }
    
    /**
     * Returns the user's last name
     * 
     * @return The last name
     */
    public String getLname() {
        return lname;
    }
    
    /**
     * Sets the user's last name
     * 
     * @param lname The last name to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }
    
    /**
     * Returns the user's username
     * 
     * @return The username
     */
    public String getuName() {
        return uName;
    }
    
    /**
     * Sets the user's username
     * 
     * @param uName The username to set
     */
    public void setuName(String uName) {
        this.uName = uName;
    }
    
    /**
     * Returns the user's date of birth
     * 
     * @return The date of birth as a string
     */
    public String getDob() {  
        return dob;
    }
    
    /**
     * Sets the user's date of birth
     * 
     * @param dob The date of birth to set
     */
    public void setDob(String dob) {  
        this.dob = dob;
    }
    
    /**
     * Returns the user's email address
     * 
     * @return The email address
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Sets the user's email address
     * 
     * @param email The email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Returns the user's phone number
     * 
     * @return The phone number
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * Sets the user's phone number
     * 
     * @param phone The phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * Returns the user's password
     * Note: For security purposes, this should typically return a hashed version
     * 
     * @return The password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Sets the user's password
     * Note: For security purposes, this should typically store a hashed version
     * 
     * @param password The password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Returns the user's system role
     * 
     * @return The role (e.g., customer, admin, manager)
     */
    public String getRole() {
        return role;
    }
    
    /**
     * Sets the user's system role
     * 
     * @param role The role to set (e.g., customer, admin, manager)
     */
    public void setRole(String role) {
        this.role = role;
    }
}
