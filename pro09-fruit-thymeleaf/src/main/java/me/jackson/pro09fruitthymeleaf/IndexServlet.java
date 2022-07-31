package me.jackson.pro09fruitthymeleaf;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //视图名称是index
        //thymeleaf会将这个视图名称对应物理视图名称上
        //逻辑视图 ： index
        //物理视图： view-prefix + 逻辑视图名称 + view-suffix
        //真实的是： /index.html
        super.processTemplate("index",req, resp);
    }
}
