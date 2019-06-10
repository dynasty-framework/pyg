package com.pyg.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * session，cookie工具类
 * @author Devil
 * @mail 562157205@qq.com
 * @date 2017年5月3日 上午11:26:11
 */
public class SessionUtil<T> {
    
    protected static final Logger logger = LoggerFactory.getLogger(SessionUtil.class);
    
    private static final String SESSION_USER = "session_user";

    private static final String SESSION_VALIDATECODE = "session_validatecode";//验证码
    
    /**
     * 设置session的值
     * @param request
     * @param key
     * @param value
     */
    public static void setAttr(HttpServletRequest request,String key,Object value){
        request.getSession(true).setAttribute(key, value);
    }
    
    /**
     * 获取session的值
     * @param request
     * @param key
     * @param value
     */
    public static Object getAttr(HttpServletRequest request,String key){
        return request.getSession(true).getAttribute(key);
    }
    
    /**
     * 删除Session值
     * @param request
     * @param key
     */
    public static void removeAttr(HttpServletRequest request,String key){
        request.getSession(true).removeAttribute(key);
    }
    
    /**
     * 设置用户信息 到session
     * @param request
     * @param user
     */
    @SuppressWarnings("unchecked")
    public static <T> void setUser(HttpServletRequest request, Object user){
        request.getSession(true).setAttribute(SESSION_USER, (T) user);
    }
    
    /**
     * 从session中获取用户信息
     * @param request
     * @return SysUser
     */
    @SuppressWarnings("unchecked")
    public static <T> T getUser(HttpServletRequest request){
       return (T) request.getSession(true).getAttribute(SESSION_USER);
    }
    
    /**
     * 从session中获取用户信息
     * @param request
     * @return SysUser
     */
    public static void removeUser(HttpServletRequest request){
       removeAttr(request, SESSION_USER);
    }
    
    /**
     * 设置验证码 到session
     * @param request
     * @param user
     */
    public static void setValidateCode(HttpServletRequest request,String validateCode){
        request.getSession(true).setAttribute(SESSION_VALIDATECODE, validateCode);
    }
    
    /**
     * 从session中获取验证码
     * @param request
     * @return SysUser
     */
    public static String getValidateCode(HttpServletRequest request){
       return (String)request.getSession(true).getAttribute(SESSION_VALIDATECODE);
    }
    
    /**
     * 从session中获删除验证码
     * @param request
     * @return SysUser
     */
    public static void removeValidateCode(HttpServletRequest request){
       removeAttr(request, SESSION_VALIDATECODE);
    }
}
