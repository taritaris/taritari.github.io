package com.taritari.blog.dto;

import lombok.Data;
import sun.dc.pr.PRError;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName LoginDto
 * @Description TODO
 * @date 2021/9/17 11:14
 * @Version 1.0
 */

@Data
public class LoginDto implements Serializable {
    @NotBlank(message = "名称不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
