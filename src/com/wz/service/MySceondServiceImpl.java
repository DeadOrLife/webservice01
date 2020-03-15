package com.wz.service;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceProvider;

/**
 * @author jamesbean
 */
@WebService(endpointInterface = "com.wz.service.IMyService")
public class MySceondServiceImpl implements IMyService{
    @Override
    public int add(int a, int b) {
        System.out.println("第二个服务加法");
        return 0;
    }

    @Override
    public int minus(int a, int b) {
        System.out.println("第二个服务减法");
        return 0;
    }

    public void info(){
        System.out.println("本类方法");
    }

}
