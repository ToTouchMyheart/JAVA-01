package java0.conc0303.homework;

import java0.conc0303.Homework03;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java0.conc0303.Homework03.sum;

/**
 * @Author zhurui
 * @Date 2021/1/28 10:24 上午
 * @Version 1.0
 */
public class UseCondition {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        AtomicInteger result = new AtomicInteger();
        // 在这里创建一个线程或线程池，
        UseCondition homework03 = new UseCondition();
        // 异步执行 下面方法
        // 1.变换结果
        new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result.set(sum());
            homework03.notifyMain();
        }).start();
        //这是得到的返回值
        homework03.waitMain();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        // 然后退出main线程
    }

    final Lock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();

    public void waitMain() {
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void notifyMain() {
        try {
            lock.lock();
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
