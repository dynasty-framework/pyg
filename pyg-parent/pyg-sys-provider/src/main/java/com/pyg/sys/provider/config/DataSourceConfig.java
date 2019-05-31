package com.pyg.sys.provider.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 提供数据库连接池工具方法
 * @author zt
 * @date 2019年4月12日 16:21:33
 */

/**
 * @description: 运营商后台web层启动类
 * @author: HP
 * @date: 2019-05-27 22:57
 */
@Configuration
@MapperScan(basePackages="com.pyg.sys.provider.mapper", sqlSessionTemplateRef="sqlSessionTemplate")
public class DataSourceConfig {

	 @Autowired
	 private JdbcConfig jdbcConfig;
	 
	@Bean(name="datasource")
    @Primary //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(jdbcConfig.getDriverClass());
        druidDataSource.setUrl(jdbcConfig.getUrl());
        druidDataSource.setUsername(jdbcConfig.getUserName());
        druidDataSource.setPassword(jdbcConfig.getPassword());
        druidDataSource.setInitialSize(jdbcConfig.getInitialSize());
        druidDataSource.setMinIdle(jdbcConfig.getMinIdle());
        druidDataSource.setMaxActive(jdbcConfig.getMaxActive());
        druidDataSource.setDbType(jdbcConfig.getDbType());
        druidDataSource.setTimeBetweenEvictionRunsMillis(jdbcConfig.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(jdbcConfig.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(jdbcConfig.getValidationQuery());
        druidDataSource.setTestWhileIdle(jdbcConfig.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(jdbcConfig.isTestOnBorrow());
        druidDataSource.setTestOnReturn(jdbcConfig.isTestOnReturn());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        try {
            druidDataSource.setFilters("stat,log4j,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }


}
