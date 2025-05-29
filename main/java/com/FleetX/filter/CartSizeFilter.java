package com.FleetX.filter;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import com.FleetX.service.CartService;

/**
 * Filter that calculates and sets the cart size as a request attribute
 * This makes the cart size available to all pages in the application
 */
@WebFilter("/*") // Applies to all requests
public class CartSizeFilter implements Filter {
    
    /**
     * Core filter method that processes each request
     * Gets the cart size from the session and adds it as a request attribute
     * 
     * @param request The servlet request
     * @param response The servlet response
     * @param chain The filter chain for invoking the next filter or resource
     * @throws IOException If an I/O error occurs
     * @throws ServletException If a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        
        // If session exists, get the cart size from the cart service
        if (session != null) {
            CartService cartService = new CartService(session);
            int cartSize = cartService.getCartSize();
            httpRequest.setAttribute("cartSize", cartSize);
        } else {
            // If no session exists, set cart size to 0
            httpRequest.setAttribute("cartSize", 0);
        }
        
        // Continue with the filter chain
        chain.doFilter(request, response);
    }
}
