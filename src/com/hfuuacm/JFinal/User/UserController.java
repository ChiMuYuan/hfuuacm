package com.hfuuacm.JFinal.User;

import com.hfuuacm.JFinal.Mysql.User;
import com.hfuuacm.JFinal.Main.sessionInterceptors;
import com.hfuuacm.Tools.Encryption;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


@Before({sessionInterceptors.class, UserInterceptors.class})
public class UserController extends Controller {
    public void index() {
        redirect("/");
    }

    public void login() throws UnsupportedEncodingException, NoSuchAlgorithmException {
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
        User user = null;

        if (uname.indexOf("@") != -1)
            user = User.dao.findFirst("SELECT * FROM User WHERE email=?", uname);
        else
            user = User.dao.findFirst("SELECT * FROM User WHERE Username=?'", uname);
        if (user == null) {
            renderJson("status", "密码或邮箱错误");
            return;
        }

        if (Encryption.MD5BASE64(upwd).equals(user.getStr("password"))) {
            setCookie("uid", user.getStr("id"), 1000*60*60*24*7);
            String auth_tocken = Encryption.MD5BASE64(uname + Encryption.RandomString());
            setCookie("auth_token", auth_tocken, 1000*60*60*24*7);
            user.set("auth_token", auth_tocken).update();

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

    public void register() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String Username = getPara("uname2");
        String password = getPara("upwd2");
        String email = getPara("uemail2");

        if (User.dao.findFirst("SELECT * FROM User WHERE Username=?", Username) != null)
            renderJson("status", "用户名已存在");
        else if (User.dao.findFirst("SELECT * FROM User WHERE email=?", email) != null)
            renderJson("status", "邮箱已存在");
        else {
            new User().set("Username", Username).set("email", email).set("permission", "3")
                    .set("password", Encryption.MD5BASE64(password)).save();

            User user = User.dao.find("SELECT * FROM User WHERE Username=?", Username).get(0);

            setCookie("uid", user.getStr("id"), 1000*60*60*24*7);
            String auth_tocken = Encryption.MD5BASE64(Username + Encryption.RandomString());
            setCookie("auth_token", auth_tocken, 1000*60*60*24*7);

            setSessionAttr("uid", user.getStr("id"));
            setSessionAttr("Username", Username);
            setSessionAttr("permission", user.getStr("permission"));

            user.set("auth_token", auth_tocken).update();
            setAttr("status", "success");
            setAttr("uname", user.getStr("Username"));
            renderJson(new String[]{"status", "uname"});
        }
    }

    public void update() {
        String uid = getSessionAttr("uid");
        String Username = getPara("uname");
        String email = getPara("ueamil");
        String password = getPara("upwd");

        if(User.dao.findFirst("SELECT * FROM User WHERE Username=? OR email=?", Username, email) == null) {
            User user = User.dao.findById(getSessionAttr("uid"));
            user.set("Username", Username).set("email", email).set("password", password).update();
            setAttr("status", "success");
            setAttr("uname", Username);
            renderJson(new String[]{"status", "uname"});
        }
        else
            renderJson("status", "用户名或邮箱已存在");
    }

    public void logout() {
        String uid = getSessionAttr("uid");

        User user = User.dao.findById("uid");
        user.set("auth_token", "").update();
        setSessionAttr("uid", "");
        setSessionAttr("Username", "");
        setSessionAttr("perimission", "");
        redirect("/");
    }

}
