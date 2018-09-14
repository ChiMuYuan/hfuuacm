package com.hfuuacm.JFinal.Article;

import com.hfuuacm.JFinal.Mysql.Article;
import com.hfuuacm.JFinal.Mysql.Subject;
import com.hfuuacm.JFinal.Mysql.User;
import com.hfuuacm.Tools.JsonTools;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Before(ArticleInterceptors.class)
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
        else
            all_articleList = Article.dao.find("SELECT * FROM article WHERE subject=? ORDER BY id LIMIT ?", number);

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

    public void addarticle() {
        String title = getPara("title");
        String comment = getPara("most-body");

        new Article().set("title", title).set("author", getSessionAttr("uid")).set("subject", 1).
                set("comment", comment).save();

    }
}
