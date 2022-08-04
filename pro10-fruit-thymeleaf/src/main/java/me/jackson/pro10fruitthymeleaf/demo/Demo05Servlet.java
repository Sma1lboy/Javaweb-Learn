package me.jackson.pro10fruitthymeleaf.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//application
@WebServlet("/demo05")
public class Demo05Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getSession().setAttribute("uname", "lili");
        ServletContext application = req.getServletContext();
        application.setAttribute("uname", "lili");
        resp.sendRedirect("demo06");
//        req.getRequestDispatcher("demo02").forward(req,resp);
    }
}
