package com.hfuuacm.JFinal;

import com.hfuuacm.JFinal.Mysql.User;
import com.jfinal.core.Controller;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public class LoginController extends Controller {
    public void index() {
        String uname = getPara("uname");
        String upwd = getPara("upwd");
        List<User> userlist = null;
        User user = null;
        if (uname.indexOf("@") != -1)
            userlist = User.dao.find("SELECT * FROM User WHERE email=?", uname);
        else
            userlist = User.dao.find("SELECT * FROM User WHERE Username=?'", uname);
        if (userlist == null) {
            renderText("账号或邮箱错误");
            return;
        }
        else
            user = userlist.get(0);
        if (upwd.equals(user.getStr("password"))) {
            setCookie("uid", user.getStr("id"), 1000*60*60*24);
            setCookie("auth_token", );
            renderText("登录成功");
        }
        else
        {
            renderText("登录成功" + userlist.get(0).getStr("password"));
        }
    }
}
