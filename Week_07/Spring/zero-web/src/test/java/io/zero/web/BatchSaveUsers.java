package io.zero.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.zero.db.entity.User;
import io.zero.db.service.UserService;

/**
 * @Author zhurui
 * @Date 2021/3/2 4:16 下午
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BatchSaveUsers {

    @Resource
    private UserService userService;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(3);

    @Test
    public void test() {
        int i = 0;
        for (; i < 500;i++) {
            int finalI = i;
            threadPool.submit(() -> {
                System.out.println(finalI);
                List<User> users = new ArrayList<>();
                for (int j = 0; j < 2000; j++) {
                    UUID randomUUID = UUID.randomUUID();
                    String name = randomUUID.toString();
                    String uuid = String.format("%018d", randomUUID.toString().hashCode());
                    User user = new User().setUserName(name).setNickName(name).setCreateTime(LocalDateTime.now())
                        .setUpdateTime(LocalDateTime.now()).setPassword("root").setCardNumber(uuid);
                    users.add(user);
                }
                userService.batchInsert(users);
            });
        }
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
        }
    }
}
