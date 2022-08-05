package me.jackson.pro13fruitthymeleaf.fruit.servlets;


import me.jackson.pro13fruitthymeleaf.fruit.dao.impl.FruitDAOImpl;
import me.jackson.pro13fruitthymeleaf.fruit.pojo.Fruit;
import me.jackson.pro13fruitthymeleaf.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = 1;


        HttpSession session = req.getSession();
        String oper = req.getParameter("oper");
        String keyword = null;
        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            //说明是查询操作
            //pageNo应该是1 keyword从请求参数中获取
            keyword = req.getParameter("keyword");
            if(keyword.isEmpty()) {
                keyword ="";
            }
            session.setAttribute("keyword",keyword);
        } else {
            String pageNoStr = req.getParameter("pageNo");
            if(StringUtil.isNotEmpty(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr);
            }

            //说明不是点击表单过来的查询
            //keyword应该从session作用域中获取
            Object keywordObj =  session.getAttribute("keyword");
            if(keywordObj != null) {
                keyword = (String) keywordObj;
            } else  {
                keyword = "";
            }
        }


        session.setAttribute("pageNo", pageNo);


        //before going to index.html page
        //we have to send data into session
        FruitDAOImpl dao = new FruitDAOImpl();
        List<Fruit> fruits = dao.getFruitList(keyword,pageNo);

        session.setAttribute("fruitList", fruits);

        long fruitCount = dao.getFruitCount(keyword);
        long pageCount = fruitCount / 5;
        if(fruitCount % 5 != 0) {
            pageCount++;
        }
        session.setAttribute("pageCount", pageCount);

        //视图名称是index
        //thymeleaf会将这个视图名称对应物理视图名称上
        //逻辑视图 ： index
        //物理视图： view-prefix + 逻辑视图名称 + view-suffix
        //真实的是： /index.html
        super.processTemplate("index",req, resp);
    }
}
