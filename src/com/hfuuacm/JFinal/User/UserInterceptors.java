package com.hfuuacm.JFinal.User;

import com.alibaba.druid.util.StringUtils;
import com.hfuuacm.JFinal.Mysql.User;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import org.omg.CORBA.INTERNAL;

import javax.sound.sampled.Control;

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
        } else if (key.equals("/user/getmember") && GetMemberInterceptors(controller)) {
            controller.redirect("/");
            return;
        } else if (key.equals("/user/getprofile") && GetProfile(controller)) {
            controller.redirect("/");
            return;
        }

        invocation.invoke();
    }

    private boolean LoginInterceptors(Controller controller) {
        String password = controller.getPara("upwd");
        String Username = controller.getPara("uname");
        String sessionuid = controller.getSessionAttr("uid");
        String sessionUsername = controller.getSessionAttr("Username");
        String sessionpermission = controller.getSessionAttr("permission");

        System.out.println(Username);
        System.out.println(password);

        if ((password == null || Username == null) &&
                (sessionuid == null || sessionUsername == null || sessionpermission == null))
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

    private boolean GetMemberInterceptors(Controller controller) {
        String uid = controller.getSessionAttr("uid");
        String Username = controller.getSessionAttr("Username");
        String page = controller.getPara("page");
        String number = controller.getPara("lists");

        if (uid == null || Username == null || page == null || number == null ||
                !StringUtils.isNumber(page) || !StringUtils.isNumber(number))
            return true;
        return false;
    }

    private boolean GetProfile(Controller controller) {
        String uid = controller.getSessionAttr("uid");
        String Username = controller.getSessionAttr("Username");
        String permission = controller.getSessionAttr("permission");
        String para_id = controller.getPara("id");

        if (uid == null || Username == null || permission == null || (para_id != null && !StringUtils.isNumber(para_id)))
            return true;

        if (para_id != null) {
            int intpermission = Integer.parseInt(permission);
            int intpara_id = Integer.parseInt(para_id);

            if (intpermission > 1 || intpermission >= intpara_id)
                return true;
        }

        return false;
    }
}
