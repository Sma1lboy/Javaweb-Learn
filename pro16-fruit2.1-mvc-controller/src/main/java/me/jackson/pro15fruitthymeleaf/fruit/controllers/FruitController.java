package me.jackson.pro15fruitthymeleaf.fruit.controllers;
import me.jackson.pro15fruitthymeleaf.fruit.dao.impl.FruitDAOImpl;
import me.jackson.pro15fruitthymeleaf.fruit.pojo.Fruit;
import me.jackson.pro15fruitthymeleaf.util.StringUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

//FruitServlet作 分包
public class FruitController{
    private FruitDAOImpl dao = new FruitDAOImpl();

    private String update(Integer id, String name, BigDecimal price, BigDecimal stock, String comment) {
        Fruit fruit = new Fruit(id, name, price, stock, comment);
        //执行更新
        dao.updateFruit(fruit);
        //资源跳转
//        resp.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }

    private String insert(Integer id, String name, BigDecimal price, BigDecimal stock, String comment){
        Fruit fruit = new Fruit(name, price, stock, comment);
        //执行更新
        dao.addFruit(fruit);
        //资源跳转
        return "redirect:fruit.do";
    }
    private String edit(Integer id, HttpServletRequest req)  {
        if(id != null) {
            Fruit fruit = dao.getFruitById(id);
            req.setAttribute("fruit", fruit);
            return "edit";
        }
        return "error";
    }
    private String delete(Integer id) {

        if(id != null) {

            dao.delById(id);
//            resp.sendRedirect("fruit.do");
            return "redirect:fruit.do";

        }
        return "error";
    }

    private String add(HttpServletRequest req) {
        //内部转发 和 thymeleaf渲染
//        super.processTemplate("add", req, resp);
        return "add";
    }

    private String index(String oper, String keyword, Integer pageNo, HttpServletRequest req) {

        HttpSession session = req.getSession();

        if(pageNo == null) {
            pageNo = 1;
        }
        if(keyword == null) {
            keyword = "";
        }

        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            session.setAttribute("keyword", keyword);
        } else {
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            }
        }
        session.setAttribute("pageNo", pageNo);

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
//        super.processTemplate("index", req, resp);
        return "index";
    }
}
