package java0.conc0303.homework;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

import static java0.conc0303.Homework03.sum;

/**
 * @Author zhurui
 * @Date 2021/1/28 9:18 上午
 * @Version 1.0
 */
public class UseLockSupport {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        AtomicInteger result = new AtomicInteger();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread current = Thread.currentThread();
        // 1.变换结果
        new Thread(() -> {
            try {
                result.set(sum());
            } finally {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LockSupport.unpark(current);
            }
        }).start();
        LockSupport.park();
        //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        // 然后退出main线程
    }
}
