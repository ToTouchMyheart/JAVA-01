package io.zero.zz.zero;

import io.zero.zz.zero.service.MathService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author zhurui
 * @Date 2021/2/8 3:23 下午
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZeroApplication.class)
public class ZeroTest {

    @Resource
    private Zero zero;

    @Resource
    private MathService mathService;

    @Test
    public void test() {
        int add = mathService.add(1, 2);
        System.out.println(add);
        System.out.println(mathService.add(1, 2));
    }

}
