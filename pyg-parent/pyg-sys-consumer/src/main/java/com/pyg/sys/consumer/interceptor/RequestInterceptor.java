package com.pyg.sys.consumer.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: HP
 * @date: 2019-07-15 19:58
 */
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        JSONObject json = new JSONObject();
        json.put("log_uuid", "12134");
        jmsTemplate.convertAndSend("log.info", json.toJSONString());
        return true;
    }
}
