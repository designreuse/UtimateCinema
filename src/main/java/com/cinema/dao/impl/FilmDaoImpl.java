package com.cinema.dao.impl;

import com.cinema.dao.FilmDao;
import com.cinema.dao.generic.HibernatePageableDao;
import com.cinema.model.Film;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public Film findByFilmNameAndDirector(String filmName, String director) {
		Criteria criteria = getCurrentSession().createCriteria(type);
		criteria.add(Restrictions.and(
				Restrictions.eq("filmName", filmName),
				Restrictions.eq("director", director)));
		return (Film) criteria.uniqueResult();
	}
}
