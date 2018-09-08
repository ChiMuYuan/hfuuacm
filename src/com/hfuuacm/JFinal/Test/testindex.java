package com.hfuuacm.JFinal.Test;

import com.jfinal.core.Controller;

public class testindex extends Controller {
    public void index() {
        setAttr("userName", "mxhnb");
        setAttr("hh", "hhv");
        setAttr("yy", "yyv");
        renderJson(new String[]{"hh", "yy"});
    }
}
