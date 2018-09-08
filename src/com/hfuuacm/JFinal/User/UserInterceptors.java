package com.hfuuacm.JFinal.User;

import com.hfuuacm.JFinal.Mysql.User;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class UserInterceptors implements Interceptor {
    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        controller.render("/");
    }
}
