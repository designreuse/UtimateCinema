package com.cinema.dao.generic;

import java.io.Serializable;

/**
 * PageableGenericDao
 * Created by rayn on 2015/12/24.
 */
public interface PageableGenericDao<T, ID extends Serializable>
		extends GenericDao<T, ID> {

	PageResult<T> findAll(int page, int pageSize);
	PageResult<T> findAllWithOrder(int page, int pageSize, String... orderArgs);
}
