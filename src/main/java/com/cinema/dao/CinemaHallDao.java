package com.cinema.dao;

import com.cinema.dao.generic.PageableGenericDao;
import com.cinema.model.CinemaHall;

/**
 * CinemaHallDao
 * Created by rayn on 2015/12/26.
 */
public interface CinemaHallDao extends PageableGenericDao<CinemaHall, Long> {

    CinemaHall findByName(String name);
}
