package com.pyg.sys.server.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pyg.pojo.User;
import com.pyg.sys.api.service.UserService;
import com.pyg.sys.server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: UserServiceImpl
 * @description: TODO
 * @author: zhaotao
 * @create: 2019-05-31 12:31
 **/
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
