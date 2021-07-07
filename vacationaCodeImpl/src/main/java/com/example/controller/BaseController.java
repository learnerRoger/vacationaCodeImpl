package com.example.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //xxxx?method=save
        String methodName = req.getParameter("method");
        Method method = null;

        //通过方法名和参数获取method对象
        try {
            method = this.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("调用的方法 " + methodName + "不存在");
        }

        //调用方法
        try {
            method.invoke(this,req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
