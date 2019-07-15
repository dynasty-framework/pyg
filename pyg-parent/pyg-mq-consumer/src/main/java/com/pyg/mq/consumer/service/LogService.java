package com.pyg.mq.consumer.service;

import com.pyg.pojo.LogInfo;

/**
 * @description:
 * @author: HP
 * @date: 2019-07-15 20:32
 */
public interface LogService {

    Integer insertLog(LogInfo log);
}
