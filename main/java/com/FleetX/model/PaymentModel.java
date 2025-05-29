package com.FleetX.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * PaymentModel represents a payment transaction in the FleetX system.
 * This class stores all information related to a payment including
 * payment identifier, associated rental, payment amount, and timestamp.
 */
public class PaymentModel {
    // Payment identification
    private int paymentId;
    private int rentalId;
    
    // Payment details
    private BigDecimal amount;
    private Timestamp paymentDate;
    
    /**
     * Default constructor
     */
    public PaymentModel() {
	}
    
    /**
     * Parameterized constructor to initialize all fields
     * 
     * @param paymentId    Unique identifier for the payment
     * @param rentalId     ID of the rental this payment is associated with
     * @param amount       Payment amount
     * @param paymentDate  Timestamp when payment was made
     */
	public PaymentModel(int paymentId, int rentalId, BigDecimal amount, Timestamp paymentDate) {
		this.paymentId = paymentId;
		this.rentalId = rentalId;
		this.amount = amount;
		this.paymentDate = paymentDate;
	}
	
	/**
	 * Gets the payment ID
	 * @return The payment ID
	 */
	public int getPaymentId() {
		return paymentId;
	}
	
	/**
	 * Sets the payment ID
	 * @param paymentId The payment ID to set
	 */
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	
	/**
	 * Gets the rental ID associated with this payment
	 * @return The rental ID
	 */
	public int getRentalId() {
		return rentalId;
	}
	
	/**
	 * Sets the rental ID
	 * @param rentalId The rental ID to set
	 */
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}
	
	/**
	 * Gets the payment amount
	 * @return The payment amount as BigDecimal
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	
	/**
	 * Sets the payment amount
	 * @param amount The payment amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	/**
	 * Gets the payment date and time
	 * @return The payment timestamp
	 */
	public Timestamp getPaymentDate() {
		return paymentDate;
	}
	
	/**
	 * Sets the payment date and time
	 * @param paymentDate The payment timestamp to set
	 */
	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

}
