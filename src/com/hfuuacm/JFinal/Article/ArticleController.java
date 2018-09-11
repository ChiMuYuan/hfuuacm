package com.hfuuacm.JFinal.Article;

import com.hfuuacm.JFinal.Mysql.Article;
import com.hfuuacm.JFinal.Mysql.Subject;
import com.jfinal.core.Controller;

public class ArticleController extends Controller {
    public void index() { redirect("/");}

    public void getlink() {
        String column = getPara("column");
        String page = getPara("page");

        if (page == null) {
            Subject subject = Subject.dao.findFirst("SELECT * FROM article WHERE topic=?", column);
            List
            Article article = Article.dao.findFirst("SELECT * FROM article WHERE ");

        }
    }
}
