package java0.conc0303.homework;

import java0.conc0302.lock.ReentrantReadWriteLockDemo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java0.conc0303.Homework03.sum;

/**
 * @Author zhurui
 * @Date 2021/1/28 9:15 上午
 * @Version 1.0
 */
public class UseReentrantWriteLock {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        AtomicInteger result = new AtomicInteger();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        // 1.变换结果
        final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        new Thread(() -> {
            try {
                rwLock.writeLock().lock();
                result.set(sum());
            } finally {
                rwLock.writeLock().unlock();   // 在子线程里控制释放资源占用
            }
        }).start();
        Thread.sleep(1);
        rwLock.readLock().lock();
        //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        rwLock.readLock().unlock();
        // 然后退出main线程
    }
}
