package com.hqjg.config.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by 50676 on 2016/9/11.
 */
@WebListener
public class SystemServletContextListener implements ServletContextListener {

    Logger log = LoggerFactory.getLogger(SystemServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("ServletContext初始化");
        log.info("serverInfo = {}", sce.getServletContext().getServerInfo());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("ServletContext销毁");
    }
}
