package com.taritari.blog.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Result
 * @Description TODO
 * @date 2021/9/17 9:20
 * @Version 1.0
 */
@Data
public class Result<T> implements Serializable {
    private int code; //200表示成功过
    /**
     *  返回内容
     * */
    private String msg;
    private T data;
    /**
     * 返回成功
     * */
    public static<T> Result succ(int code, String msg, T data) {
        Result m = new Result();
        m.setCode(200);
        m.setData(data);
        m.setMsg(msg);
        return m;
    }

    public static<T> Result succ(){
        return succ( 200,"操作成功",null);
    }
    public static<T> Result succ(T data){
        return succ( 200,"操作成功",data);
    }
    public static<T> Result succ(String msg,T data){
        return succ( 200,msg,null);
    }
    public static<T> Result succ(int code,String msg){
        return succ( 200,msg,null);
    }

    /**
     * 返回失败
     * */
    public static<T> Result fail(int code,String msg,T data){
        Result m = new Result();
        m.setCode(400);
        m.setData(data);
        m.setMsg(msg);
        return m;
    }

    public static<T> Result fail(String msg){
        return fail(400,msg,null);
    }
    public static<T> Result fail(String msg,T data){
        return fail(400,msg,data);
    }
}
