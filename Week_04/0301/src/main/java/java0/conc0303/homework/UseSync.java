package java0.conc0303.homework;

import static java0.conc0303.Homework03.sum;

/**
 * @Author zhurui
 * @Date 2021/1/28 9:53 上午
 * @Version 1.0
 */
public class UseSync {

    private int result = 0;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        UseSync homework03 = new UseSync();
        // 1.变换结果
        new Thread(() -> homework03.result(sum())).start();
        //这是得到的返回值
        Thread.sleep(100);
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + homework03.result(0));
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        // 然后退出main线程
    }

    public synchronized int result(int result) {
        this.result = result + this.result;
        return this.result;
    }

}
