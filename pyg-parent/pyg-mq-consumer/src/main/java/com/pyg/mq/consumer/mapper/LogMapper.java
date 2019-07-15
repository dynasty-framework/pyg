package com.pyg.mq.consumer.mapper;

import com.pyg.pojo.LogInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: HP
 * @date: 2019-07-15 20:58
 */
@Mapper
public interface LogMapper {

    Integer insertLog(LogInfo logInfo);
}
