package com.pyg.mq.consumer.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 提供数据库连接池配置属性
 * @author zt
 * @date 2019年4月12日 16:21:33
 */

@Component
@Data
public class JdbcConfig {

    /*
     * Could not resolve placeholder 'spring.datasource.driver-class-name' in value "${spring.datasource.driver-class-name}"
     * 项目在配置更改后要对maven进行clean，install的操作，清除以重新编译项目
     **/

    /**
     * 驱动名称
     */
    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    /**
     * 数据库连接url
     */
    @Value("${spring.datasource.url}")
    private String url;

    /**
     * 数据库用户名
     */
    @Value("${spring.datasource.username}")
    private String userName;

    /**
     * 数据库密码
     */
    @Value("${spring.datasource.password}")
    private String password;

    /**
     * 数据库连接池初始化大小
     */
    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    /**
     * 数据库连接池最小最小连接数
     */
    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    /**
     * 数据库连接池最大连接数
     */
    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    /**
     * 获取连接等待超时的时间
     */
    @Value("${spring.datasource.maxWait}")
    private long maxWait;

    /**
     * 多久检测一次
     */
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    /**
     * 连接在池中最小生存的时间
     */
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;

    /**
     * 测试连接是否有效的sql
     */
    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    /**
     * 申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，检测连接是否有效
     */
    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    /**
     * 申请连接时,检测连接是否有效
     */
    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    /**
     * 归还连接时,检测连接是否有效
     */
    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    /**
     * 数据库连接池类型
     */
    @Value("${spring.datasource.type}")
    private String dbType;

}
