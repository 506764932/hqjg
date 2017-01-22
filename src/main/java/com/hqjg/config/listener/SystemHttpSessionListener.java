package com.hqjg.config.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by 50676 on 2016/9/11.
 */
@WebListener
public class SystemHttpSessionListener implements HttpSessionListener {

    Logger log = LoggerFactory.getLogger(SystemHttpSessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        //1、获取Servlet上下文
        ServletContext context = httpSessionEvent.getSession().getServletContext();
        //2、取存在的变量值
        Integer count=(Integer)context.getAttribute("count");
        //3、如果不存在则为1，如果存在则加1
        if(count == null) {
            count = new Integer(1);
        }else{
            int co = count.intValue( );
            count = new Integer(co + 1);
        }
        log.info("sessionCreated, now count = {}", count);
        //4、将处理后的值以count为名存入context中
        //保存人数
        context.setAttribute("count", count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        //1、获取Servlet上下文
        ServletContext context = httpSessionEvent.getSession().getServletContext();

        context.removeAttribute(httpSessionEvent.getSession().getId());
        //2、取出count对应的值
        Integer count = (Integer)context.getAttribute("count");
        int co = count.intValue();
        count = new Integer(co - 1);
        log.info("sessionDestroyed , before count = {}, after count = {}", co, count);
        //3、将数量减1后重新存入context的count中
        context.setAttribute("count", count);
    }
}
