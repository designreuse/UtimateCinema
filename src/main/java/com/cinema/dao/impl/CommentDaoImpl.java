package com.cinema.dao.impl;

import com.cinema.dao.CommentDao;
import com.cinema.dao.generic.HibernatePageableDao;
import com.cinema.model.Comment;
import org.springframework.stereotype.Repository;

/**
 * CommentDaoImpl
 * Created by rayn on 2015/12/26.
 */
@Repository("commentDao")
public class CommentDaoImpl extends HibernatePageableDao<Comment, Long>
		implements CommentDao {

	public CommentDaoImpl() {
		super(Comment.class);
	}
}
