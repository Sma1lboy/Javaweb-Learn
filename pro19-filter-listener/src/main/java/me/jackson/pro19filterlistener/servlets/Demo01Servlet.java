package me.jackson.pro19filterlistener.servlets;

import javax.servlet.ServletException;
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
@WebServlet("/demo01.do")
public class Demo01Servlet extends HttpServlet {

    @Override
    protected  void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo01 service");

        req.getRequestDispatcher("succ.html").forward(req,resp);
    }
}
