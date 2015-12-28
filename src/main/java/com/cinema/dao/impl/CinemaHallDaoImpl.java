package com.cinema.dao.impl;

import com.cinema.dao.CinemaHallDao;
import com.cinema.dao.generic.HibernatePageableDao;
import com.cinema.model.CinemaHall;
import org.springframework.stereotype.Repository;

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
}
