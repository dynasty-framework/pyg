package com.pyg.common.utils;

import java.util.Random;

/**
 * 验证码生成
 * Created by samchen on 2016/7/4.
 */
public class VerifyCode {

    /**
     * 生成数字验证码
     * @param size 验证码的位数
     * @return 验证码字符串
     */
    public static String numberCode(int size){
        Random rand = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i = 0 ; i < size ; i ++){
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(VerifyCode.numberCode(6));
    }
}
