package com.cinema.model;

import org.apache.struts2.json.annotations.JSON;

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

	@Column(name = "col_num")
	private int colNumber;

	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "cinema_sale_id", nullable = false)
	private CinemaSale cinemaSale;

	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
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

	@JSON(serialize = false)
	public CinemaSale getCinemaSale() {
		return cinemaSale;
	}

	public void setCinemaSale(CinemaSale cinemaSale) {
		this.cinemaSale = cinemaSale;
	}

	@JSON(serialize = false)
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
