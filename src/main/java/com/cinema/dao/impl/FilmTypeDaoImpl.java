package com.cinema.dao.impl;

import com.cinema.dao.FilmTypeDao;
import com.cinema.dao.generic.HibernatePageableDao;
import com.cinema.model.FilmType;
import org.springframework.stereotype.Repository;

/**
 * FilmTypeDaoImpl
 * Created by rayn on 2015/12/26.
 */
@Repository("filmTypeDao")
public class FilmTypeDaoImpl extends HibernatePageableDao<FilmType, Long>
		implements FilmTypeDao {

	public FilmTypeDaoImpl() {
		super(FilmType.class);
	}
}
