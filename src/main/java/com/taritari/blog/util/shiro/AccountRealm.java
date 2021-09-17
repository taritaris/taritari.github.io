package com.taritari.blog.util.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.taritari.blog.entity.User;
import com.taritari.blog.service.UserService;
import com.taritari.blog.util.JwtUtil;
import com.taritari.blog.vo.AccountVo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName AccountRealm
 * @Description TODO
 * @date 2021/9/17 8:56
 * @Version 1.0
 */
@Component
public class AccountRealm extends AuthorizingRealm {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    /**
     * supports：为了让realm支持jwt的凭证校验
     * */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
    /**
     * 认证
     * */
    /**
     * 其实主要就是doGetAuthenticationInfo登录认证这个方法，可
     * 以看到我们通过jwt获取到用户信息，
     * 判断用户的状态，最后异常就抛出对应的异常信息，
     * 否者封装成SimpleAuthenticationInfo返回给shiro。
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken =(JwtToken)token;
        /**
         * @jwtToken.getPrincipal()).getSubject(); 获取用户id
         * */
        String userId = jwtUtil.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        User user = userService.getById(Long.valueOf(userId));
        if (user == null){
            throw new UnknownAccountException("账户不存在");
        }
        if (user.getStatus()==-1){
            throw new LockedAccountException("账号已被锁定");
        }
        AccountVo Accountvo=new AccountVo();
        BeanUtil.copyProperties(user,Accountvo);
        return new SimpleAuthenticationInfo(Accountvo,jwtToken.getCredentials(),getName());
    }
}
