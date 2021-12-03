package com.zlyang.mall.entities;

/**
 * @author: zlyang
 * @date: 2021-12-03 16:16
 * @description:
 */
public enum ResultMsgEnum {
    /**
     * 成功
     */
    SUCCESS(0, "成功"),
    /**
     * 失败
     */
    FAIL(1, "常规失败"),
    DATABASE_OPERATION_FAILED(2, "数据库操作失败"),
    AUTH_ERROR(502, "授权失败!"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试!");

    private final int code;
    private final String message;

    ResultMsgEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}


