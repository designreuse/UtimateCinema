package com.cinema.dao.impl;

import com.cinema.dao.CommentDao;
import com.cinema.dao.generic.HibernatePageableDao;
import com.cinema.dao.generic.PageResult;
import com.cinema.model.Comment;
import com.cinema.model.Film;
import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


	//根据film筛选评论，并且排序
	@Transactional
	public PageResult<Comment> findByFilm(int page, int pageSize, Film film, String... orderArgs) {
		Criteria criteria = getCurrentSession().createCriteria(type);
		for (int i = 0; i < orderArgs.length; i += 2) {
			if (orderArgs[i + 1].equals("asc")) {
				criteria.addOrder(Property.forName(orderArgs[i]).asc());
			} else if (orderArgs[i + 1].equals("desc")) {
				criteria.addOrder(Property.forName(orderArgs[i]).desc());
			}
		}
		criteria.add(Restrictions.and(Restrictions.eq("film",film)));
		criteria.setFirstResult(pageSize * (page - 1));
		criteria.setMaxResults(pageSize);
		return new PageResult<Comment>(page, pageSize, criteria.list(), this.count());
	}


}
