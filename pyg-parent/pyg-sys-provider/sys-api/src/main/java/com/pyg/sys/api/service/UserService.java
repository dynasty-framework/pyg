package com.pyg.sys.api.service;

import com.pyg.pojo.User;
import com.pyg.pojo.result.ReturnInfo;

/**
 * @description: 用户服务层接口
 * @author: HP
 * @date: 2019-05-30 19:57
 */
public interface UserService {

    ReturnInfo Login(User user);
}
