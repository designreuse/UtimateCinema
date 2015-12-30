package com.cinema.action;

import com.cinema.action.base.BaseAction;
import com.cinema.dao.FilmDao;
import com.cinema.model.CinemaSale;
import com.cinema.model.Film;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;

/**
 * IndexAction
 * Created by rayn on 2015/12/27.
 */
@ParentPackage("main")
@Controller
@Scope("prototype")
public class IndexAction extends BaseAction {

	@Autowired
	private FilmDao filmDao;


	private String title;
	private List<Film> filmList;
	private Set<CinemaSale> saleSet;

	private long filmId;
	private Film film;

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
		title = "电影订票系统";
		filmList = filmDao.findAllWithOrder("premiereDate", "desc");
		return SUCCESS;
	}

	@Action(value = "/film/sales",
			results = {
					@Result(name = "success", location = "/views/main/sales.jsp")
			}
	)
	public String sales() {
		film = filmDao.findOne(filmId);
		saleSet = film.getSales();
		title = film.getFilmName() + " - 订票";
		return SUCCESS;
	}

	public String getTitle() {
		return title;
	}

	public List<Film> getFilmList() {
		return filmList;
	}

	public void setFilmId(long filmId) {
		this.filmId = filmId;
	}

	public Film getFilm() {
		return film;
	}

	public Set<CinemaSale> getSaleSet() {
		return saleSet;
	}
}
