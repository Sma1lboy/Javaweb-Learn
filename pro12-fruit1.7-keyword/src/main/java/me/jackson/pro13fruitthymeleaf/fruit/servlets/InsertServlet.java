package me.jackson.pro13fruitthymeleaf.fruit.servlets;

import me.jackson.pro13fruitthymeleaf.fruit.dao.impl.FruitDAOImpl;
import me.jackson.pro13fruitthymeleaf.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/insert.do")
public class InsertServlet extends ViewBaseServlet {
    FruitDAOImpl dao = new FruitDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");


        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        String stockStr = req.getParameter("stock");
        String comment = req.getParameter("comment");
        BigDecimal price = new BigDecimal(priceStr);
        BigDecimal stock = new BigDecimal(stockStr);
        Fruit fruit = new Fruit(name, price, stock, comment);
        //执行更新
        dao.addFruit(fruit);
        //资源跳转
//        super.processTemplate("index", req, resp);
        //此处定向  重新获取请求 获取 fruit 才能覆盖到session上面
        resp.sendRedirect("index");
    }
}
