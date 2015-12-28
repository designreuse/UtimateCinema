package com.cinema.dao;

import com.cinema.dao.generic.GenericDao;
import com.cinema.dao.generic.PageableGenericDao;
import com.cinema.model.User;

/**
 * UserDao
 * Created by rayn on 2015/12/24.
 */
public interface UserDao extends PageableGenericDao<User, Long> {

	User findByUsername(String username);

}
