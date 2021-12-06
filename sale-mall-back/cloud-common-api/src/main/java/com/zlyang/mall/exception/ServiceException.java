package com.zlyang.mall.exception;

import com.zlyang.mall.entities.ResultMsgEnum;

/**
 * @author: zlyang
 * @date: 2021-12-06 10:24
 * @description:
 */
public class ServiceException extends RuntimeException{
    private int code;

    public int getCode() {
        return code;
    }

    /**
     * 使用已有的错误类型
     * @param type 枚举类中的错误类型
     */
    public ServiceException(ResultMsgEnum type){
        super(type.getMessage());
        this.code = type.getCode();
    }

    /**
     * 自定义错误类型
     * @param code 自定义的错误码
     * @param msg 自定义的错误提示
     */
    public ServiceException(int code, String msg){
        super(msg);
        this.code = code;
    }
}
