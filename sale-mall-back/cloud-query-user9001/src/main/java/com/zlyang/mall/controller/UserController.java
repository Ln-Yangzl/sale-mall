package com.zlyang.mall.controller;

import com.zlyang.mall.entities.CommonResult;
import com.zlyang.mall.entities.ResultMsgEnum;
import com.zlyang.mall.entities.User;
import com.zlyang.mall.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: zlyang
 * @date: 2021-12-03 16:25
 * @description:
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping(value = "/user/get")
    public CommonResult getUserById(@RequestParam(name = "userId") Integer id){
        User user = userService.getUserById(id);
        return CommonResult.success(user);
    }

    @PostMapping(value = "/user/create")
    public CommonResult create(User user){
        int status = userService.create(user);
        if(status > 0){
            return CommonResult.success(null);
        } else {
            return CommonResult.error(ResultMsgEnum.DATABASE_OPERATION_FAILED);
        }
    }
}
