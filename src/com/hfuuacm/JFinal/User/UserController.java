package com.hfuuacm.JFinal.User;

import com.hfuuacm.JFinal.Mysql.User;
import com.hfuuacm.Tools.Encryption;
import com.jfinal.core.Controller;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

public class UserController extends Controller {
    public void index() {
        render("");
    }

    public void Login() throws UnsupportedEncodingException, NoSuchAlgorithmException {
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
            setCookie("uid", user.getStr("id"), 1000*60*60*24*7);
            String auth_tocken = Encryption.MD5BASE64(uname + Encryption.RandomString());
            setCookie("auth_token", auth_tocken, 1000*60*60*24*7);
            user.dao().set("cookies_token", auth_tocken).update();
            renderText("登录成功");
        }
        else
            renderText("登录失败" + userlist.get(0).getStr("password"));
    }

    public void test() {
        renderText("test");
    }
}
