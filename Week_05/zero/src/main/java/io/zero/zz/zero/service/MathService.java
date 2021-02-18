package io.zero.zz.zero.service;

import io.zero.zz.zero.annotation.ResultCache;
import org.springframework.stereotype.Service;

/**
 * @Author zhurui
 * @Date 2021/2/8 7:11 下午
 * @Version 1.0
 */
@Service
public class MathService {

    @ResultCache
    public int add(int a, int b) {
        return a + b;
    }


}
