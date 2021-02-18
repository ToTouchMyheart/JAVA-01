package io.zero.zz.zero.aop;

import io.zero.zz.zero.annotation.ResultCache;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author zhurui
 * @Date 2021/2/8 7:09 下午
 * @Version 1.0
 */
@Aspect
@Component
public class ResultCaseAop {

    private static Map<Key, Result> cache = new ConcurrentHashMap<>();

    private static final long EXPIRE = 60 * 1000L;

    @Pointcut(value = "execution(* io.zero.zz.zero.service.*Service.add(..))")
    public void point() {

    }

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature sig = (MethodSignature) joinPoint.getSignature();
        Object target = joinPoint.getTarget();
        Method currentMethod = target.getClass().getMethod(sig.getName(), sig.getParameterTypes());
        // 获取注解
        ResultCache resultCache = currentMethod.getDeclaredAnnotation(ResultCache.class);
        if (resultCache != null) {
            Key key = new Key().setMethod(currentMethod).setArgs(joinPoint.getArgs());
            Result result = cache.get(key);
            long current = result == null ? 0 : result.getTimeStamp();
            if (System.currentTimeMillis() - current < EXPIRE) {
                assert result != null;
                return result.getResult();
            } else {
                Object proceed = joinPoint.proceed();
                cache.put(key, new Result(proceed, System.currentTimeMillis()));
                return proceed;
            }
        }
        return joinPoint.proceed();
    }


    @Data
    @AllArgsConstructor
    class Result {
        Object result;
        Long timeStamp;

    }


    @Data
    @Accessors(chain = true)
    class Key {
        Method method;
        Object[] args;
    }
}
