package com.pyg.sys.consumer.hystrix;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixConfig {
    //用来拦截处理HystrixCommand注解
    @Bean
    public HystrixCommandAspect hystrixAspect() {
        return new HystrixCommandAspect();
     }

    //如果需要对该controller进行监控，添加这个用来向监控中心Dashboard发送stream信息
    @Bean
    public ServletRegistrationBean hystrixMetricsStreamServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        //这个要和turbine版本中的默认访问链接一致，不然还是无法访问的
        registration.addUrlMappings("/hystrix.stream");
        return registration;
    }
}
