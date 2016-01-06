package com.cinema.action;

import com.cinema.action.base.BaseAction;
import com.cinema.dao.CommentDao;
import com.cinema.dao.FilmDao;
import com.cinema.dao.UserDao;
import com.cinema.dao.generic.PageResult;
import com.cinema.model.Comment;
import com.cinema.model.Film;
import com.cinema.model.User;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import java.util.Date;

/**
 * Created by Administrator on 2015/12/30.
 */
@ParentPackage("main")
@Controller
@Scope("prototype")
public class CommentAction extends BaseAction{

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private FilmDao filmDao;

    private String title;
    private int pageSize;
    private int page;
    private String content;
    private long filmId;
    private long userId;

    public CommentAction(){
        super(CommentAction.class);
    }

    public String index(){
        title = "���۹���";
        pageSize = 10;
        return  SUCCESS;
    }

    //�������
    @Action(value = "/comment/add")
    public String addComment(){
        Comment comment = new Comment();
        comment.setUser(getUser());
        comment.setFilm(getFilm());
        comment.setPostTime(new Date());
        comment.setContent(content);
        commentDao.create(comment);
        jsonResponse.put("ret", JsonResult.OK);
        return "json";
    }

    //�鿴����
    @Action(value = "/comment/get")
    public String getComments(){
        Film film = getFilm();
        logger.info("page:" + page + "pageSize:" + pageSize);
        //����film��ȡ����(ͨ��postTime��������)
        PageResult<Comment> pageResult = commentDao.findByFilm(page, pageSize ,film,"postTime", "desc");
        jsonResponse.put("totalPage", pageResult.getPages());
        jsonResponse.put("page", page);
        jsonResponse.put("items", pageResult.getItems());
        return "json";
    }

    //����id��ȡUser
    public User getUser(){
        User user = userDao.findOne(userId);
        return user;
    }

    //����id��ȡFilm
    public Film getFilm(){
        Film film = filmDao.findOne(filmId);
        return film;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getFilmId() {
        return filmId;
    }

    public void setFilmId(long filmId) {
        this.filmId = filmId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
