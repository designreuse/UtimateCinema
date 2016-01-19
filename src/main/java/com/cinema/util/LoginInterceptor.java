package com.cinema.util;

import com.cinema.dao.UserDao;
import com.cinema.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * LoginInterceptor
 * Created by rayn on 2015/12/28.
 */
public class LoginInterceptor extends AbstractInterceptor {     //登录拦截器

    @Autowired
    private UserDao userDao;


    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        ActionContext context = actionInvocation.getInvocationContext();
        Map<String, Object> session = context.getSession();
        User current_user = (User) session.get(LoginHelper.USER_SESSION);   //session 中获取当前用户
        if (current_user != null) {
            return actionInvocation.invoke();                                //继续登录
        } else {
            HttpServletRequest request = ServletActionContext.getRequest();
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user")) {
                        String username = "";
                        try {
                            username = URLDecoder.decode(cookie.getValue(), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        current_user = userDao.findByUsername(username);
                        if (current_user != null) {
                            session.put(LoginHelper.USER_SESSION, current_user);
                            return actionInvocation.invoke();
                        } else {
                            return Action.LOGIN;
                        }
                    }
                }
            }
        }
        return Action.LOGIN;
    }

    private String getNextURL(HttpServletRequest request) {
        String path = request.getRequestURI();
        String queryString = request.getQueryString();
        if (queryString == null) queryString = "";
        return path + "?" + queryString;
    }
}
