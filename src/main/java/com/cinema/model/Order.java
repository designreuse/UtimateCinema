package com.cinema.model;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Order
 * Created by rayn on 2015/12/24.
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "order_time")
	private Date orderTime;

	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
	private User user;

	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "cinema_sale_id")
	private CinemaSale cinemaSale;

	@Column(name = "is_comment")
	private boolean isComment;

	@OneToMany(
			mappedBy = "order",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.EAGER
	)
	@OrderBy("row_num asc, col_num asc")
	private Set<Seat> seats = new HashSet<Seat>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@JSON(format = "yyyy-MM-dd HH:mm")
	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CinemaSale getCinemaSale() {
		return cinemaSale;
	}

	public void setCinemaSale(CinemaSale cinemaSale) {
		this.cinemaSale = cinemaSale;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	public boolean isComment() {
		return isComment;
	}

	public void setIsComment(boolean isComment) {
		this.isComment = isComment;
	}
}
