package com.hfuuacm.JFinal;

import com.hfuuacm.JFinal.Mysql.*;
import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;


public class MainConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        constants.setViewType(ViewType.JSP);

    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/", jspindex.class);
        routes.add("/user", userController.class);
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
        DruidPlugin druidPlugin =
                new DruidPlugin("jdbc:mysql:chimuyuan.cn:3306/hfuuacm?useUnicode=true&characterEncoding=UTF-8",
                "Username", "password");
        plugins.add(druidPlugin);
        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
        activeRecordPlugin.addMapping("article", Article.class);
        activeRecordPlugin.addMapping("loginlog", Loginlog.class);
        activeRecordPlugin.addMapping("oplog", Oplog.class);
        activeRecordPlugin.addMapping("permission", Permission.class);
        activeRecordPlugin.addMapping("subject", Subject.class);
        activeRecordPlugin.addMapping("User", User.class);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
