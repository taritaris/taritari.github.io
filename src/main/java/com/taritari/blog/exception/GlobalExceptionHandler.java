package com.taritari.blog.exception;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @date 2021/9/17 11:15
 * @Version 1.0
 */
/**
 * 有时候不可避免服务器报错的情况，如果不配置异常处理机制，
 * 就会默认返回tomcat或者nginx的5XX页面，
 * 对普通用户来说，不太友好，用户也不懂什么情况。这
 * 时候需要我们程序员设计返回一个友好简单的格式给前端。
 * */

/**
 * 通过使用@ControllerAdvice来进行统一异常处理，
 * @ExceptionHandler(value = RuntimeException.class)来指定捕获的Exception各个类型异常 ，
 * 这个异常的处理，是全局的，所有类似的异常，都会跑到这个地方处理。
 * */

import com.taritari.blog.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@Slf4j
//异步 使用@ControllerAdvice来进行统一异常处理
@RestControllerAdvice
public class GlobalExceptionHandler {
    //捕获shiro异常
    /**
     * ShiroException：
     * shiro抛出的异常，比如没有权限，用户登录异常
     * UNAUTHORIZED(401, "Unauthorized"),401 没有权限
     * */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public Result handle401(ShiroException e){
        return Result.fail(401,e.getMessage(),null);
    }
    /**
     * 处理Assert的异常
     * IllegalArgumentException：处理Assert的异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e){
        log.error("Assert断言异常:--------->[{}]",e.getMessage());
        return Result.fail(e.getMessage());
    }

    /**
     * @Validated 校验错误异常处理
     * MethodArgumentNotValidException：处理实体校验的异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e){
        log.error("运行时异常:---------->[{}]",e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.fail(objectError.getDefaultMessage());
    }
    /**
     * RuntimeException：捕捉其他异常
     * */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e){
        log.error("运行时异常:----------->[{}]",e);
        return Result.fail(e.getMessage());
    }
}
