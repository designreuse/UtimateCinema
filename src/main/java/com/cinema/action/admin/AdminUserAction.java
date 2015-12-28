package com.cinema.action.admin;

import com.cinema.action.base.BaseAction;
import com.cinema.dao.UserDao;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * AdminUserAction
 * Created by rayn on 2015/12/27.
 */
@Namespace("/admin")
@Controller
@Scope("prototype")
public class AdminUserAction extends BaseAction {

	@Autowired
	private UserDao userDao;

	public AdminUserAction() {
		super(AdminUserAction.class);
	}

	@Action(value = "/users",
			results = {
					@Result(name = "success", location = "admin/users.jsp")
			}
	)
	public String index() {
		return SUCCESS;
	}
}
