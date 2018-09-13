package com.hfuuacm.JFinal.Test;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

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

    public void uploadfile() {
        getFile("pic_title");

        renderText("<h1>success</h1>");

    }
}
