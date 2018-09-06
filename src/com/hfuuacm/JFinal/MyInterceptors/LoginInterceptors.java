package com.hfuuacm.JFinal.MyInterceptors;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class LoginInterceptors implements Interceptor {
    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        String uid = controller.getCookie("uid");
        String token = controller.getCookie("auth_token");
        if (uid == null || token == null)
            controller.render("/");
    }
}
