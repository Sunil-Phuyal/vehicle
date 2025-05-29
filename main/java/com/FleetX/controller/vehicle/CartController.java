package com.FleetX.controller.vehicle;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.FleetX.model.CartModel;
import com.FleetX.service.CartService;

/**
 * Servlet implementation class CartController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/cart" })
public class CartController extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartService cartService = new CartService(session);
        
        List<CartModel> cartItems = cartService.getCartItems();
        BigDecimal totalPrice = cartService.calculateTotalPrice();
        
        request.setAttribute("cartItems", cartItems);
        request.setAttribute("totalPrice", totalPrice);
        
        request.getRequestDispatcher("/WEB-INF/Pages/cart.jsp").forward(request, response);
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartService cartService = new CartService(session);
        
        String action = request.getParameter("action");
        
        if ("remove".equals(action)) {
            int itemIndex = Integer.parseInt(request.getParameter("itemIndex"));
            cartService.removeItem(itemIndex);
        } else if ("clear".equals(action)) {
            cartService.clearCart();
        }
        
        // Redirect back to cart page
        response.sendRedirect(request.getContextPath() + "/cart");
    }
}
