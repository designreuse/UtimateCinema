package com.cinema.action.admin;

import com.cinema.action.base.BaseAction;
import com.cinema.dao.CinemaHallDao;
import com.cinema.dao.CinemaSaleDao;
import com.cinema.dao.FilmDao;
import com.cinema.model.CinemaHall;
import com.cinema.model.CinemaSale;
import com.cinema.model.Film;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

/**
 * AdminSaleAction
 * Created by rayn on 2015/12/29.
 */
@Namespace("/admin")
@Controller
@Scope("prototype")
public class AdminSaleAction extends BaseAction {

    @Autowired
    private FilmDao filmDao;

    @Autowired
    private CinemaSaleDao cinemaSaleDao;

    @Autowired
    private CinemaHallDao cinemaHallDao;

    private Film film;
    private CinemaHall cinemaHall;
    private Set<CinemaSale> saleSet;
    private List<CinemaHall> hallList;

    private String title;
    private String opt;               //opt是对应的操作类型
    private long id;
    private long filmId;
    private long hallId;
    private String startTime;       //电影开始时间，结束时间
    private String endTime;
    private double money;

    public AdminSaleAction() {
        super(AdminSaleAction.class);
    }     //同样的将class类型传入

    @Action(value = "/sales",
            results = {
                    @Result(name = "success", location = "admin/sales.jsp")    //返回到售票页面
            }
    )
    public String index() {           //action对应的方法
        title = "放映管理";
        film = filmDao.findOne(filmId);
        saleSet = film.getSales();
        hallList = cinemaHallDao.findAll();
        return SUCCESS;
    }

    @Action(value = "/sales/edit")    //编辑订单action
    public String addOrEditSale() {
        logger.info(startTime + " - " + endTime);
        CinemaSale sale;
        film = filmDao.findOne(filmId);
        cinemaHall = cinemaHallDao.findOne(hallId);
        if (opt.equals("add")) {
            sale = new CinemaSale();             //add new one
        } else {
            sale = cinemaSaleDao.findOne(id);  //找寻原订单
        }
        sale.setFilm(film);
        sale.setCinemaHall(cinemaHall);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            sale.setStartTime(sdf.parse(startTime));
            sale.setEndTime(sdf.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sale.setMoney(money);
        cinemaSaleDao.saveOrUpdate(sale);           //订单update
        jsonResponse.put("ret", JsonResult.OK);
        return "json";                             //??????
    }

    @Action(value = "/sales/one")                  //查看订单
    public String sales() {
        jsonResponse.put("ret", JsonResult.OK);
        jsonResponse.put("item", cinemaSaleDao.findOne(id));
        return "json";
    }

    @Action(value = "/sales/del")                 //删除订单
    public String deleteSale() {
        cinemaSaleDao.delete(id);
        jsonResponse.put("ret", JsonResult.OK);
        return "json";
    }


    //变量的getter、setter

    public Set<CinemaSale> getSaleSet() {
        return saleSet;
    }

    public List<CinemaHall> getHallList() {
        return hallList;
    }

    public String getTitle() {
        return title;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFilmId(long filmId) {
        this.filmId = filmId;
    }

    public long getFilmId() {
        return filmId;
    }

    public Film getFilm() {
        return film;
    }

    public void setHallId(long hallId) {
        this.hallId = hallId;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
