package com.hfuuacm.JFinal.Test;

import com.jfinal.core.Controller;

public class testindex extends Controller {
    public void index() {
        setAttr("userName", "<h1>mxhnb</h1>");
        renderFreeMarker("index.html");
    }
}
