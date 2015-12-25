package com.cinema.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * CinemaHall
 * Created by rayn on 2015/12/24.
 */
@Entity
@Table(name = "cinema_hall")
public class CinemaHall implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private String name;

	@Column(name = "row_size")
	private int rowSize;

	@Column(name = "column_size")
	private int columnSize;

	@OneToMany(
			mappedBy = "film",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY
	)
	@OrderBy("start_time asc")
	private Set<CinemaSale> sales = new TreeSet<CinemaSale>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRowSize() {
		return rowSize;
	}

	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public Set<CinemaSale> getSales() {
		return sales;
	}

	public void setSales(Set<CinemaSale> sales) {
		this.sales = sales;
	}
}