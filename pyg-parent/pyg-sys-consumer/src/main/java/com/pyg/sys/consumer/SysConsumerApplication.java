package com.pyg.sys.consumer;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

/**
 * @description: 运营商后台web层启动类
 * @author: HP
 * @date: 2019-05-27 22:57
 */
@SpringBootApplication
//开启hystrix服务熔断
@EnableHystrix
public class SysConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysConsumerApplication.class, args);
    }

    //用来拦截处理HystrixCommand注解
    @Bean
    public HystrixCommandAspect hystrixAspect() {
        return new HystrixCommandAspect();
    }

    // SpringBoot2.0以后，不提供 hystrix.stream节点，需要自己增加
    //用来向Dashboard监控中心发送stream信息
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        //监控该服务的url路径
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
