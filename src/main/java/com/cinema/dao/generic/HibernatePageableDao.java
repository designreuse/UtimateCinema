package com.cinema.dao.generic;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

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
		Criteria criteria = getCurrentSession().createCriteria(type);
		criteria.setFirstResult(pageSize * (page - 1));
		criteria.setMaxResults(pageSize);
		return new PageResult<T>(page, pageSize, criteria.list(), this.count());
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public PageResult<T> findAllWithOrder(int page, int pageSize, String... orderArgs) {
		Criteria criteria = getCurrentSession().createCriteria(type);
		for (int i = 0; i < orderArgs.length; i += 2) {
			if (orderArgs[i + 1].equals("asc")) {                        //订单排序
				criteria.addOrder(Property.forName(orderArgs[i]).asc());
			} else if (orderArgs[i + 1].equals("desc")) {
				criteria.addOrder(Property.forName(orderArgs[i]).desc());
			}
		}
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		//criteria.setProjection(Projections.distinct(Projections.property("id")));
		criteria.setFirstResult(pageSize * (page - 1));
		criteria.setMaxResults(pageSize);
		return new PageResult<T>(page, pageSize, criteria.list(), this.count());
	}
}
