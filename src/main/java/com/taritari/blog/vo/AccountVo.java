package com.taritari.blog.vo;

/**
 * @ClassName AccountVo
 * @Description TODO
 * @date 2021/9/17 10:17
 * @Version 1.0
 */

import lombok.Data;

/**
 * 登录成功之后用户信息载体
 * */
@Data
public class AccountVo {
    private Long id;
    private String username;
    private String avatar;
    private String email;
}
