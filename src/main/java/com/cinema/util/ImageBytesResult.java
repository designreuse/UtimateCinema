package com.cinema.util;


import com.cinema.action.film.PosterAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;

/**
 * ImageBytesResult
 * Created by rayn on 2015/12/29.
 */
public class ImageBytesResult implements Result {

    public void execute(ActionInvocation actionInvocation)
            throws Exception {
        PosterAction action = (PosterAction) actionInvocation.getAction();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType(action.getCustomContentType());
        response.getOutputStream().write(action.getImageInByte());
        response.getOutputStream().flush();
    }
}
