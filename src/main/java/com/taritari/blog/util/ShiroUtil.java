package com.taritari.blog.util;

import com.taritari.blog.vo.AccountVo;
import org.apache.shiro.SecurityUtils;

/**
 * @ClassName ShiroUtil
 * @Description TODO
 * @date 2021/9/17 13:56
 * @Version 1.0
 */

public class ShiroUtil {
    public static AccountVo getProfile() {
        return  (AccountVo) SecurityUtils.getSubject().getPrincipal();
    }
}
