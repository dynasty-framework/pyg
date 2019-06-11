package com.pyg.mq.provider.service;

import javax.jms.Destination;

/**
 * @description:
 * @author: HP
 * @date: 2019-06-11 22:59
 */
public interface MQProviderService {

    // 发送消息，destination是发送到的队列，message是待发送的消息
    void sendMessage(Destination destination, final String message);
}
