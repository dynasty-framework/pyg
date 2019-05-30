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
