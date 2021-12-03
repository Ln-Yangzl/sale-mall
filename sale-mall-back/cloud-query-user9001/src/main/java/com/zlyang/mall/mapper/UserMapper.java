package com.zlyang.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlyang.mall.entities.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: zlyang
 * @date: 2021-12-03 16:07
 * @description:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
