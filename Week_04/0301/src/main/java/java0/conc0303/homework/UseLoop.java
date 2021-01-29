package java0.conc0303.homework;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static java0.conc0303.Homework03.sum;

/**
 * @Author zhurui
 * @Date 2021/1/28 9:47 上午
 * @Version 1.0
 */
public class UseLoop {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        AtomicInteger result = new AtomicInteger();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        // 1.变换结果
        new Thread(() -> result.set(sum())).start();
        //这是得到的返回值
        while (Objects.equals(0, result.get())) {
            Thread.sleep(1);
        }
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        // 然后退出main线程
    }

}
