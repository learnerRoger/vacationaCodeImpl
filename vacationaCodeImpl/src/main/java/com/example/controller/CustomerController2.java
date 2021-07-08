package com.example.controller;

import com.example.entity.Customer;
import com.example.entity.Page;
import com.example.entity.ResponseResult;
import com.example.service.CustomerService;
import com.example.utils.ResponseUtils;
import com.example.utils.ResponseUtils2;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/customers")
public class CustomerController2{
    private CustomerService customerService = new CustomerService();
    //方式为get
    //testdemo
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult<Object> index(Customer customer) throws Exception {
        List<Customer> customers = customerService.findAll();
        return ResponseUtils2.success(customers);

    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult<Object> read(@PathParam("id") int id) throws Exception {
        Customer customer = customerService.findById(id);
        return ResponseUtils2.success(customer);
    }

    @GET
    @Path("/{pageIndex}/{pageSize}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult<Object> page(@PathParam("pageIndex") int pageIndex,@PathParam("pageSize") int pageSize) throws Exception{
        Page<Customer> result = customerService.findListByPage(pageIndex,pageSize);
        return ResponseUtils2.success(result);

    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult<Object> create(Customer customer) throws Exception{
        int result = customerService.save(customer);
        if(result > 0){
            return  ResponseUtils2.success(null);
        }else{
            return  ResponseUtils2.error("新增病人失败！");
        }
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult<Object> update(Customer customer) throws Exception{
        int result = customerService.modify(customer);
        if(result > 0){
             return  ResponseUtils2.success(null);
        }else{
             return   ResponseUtils2.error("修改病人失败！");
            }
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult<Object> delete(@PathParam("id") int id) throws Exception {
        int result = customerService.remove(id);
        if (result > 0) {
             return  ResponseUtils2.success(null);
        }else {
             return  ResponseUtils2.error("删除病人失败！");
            }
        }

}
