package io.zero.zz.zero;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.function.Function;

/**
 * @Author zhurui
 * @Date 2021/2/8 3:28 下午
 * @Version 1.0
 */

@Accessors(chain = true)
@Data
public class Zero {
    private String port;


    public static void main(String[] args) {

        System.out.println(testFunction(2,i -> i * 2 + 1));
    }

    public static int testFunction(int i, Function<Integer,Integer> function) {

        return function.apply(i);
    }
}
