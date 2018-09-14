package com.hfuuacm.Tools;

import com.hfuuacm.JFinal.Mysql.Subject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonTools {
    public static void subjectGetnamelist (List<Subject> all, List<Object> result) {
        for (int i = 0; i < all.size(); i ++) {
            Subject subject = all.get(i);
            Map<Object, Object> map = new HashMap<>();
            map.put("name", subject.getStr("topic"));
            map.put("id", subject.getInt("id"));
            result.add(map);
        }
    }
}
