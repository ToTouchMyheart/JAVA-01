package io.zero.zz.zero.buddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @Author zhurui
 * @Date 2021/2/20 1:59 下午
 * @Version 1.0
 */
public class HelloWorld {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(HelloWorld.class.getClassLoader())
                .getLoaded();
        System.out.println(dynamicType.newInstance().toString());
    }


}
