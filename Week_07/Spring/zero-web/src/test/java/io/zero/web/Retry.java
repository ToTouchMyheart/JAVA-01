package io.zero.web;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @Author zhurui
 * @Date 2021/3/3 3:47 下午
 * @Version 1.0
 */
public class Retry implements IRetryAnalyzer {
    private int counter = 0;
    private int retryLimit = 4;

    @Override
    public boolean retry(ITestResult result) {
        if (counter < retryLimit) {
            counter++;
            System.out.println("重试-----" + counter);
            return true;
        }
        return false;
    }
}