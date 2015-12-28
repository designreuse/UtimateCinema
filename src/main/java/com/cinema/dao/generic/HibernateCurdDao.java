package com.cinema.dao.generic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * HibernateCurdDao
 * Created by rayn on 2015/12/24.
 */
public class HibernateCurdDao<T, ID extends Serializable>
		implements GenericDao<T, ID> {

	@Autowired
	private SessionFactory sessionFactory;

	protected final transient Class<T> type;

	public HibernateCurdDao(Class<T> type) {
		this.type = type;
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public <S extends T> S create(S entity) {
		return (S) getCurrentSession().save(entity);
	}

	@Transactional
	public <S extends T> void update(S entity) {
		getCurrentSession().update(entity);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public T findOne(ID primaryKey) {
		return (T) getCurrentSession().get(type, primaryKey);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getCurrentSession().createQuery("from " + this.type.getName()).list();
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public Long count() {
		return (Long) getCurrentSession().createQuery("select count(*) from " + this.type.getName()).uniqueResult();
	}

	@Transactional
	public void delete(ID primaryKey) {
		delete(findOne(primaryKey));
	}

	@Transactional
	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}

	@Transactional
	public boolean exists(ID primaryKey) {
		return findOne(primaryKey) != null;
	}
}
