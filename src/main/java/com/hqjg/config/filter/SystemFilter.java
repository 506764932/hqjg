package com.hqjg.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 50676 on 2016/9/11.
 */

@WebFilter(filterName="systemFilter",urlPatterns="/*")
public class SystemFilter implements Filter {

    Logger log = LoggerFactory.getLogger(SystemFilter.class);

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        log.error("执行过滤操作");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        httpRequest.getSession();
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // 表明它允许"http://xxx"发起跨域请求
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        // 表明在xxx秒内，不需要再发送预检验请求，可以缓存该结果
        httpResponse.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        // 表明它允许xxx的外域请求
        httpResponse.setHeader("Access-Control-Max-Age", "3628800");
        httpResponse.setHeader("cors.exposed.headers", "Access-Control-Allow-Origin,Access-Control-Allow-Credentials");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        // 表明它允许跨域请求包含xxx头
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

}
