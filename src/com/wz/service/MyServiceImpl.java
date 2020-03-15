package com.wz.service;

import javax.jws.WebService;
import javax.xml.ws.WebServiceProvider;

/**
 * WebService的实现类
 * @author jamesbean
 */
@WebService(endpointInterface="com.wz.service.IMyService")
public class MyServiceImpl implements IMyService {
    @Override
    public int add(int a, int b) {
        System.out.println("相加：a+b="+(a+b));
        return a+b;
    }

    @Override
    public int minus(int a, int b) {
        System.out.println("相减：a-b="+(a-b));
        return a-b;
    }
}
