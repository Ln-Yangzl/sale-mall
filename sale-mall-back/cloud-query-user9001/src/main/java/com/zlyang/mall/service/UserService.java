package com.zlyang.mall.service;

import com.zlyang.mall.entities.User;
import com.zlyang.mall.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zlyang
 * @date: 2021-12-03 16:08
 * @description:
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User getUserById(Integer id){
        return userMapper.selectById(id);
    }

    public int create(User user){
        return userMapper.insert(user);
    }
}
