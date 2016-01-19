package com.cinema.action;

import com.cinema.action.base.BaseAction;
import com.cinema.dao.UserDao;
import com.cinema.model.User;
import com.cinema.util.LoginHelper;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.UnsupportedEncodingException;

/**
 * LoginAction
 * Created by rayn on 2015/12/24.
 */
@ParentPackage("main")
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
					@Result(name = "success", location = "login.jsp")
			}
	)
	public String index() {
		return SUCCESS;
	}                        //显示登录界面

	@Action(value = "/login/verify")                                  //验证界面
	public String login() {
		logger.info(rememberMe);
		User has = userDao.findByUsername(user.getUsername());
		if (has == null) {
			jsonResponse.put("ret", JsonResult.FAIL);
			jsonResponse.put("error", "用户不存在");
			return "json";
		} else if ( !has.getPassword().equals(user.getPassword()) ) {
			jsonResponse.put("ret", JsonResult.FAIL);
			jsonResponse.put("error", "密码填写错误");
		} else {
			try {
				LoginHelper.login(request, response, has, rememberMe == 1);
				jsonResponse.put("ret", JsonResult.OK);
				if (has.isAdmin()) {
					jsonResponse.put("url", request.getContextPath() + "/admin/users");
				} else {
					jsonResponse.put("url", request.getContextPath() + "/index");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "json";
	}

	@Action(value = "/logout",
			results = {
					@Result(name = "success", location = "login.jsp")
			}
	)
	public String destroy() {
		try {
			LoginHelper.logout(request, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
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

}
