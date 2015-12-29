package com.cinema.action;

import com.cinema.action.base.BaseAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * IndexAction
 * Created by rayn on 2015/12/27.
 */
@ParentPackage("main")
@Controller
@Scope("prototype")
public class IndexAction extends BaseAction {


	public IndexAction() {
		super(IndexAction.class);
	}

	@Action(value = "/",
			results = {
					@Result(name = "success", type = "redirect",
							params = { "location", "index" })
			}
	)
	public String main() {
		return SUCCESS;
	}

	@Action(value = "/index",
			results = {
					@Result(name = "success", location = "main/index.jsp")
			}
	)
	public String index() {
		return SUCCESS;
	}
}
