package com.taritari.blog.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taritari.blog.common.Result;
import com.taritari.blog.dto.LoginDto;
import com.taritari.blog.entity.User;
import com.taritari.blog.service.UserService;
import com.taritari.blog.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


/**
 * @ClassName AccountController
 * @Description TODO
 * @date 2021/9/17 11:27
 * @Version 1.0
 */
@RestController
@Api(tags = "用户管理模块")
public class AccountController {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserService userService;
    /**
     * 传过来用户名和密码，放在传过来的body里面，将其转成dto
     * 从 @RequestBody 获取LoginDto
     * @Validated 校验
     * Assert 断言  抛出全局异常IllegalStateException
     * HttpServletResponse response 讲jwt放在Header里面
     * */
    @ApiOperation("登录接口")
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto,
                        HttpServletResponse response){
        User user = userService.getOne(new QueryWrapper<User>()
                .eq("username", loginDto.getUsername()));
        Assert.notNull(user,"用户不存在");
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
            return Result.fail("密码错误!");
        }
        //密码正确 jwtUtils.getneratorToken 生成jwt Token
        String jwt =jwtUtil.generateToken(user.getId());
        //将返回的jwt放在Header里面   传入 HttpServletResponse response
        //Header名称：Authorization
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-Control-Expose-Headers","Authorization");
        //返回用户的一些信息   用户也可以另一个接口
        return Result.succ(MapUtil.builder()
        .put("id",user.getId())
        .put("username",user.getUsername())
        .put("avatar",user.getAvatar())
        .put("email",user.getEmail())
        .map());
    }
    /**
     * 退出
     * */
    @ApiOperation("退出接口")
    @RequiresAuthentication  // @RequiresAuthentication说明需要登录之后才能访问的接口
    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }
}
