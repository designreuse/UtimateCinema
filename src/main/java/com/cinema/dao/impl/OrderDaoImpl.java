package com.cinema.dao.impl;

import com.cinema.dao.OrderDao;
import com.cinema.dao.generic.HibernatePageableDao;
import com.cinema.model.Order;
import org.springframework.stereotype.Repository;

/**
 * OrderDaoImpl
 * Created by rayn on 2015/12/26.
 */
@Repository("orderDao")
public class OrderDaoImpl extends HibernatePageableDao<Order, Long>
		implements OrderDao{

	public OrderDaoImpl() {
		super(Order.class);
	}
}
