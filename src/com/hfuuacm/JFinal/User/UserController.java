package com.hfuuacm.JFinal.User;

import com.alibaba.druid.util.StringUtils;
import com.hfuuacm.JFinal.Mysql.Subject;
import com.hfuuacm.JFinal.Mysql.User;
import com.hfuuacm.JFinal.Main.sessionInterceptors;
import com.hfuuacm.Tools.Encryption;
import com.hfuuacm.Tools.JsonTools;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import org.omg.CORBA.OBJ_ADAPTER;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
            user = User.dao.findFirst("SELECT * FROM User WHERE Username=?", uname);
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
            setSessionAttr("Username", user.getStr("Username"));
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

        User user = User.dao.findById(uid);
        user.set("auth_token", null).update();
        setSessionAttr("uid", null);
        setSessionAttr("Username", null);
        setSessionAttr("permission", null);
        setCookie("auth_token", null, 0);
        redirect("/");
    }

    public void getmember() {
        String sessionpermission = getSessionAttr("permission");
        int page = Integer.parseInt(getPara("page"));
        int number = Integer.parseInt(getPara("lists"));
        int stid = number * (page-1);

        List<User> all_userList = User.dao.find("SELECT * FROM User WHERE permission >= ? LIMIT ?, ?",
                sessionpermission == null ? 3 : Integer.parseInt(sessionpermission), stid, number);
        List<Object> userList = new ArrayList<>();

        for (int i = 0; i < all_userList.size(); i ++) {
            User user = all_userList.get(i);

            Map<Object, Object> map = new HashMap<>();
            map.put("id", user.get("id"));
            map.put("Username", user.get("Username"));
            map.put("email", user.getStr("email"));
            int permission = user.getInt("permission");

            if (permission == 0)
                map.put("permission", "root");
            else if (permission == 1)
                map.put("permission", "系统管理员");
            else if(permission == 2) {
                map.put("permission", "栏目管理员");
                List<Object> columnList = new ArrayList<>();
                List<Subject> subjectList = Subject.dao.find(
                        "SELECT topic FROM subject JOIN permission ON subject.id = subject_id WHERE user_id = ?",
                        user.getInt("id"));

                JsonTools.subjectGetnamelist(subjectList, columnList);

                map.put("column", columnList);
            } else
                map.put("permission", "普通用户");

            userList.add(map);
        }

        Map<Object, Object> map = new HashMap<>();
        map.put("status", "success");
        map.put("member", userList);
        map.put("all_page", Math.ceil((double) User.dao.find("SELECT * FROM User").size() / number));

        renderJson(map);
    }

    public void getprofile() {
        int find_id = Integer.parseInt(getSessionAttr("uid"));

        String Para_id = getPara("id");
        if (Para_id != null)
            find_id = Integer.parseInt(Para_id);

        User user = User.dao.findById(find_id);
        int permission = user.getInt("permission");

        Map<Object, Object> JSON = new HashMap<>();

        List<Subject> subjectList = Subject.dao.find("SELECT * FROM subject");
        List<Object> all_column = new ArrayList<>();
        for (int i = 0; i < subjectList.size(); i ++) {
            Subject subject = subjectList.get(i);
            Map<Object, Object> map = new HashMap<>();

            map.put("id", subject.getInt("id"));
            map.put("name", subject.getStr("topic"));
            all_column.add(map);
        }

        if (permission == 2) {
            List<Subject> subject_List = Subject.dao.find(
                    "SELECT topic FROM subject JOIN permission ON subject.id = subject_id WHERE user_id = ?",
                    user.getInt("id"));

            List<Object> objectList = new ArrayList<>();
            JsonTools.subjectGetnamelist(subject_List, objectList);
            JSON.put("column", objectList);
        }

        JSON.put("status", "success");
        JSON.put("Username", user.getStr("Username"));
        JSON.put("email", user.getStr("email"));
        JSON.put("permission", permission);
        JSON.put("all_column", all_column);

        renderJson(JSON);
    }

    public void updateprofile() {
        String Username = getPara("uname");
        String password = getPara("upwd");

    }
}
