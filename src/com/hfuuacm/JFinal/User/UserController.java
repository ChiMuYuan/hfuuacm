package com.hfuuacm.JFinal.User;

import com.hfuuacm.JFinal.Mysql.User;
import com.hfuuacm.JFinal.Main.sessionInterceptors;
import com.hfuuacm.Tools.Encryption;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserController extends Controller {
    public void index() {
        render("/");
    }

    @Before(sessionInterceptors.class)
    public void Login() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String uid = getSessionAttr("uid");
        String Username = getSessionAttr("Username");
        String permission = getSessionAttr("permission");
        if (uid != null && Username != null && permission != null) {
            setAttr("status", "success");
            setAttr("uname", getSessionAttr("Username"));
            renderJson(new String[]{"status", "uname"});
            return;
        }

        String uname = getPara("uname");
        String upwd = getPara("upwd");
        List<User> userlist = null;
        User user = null;
        if (uname.indexOf("@") != -1)
            userlist = User.dao.find("SELECT * FROM User WHERE email=?", uname);
        else
            userlist = User.dao.find("SELECT * FROM User WHERE Username=?'", uname);
        if (userlist == null) {
            renderJson("status", "密码或邮箱错误");
            return;
        }
        else
            user = userlist.get(0);
        if (upwd.equals(user.getStr("password"))) {
            setCookie("uid", user.getStr("id"), 1000*60*60*24*7);
            String auth_tocken = Encryption.MD5BASE64(uname + Encryption.RandomString());
            setCookie("auth_token", auth_tocken, 1000*60*60*24*7);
            user.dao().set("cookies_token", auth_tocken).update();

            setSessionAttr("uid", user.getStr("id"));
            setSessionAttr("Username", uname);
            setSessionAttr("permission", user.getStr("permission"));

            setAttr("status", "success");
            setAttr("uname", user.getStr("Username"));
            renderJson(new String[]{"status", "uname"});
        }
        else
            renderJson("status", "密码错误");
    }

    public void Register() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String Username = getPara("uanme2");
        String password = getPara("upwd2");
        String email = getPara("uemail2");
        if (Username == null || password == null || email == null) {
            render("/");
            return;
        }

        if (User.dao.find("SELSECT * FROM User WHERE Username=?", Username) != null)
            renderJson("status", "用户名已存在");
        else if (User.dao.find("SELSECT * FROM User WHERE email=?", email) != null)
            renderJson("status", "邮箱已存在");
        else {
            new User().set("Username", Username).set("email", email)
                    .set("password", Encryption.MD5BASE64(password)).save();

            User user = User.dao.find("SELECT * FROM User WHERE Username=?", Username).get(0);

            setCookie("uid", user.getStr("id"), 1000*60*60*24*7);
            String auth_tocken = Encryption.MD5BASE64(Username + Encryption.RandomString());
            setCookie("auth_token", auth_tocken, 1000*60*60*24*7);

            setSessionAttr("uid", user.getStr("id"));
            setSessionAttr("Username", Username);
            setSessionAttr("permission", user.getStr("permission"));

            user.dao().set("cookies_token", auth_tocken).update();
            setAttr("status", "success");
            setAttr("uname", user.getStr("Username"));
            renderJson(new String[]{"status", "uname"});
        }

    }
}
