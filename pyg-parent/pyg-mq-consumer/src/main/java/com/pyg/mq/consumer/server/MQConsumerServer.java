package com.pyg.mq.consumer.server;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: HP
 * @date: 2019-06-12 20:46
 */
@Component
public class MQConsumerServer {

    @JmsListener(destination="login.queue")
    //注解@SendTo("out.queue")，该注解的意思是将return回的值，再发送到"out.queue"队列中
    //@SendTo("out.queue")
    public void consumerMessage(String text){
        System.out.println("login.queue队列收到的回复报文为:"+text);
    }
}
