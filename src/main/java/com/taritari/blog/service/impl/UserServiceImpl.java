package com.taritari.blog.service.impl;

import com.taritari.blog.common.Result;
import com.taritari.blog.entity.User;
import com.taritari.blog.mapper.UserMapper;
import com.taritari.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Author: @taritari
 * @since 2021-09-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result index(){
        User user = userMapper.selectById(1L);
        return Result.succ("测试接口成功",user);
    }
}
