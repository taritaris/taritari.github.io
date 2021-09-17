package com.taritari.blog.util;

import io.jsonwebtoken.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName JwtUtil
 * @Description TODO
 * @date 2021/9/17 9:55
 * @Version 1.0
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "taritari.jwt")
public class JwtUtil {
    /**
     * 秘钥
     * */
    private String secret;
    /**
     * token有效时长,7天，单位，秒
     * */
    private long expire;
    /**
     * Authorization
     * */
    private String header;


    /**
     * 生成Token
     * */
    public String generateToken(long userId){
        Date nowData=new Date();
        //过期时间
        Date expireDate = new Date(nowData.getTime()+expire*1000);
        return Jwts.builder()//生成token
                .setHeaderParam("typ","JWT")
                .setSubject(userId+"")
                .setIssuedAt(nowData)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * 校验token
     * */
    public Claims getClaimByToken(String token){
        try {
            Claims claims  = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        }catch (Exception e){
            log.debug("validate is token error",e);
            return null;
        }
    }
    /**
     * 验证token是否过期
     * */
    public boolean isTokenExpired(Date expiration){
        boolean before = expiration.before(new Date());
        return before;
    }
}
