package me.jackson.pro10fruitthymeleaf.fruit.servlets;


import me.jackson.pro10fruitthymeleaf.fruit.dao.FruitDAO;
import me.jackson.pro10fruitthymeleaf.fruit.dao.impl.FruitDAOImpl;
import me.jackson.pro10fruitthymeleaf.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {
    private FruitDAO dao = new FruitDAOImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Integer id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        String stockStr = req.getParameter("stock");
        String comment = req.getParameter("comment");
        BigDecimal price = new BigDecimal(priceStr);
        BigDecimal stock = new BigDecimal(stockStr);
        Fruit fruit = new Fruit(id, name, price, stock, comment);
        //执行更新
        dao.updateFruit(fruit);
        //资源跳转
//        super.processTemplate("index", req, resp);
        //此处定向  重新获取请求 获取 fruit 才能覆盖到session上面
        resp.sendRedirect("index");
    }
}
