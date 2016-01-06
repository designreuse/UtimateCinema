package com.cinema.dao.impl;

import com.cinema.dao.CinemaHallDao;
import com.cinema.dao.generic.HibernatePageableDao;
import com.cinema.model.CinemaHall;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * CinemaHallDaoImpl
 * Created by rayn on 2015/12/26.
 */
@Repository("cinemaHallDao")
public class CinemaHallDaoImpl extends HibernatePageableDao<CinemaHall, Long>
		implements CinemaHallDao {

	public CinemaHallDaoImpl() {
		super(CinemaHall.class);
	}

	@Transactional
	public CinemaHall findByName(String name) {
		Criteria criteria = getCurrentSession().createCriteria(type);
		criteria.add(Restrictions.eq("name", name));
		return (CinemaHall) criteria.uniqueResult();
	}
}
