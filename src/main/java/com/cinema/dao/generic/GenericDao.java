package com.cinema.dao.generic;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * GenericDao
 * Created by rayn on 2015/12/24.
 */
public interface GenericDao<T, ID extends Serializable> {

	<S extends T> S create(S entity);

	<S extends T> void update(S entity);

	T findOne(ID primaryKey);

	List<T> findAll();

	Integer count();

	void delete(ID primaryKey);

	void delete(T entity);

	boolean exists(ID primaryKey);

}
