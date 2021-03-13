package io.zero.service.impl;

import io.zero.entity.User;
import io.zero.mapper.UserMapper;
import io.zero.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public  List<User> list() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    @Override
    public String insertForeach(List<User> userList) {
        for (User user : userList) {
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setStatus(0);
        }
        //批量插入数据
        userMapper.insertForeach(userList);
        return "保存成功";
    }

}
