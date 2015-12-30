package com.cinema.dao;

import com.cinema.dao.generic.PageResult;
import com.cinema.dao.generic.PageableGenericDao;
import com.cinema.model.Comment;
import com.cinema.model.Film;

import java.util.List;

/**
 * CommentDao
 * Created by rayn on 2015/12/26.
 */
public interface CommentDao extends PageableGenericDao<Comment, Long> {

    PageResult<Comment> findByFilm(int page, int pageSize, Film film, String... orderArgs);
}
