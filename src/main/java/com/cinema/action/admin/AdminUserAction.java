package com.cinema.action.admin;

import com.cinema.action.base.BaseAction;
import com.cinema.dao.UserDao;
import com.cinema.dao.generic.PageResult;
import com.cinema.model.User;
import com.cinema.util.LoginHelper;
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

	private String title;
	private int page;
	private int pageSize;

	private long id;
	private String username;
	private String password;
	private String email;
	private String phone;
	private int sex;

	public AdminUserAction() {
		super(AdminUserAction.class);
	}

	@Action(value = "/users",
			results = {
					@Result(name = "success", location = "admin/users.jsp")
			}
	)
	public String index() {
		title = "用户管理";
		pageSize = 10;
		return SUCCESS;
	}

	@Action(value = "/users/get")
	public String users() {
		logger.info(pageSize);
		PageResult pageResult = userDao.findAll(page, pageSize);
		jsonResponse.put("totalPage", pageResult.getPages());
		jsonResponse.put("page", page);
		jsonResponse.put("items", pageResult.getItems());
		return "json";
	}

	@Action(value = "/users/one")
	public String user() {
		jsonResponse.put("ret", JsonResult.OK);
		jsonResponse.put("item", userDao.findOne(id));
		return "json";
	}

	@Action(value = "/users/del")
	public String deleteUser() {
		if (id == LoginHelper.getCurrentUser(session).getId()) {
			jsonResponse.put("ret", JsonResult.FAIL);
			jsonResponse.put("error", "不能删除本人账户");
		} else {
			userDao.delete(id);
			jsonResponse.put("ret", JsonResult.OK);
		}
		return "json";
	}

	@Action(value = "/users/edit")
	public String editUser() {
		User user = userDao.findOne(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone(phone);
		user.setSex(sex == 1);
		userDao.update(user);
		jsonResponse.put("ret", JsonResult.OK);
		return "json";
	}

	public String getTitle() {
		return title;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}


	public void setId(long id) {
		this.id = id;
	}

	public void setSex(int sex) {
		this.sex = sex;
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
}
