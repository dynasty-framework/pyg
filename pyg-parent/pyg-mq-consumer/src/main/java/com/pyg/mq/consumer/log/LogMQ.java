package com.pyg.mq.consumer.log;

import com.alibaba.fastjson.JSON;
import com.pyg.mq.consumer.mapper.LogMapper;
import com.pyg.pojo.LogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


/**
 * @description:
 * @author: HP
 * @date: 2019-07-15 19:53
 */
@Component
public class LogMQ {

    @Autowired
    private LogMapper logMapper;

    @JmsListener(destination="log.info")
    public void sendMessage(String text) {
        LogInfo logInfo = JSON.parseObject(text, LogInfo.class);
        logMapper.insertLog(logInfo);
        System.out.println("发送消息" + text);
    }
}
