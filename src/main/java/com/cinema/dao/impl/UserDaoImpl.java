package com.cinema.dao.impl;

import com.cinema.dao.UserDao;
import com.cinema.dao.generic.HibernatePageableDao;
import com.cinema.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserDao implement
 * Created by rayn on 2015/12/24.
 */
@Repository("userDao")
public class UserDaoImpl extends HibernatePageableDao<User, Long>
		implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

	@Transactional
	public User findByUsername(String username) {
		Criteria criteria = getCurrentSession().createCriteria(type);
		criteria.add(Restrictions.eq("username", username));
		return (User) criteria.uniqueResult();
	}
}
