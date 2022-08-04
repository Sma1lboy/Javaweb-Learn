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

@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet{

    private FruitDAO dao = new FruitDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String strId = req.getParameter("id");
        if(StringUtil.isNotEmpty(strId)) {
            //如果不是空就获取到这个fruit
            int id = Integer.parseInt(strId);
            Fruit fruit = dao.getFruitById(id);
            req.setAttribute("fruit", fruit);
            //内部转发fruit然后传送到edit页面  add同理
            super.processTemplate("edit", req, resp);
        }
    }
}
