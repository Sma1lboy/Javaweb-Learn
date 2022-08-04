package me.jackson.pro10fruitthymeleaf.fruit.servlets;

import me.jackson.pro10fruitthymeleaf.fruit.dao.FruitDAO;
import me.jackson.pro10fruitthymeleaf.fruit.dao.impl.FruitDAOImpl;
import me.jackson.pro10fruitthymeleaf.fruit.pojo.Fruit;
import me.jackson.pro10fruitthymeleaf.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet{

    private FruitDAOImpl dao = new FruitDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //内部转发 和 thymeleaf渲染
        super.processTemplate("add", req, resp);
    }

}
