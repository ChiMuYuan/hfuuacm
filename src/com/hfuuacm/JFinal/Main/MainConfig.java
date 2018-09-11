package com.hfuuacm.JFinal.Main;

import com.alibaba.druid.wall.WallFilter;
import com.hfuuacm.JFinal.User.UserController;
import com.hfuuacm.JFinal.Mysql.*;
import com.hfuuacm.JFinal.Test.testindex;
import com.jfinal.config.*;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

import java.io.File;


public class MainConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants constants) {
        constants.setDevMode(true);

        constants.setError403View("/error/403.html");
        constants.setError404View("/error/404.html");
        constants.setError500View("/error/500.html");
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/", htmlindex.class);
        routes.add("/user", UserController.class);
//        routes.add("/subject", SubjectCon)

        routes.add("test", testindex.class);
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
        String path = PathKit.getRootClassPath() + "\\hfuuacm.properties";
        Prop prop = PropKit.use(new File(path), "UTF-8");
        DruidPlugin druidPlugin =
                new DruidPlugin("jdbc:mysql://chimuyuan.cn:3306/hfuuacm?useUnicode=true&characterEncoding=UTF-8",
                        prop.get("MysqlUser"), prop.get("MysqlPassword"));
        WallFilter wall = new WallFilter();
        wall.setDbType("mysql");
        druidPlugin.addFilter(wall);

        plugins.add(druidPlugin);

        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
        activeRecordPlugin.addMapping("article", Article.class);
        activeRecordPlugin.addMapping("loginlog", Loginlog.class);
        activeRecordPlugin.addMapping("permission", Permission.class);
        activeRecordPlugin.addMapping("subject", Subject.class);
        activeRecordPlugin.addMapping("User", User.class);
        plugins.add(activeRecordPlugin);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
