package io.zero.web;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @Author zhurui
 * @Date 2021/3/2 4:31 下午
 * @Version 1.0
 */
public class TestCase {

    @BeforeTest
    public void beforeTest() throws NoSuchFieldException {
        System.out.println("---------------before");
    }

    @Test(retryAnalyzer = Retry.class)
    public void test() {
        System.out.println("------------test------------");
        throw new RuntimeException();

    }

    @AfterMethod
    public void tearDownAfterTest(ITestContext context) {
        System.out.println("---------------after");

    }
}
