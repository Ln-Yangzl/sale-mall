package com.zlyang.mall.entities;

/**
 * @author: zlyang
 * @date: 2021-12-03 16:16
 * @description: 类内部的状态枚举类
 */
public enum StatusEnum {

    /**
     * 成功
     */
    SUCCESS(1, "成功"),
    /**
     * 失败
     */
    FAIL(0, "常规失败"),
    DATABASE_OPERATION_FAILED(-1, "数据库操作失败"),
    AUTH_ERROR(502, "授权失败!"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试!");

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    StatusEnum(int code, String message){
        this.code = code;
        this.message = message;
    }
}
