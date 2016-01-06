package com.cinema.dao.impl;

import com.cinema.dao.SeatDao;
import com.cinema.dao.generic.HibernatePageableDao;
import com.cinema.model.CinemaSale;
import com.cinema.model.Seat;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * SeatDaoImpl
 * Created by rayn on 2015/12/26.
 */
@Repository("seatDao")
public class SeatDaoImpl extends HibernatePageableDao<Seat, Long>
		implements SeatDao{

	public SeatDaoImpl() {
		super(Seat.class);
	}

	@Transactional
	public Seat findByCinemaSaleWithRowAndCol(CinemaSale cinemaSale, int row, int col) {
		Criteria criteria = getCurrentSession().createCriteria(type);
		criteria.add(Restrictions.and(
				Restrictions.eq("cinemaSale", cinemaSale),
				Restrictions.eq("rowNumber", row),
				Restrictions.eq("colNumber", col)
		));
		return (Seat) criteria.uniqueResult();
	}
}
