package com.cinema.action;

import com.cinema.action.base.BaseAction;
import com.cinema.dao.UserDao;
import com.cinema.model.User;
import com.cinema.util.LoginHelper;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;

/**
 * LoginAction
 * Created by rayn on 2015/12/24.
 */
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction {

	@Autowired
	private UserDao userDao;

	private User user;

	private int rememberMe;

	public LoginAction() {
		super(LoginAction.class);
	}

	@Action(value = "/login",
			results = {
					@Result(name = "success", location = "login.jsp"),
					@Result(name = "admin", type = "redirect",
							params = {
									"location", "admin/users"
							}),
					@Result(name = "normal", type = "redirect",
							params = {
									"location", "index"
							})
			}
	)
	public String index() {
		user = (User) session.getAttribute(LoginHelper.USER_SESSION);
		if (user != null) {
			logger.info(user);
			return dispatchUser(user);
		}
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user")) {
					user = userDao.findByUsername(cookie.getValue());
					if (user != null) {
						session.setAttribute(LoginHelper.USER_SESSION, user);
						return dispatchUser(user);
					} else {
						return SUCCESS;
					}
				}
			}
		}
		return SUCCESS;
	}

	@Action(value = "/login/verify",
			results = {
					@Result(name = "admin", type = "redirect",
							params = {
									"location", "admin/users"
							}),
					@Result(name = "normal", type = "redirect",
							params = {
									"location", "index"
							}),
					@Result(name = "error", type = "json", params = {"root", "jsonResponse"})
			}
	)
	public String login() {
		logger.info(rememberMe);
		User has = userDao.findByUsername(user.getUsername());
		if (has == null) {
			jsonResponse.put("ret", JsonResult.FAIL);
			jsonResponse.put("error", "用户不存在");
			return ERROR;
		} else if ( !has.getPassword().equals(user.getPassword()) ) {
			jsonResponse.put("ret", JsonResult.FAIL);
			jsonResponse.put("error", "密码填写错误");
			return ERROR;
		}
		LoginHelper.login(request, response, has, rememberMe == 1);
		return dispatchUser(has);
	}

	@Action(value = "/logout",
			results = {
					@Result(name = "success", location = "login.jsp")
			}
	)
	public String destroy() {
		LoginHelper.logout(request, response);
		return SUCCESS;
	}

	@JSON(serialize = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JSON(serialize = false)
	public int getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(int rememberMe) {
		this.rememberMe = rememberMe;
	}

	private String dispatchUser(User user) {
		if (user.isAdmin()) return "admin";
		else                return "normal";
	}
}
