package io.zero.zz.zero.aop;

import java.lang.reflect.Proxy;

/**
 * @Author zhurui
 * @Date 2021/2/20 1:15 下午
 * @Version 1.0
 */
public class AopProxy {
    public static void main(String[] args) {
        TempServiceImpl serviceImpl = new TempServiceImpl();
        TempServiceHandler fooHandler = new TempServiceHandler(serviceImpl);
        TempService service = (TempService) Proxy.newProxyInstance(serviceImpl.getClass().getClassLoader(), serviceImpl.getClass().getInterfaces(), fooHandler);
        service.service();
    }


}
