package com.FleetX.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import com.FleetX.util.CookieUtil;
import com.FleetX.util.SessionUtil;

/**
 * Authentication and authorization filter for the FleetX application.
 * Controls access to different parts of the application based on user roles.
 * Handles routing logic for admins, customers, and unauthenticated users.
 */
@SuppressWarnings("serial")
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter extends HttpFilter implements Filter {

    // Common
    private static final String LOGIN = "/login";
    private static final String REGISTER = "/register";
    private static final String ROOT = "/";
    private static final String HOME = "/home";
    private static final String ABOUT = "/about";

    // Admin Routes
    private static final String DASHBOARD = "/Dashboard";
    private static final String EDIT_VEHICLE = "/EditVehicle";

    // Customer Routes
    private static final String VEHICLE = "/vehicle";
    private static final String VEHICLE_DETAIL = "/vehicleDetail";
    private static final String CHECKOUT = "/checkout";
    private static final String CART = "/cart";
    private static final String USER_PROFILE = "/userprofile";
    private static final String CONTACT = "/contact";

    /**
     * Called when the filter is destroyed.
     * Not used in this implementation.
     */
    @Override
    public void destroy() {
        // Not used
    }

    /**
     * Called when the filter is initialized.
     * Not used in this implementation.
     * 
     * @param fConfig Filter configuration
     * @throws ServletException If a servlet error occurs
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        // Not used
    }

    /**
     * Main filter method that handles request authentication and authorization.
     * Implements role-based access control for the application.
     * 
     * @param request The servlet request
     * @param response The servlet response
     * @param chain The filter chain for invoking the next filter or resource
     * @throws IOException If an I/O error occurs
     * @throws ServletException If a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        String ctx = req.getContextPath();
        boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;
        String role = CookieUtil.getCookie(req, "role") != null ? CookieUtil.getCookie(req, "role").getValue() : null;

        // Whitelisted URIs (always allowed)
        if (uri.endsWith(ROOT) || uri.endsWith(HOME) || uri.endsWith(ABOUT)
                || uri.endsWith(LOGIN) || uri.endsWith(REGISTER)
                || uri.endsWith(".css") || uri.endsWith(".js") || uri.contains("/assets/")) {
            chain.doFilter(request, response);
            return;
        }

        // Admin Access Control
        if ("admin".equals(role)) {
            if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
                res.sendRedirect(ctx + DASHBOARD);
            } else if (uri.startsWith(ctx + DASHBOARD) || uri.startsWith(ctx + EDIT_VEHICLE)) {
                chain.doFilter(request, response);
            } else {
                // Restrict admin from accessing customer pages
                res.sendRedirect(ctx + DASHBOARD);
            }
            return;
        }

        // Customer Access Control
        if ("customer".equals(role)) {
            if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
                res.sendRedirect(ctx + HOME);
            } else if (uri.startsWith(ctx + VEHICLE)
                    || uri.startsWith(ctx + VEHICLE_DETAIL)
                    || uri.startsWith(ctx + CHECKOUT)
                    || uri.startsWith(ctx + CART)
                    || uri.startsWith(ctx + USER_PROFILE)
                    || uri.startsWith(ctx + CONTACT)
                    || uri.startsWith(ctx + HOME)) {
                chain.doFilter(request, response);
            } else {
                // Restrict customer from accessing admin or other protected pages
                res.sendRedirect(ctx + HOME);
            }
            return;
        }

        // Not Logged In: restrict access to protected routes
        if (uri.startsWith(ctx + VEHICLE)
                || uri.startsWith(ctx + VEHICLE_DETAIL)
                || uri.startsWith(ctx + CHECKOUT)
                || uri.startsWith(ctx + CART)
                || uri.startsWith(ctx + USER_PROFILE)
                || uri.startsWith(ctx + DASHBOARD)
                || uri.startsWith(ctx + EDIT_VEHICLE)) {
            res.sendRedirect(ctx + LOGIN);
            return;
        }

        // Allow all others
        chain.doFilter(request, response);
    }
}
