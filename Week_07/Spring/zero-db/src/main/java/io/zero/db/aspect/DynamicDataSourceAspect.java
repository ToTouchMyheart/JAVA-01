package io.zero.db.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import io.zero.db.annotations.SelfDataSource;
import io.zero.db.configuration.DynamicDataSourceHolder;

/**
 * @Author zhurui
 * @Date 2021/3/7 9:05 下午
 * @Version 1.0
 */
@Aspect
public class DynamicDataSourceAspect {

    public void pointCut() {};

    public void before(JoinPoint point) {
        Object target = point.getTarget();
        String methodName = point.getSignature().getName();
        Class<?>[] clazz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature)point.getSignature()).getMethod().getParameterTypes();
        try {
            Method method = clazz[0].getMethod(methodName, parameterTypes);
            if (method != null && method.isAnnotationPresent(SelfDataSource.class)) {
                SelfDataSource data = method.getAnnotation(SelfDataSource.class);
                DynamicDataSourceHolder.putDataSource(data.value());
            }
        } catch (Exception e) {
        }
    }

    public void after(JoinPoint point) {
        DynamicDataSourceHolder.clearDataSource();
    }
}
