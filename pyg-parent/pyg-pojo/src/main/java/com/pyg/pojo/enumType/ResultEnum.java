package com.pyg.pojo.enumType;

/**
 * @description:
 * @author: HP
 * @date: 2019-05-30 20:18
 */
public enum ResultEnum {

    success(0, "success"),

    /****************************** 系统内部错误开始 **********************************/
    failure(1, "failure"),

    /********************************* 用户相关接口 ************************************/
    USER_NAME_ERROR(2001,"用户名错误"),
    USER_PWD_ERROR(2002,"用户名错误"),
    USER_MOBILE_NOT_NULL(2003,"用户手机号不能是空"),
    REG_MOBILE_EXISTS(2004,"该手机号已注册"),
    LOGIN_NOT_EXISTS(2005,"该手机号未注册"),
    USER_CODE_NOT_NULL(2006,"请先发送验证码"),
    USER_VERIFY_QUICK(2007,"您操作过快，请稍后重试"),
    USER_VERIFY_ERROR(2008,"您输入验证码错误"),
    USER_CODE_EXPIRE(2009,"验证码已过期"),
    USER_CODE_OVERDUE(2010,"二维码已超时，请刷新页面重新扫码"),
    USER_PHONE_FROZEN(2012,"访问受限制"),
    USER_LACK_COMPANY(2013,"请完善您的公司信息"),
    USER_IS_UNABLE(2014,"用户已被冻结"),
    USER_LOGIN_OVERDUE(2015,"用户登录已超时，请重新登录"),
    USER_LOGIN_UNVALID(2015,"您登录的账号已无效，请重新登录"),//index2015统一返回登录页面
    USER_NO_ACCESS(2016,"无权限访问，请联系管理员"),
    USER_PASSWORD_ERROR(2017,"密码验证错误"),
    USER_NAME_ERROR_01(2018,"账号不能包含@符"),
    USER_NAME_ERROR_02(2019,"账号不能是纯数字"),
    USER_PHONE_ERROR_01(2020,"请输入正确的手机号"),
    USER_PHONE_ERROR_02(2020,"请输入11位手机号"),
    USER_EMAIL_ERROR_01(2021,"请输入正确的邮箱地址"),


    /********************************* 用户相关接口 ************************************/

    ;


    private int index;

    private String message;

    private ResultEnum(int index, String message) {
        this.index = index;
        this.message = message;
    }

    public int getIndex() {
        return index;
    }

    public String getMessage() {
        return message;
    }

    public static ResultEnum getByIndex(int index) {
        for (ResultEnum os: ResultEnum.values()) {
            if (os.getIndex() == index) {
                return os;
            }
        }
        return null;
    }

    public static ResultEnum getByMessage(String message) {
        for (ResultEnum os: ResultEnum.values()) {
            if (os.getMessage().equals(message)) {
                return os;
            }
        }
        return null;
    }
}
