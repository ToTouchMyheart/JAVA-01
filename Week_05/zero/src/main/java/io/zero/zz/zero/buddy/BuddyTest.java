package io.zero.zz.zero.buddy;

import io.zero.zz.zero.aop.TempServiceImpl;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author zhurui
 * @Date 2021/2/20 2:02 下午
 * @Version 1.0
 */
public class BuddyTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(TempServiceImpl.class)
                .method(ElementMatchers.named("service"))
                .intercept(MethodDelegation.to(GeneralInterceptor.class))
                .make();

        // 加载类
        Class<?> clazz = dynamicType.load(BuddyTest.class.getClassLoader())
                .getLoaded();

        // 反射调用
        clazz.getMethod("service").invoke(clazz.getDeclaredConstructor().newInstance());
    }


}
