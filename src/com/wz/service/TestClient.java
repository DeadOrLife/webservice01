package com.wz.service;

import javax.swing.*;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * WebService的客户端
 * @author jamesbean
 */
public class TestClient {

    public static void main(String[] args) {
        //1.创建URL对象
        try {
            URL url  = new URL("http://localhost:8888/ns?wsdl");
            //2.创建QName对象 传入namespace 与 对应的实体名称
            QName qName = new QName("http://service.wz.com/","MyServiceImplService");
            //3.创建Service对象
            Service service = Service.create(url,qName);
            //4.获取接口对象
            //注：这种服务有问题 因为需要依赖IMyService接口 如果跨平台 那么调用服务方法的一方没有对应的接口 那么将无法进行对应方法的调用
            IMyService myService = service.getPort(IMyService.class);
            int add = myService.add(1, 2);
            System.out.println(add);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
