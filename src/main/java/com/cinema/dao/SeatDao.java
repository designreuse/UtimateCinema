package com.cinema.dao;

import com.cinema.dao.generic.PageableGenericDao;
import com.cinema.model.CinemaSale;
import com.cinema.model.Seat;

/**
 * SeatDao
 * Created by rayn on 2015/12/26.
 */
public interface SeatDao extends PageableGenericDao<Seat, Long> {
     Seat findByCinemaSaleWithRowAndCol(CinemaSale cinemaSale, int row, int col);
}
