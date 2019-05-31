package com.pyg.sys.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @description: 运营商后台服务层
 * @author: HP
 * @date: 2019-05-30 19:29
 */
@SpringBootApplication(scanBasePackages = "com.pyg.sys.server", exclude = DataSourceAutoConfiguration.class)
public class SysServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysServerApplication.class, args);
    }

}
