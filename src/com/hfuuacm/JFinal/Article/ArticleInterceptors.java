package com.hfuuacm.JFinal.Article;

import com.alibaba.druid.util.StringUtils;
import com.hfuuacm.JFinal.Mysql.Subject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class ArticleInterceptors implements Interceptor {
    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        String key = invocation.getActionKey();

        if (key.equals("/article/getarticlelink") && GetlinkInterceptor(controller)) {
            controller.redirect("/");
            return;
        }

        invocation.invoke();
    }

    private boolean GetlinkInterceptor(Controller controller) {
        String number = controller.getPara("lists");
        String page  = controller.getPara("page");
        String column = controller.getPara("column");

        if (number == null || !StringUtils.isNumber(number) || (page != null && !StringUtils.isNumber(page)) ||
                (column != null && Subject.dao.findFirst("SELECT * FROM subject WHERE topic=?", column) != null))
            return true;
        return false;
    }
}
