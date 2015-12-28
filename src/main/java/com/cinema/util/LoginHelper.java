package com.cinema.util;

import com.cinema.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * LoginHelper
 * Created by rayn on 2015/12/27.
 */
public class LoginHelper {

	public static final String USER_SESSION = "current_user";

	public static void login(HttpServletRequest request, HttpServletResponse response,
	                         User user, boolean remember) {
		if (remember) {
			Cookie rememberCookie = new Cookie("user", user.getUsername());
			rememberCookie.setMaxAge(60 * 60 * 24 * 3); // 三天
			rememberCookie.setPath("/");
			response.addCookie(rememberCookie);
		}
		request.getSession().setAttribute(USER_SESSION, user);
	}

	public static void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User current_user = (User) session.getAttribute(USER_SESSION);
		if (current_user != null) {
			request.getSession().removeAttribute(USER_SESSION);
			Cookie rememberCookie = new Cookie("user", current_user.getUsername());
			rememberCookie.setMaxAge(0);
			rememberCookie.setPath("/");
			response.addCookie(rememberCookie);
		}
	}
}