package com.wz.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * WebService服务接口
 * @author jamesbean
 */
@WebService
public interface IMyService {
    @WebResult(name = "addresult")
    public int add(@WebParam(name = "aaa") int a, int b);

    public int minus(int a,int b);
}
