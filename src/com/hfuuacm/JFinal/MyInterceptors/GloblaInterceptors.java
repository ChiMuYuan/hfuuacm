package com.hfuuacm.JFinal.MyInterceptors;

import com.jfinal.aop.Invocation;
import com.jfinal.aop.Interceptor;

public class GloblaInterceptors implements Interceptor {
    public void intercept(Invocation inv) {
        System.out.println("Before method invoking");
        inv.invoke();
        System.out.println("After method invoking");
    }
}
