package com.hfuuacm.JFinal.Mysql;

import com.jfinal.plugin.activerecord.Model;

public class Oplog extends Model<Oplog> {
    public static final Oplog dao = new Oplog().dao();
}
