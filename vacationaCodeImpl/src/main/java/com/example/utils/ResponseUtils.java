package com.example.utils;

import com.example.entity.ResponseResult;
import com.google.gson.Gson;

import javax.jws.Oneway;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtils {
    public static void print(HttpServletResponse resp, String json) throws IOException {
        resp.setContentType("text/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().append(json);
    }

    public static void success(HttpServletResponse resp,int code,String message,Object data) throws Exception{
        ResponseResult<Object> result = new ResponseResult<Object>(code, message, data);
        String json = (new Gson()).toJson(result);
        print(resp,json);
    }

    public static void success(HttpServletResponse resp, String message,Object data) throws Exception{
        success(resp,0,message,data);
    }

    public static void success(HttpServletResponse resp,Object data) throws Exception {
        success(resp,"成功",data);
    }

    public static void error(HttpServletResponse resp, int code,String message) throws  Exception{
        ResponseResult<Object> result = new ResponseResult<>(code,message,null);
        String json = (new Gson()).toJson(result);
        print(resp,json);
    }

    public static void error(HttpServletResponse resp,String message) throws Exception{
        error(resp, -1, message);
    }

    public static void error(HttpServletResponse resp) throws Exception{
        error(resp,"失败");
    }
}
