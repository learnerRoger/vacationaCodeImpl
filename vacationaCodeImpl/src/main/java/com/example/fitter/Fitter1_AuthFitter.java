package com.example.fitter;

import com.example.utils.CacheUtils;
import com.example.utils.ResponseUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(urlPatterns = {"/*"})
public class Fitter1_AuthFitter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("AuthFitter doFitter");
        HttpServletRequest req = (HttpServletRequest) request;
        boolean isLoginMethod = false;
        if ("login".equals(req.getParameter("method"))){
            isLoginMethod = true;
        }else if (req.getRequestURI().indexOf("/login") > 0) {
            isLoginMethod = true;
        }

        if (isLoginMethod){
            chain.doFilter(request,response);
        }else {
            String token = req.getHeader("token");
            Object result = CacheUtils.get(token,null);
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
