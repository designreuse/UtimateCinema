package com.cinema.dao.impl;

import com.cinema.dao.SeatDao;
import com.cinema.dao.generic.HibernatePageableDao;
import com.cinema.model.Seat;

/**
 * SeatDaoImpl
 * Created by rayn on 2015/12/26.
 */
public class SeatDaoImpl extends HibernatePageableDao<Seat, Long>
		implements SeatDao{

	public SeatDaoImpl() {
		super(Seat.class);
	}
}
