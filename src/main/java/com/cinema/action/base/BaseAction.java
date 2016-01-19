package com.cinema.action.base;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * BaseAction
 * Created by rayn on 2015/12/27.
 */


//base action 规范了action的一般操作，定义logger，map等等一些必要的变量
public class BaseAction extends ActionSupport {

	protected Logger logger;
	protected Map<String, Object> jsonResponse;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	protected class JsonResult {
		public static final String OK = "ok";
		public static final String FAIL = "fail";
	}

	public BaseAction(Class type) {
		logger = LogManager.getLogger(type);
		jsonResponse = new HashMap<String, Object>();
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		session = request.getSession();
	}

	@JSON(name = "response")      //将get方法返回的痛惜封装成json对象
	public Map<String, Object> getJsonResponse() {
		return jsonResponse;
	}


}
