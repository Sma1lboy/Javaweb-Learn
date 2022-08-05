package me.jackson.pro13fruitthymeleaf.fruit.servlets;


import me.jackson.pro13fruitthymeleaf.fruit.dao.impl.FruitDAOImpl;
import me.jackson.pro13fruitthymeleaf.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/del.do")
public class DeleteServlet extends ViewBaseServlet{
    FruitDAOImpl dao = new FruitDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if(StringUtil.isNotEmpty(idStr)) {
            int id = Integer.parseInt(idStr);
            dao.delById(id);

            resp.sendRedirect("index");
        }
    }
}
