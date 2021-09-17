package com.taritari.blog.service;

import com.taritari.blog.common.Result;
import com.taritari.blog.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taritari.blog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Author: @taritari
 * @since 2021-09-17
 */
public interface UserService extends IService<User> {
    /**
     * 测试接口
     * */
    public Result index();
}
