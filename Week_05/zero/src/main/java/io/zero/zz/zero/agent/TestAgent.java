package io.zero.zz.zero.agent;

import java.lang.instrument.Instrumentation;

/**
 * @Author zhurui
 * @Date 2021/2/20 2:30 下午
 * @Version 1.0
 */
public class TestAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("this is an agent.");
        System.out.println("args:" + agentArgs);
    }

    public static void main(String[] args) {
        System.out.println("test");
    }

}
