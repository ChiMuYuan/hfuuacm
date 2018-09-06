package com.hfuuacm.JFinal;

import com.jfinal.core.Controller;

public class jspindex extends Controller {
    public void index() {
        setAttr("userName", "<h1>mxhnb</h1>");
        renderFreeMarker("index.html");
    }
}
