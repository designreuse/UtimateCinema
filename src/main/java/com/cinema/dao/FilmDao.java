package com.cinema.dao;

import com.cinema.dao.generic.PageableGenericDao;
import com.cinema.model.Film;

/**
 * FilmDao
 * Created by rayn on 2015/12/26.
 */
public interface FilmDao extends PageableGenericDao<Film, Long> {

    Film findByFilmNameAndDirector(String filmName, String director);
}
