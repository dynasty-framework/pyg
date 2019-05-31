package com.pyg.sys.server.mapper;

import com.pyg.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: HP
 * @date: 2019-05-30 20:06
 */
@Mapper
public interface UserMapper {

    User Login(User user);

    User insert(User user);

    User update(User user);
}
