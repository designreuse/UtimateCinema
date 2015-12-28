package com.cinema.dao.generic;

import java.io.Serializable;
import java.util.List;

/**
 * PageResult
 * Created by rayn on 2015/12/24.
 */
public class PageResult<T> implements Serializable {

	private static final int DEFAULT_PAGE_SIZE = 10;

	/**
	 * current page num, start from 1.
	 */
	private int page;

	/**
	 * size per page
	 */
	private int pageSize;

	/**
	 * total size
	 */
	private long total;

	/**
	 * items of current page
	 */
	private List<T> items;


	public PageResult(int page, int pageSize, List<T> items, long total) {
		this.page = page;
		this.pageSize = pageSize;
		this.items = items;
		this.total = total;
	}

	public PageResult(int page, List<T> items, int total) {
		this(page, DEFAULT_PAGE_SIZE, items, total);
	}


	/**
	 * the items for the current page
	 */
	public List<T> getItems() {
		return items;
	}

	/**
	 * The total number of pages
	 */
	public long getPages() {
		return (total + (pageSize - 1)) / pageSize;
	}

	/**
	 * The total number of items without page
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * True if a previous page exists
	 */
	public boolean hasPrev() {
		return page > 1;
	}

	/**
	 * True if a next page exists.
	 */
	public boolean hasNext() {
		return page < getPages();
	}
}
