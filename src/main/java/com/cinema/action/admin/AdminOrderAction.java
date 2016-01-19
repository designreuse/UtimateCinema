package com.cinema.action.admin;

import com.cinema.action.base.BaseAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * AdminOrderAction
 * Created by rayn on 2015/12/30.
 */
@Namespace("/admin")
@Controller
@Scope("prototype")
public class AdminOrderAction extends BaseAction {

    private String title;
    private int page;
    private int pageSize;

    public AdminOrderAction() {
        super(AdminOrderAction.class);
    }


    @Action(value = "/orders",
            results = {
                    @Result(name = "success", location = "/views/admin/orders.jsp")
            }
    )
    public String index() {                                                      //navbar 中admin用户 点击所有订单
        title = "全部订单";                                                     //title赋值有什么用呢？
        return SUCCESS;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
