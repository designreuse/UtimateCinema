package com.cinema.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Seat
 * Created by rayn on 2015/12/24.
 */
@Entity
@Table(name = "seat")
public class Seat implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "row_num")
	private int rowNumber;

	@Column(name = "col_name")
	private int colNumber;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cinema_sale_id", nullable = false)
	private CinemaSale cinemaSale;

	@ManyToOne(cascade = CascadeType.ALL)
	private Order order;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public int getColNumber() {
		return colNumber;
	}

	public void setColNumber(int colNumber) {
		this.colNumber = colNumber;
	}

	public CinemaSale getCinemaSale() {
		return cinemaSale;
	}

	public void setCinemaSale(CinemaSale cinemaSale) {
		this.cinemaSale = cinemaSale;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
