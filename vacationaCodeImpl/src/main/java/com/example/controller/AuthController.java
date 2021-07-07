package com.example.controller;


import com.example.entity.Admin;
import com.example.service.AdminService;
import com.example.utils.CacheUtils;
import com.example.utils.ResponseUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@WebServlet("/auth")
public class AuthController extends BaseController{
    private AdminService adminService = new AdminService();
    public void login(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Admin admin = adminService.login(new Admin(username,password));
        System.out.println(admin.getUsername() + " " + admin.getPassword());
        if (admin != null){
            String token = UUID.randomUUID().toString().replace("-","");
            CacheUtils.put(token,admin);
            ResponseUtils.success(resp,token);
        }else {
            ResponseUtils.error(resp,"用户名或者密码错误！");
        }
    }

    public void logout (HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String token = req.getHeader("token");
        System.out.println(token);
        Object obj = CacheUtils.get(token,null);
        if(obj == null){
            ResponseUtils.error(resp,"未登录，不能进行注销！");
        }else {
            CacheUtils.remove(token);
            ResponseUtils.success(resp,"注销成功！",null);
        }
    }
}
