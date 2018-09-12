package com.hfuuacm.JFinal.Article;

import com.hfuuacm.JFinal.Mysql.Article;
import com.hfuuacm.JFinal.Mysql.Subject;
import com.hfuuacm.JFinal.Mysql.User;
import com.jfinal.core.Controller;
import java.util.List;
import java.util.Map;

public class ArticleController extends Controller {
    public void index() { redirect("/");}

    public void getlink() {
        String column = getPara("column");
        String page = getPara("page");
        String number = getPara("lists");
        int stid = 0, all_page = 0;

        List<Article> all_articleList = null;
        if (column != null) {
            if (page != null)
                stid += 10 * Integer.parseInt(page);

            Subject subject = Subject.dao.findFirst("SELECT * FROM article WHERE topic=?", column);
            all_page = subject.getInt("number");
            all_articleList = Article.dao.find("SELECT * FROM article WHERE subject=? ORDER BY id LIMIT ?,?",
                    subject.getStr("id"), stid, number);
        }
        else
            all_articleList = Article.dao.find("SELECT * FROM article WHERE subject=? ORDER BY id LIMIT ?", number);

        List<Object> articleList = null;
        for (int i = 0; i < all_articleList.size(); i ++) {
            Article article = all_articleList.get(i);
            Map<Object, Object> articleMap = null;
            articleMap.put("id", article.getStr("id"));
            articleMap.put("title", article.getStr("title"));
            articleMap.put("time", article.getStr("Lasttime"));
            articleMap.put("author", User.dao.findById(article.getStr("author")).getStr("Username"));
            articleList.add(articleMap);
        }

        Map<Object, Object> Json = null;
        Json.put("status", "success");
        Json.put("article", articleList);
        Json.put("all_page", Math.ceil(all_page / 10.0));

        renderJson(Json);
    }
}
