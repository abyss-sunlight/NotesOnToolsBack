package com.example.notesontoolsback.vo;

public class Result<T> {

    public int code;
    public String msg;
    public T data;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.msg = "success";
        result.code = 200;
        result.data = data;
        return result;
    }
    public static <T> Result success() {
        Result<T> result = new Result<T>();
        result.msg = "success";
        result.code = 200;
        return result;
    }

}


