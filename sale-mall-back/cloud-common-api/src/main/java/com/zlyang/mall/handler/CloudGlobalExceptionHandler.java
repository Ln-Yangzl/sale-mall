package com.zlyang.mall.handler;

import com.zlyang.mall.entities.CommonResult;
import com.zlyang.mall.entities.ResultMsgEnum;
import com.zlyang.mall.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: zlyang
 * @date: 2021-12-06 9:25
 * @description: 公共全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class CloudGlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public CommonResult nullPointerExceptionHandler(NullPointerException e){
        log.error("空指针异常，请检查key值是否正确", e);
        return CommonResult.error(ResultMsgEnum.NULL_POINTER_EXCEPTION);
    }

    @ExceptionHandler(ServiceException.class)
    public CommonResult serviceExceptionHandler(ServiceException e){
        log.error("ServiceException:" + e.getMessage(), e);
        return CommonResult.error(e.getCode(), e.getMessage());
    }

}
