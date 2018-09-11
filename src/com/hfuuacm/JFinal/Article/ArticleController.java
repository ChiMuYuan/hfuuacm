package com.hfuuacm.JFinal.Article;

import com.hfuuacm.JFinal.Mysql.Article;
import com.hfuuacm.JFinal.Mysql.Subject;
import com.jfinal.core.Controller;
import java.util.List;
import java.util.Map;

public class ArticleController extends Controller {
    public void index() { redirect("/");}

    public void getlink() {
        String column = getPara("column");
        String page = getPara("page");
        int stid = 0;

        Subject subject = Subject.dao.findFirst("SELECT * FROM article WHERE topic=?", column);

        if (page != null)
            stid += 10 * Integer.parseInt(page);

        List<Article> all_articleList = Article.dao.find
                ("SELECT * FROM aritcle WHERE subject=? ORDER BY id LIMIT ?,10", stid, subject.getStr("id"));

        List<Object> articleList = null;
        for (int i = 0; i < all_articleList.size(); i ++) {
            Article article = all_articleList.get(i);
            Map<Object, Object> articleMap = null;
            articleMap.put("id", article.getStr("id"));
            articleMap.put("title", article.getStr("title"));
            articleMap.put("time", article.getStr("Lasttime"));
            articleList.add(articleMap);
        }

        Map<Object, Object> Json = null;
        Json.put("status", "sucess");
        Json.put("article", articleList);
        Json.put("all_page", Math.ceil(subject.getInt("number") / 10.0));

        renderJson(Json);
    }
}
