package io.zero.zz.zero.annotation;

import java.lang.annotation.*;

/**
 * @Author zhurui
 * @Date 2021/2/8 7:03 下午
 * @Version 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ResultCache {

    Class<?> resultType() default Object.class;
}
