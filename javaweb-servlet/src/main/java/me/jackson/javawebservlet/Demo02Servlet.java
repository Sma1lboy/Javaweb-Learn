package me.jackson.javawebservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//showing the servlet life cycle
public class Demo02Servlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("正在初始化......");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("正在服务");
    }

    @Override
    public void destroy() {
        System.out.println("正在销毁...");
    }
}

