package com.pyg.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 一般验证
 * Created by 37126 on 2017/4/23.
 */
public class CommonVerify {

    /**
     * 手机号验证
     * @param telNum
     * @return
     */
    public static boolean isMobileNum(String telNum){
        String regex = "^1[34578]\\d{9}$";
        Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(telNum);
        return m.matches();
    }
    
    /**
     * 邮箱验证
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        String regex = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
        Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);
        return m.matches();
    }

}
