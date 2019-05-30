package com.pyg.sys.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pyg.pojo.User;
import com.pyg.sys.provider.mapper.UserMapper;
import com.pyg.sys.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: HP
 * @date: 2019-05-30 20:03
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User Login(User user) {
        User login = userMapper.Login(user);
        return login;
    }
}
