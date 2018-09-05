package com.hfuuacm.JFinal.Mysql;

import com.jfinal.plugin.activerecord.Model;

public class Loginlog extends Model<Loginlog> {
    public static final Loginlog dao = new Loginlog().dao();
}
