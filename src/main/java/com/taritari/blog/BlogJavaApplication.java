package com.taritari.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.taritari.blog.mapper")
public class BlogJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogJavaApplication.class, args);
    }

}
