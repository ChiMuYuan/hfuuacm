package com.hfuuacm.JFinal.Article;

import com.hfuuacm.JFinal.Mysql.Article;
import com.hfuuacm.JFinal.Mysql.Subject;
import com.hfuuacm.JFinal.Mysql.User;
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

        for (int i = 0; i < all_subject.size(); i ++) {
            Subject subject = all_subject.get(i);
            subjectList.add(subject.getStr("topic"));
        }

        Map<Object, Object> Json = new HashMap<>();
        Json.put("status", "success");
        Json.put("column", subjectList);

        renderJson(Json);
    }

    public void getarticlelink() {
        String column = getPara("column");
        String page = getPara("page");
        String number = getPara("lists");
        int stid = 0, all_page = 0;

        List<Article> all_articleList;
        if (column != null) {
            if (page != null)
                stid += 10 * Integer.parseInt(page);

            Subject subject = Subject.dao.findFirst("SELECT * FROM subject WHERE topic=?", column);
            all_page = subject.getInt("number");
            all_articleList = Article.dao.find("SELECT * FROM article WHERE subject=? ORDER BY id LIMIT ?,?",
                    subject.getStr("id"), stid, Integer.parseInt(number));
        }
        else
            all_articleList = Article.dao.find("SELECT * FROM article WHERE subject=? ORDER BY id LIMIT ?",
                    Integer.parseInt(number));

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
        Json.put("all_page", Math.ceil(all_page / 10.0));

        renderJson(Json);
    }
}
