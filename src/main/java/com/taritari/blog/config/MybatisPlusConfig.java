package com.taritari.blog.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName MybatisPlusConfig
 * @Description TODO
 * @date 2021/9/17 10:45
 * @Version 1.0
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.taritari.mapper")
public class MybatisPlusConfig {
    /**
     * 分页插件
     * */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor =new PaginationInterceptor();
        return paginationInterceptor;
    }
}
