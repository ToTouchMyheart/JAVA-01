package io.zero.db.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author zhu_rui
 * @Date 2019/12/4
 * @Desciption:
 */
@Aspect
@Component
public class BatchInsertAspect {

    /**
     * 在batch插入更新前判断列表不能为空
     * 
     * @param pjp
     */
    @Around("execution(public * io.zero.db.dao.*Mapper.batch*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        List<?> list = (List)pjp.getArgs()[0];
        if (!CollectionUtils.isEmpty(list)) {
            return pjp.proceed();
        } else {
            return 0;
        }
    }
}
