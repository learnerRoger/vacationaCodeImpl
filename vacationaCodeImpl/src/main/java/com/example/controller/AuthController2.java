package com.example.controller;
import com.example.entity.Admin;
import com.example.entity.ResponseResult;
import com.example.service.AdminService;
import com.example.utils.CacheUtils;
import com.example.utils.ResponseUtils2;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("")
public class AuthController2{
    private AdminService adminService = new AdminService();

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult<Object> login(Admin admin) throws Exception{
        if (admin != null){
            String token = UUID.randomUUID().toString().replace("-","");
            CacheUtils.put(token,admin);
            return ResponseUtils2.success(token);
        }else {
            return ResponseUtils2.error("用户名或者密码错误！");
        }
    }
    @POST
    @Path("logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult<Object> logout (@HeaderParam("token") String token) throws Exception{
        Object obj = CacheUtils.get(token,null);
        if(obj == null){
            return ResponseUtils2.error("未登录，不能进行注销！");
        }else {
            CacheUtils.remove(token);
            return ResponseUtils2.success("注销成功！",null);
        }
    }
}
