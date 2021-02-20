package io.zero.zz.zero.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author zhurui
 * @Date 2021/2/20 1:11 下午
 * @Version 1.0
 */
public class TempServiceHandler implements InvocationHandler {
    Object target;

    public TempServiceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void before() {
        System.out.println("before");
    }

    private void after() {
        System.out.println("after");
    }
}