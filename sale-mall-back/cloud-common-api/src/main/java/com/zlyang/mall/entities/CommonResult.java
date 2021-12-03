package com.zlyang.mall.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zlyang
 * @date: 2021-12-03 16:16
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private int code;

    private String message;

    private T data;

    public CommonResult(int code, String message) {
        this(code, message, null);
    }

    /**
     * 成功
     */
    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<T>();
        result.setCode(ResultMsgEnum.SUCCESS.getCode());
        result.setMessage(ResultMsgEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 失败，自定义状态码
     */
    public static <T> CommonResult<T> error(int code, String message) {
        return new CommonResult(code, message);
    }

    /**
     * 失败，返回枚举类型统一的的状态码和信息
     * @param rme
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> error(ResultMsgEnum rme){
        return new CommonResult(rme.getCode(), rme.getMessage());
    }
}
