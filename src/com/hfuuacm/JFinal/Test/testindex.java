package com.hfuuacm.JFinal.Test;

import com.hfuuacm.JFinal.Mysql.Article;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.upload.UploadFile;

import java.util.Date;

public class testindex extends Controller {
    public void index() {
        render("/test.html");
    }

    public void realjson() {
        setAttr("status", "yes");
        renderJson(new String[]{"status"});
    }

    public void badjson() {
        renderJson("status", "no");
    }

    public void selecttest() {
        String[] str = getParaValues("t");
        String s = "";
        for (int i = 0; i < str.length; i ++)
            s += str[i] + "t = "+i;

        renderText(s);
    }

    public void uploadfile() {
        getFile("pic_title");

        renderText("<h1>success</h1>");

    }

    public void datetest() {
        new Article().set("title", "这是标题").set("author", 2).set("subject", 1).
                set("content", "这是内容").set("Lasttime", new Date()).save();
    }

    public void counttest() {
        renderText(Db.queryInt("SELECT COUNT(*) FROM article").toString());
    }
}
