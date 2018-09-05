package com.hfuuacm.JFinal.Mysql;

import com.jfinal.plugin.activerecord.Model;

public class Subject extends Model<Subject> {
    public static final Subject dao = new Subject().dao();
}
