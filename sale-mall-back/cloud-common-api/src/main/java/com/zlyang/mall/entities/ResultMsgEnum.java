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
    FAIL(1, "未知异常"),
    DATABASE_OPERATION_FAILED(2, "数据库操作失败"),
    DATABASE_KEY_NOT_FOUND(3, "数据库中对应key不存在"),
    NULL_POINTER_EXCEPTION(4, "检查传入参数是否在数据库中存在"),
    SERVICE_CALL_FAIL(5, "微服务调用异常"),
    SECKILL_FULL(-1, "目标秒杀已无余量"),
    ACCESS_VIOLATION(-2, "非法访问");

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


