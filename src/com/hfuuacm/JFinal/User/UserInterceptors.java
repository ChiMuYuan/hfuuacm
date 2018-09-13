package com.hfuuacm.JFinal.User;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class UserInterceptors implements Interceptor {
    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        String key = invocation.getActionKey();

        if (key.equals("/user/login") && LoginInterceptors(controller)) {
            controller.renderJson("status", false);
            return;
        } else if (key.equals("/user/register") && RegisterInterceptors(controller)) {
            controller.redirect("/");
            return;
        } else if (key.equals("/user/update") && UpdateInterceptors(controller)) {
            controller.redirect("/");
            return;
        } else if(key.equals("/user/logout") && LogoutInterceptors(controller)) {
            controller.redirect("/");
            return;
        }

        invocation.invoke();
    }

    private boolean LoginInterceptors(Controller controller) {
        String password = controller.getPara("password");
        String Username = controller.getPara("uname");
        String sessionuid = controller.getSessionAttr("uid");
        String sessionUsername = controller.getSessionAttr("Username");
        String sessionpermission = controller.getSessionAttr("permission");

        if ((password == null || Username == null) && (sessionuid == null || sessionUsername == null || sessionpermission == null))
            return true;
        return false;
    }

    private boolean RegisterInterceptors(Controller controller) {
        String Username = controller.getPara("uname2");
        String email = controller.getPara("uemail2");
        String password = controller.getPara("upwd2");

        if (Username == null || email == null || password == null)
            return true;
        return false;
    }

    private boolean UpdateInterceptors(Controller controller) {
        String uid = controller.getSessionAttr("uid");
        String Username = controller.getPara("uname");
        String password = controller.getPara("upwd");
        String email = controller.getPara("uemail");

        if (uid == null || Username == null || password == null || email == null)
            return true;
        return false;
    }

    private boolean LogoutInterceptors(Controller controller) {
        String uid = controller.getSessionAttr("uid");

        if (uid == null)
            return true;
        return false;
    }
}
