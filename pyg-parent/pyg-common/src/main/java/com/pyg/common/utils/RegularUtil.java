package com.pyg.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则验证
 *
 * @author chenfei
 * @date 2015年6月4日 下午4:00:45
 */
public class RegularUtil {
    /**
     * 自定义验证
     * @param str 验证的string
     * @param regular 正则类型
     * @return boolean
     */
    public static boolean verify(String str , String regular) {
        Pattern pat = Pattern.compile(regular);
        Matcher mat = pat.matcher(str);
        boolean mark = mat.matches();
        return mark;
    }
    
}
