package com.hfuuacm.JFinal.Article;

import com.hfuuacm.JFinal.Mysql.Article;
import com.hfuuacm.JFinal.Mysql.Subject;
import com.hfuuacm.JFinal.Mysql.User;
import com.hfuuacm.JFinal.Main.sessionInterceptors;
import com.hfuuacm.Tools.JsonTools;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;

import java.util.*;

@Before({sessionInterceptors.class, ArticleInterceptors.class})
public class ArticleController extends Controller {
    public void index() { redirect("/");}

    public void getcolumn() {
        List<Subject> all_subject = Subject.dao.find("SELECT * FROM subject");
        List<Object> subjectList = new ArrayList<>();

        JsonTools.subjectGetnamelist(all_subject, subjectList);

        Map<Object, Object> Json = new HashMap<>();
        Json.put("status", "success");
        Json.put("column", subjectList);

        renderJson(Json);
    }

    public void getarticlelink() {
        String column = getPara("column");
        int page = Integer.parseInt(getPara("page"));
        int number = Integer.parseInt(getPara("lists"));
        int stid = 0, all_page = 0;

        List<Article> all_articleList;
        if (column != null) {
                stid += number * (page-1);

            Subject subject = Subject.dao.findFirst("SELECT * FROM subject WHERE topic=?", column);
            all_page = subject.getInt("number");
            all_articleList = Article.dao.find("SELECT * FROM article WHERE subject=? ORDER BY id LIMIT ?,?",
                    subject.getStr("id"), stid, number);
        }
        else {
            all_articleList = Article.dao.find("SELECT * FROM article ORDER BY id LIMIT ?", number);
            all_page = Db.queryInt("SELECT COUNT(*) FROM article");
        }

        List<Object> articleList = new ArrayList<>();
        for (int i = 0; i < all_articleList.size(); i ++) {
            Article article = all_articleList.get(i);
            Map<Object, Object> articleMap = new HashMap<>();
            articleMap.put("id", article.getStr("id"));
            articleMap.put("title", article.getStr("title"));
            articleMap.put("time", article.getStr("Lasttime"));
            articleMap.put("author", User.dao.findById(article.getStr("author")).getStr("Username"));
            articleList.add(articleMap);
        }

        Map<Object, Object> Json = new HashMap<>();
        Json.put("status", "success");
        Json.put("article", articleList);
        Json.put("all_page", Math.ceil((double)all_page / number));

        renderJson(Json);
    }

    public void getarticle() {
        String id = getPara("id");

        Article article = Article.dao.findById(id);
        String author = User.dao.findById(article.getStr("author")).getStr("Username");

        Map<Object, Object> JSON = new HashMap<>();
        Map<Object, Object> column = new HashMap<>();
        column.put("id", article.getInt("subject"));
        column.put("name", Subject.dao.findById(article.getStr("subject")).getStr("topic"));

        JSON.put("column", column);
        JSON.put("id", article.getStr("id"));
        JSON.put("author", author);
        JSON.put("title", article.getStr("title"));
        JSON.put("content", article.getStr("content"));
        JSON.put("Lasttime", article.getStr("Lasttime"));
        JSON.put("viewer", article.getInt("viewer")+1);

        int viewer = article.getInt("viewer");
        article.set("viewer", viewer+1).update();

        renderJson(JSON);

    }

    public void addarticle() {
        String title = getPara("title");
        String content = getPara("body");
        String column = getPara("column");

        new Article().set("title", title).set("author", getSessionAttr("uid")).set("subject", column).
                set("content", content).set("Lasttime", new Date()).set("viewer", 0).save();

        Subject subject = Subject.dao.findById(column);
        subject.set("number", Db.queryInt("SELECT COUNT(*) FROM article WHERE subject=?", column)).update();

        redirect("/");
    }
}
