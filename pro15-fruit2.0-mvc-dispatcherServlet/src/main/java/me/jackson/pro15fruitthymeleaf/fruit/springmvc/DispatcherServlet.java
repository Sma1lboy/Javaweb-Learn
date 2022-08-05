package me.jackson.pro15fruitthymeleaf.fruit.springmvc;

import me.jackson.pro15fruitthymeleaf.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet{

    private Map<String, Object> beanMap = new HashMap<>();

    public DispatcherServlet() {

    }
    public void init() {
        //加载配置文件  读取XML类内元素 再获取元素的值
        try {
            URL resource = this.getClass().getClassLoader().getResource("/applicationContext.xml");
            FileInputStream is = new FileInputStream(resource.getPath());

            //使用documentBuilderFactory to create DocumentBuilder
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            //parse input stream
            Document document = documentBuilder.parse(is);
            //get beanList
            NodeList beanNodeLists = document.getElementsByTagName("bean");

            for(int i = 0; i < beanNodeLists.getLength(); i++) {
                Node beanNode = beanNodeLists.item(i);
                if(beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    //从现在的tag中抓取id 和class属性
                    String beanId = beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");
                    Class controllerBeanClass = Class.forName(className);
                    Object beanObj = controllerBeanClass.getDeclaredConstructor().newInstance();

                    Method setServletContextMethod = controllerBeanClass.getDeclaredMethod("setServletContext", ServletContext.class);

                    System.out.println(this.getServletContext());
                    setServletContextMethod.invoke(beanObj, this.getServletContext());


                    beanMap.put(beanId, beanObj);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");


        //url is localhost:5432/pro15/hello.do
        //servletPath is /hello.do
        //1: makes /hello.do -> hello
        //2: hello -> HelloController

        String servletPath = req.getServletPath();
        // delete the slash so
        servletPath = servletPath.substring(1);
        //now it's hello.do; delete the .do
        servletPath = servletPath.substring(0, servletPath.length() - 3);

        Object controllerBeanObj = beanMap.get(servletPath);



        //通过xml获取到该对象的值后， 再通过object class 的反射到该位置的 method

        String operate = req.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }

        try {
            Method method = controllerBeanObj.getClass().getDeclaredMethod(operate, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(controllerBeanObj, req, resp);
//            if(method != null) {
//                method.invoke(this, req, resp);
//            } else {
//                throw  new RuntimeException("operate value invalid")
//            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
