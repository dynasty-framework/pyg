package com.pyg.sys.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pyg.pojo.User;
import com.pyg.pojo.result.ReturnInfo;
import com.pyg.sys.api.service.UserService;
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
    @HystrixCommand(fallbackMethod = "loginError")//接口调用失败后，调用onError方法
    public ReturnInfo login(@RequestBody User user) {
        ReturnInfo re = ReturnInfo.success();
        try {
            return userService.Login(user);
        }catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            re = ReturnInfo.failure();
        }
        return re;
    }

    /**
     * 如果fallback方法的参数和原方法参数个数不一致，则会出现FallbackDefinitionException: fallback method wasn't found
     * fallback方法的返回值与原方法的返回值也要一致
     *
     * 登录熔断、降级接口
     */
    public ReturnInfo loginError(User user) {
        ReturnInfo re = ReturnInfo.failure();
        re.setMsg("服务器正忙。。。请稍后访问！");
        return re;
    }


}
