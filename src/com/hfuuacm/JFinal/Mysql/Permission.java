package com.hfuuacm.JFinal.Mysql;

import com.jfinal.plugin.activerecord.Model;

public class Permission extends Model<Permission> {
    public static final Permission dao = new Permission().dao();
}
