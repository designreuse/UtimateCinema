package com.cinema.dao.generic;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * HibernatePageableDao
 * Created by rayn on 2015/12/24.
 */
public class HibernatePageableDao<T, ID extends Serializable>
		extends HibernateCurdDao<T, ID>
		implements PageableGenericDao<T, ID> {

	public HibernatePageableDao(Class<T> type) {
		super(type);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public PageResult<T> findAll(int page, int pageSize) {
		long total = this.count();
		List<T> items = getCurrentSession()
				.createQuery("from " + this.type.getName())
				.setFirstResult(pageSize * (page - 1))
				.setMaxResults(pageSize).list();
		return new PageResult<T>(page, pageSize, items, total);
	}
}
