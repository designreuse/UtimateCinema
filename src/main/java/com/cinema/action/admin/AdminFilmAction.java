package com.cinema.action.admin;

import com.cinema.action.base.BaseAction;
import com.cinema.dao.FilmDao;
import com.cinema.dao.generic.PageResult;
import com.cinema.model.Film;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * AdminFilmAction
 * Created by rayn on 2015/12/28.
 */
@Namespace("/admin")
@Controller
@Scope("prototype")
public class AdminFilmAction extends BaseAction {

    @Autowired
    private FilmDao filmDao;                           //spring bean的自动装配

    private String title;
    private int page;
    private int pageSize;

    // film upload   jsp参数过来自动赋值
    private String opt;
    private long id;
    private String filmName;
    private String director;
    private String actors;
    private String language;
    private int length;
    private Date premiereDate;
    private String intro;
    private File poster;



    public AdminFilmAction() {
        super(AdminFilmAction.class);
    }

    @Action(value = "/films",
            results = {
                    @Result(name = "success", location = "admin/films.jsp")
            }
    )
    public String index() {                                   //navbar 中影片管理的点击之后的action响应
        title = "电影管理";                                  //pagesize值得设置用于jsp中的接收
        pageSize = 10;
        return SUCCESS;
    }


    @Action(value = "/films/edit")                           //修改电影的时候
    public String addOrEditFilm() {
        byte[] fileContent = null;
        Film film;
        logger.debug(opt);
        if (opt.equals("add")) {                             //操作是add时
            Film has = filmDao.findByFilmNameAndDirector(filmName, director);
            if (has != null) {
                jsonResponse.put("ret", JsonResult.FAIL);
                jsonResponse.put("error", "影片已存在");
            }

            film = new Film();
        } else {                                             //操作是edit时
            film = filmDao.findOne(id);
        }
        try {
            if (poster != null) {                          //海报不为空时。将海报转化成字节数组
                fileContent = IOUtils.toByteArray(new FileInputStream(poster));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        film.setFilmName(filmName);
        film.setDirector(director);
        film.setActors(actors);
        film.setLanguage(language);
        film.setLength(length);
        film.setPoster(fileContent);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            film.setPremiereDate(sdf.parse(sdf.format(premiereDate)));//格式化时间存入
        } catch (ParseException e) {
            e.printStackTrace();
        }
        film.setIntro(intro);                                         //影片详情存入
        filmDao.saveOrUpdate(film);
        jsonResponse.put("ret", JsonResult.OK);
        return "json";                                               //返回JsonResponse
    }


    @Action(value = "/films/get")                    //页面显示刷新的时候
    public String filmList() {
        logger.info("page:" + page + "  pageSize:" + pageSize);
        PageResult pageResult = filmDao.findAllWithOrder(page, pageSize, "premiereDate", "desc");
        jsonResponse.put("totalPage", pageResult.getPages());
        jsonResponse.put("page", page);
        jsonResponse.put("items", pageResult.getItems());
        return "json";
    }

    @Action(value = "/films/one")                    //查找id的电影
    public String film() {
        jsonResponse.put("ret", JsonResult.OK);
        jsonResponse.put("item", filmDao.findOne(id));
        return "json";
    }

    @Action(value = "/films/del")                   //删除id电影
    public String deleteFilm() {
        filmDao.delete(id);
        jsonResponse.put("ret", JsonResult.OK);
        return "json";
    }


    //getter setter方法
    public String getTitle() {
        return title;
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

    public void setPoster(File poster) {
        this.poster = poster;
    }

    public void setPremiereDate(Date premiereDate) {
        this.premiereDate = premiereDate;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }
}
