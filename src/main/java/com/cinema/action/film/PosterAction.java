package com.cinema.action.film;

import com.cinema.action.base.BaseAction;
import com.cinema.dao.FilmDao;
import com.cinema.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * PosterAction
 * Created by rayn on 2015/12/29.
 */
@Controller
@Scope("prototype")
public class PosterAction extends BaseAction {

    @Autowired
    private FilmDao filmDao;

    byte[] imageInByte;        //图片字节数组
    private long id;           //图片id

    public PosterAction() {
        super(PosterAction.class);
    }   //将类型传到基类action

    public String execute() {
        return SUCCESS;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getImageInByte() {
        Film film = filmDao.findOne(id);
        return film.getPoster();
    }

    public String getCustomContentType() {
        return "image/jpeg";
    }

    public String getCustomContentDisposition() { return "poster.jpg";}
}
