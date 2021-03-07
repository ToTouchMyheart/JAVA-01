package io.zero.db.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import io.zero.db.dao.UserMapper;
import io.zero.db.entity.User;

/**
 * @Author zhurui
 * @Date 2021/3/2 4:14 下午
 * @Version 1.0
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void batchInsert(List<User> users) {
        userMapper.batchInsertSelective(users, User.Column.excludes(User.Column.id));
    }

}
