package java0.conc0303.homework;

import java0.conc0303.Homework03;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author zhurui
 * @Date 2021/1/28 8:50 上午
 * @Version 1.0
 */
public class UseFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        // 1.变换结果
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> future = executor.submit(Homework03::sum);
        executor.shutdown();
        int result = future.get();
        //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        // 然后退出main线程
    }
}
