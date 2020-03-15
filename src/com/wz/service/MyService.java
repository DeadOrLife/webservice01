package com.wz.service;

import javax.xml.ws.Endpoint;

/**
 * 创建服务 开启服务
 * @author jamesbean
 */
public class MyService {
    public static void main(String[] args) {
        //服务发布的访问路径
        String address = "http://localhost:8888/ns";
        //发布的地址，发布服务的那个实现类
        Endpoint.publish(address,new MyServiceImpl());

       //服务发布的访问路径
        //String addresss = "http://localhost:8008/ns";
        //发布的地址，发布服务的那个实现类
        //Endpoint.publish(addresss,new MySceondServiceImpl());


    }
}
