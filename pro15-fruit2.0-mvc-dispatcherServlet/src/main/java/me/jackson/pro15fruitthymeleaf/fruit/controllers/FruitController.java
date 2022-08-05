package me.jackson.pro15fruitthymeleaf.fruit.controllers;

import me.jackson.pro15fruitthymeleaf.fruit.dao.impl.FruitDAOImpl;
import me.jackson.pro15fruitthymeleaf.fruit.pojo.Fruit;
import me.jackson.pro15fruitthymeleaf.fruit.springmvc.ViewBaseServlet;
import me.jackson.pro15fruitthymeleaf.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

//FruitServlet作 分包
public class FruitController extends ViewBaseServlet {
    private FruitDAOImpl dao = new FruitDAOImpl();
    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) throws ServletException {
        this.servletContext = servletContext;
        super.init(servletContext);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        resp.sendRedirect("fruit.do");
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        resp.sendRedirect("fruit.do");
    }
    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if(StringUtil.isNotEmpty(idStr)) {
            int id = Integer.parseInt(idStr);
            dao.delById(id);

            resp.sendRedirect("fruit.do");
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //内部转发 和 thymeleaf渲染
        super.processTemplate("add", req, resp);
    }

    private void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession();

        //get current page number
        int pageNo = 1;


        //if oper != null说明我们是点击查询按钮过来的
        //if oper == null 说明我们不是通过表单的查询按钮过来的
        String oper = req.getParameter("oper");
        String keyword = "";
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            //说明是查询操作
            //设置pageNo为默认情况1
//            pageNo = 1;
            // keyword从请求参数中获取from request 保存域
            keyword = req.getParameter("keyword");
            //自己理解的是 传参 keyword传了 但是传了个"" 所以不需要判断
//            if(keyword.isEmpty()) {
//                keyword ="";
//            }
//            获取keyword以后 保存到会话保存域里面 当翻页的时候会根据keyword搜索
//            默认情况下是keyword是空，所以默认情况下是搜索所有东西
            session.setAttribute("keyword", keyword);
        } else {

            //如果不是查询操作
            //每次获取列表之前
            //我们得先确定 页数 和 关键字

//            页数
            String pageNoStr = req.getParameter("pageNo");
            if (StringUtil.isNotEmpty(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr);
            }
//             关键字
            //说明不是点击表单过来的查询
            //keyword从session作用域中获取
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            }
        }


        session.setAttribute("pageNo", pageNo);


        //before going to index.html page
        //we have to send data into session
        FruitDAOImpl dao = new FruitDAOImpl();
        List<Fruit> fruits = dao.getFruitList(keyword, pageNo);

        session.setAttribute("fruitList", fruits);

        long fruitCount = dao.getFruitCount(keyword);
        long pageCount = fruitCount / 5;
        if (fruitCount % 5 != 0) {
            pageCount++;
        }
        session.setAttribute("pageCount", pageCount);

        //视图名称是index
        //thymeleaf会将这个视图名称对应物理视图名称上
        //逻辑视图 ： index
        //物理视图： view-prefix + 逻辑视图名称 + view-suffix
        //真实的是： /index.html
        super.processTemplate("index", req, resp);
    }
}
