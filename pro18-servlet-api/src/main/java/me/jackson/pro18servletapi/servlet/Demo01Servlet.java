package me.jackson.pro18servletapi.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/8
 */

@WebServlet(
        urlPatterns = {
                "/demo01",
                "/demo0001"
        },
        initParams = {
                @WebInitParam(
                        name = "hello",
                        value = "world"
                )
        }
)
public class Demo01Servlet extends HttpServlet {


    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        String str = config.getInitParameter("hello");
        System.out.println(str);
        //方式一 通过web.xml存储 parameter
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("cool");
    }
}


//servlet的生命周期，实例化 初始化 服务 销毁
//Servlet中的初始化方法有两个 init() init(config)
//其中参数的是
//public void init(ServletConfig config) throws ServletException {
//    this.config = config;
//    this.init();
//}
//无参的
//public void init() throws ServletException {
//}
//如果我们想在初始化的时候做一些准备操作， 我们可以重写init
// 通过获取Config对象 getServletConfig()
// 获取初始化参数值
