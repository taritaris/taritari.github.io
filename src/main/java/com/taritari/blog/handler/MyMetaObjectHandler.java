package com.taritari.blog.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ClassName MyMetaObjectHandler
 * @Description 字段自动填充
 * @date 2021/9/17 10:40
 * @Version 1.0
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill------");
        this.setFieldValByName("created", LocalDateTime.now(),metaObject);
        this.setFieldValByName("updateTime", new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
