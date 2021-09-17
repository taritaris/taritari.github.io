package com.taritari.blog.controller;


import com.taritari.blog.common.Result;
import com.taritari.blog.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Author: @taritari
 * @since 2021-09-17
 */
@RestController
@RequestMapping("/user")
@Api(tags = "测试模块")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public Result index(){
        return userService.index();
    }
}
