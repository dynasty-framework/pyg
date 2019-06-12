package com.pyg.mq.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @description:
 * @author: HP
 * @date: 2019-06-12 20:44
 */
@SpringBootApplication
@EnableJms
public class MQConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MQConsumerApplication.class, args);
    }
}
