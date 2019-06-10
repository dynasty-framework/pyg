package com.pyg.common.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by evan on 2017/4/17.
 */
public class OrderUtil {

    private OrderUtil() {}

    /**
     * 获取订单编号
     * @return
     */
    public static String selectEntrustOrderSn(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyMMddmmss");
        Date date=new Date();
        return "KSJ" + fmt.format(date) +""+ numberCode(6);
    }

    /**
     * 生成规定位数的随机数
     * @param size 长度
     * @return
     */
    public static String numberCode(int size){
        Random rand =new Random();
        StringBuffer sb = new StringBuffer();
        for(int i = 0 ; i < size ; i ++){
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }
}
