package com.cinema.dao.impl;

import com.cinema.dao.FilmDao;
import com.cinema.dao.generic.HibernatePageableDao;
import com.cinema.model.Film;
import org.springframework.stereotype.Repository;

/**
 * FilmDaoImpl
 * Created by rayn on 2015/12/26.
 */
@Repository("filmDao")
public class FilmDaoImpl extends HibernatePageableDao<Film, Long>
		implements FilmDao {

	public FilmDaoImpl() {
		super(Film.class);
	}
}
