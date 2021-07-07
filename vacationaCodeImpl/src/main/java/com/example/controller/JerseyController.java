package com.example.controller;
//import javax.ws.rs.Path;

import com.example.entity.Customer;
import com.example.service.CustomerService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("")
public class JerseyController {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(){
        return "hello,Jersey";
    }

    @GET
    @Path("/hello/{username}")
    public String helloUser(@PathParam("username") String username){
        return "hello " + username;
    }

    @GET
    @Path("/customers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomers(){
        return (new CustomerService().findAll());
    }
}
