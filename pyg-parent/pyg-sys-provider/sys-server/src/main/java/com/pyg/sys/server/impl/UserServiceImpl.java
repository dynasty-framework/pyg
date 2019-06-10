package com.pyg.sys.server.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pyg.common.utils.Bcrypt;
import com.pyg.pojo.User;
import com.pyg.pojo.enumType.ResultEnum;
import com.pyg.pojo.result.ReturnInfo;
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
    public ReturnInfo Login(User loginUser) {
        ReturnInfo re = ReturnInfo.success();
        User user = userMapper.login(loginUser);
        if(loginUser == null) {
            re.setMsg(ResultEnum.USER_NAME_ERROR.getMessage());
            re.setCode(ResultEnum.USER_NAME_ERROR.getIndex());
            return re;
        }
        if(!Bcrypt.verifyPwd(loginUser.getPassword(), user.getPassword())) {
            re.setMsg(ResultEnum.USER_PWD_ERROR.getMessage());
            re.setCode(ResultEnum.USER_PWD_ERROR.getIndex());
            return re;
        }



        re.setData(user);
        return re;
    }
}
