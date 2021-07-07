package com.example.config;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
public class JSONConfig extends ResourceConfig{
    public JSONConfig(){
        //控制器包的名字
        packages("com.example.controller");
        register(JacksonJsonProvider.class);
    }
}
