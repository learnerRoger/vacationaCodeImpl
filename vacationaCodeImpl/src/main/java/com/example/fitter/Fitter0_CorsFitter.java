package com.example.fitter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class Fitter0_CorsFitter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        //允许的主机地址
        resp.setHeader("Access-Control-Allow-Origin","*");
        //GET  POST PUT DELETE
        resp.setHeader("Access-Control-Allow-Methods","*");
        //缓存时间
        resp.setHeader("Access-Control-Max-Age","3600");
        //允许的请求头
        resp.setHeader("Access-Control-Allow-Headers","*");
        //是否携带cookie
        resp.setHeader("Access-Control-Allow-Credentials","false");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
    }
}
