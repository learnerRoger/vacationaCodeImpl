package com.example.controller;

import com.example.entity.Customer;
import com.example.entity.Page;
import com.example.entity.ResponseResult;
import com.example.service.CustomerService;
import com.example.utils.ResponseUtils;
import com.example.utils.ResponseUtils2;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("")
public class CustomerController2{
    private CustomerService customerService = new CustomerService();

    @GET
    @Path("index")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult<Object> index(Customer customer) throws Exception {
        List<Customer> customers = customerService.findAll();
        return ResponseUtils2.success(customers);
    }



    public void read(HttpServletRequest req,HttpServletResponse resp) throws IOException{
        int id  = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerService.findById(id);
        try {
            ResponseUtils.success(resp,customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void page(HttpServletRequest req,HttpServletResponse resp) throws IOException{
        int pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        Page<Customer> result = customerService.findListByPage(pageIndex,pageSize);
        try {
            ResponseUtils.success(resp,result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(HttpServletRequest req,HttpServletResponse resp) throws IOException{
        int pno = Integer.parseInt(req.getParameter("pno"));
        String pna = req.getParameter("pna");
        String psex = req.getParameter("psex");
        int page = Integer.parseInt(req.getParameter("page"));
        String ptel = req.getParameter("ptel");
        String pkind = req.getParameter("pkind");
        String queuenum = req.getParameter("queuenum");
        Customer customer = new Customer(pno,pna,psex,page,ptel,pkind,queuenum);
        int result = customerService.save(customer);
        if(result > 0){
            try {
                ResponseUtils.success(resp,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                ResponseUtils.error(resp,"新增病人失败！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(HttpServletRequest req,HttpServletResponse resp) throws IOException{
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        System.out.println(req.getParameter("pno"));
        int pno = Integer.parseInt(req.getParameter("pno"));
        String pna = req.getParameter("pna");
        String psex = req.getParameter("psex");
        int page = Integer.parseInt(req.getParameter("page"));
        String ptel = req.getParameter("ptel");
        String pkind = req.getParameter("pkind");
        String queuenum = req.getParameter("queuenum");
        Customer customer = new Customer(pno,pna,psex,page,ptel,pkind,queuenum);
        int result = customerService.modify(customer);
        if(result > 0){
            try {
                ResponseUtils.success(resp,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                ResponseUtils.error(resp,"修改病人失败！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int result = customerService.remove(id);
        if (result > 0) {
            try {
                ResponseUtils.success(resp, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                ResponseUtils.error(resp, "删除病人失败！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
