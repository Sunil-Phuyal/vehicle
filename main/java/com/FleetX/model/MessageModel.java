package com.FleetX.model;
import java.sql.Timestamp;

/**
 * MessageModel represents a message entity within the FleetX application.
 * This class stores information about messages including sender details,
 * message content, and timing information for communication purposes.
 */
public class MessageModel {
    // Unique identifier for the message
    private int messageId;
    // User ID of the message sender
    private int userId;
    // Email address of the message sender or recipient
    private String email;     
    // Subject line of the message
    private String subject;
    // Main content/body of the message
    private String content;
    // Timestamp when the message was sent
    private Timestamp sentAt;
    
    /**
     * Default constructor for creating an empty MessageModel instance
     */
    public MessageModel() {}
    
    /**
     * Full constructor with all message properties
     * 
     * @param messageId Unique identifier for the message
     * @param userId User ID of the sender
     * @param email Email address associated with the message
     * @param subject Subject line of the message
     * @param content Main content/body of the message
     * @param sentAt Timestamp when the message was sent
     */
    public MessageModel(int messageId, int userId, String email, String subject, String content, Timestamp sentAt) {
        this.messageId = messageId;
        this.userId = userId;
        this.email = email;
        this.subject = subject;
        this.content = content;
        this.sentAt = sentAt;
    }
    
    /**
     * Simplified constructor with only content and timestamp
     * Useful for quick message creation without full metadata
     * 
     * @param content Main content/body of the message
     * @param sentAt Timestamp when the message was sent
     */
    public MessageModel(String content, Timestamp sentAt) {
        this.content = content;
        this.sentAt = sentAt;
    }
    
    // Getters and Setters
    
    /**
     * Returns the message's unique identifier
     * 
     * @return The message ID
     */
    public int getMessageId() {
        return messageId;
    }
    
    /**
     * Sets the message's unique identifier
     * 
     * @param messageId The message ID to set
     */
    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
    
    /**
     * Returns the user ID of the message sender
     * 
     * @return The user ID
     */
    public int getUserId() {
        return userId;
    }
    
    /**
     * Sets the user ID of the message sender
     * 
     * @param userId The user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    /**
     * Returns the email address associated with the message
     * 
     * @return The email address
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Sets the email address associated with the message
     * 
     * @param email The email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Returns the subject line of the message
     * 
     * @return The message subject
     */
    public String getSubject() {
        return subject;
    }
    
    /**
     * Sets the subject line of the message
     * 
     * @param subject The subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    /**
     * Returns the main content/body of the message
     * 
     * @return The message content
     */
    public String getContent() {
        return content;
    }
    
    /**
     * Sets the main content/body of the message
     * 
     * @param content The content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * Returns the timestamp when the message was sent
     * 
     * @return The sent timestamp
     */
    public Timestamp getSentAt() {
        return sentAt;
    }
    
    /**
     * Sets the timestamp when the message was sent
     * 
     * @param sentAt The sent timestamp to set
     */
    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }
}
