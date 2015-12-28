package com.cinema.dao.impl;

import com.cinema.dao.CinemaSaleDao;
import com.cinema.dao.generic.HibernatePageableDao;
import com.cinema.model.CinemaSale;
import org.springframework.stereotype.Repository;

/**
 * CinemaSaleDaoImpl
 * Created by rayn on 2015/12/26.
 */
@Repository("cinemaSaleDao")
public class CinemaSaleDaoImpl extends HibernatePageableDao<CinemaSale, Long>
		implements CinemaSaleDao {

	public CinemaSaleDaoImpl() {
		super(CinemaSale.class);
	}
}
