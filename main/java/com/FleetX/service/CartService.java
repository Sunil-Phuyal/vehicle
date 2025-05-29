package com.FleetX.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.FleetX.model.CartModel;
import jakarta.servlet.http.HttpSession;

/**
 * Service class that manages shopping cart operations in the FleetX application.
 * This class handles adding, removing, retrieving cart items and calculating totals.
 * Cart items are stored in the HTTP session for persistence across requests.
 */
public class CartService {
    private static final String CART_SESSION_KEY = "cartItems";
    private HttpSession session;
    
    /**
     * Constructor that initializes the cart service with the user's session.
     * 
     * @param session The HTTP session used to store cart data
     */
    public CartService(HttpSession session) {
        this.session = session;
    }
    
    /**
     * Adds a new item to the cart if it doesn't already exist.
     * Prevents duplicate vehicles by checking the vehicle ID.
     * 
     * @param item The cart item to add
     * @return true if item was added successfully, false if it already exists
     */
    @SuppressWarnings("unchecked")
    public boolean addItem(CartModel item) {
        List<CartModel> cart = (List<CartModel>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
        }
        // Check for duplicate vehicle by vehicle ID
        boolean alreadyExists = cart.stream()
                .anyMatch(existingItem -> existingItem.getVehicleId() == item.getVehicleId());
        if (!alreadyExists) {
            cart.add(item);
            session.setAttribute(CART_SESSION_KEY, cart);
            return true;
        }
        return false; // Not added due to duplication
    }
    
    /**
     * Removes an item from the cart at the specified index.
     * 
     * @param index The position of the item to remove
     */
    @SuppressWarnings("unchecked")
    public void removeItem(int index) {
        List<CartModel> cart = (List<CartModel>) session.getAttribute(CART_SESSION_KEY);
        if (cart != null && index >= 0 && index < cart.size()) {
            cart.remove(index);
            session.setAttribute(CART_SESSION_KEY, cart);
        }
    }
    
    /**
     * Retrieves all items currently in the cart.
     * If the cart doesn't exist yet, creates a new empty cart.
     * 
     * @return List of cart items
     */
    @SuppressWarnings("unchecked")
    public List<CartModel> getCartItems() {
        List<CartModel> cart = (List<CartModel>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }
    
    /**
     * Removes all items from the cart.
     * Logs the clearing operation and the result.
     */
    public void clearCart() {
        System.out.println("Clearing cart...");
        session.removeAttribute(CART_SESSION_KEY);
        System.out.println("Cart after clearing: " + session.getAttribute(CART_SESSION_KEY));
    }
    
    /**
     * Calculates the total price of all items in the cart.
     * Uses BigDecimal for precise currency calculations.
     * 
     * @return The total price of all cart items
     */
    public BigDecimal calculateTotalPrice() {
        List<CartModel> cart = getCartItems();
        // Sum the total prices of all cart items, using BigDecimal for precision
        return cart.stream()
                .map(CartModel::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    /**
     * Gets the number of items in the cart.
     * 
     * @return The number of items in the cart, or 0 if the cart is empty
     */
    @SuppressWarnings("unchecked")
    public int getCartSize() {
        List<CartModel> cart = (List<CartModel>) session.getAttribute(CART_SESSION_KEY);
        return cart != null ? cart.size() : 0;
    }
}
