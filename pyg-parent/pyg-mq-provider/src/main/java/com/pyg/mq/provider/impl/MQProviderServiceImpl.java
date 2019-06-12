package com.pyg.mq.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pyg.mq.provider.service.MQProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;

import javax.jms.Destination;

/**
 * @description:
 * @author: HP
 * @date: 2019-06-11 23:02
 */
@Service
public class MQProviderServiceImpl implements MQProviderService {

    // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Override
    public void sendMessage(Destination destination, final String message) {
        System.out.println("发送消息");
        jmsTemplate.convertAndSend(destination, message);
    }

    //@JmsListener(destination="login.queue")
    //注解@SendTo("out.queue")，该注解的意思是将return回的值，再发送到"out.queue"队列中
    //@SendTo("out.queue")
    /*public void consumerMessage(String text){
        System.out.println("login.queue队列收到的回复报文为:"+text);
    }*/
}
