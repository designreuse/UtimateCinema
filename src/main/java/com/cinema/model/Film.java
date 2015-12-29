package com.cinema.model;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * Film
 * Created by rayn on 2015/12/24.
 */
@Entity
@Table(name = "film")
public class Film implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] poster;

	@Column(name = "film_name")
	private String filmName;

	@Column
	private String director;

	@Column
	private String actors;

	@Column
	private String language;

	@Column
	private int length;

	@Column(name = "premiere_date")
	private Date premiereDate;

	@Column(length = 4096)
	private String intro;


	@OneToMany(
			mappedBy = "film",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY
	)
	@OrderBy("post_time desc")
	private Set<Comment> comments = new TreeSet<Comment>();


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

	@JSON(serialize = false)
	public byte[] getPoster() {
		return poster;
	}

	public void setPoster(byte[] poster) {
		this.poster = poster;
	}

	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getPremiereDate() {
		return premiereDate;
	}

	public void setPremiereDate(Date premiereDate) {
		this.premiereDate = premiereDate;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	@JSON(serialize = false)
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@JSON(serialize = false)
	public Set<CinemaSale> getSales() {
		return sales;
	}

	public void setSales(Set<CinemaSale> sales) {
		this.sales = sales;
	}

}
