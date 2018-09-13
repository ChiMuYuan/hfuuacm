package com.hfuuacm.JFinal.Main;

import com.hfuuacm.JFinal.Mysql.User;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class sessionInterceptors implements Interceptor {
    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        if (controller.getSessionAttr("uid") == null || controller.getSessionAttr("Username") == null
                || controller.getSessionAttr("permission") == null) {
            String uid = controller.getCookie("uid");
            String auth_token = controller.getCookie("auth_token");

            if (uid != null && auth_token != null) {
                User user = User.dao.findById(uid);

                if (user != null && user.getStr("auth_token").equals(auth_token)) {
                    controller.setSessionAttr("uid", user.getStr("id"));
                    controller.setSessionAttr("Username", user.getStr("Username"));
                    controller.setSessionAttr("permission", user.getStr("permission"));
                }
            }
        }
        invocation.invoke();
    }
}
