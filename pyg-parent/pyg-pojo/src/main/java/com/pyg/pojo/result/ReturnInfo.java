package com.pyg.pojo.result;

import com.pyg.pojo.enumType.ResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: HP
 * @date: 2019-05-30 20:13
 */
@Data
public class ReturnInfo implements Serializable {

    //自定义返回码
    private Integer code;

    //自定义返回信息
    private String msg;

    //返回数据
    private Object data;

    //成功
    public static ReturnInfo success() {
        ReturnInfo re = new ReturnInfo();
        re.setCode(ResultEnum.success.getIndex());
        re.setMsg(ResultEnum.success.getMessage());
        return re;
    }

    //失败
    public static ReturnInfo failure() {
        ReturnInfo re = new ReturnInfo();
        re.setCode(ResultEnum.failure.getIndex());
        re.setMsg(ResultEnum.failure.getMessage());
        return re;
    }


}
