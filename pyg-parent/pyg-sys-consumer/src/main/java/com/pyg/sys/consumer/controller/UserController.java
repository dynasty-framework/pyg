package com.pyg.sys.consumer.controller;

import com.pyg.pojo.User;
import com.pyg.pojo.result.ReturnInfo;
import com.pyg.sys.provider.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.alibaba.dubbo.config.annotation.Reference;


/**
 * @description:
 * @author: HP
 * @date: 2019-05-30 20:07
 */
@RestController
@RequestMapping("/sys/user")
@Slf4j
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping(value = ("/login"), method = RequestMethod.POST)
    @ResponseBody
    public ReturnInfo login(@RequestBody User user) {
        ReturnInfo re = ReturnInfo.success();
        try {
            log.info("======== 用户登录 ========");
            User login = userService.Login(user);
            if(login == null) {
                re = ReturnInfo.failure();
            }
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return re;
    }
}
