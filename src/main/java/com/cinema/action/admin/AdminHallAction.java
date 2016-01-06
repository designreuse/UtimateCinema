package com.cinema.action.admin;

import com.cinema.action.base.BaseAction;
import com.cinema.dao.CinemaHallDao;
import com.cinema.dao.generic.PageResult;
import com.cinema.model.CinemaHall;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * AdminHallAction
 * Created by Administrator on 2015/12/28.
 */
@Namespace("/admin")
@Controller
@Scope("prototype")
public class AdminHallAction extends BaseAction {

    private String title;
    private int page;
    private int pageSize;

    private String opt;
    private long id;
    private String name; //影厅名（几号厅）
    private int rowSize;
    private int columnSize;

    @Autowired
    private CinemaHallDao cinemaHallDao;

    public AdminHallAction() {
        super(AdminHallAction.class);
    }

    /**
     * 跳转到影厅管理主页
     */
    @Action(value = "/halls",
            results = {
                    @Result(name = "success", location = "admin/halls.jsp")
            }
    )
    public String index() {
        title = "影厅管理";
        pageSize = 10;
        return SUCCESS;
    }

    /**
     * 获取影厅信息
     */
    @Action(value = "/halls/get")
    public String halls() {
        logger.info(pageSize);
        PageResult pageResult = cinemaHallDao.findAll(page, pageSize);
        jsonResponse.put("totalPage", pageResult.getPages());
        jsonResponse.put("page", page);
        jsonResponse.put("items", pageResult.getItems());
        return "json";
    }

    @Action(value = "/halls/one")
    public String hall() {
        jsonResponse.put("ret", JsonResult.OK);
        jsonResponse.put("item", cinemaHallDao.findOne(id));
        return "json";
    }

    @Action(value = "/halls/del")
    public String deleteFilm() {
        cinemaHallDao.delete(id);
        jsonResponse.put("ret", JsonResult.OK);
        return "json";
    }

    /**
     * 添加或修改影厅信息
     */
    @Action(value = "/halls/edit")
    public String addOrEditHall() {
        CinemaHall hall;
        if (opt.equals("add")) {
            CinemaHall has = cinemaHallDao.findByName(name);
            if (has != null) {
                jsonResponse.put("ret", JsonResult.FAIL);
                jsonResponse.put("error", "该影厅已存在");
                return "json";
            }
            hall = new CinemaHall();
        } else {
            hall = cinemaHallDao.findOne(id);
        }
        hall.setName(name);
        hall.setColumnSize(columnSize);
        hall.setRowSize(rowSize);
        cinemaHallDao.saveOrUpdate(hall);
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


    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public void setId(long id) {
        this.id = id;
    }
}
