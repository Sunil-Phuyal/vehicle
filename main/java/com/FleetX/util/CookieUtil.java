package com.FleetX.util;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * CookieUtil provides utility methods for managing HTTP cookies in the FleetX application.
 * This class offers functionality to create, retrieve, and delete cookies, facilitating
 * session management and user preference storage.
 */
public class CookieUtil {
	
	/**
	 * Creates and adds a new cookie to the HTTP response.
	 * 
	 * @param response The HTTP response object to which the cookie will be added
	 * @param name The name of the cookie to be created
	 * @param value The value to be stored in the cookie
	 * @param maxAge The maximum age of the cookie in seconds (determines expiration)
	 *               A value of -1 means the cookie will expire when the browser is closed
	 *               A value of 0 will delete the cookie
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		cookie.setPath("/"); // Make cookie available to the entire application
		response.addCookie(cookie);
	}
	
	/**
	 * Retrieves a specific cookie from the HTTP request based on its name.
	 * Uses Java 8 Stream API to efficiently find the matching cookie.
	 * 
	 * @param request The HTTP request object from which to retrieve cookies
	 * @param name The name of the cookie to find
	 * @return The Cookie object if found, null otherwise
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		if(request.getCookies() != null) {
			return Arrays.stream(request.getCookies())
					.filter(cookie -> name.equals(cookie.getName()))
					.findFirst()
					.orElse(null);
		}
		return null;
	}
	
	/**
	 * Deletes a cookie by setting its value to null and max age to zero.
	 * This effectively instructs the browser to remove the cookie.
	 * 
	 * @param response The HTTP response object to which the deletion cookie will be added
	 * @param name The name of the cookie to be deleted
	 */
	public static void deleteCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/"); // Make cookie available to the entire application
        response.addCookie(cookie);
    }
}
