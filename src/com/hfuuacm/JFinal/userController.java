package com.hfuuacm.JFinal;

import com.hfuuacm.JFinal.Mysql.User;
import com.jfinal.core.Controller;
import java.util.List;

public class userController extends Controller {
    public void index() {
        String uname = getPara("uname");
        String upwd = getPara("upwd");
        List<User> userlist = null;
        if (uname.indexOf("@") != -1)
            userlist = User.dao.find("SELECT * FROM User WHERE email=" + uname);
        else
            userlist = User.dao.find("SELECT * FROM User WHERE Username=" + uname);
        if (userlist == null)
            renderText("账号邮箱错误");
        else if (upwd.equals(userlist.get(0).getSql("password")))
            renderText("密码错误");
        else
            renderText("登录成功");
    }
}
