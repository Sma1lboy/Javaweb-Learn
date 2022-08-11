package me.jackson.pro17fruitthymeleaf.fruit.listeners;

import me.jackson.pro17fruitthymeleaf.util.ioc.BeanFactory;
import me.jackson.pro17fruitthymeleaf.util.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/10
 */
@WebListener()
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //1.
        ServletContext application = servletContextEvent.getServletContext();
        //2.
        String path = application.getInitParameter("contextConfigLocation");
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);
        application.setAttribute("beanFactory", beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
