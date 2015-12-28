package com.cinema.action;

import com.cinema.action.base.BaseAction;
import com.cinema.dao.UserDao;
import com.cinema.model.User;
import com.cinema.util.LoginHelper;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


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

	@Action(value = "/register/post",
			results = {
					@Result(name = "success", type = "redirect",
							params = {
									"location", "login"
							}),
					@Result(name = "error", type = "json")
			}
	)
	public String regsiter() {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone(phone);
		user.setSex(sex == 1);
		user.setAdmin(false);
		user.setRegisterTime(LocalDateTime.now());
		userDao.create(user);
		LoginHelper.login(request, response, user, false);
		return SUCCESS;
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
