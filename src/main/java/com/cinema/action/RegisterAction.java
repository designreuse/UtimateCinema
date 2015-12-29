package com.cinema.action;

import com.cinema.action.base.BaseAction;
import com.cinema.dao.UserDao;
import com.cinema.model.User;
import com.cinema.util.LoginHelper;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Date;


/**
 * RegisterAction
 * Created by rayn on 2015/12/27.
 */
@Controller
@Scope("prototype")
public class RegisterAction extends BaseAction {

	@Autowired
	private UserDao userDao;

	private String username;
	private String password;
	private String email;
	private String phone;
	private int sex;

	public RegisterAction() {
		super(RegisterAction.class);
	}

	@Action(value = "/register",
			results = {
					@Result(name = "input", location = "register.jsp")
			}
	)
	public String index() {
		return INPUT;
	}

	@Action(value = "/register/post")
	public String register() {
		User has = userDao.findByUsername(username);
		if (has != null) {
			jsonResponse.put("ret", JsonResult.FAIL);
			jsonResponse.put("error", "该用户名已被注册");
			return "json";
		}
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone(phone);
		user.setSex(sex == 1);
		user.setAdmin(true);
		user.setRegisterTime(new Date());
		userDao.create(user);
		try {
			LoginHelper.login(request, response, user, false);
			jsonResponse.put("ret", JsonResult.OK);
			jsonResponse.put("url", request.getContextPath() + "/");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "json";
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
}
