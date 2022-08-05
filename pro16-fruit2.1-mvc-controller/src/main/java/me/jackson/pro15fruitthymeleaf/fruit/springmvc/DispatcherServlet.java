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
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet{

    private Map<String, Object> beanMap = new HashMap<>();

    public DispatcherServlet() {

    }
    public void init() throws ServletException {
        super.init();
        //加载配置文件  读取XML类内元素列表 再获取元素的值 把各个bean元素的id和class 对应起来
        //
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
                    //为该类反射实例化
                    Class controllerBeanClass = Class.forName(className);
                    Object beanObj = controllerBeanClass.getDeclaredConstructor().newInstance();

                    //放入到map当中
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

        //获取到client填入的地址
        //然后通过优化获得准确地址
        String servletPath = req.getServletPath();
        // delete the slash so
        servletPath = servletPath.substring(1);
        //now it's hello.do; delete the .do
        servletPath = servletPath.substring(0, servletPath.length() - 3);

        //通过名字找到对应的controller
        Object controllerBeanObj = beanMap.get(servletPath);

        //通过xml获取到该对象的值后， 再通过object class 的反射到该位置的 method

        String operate = req.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }

        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for(Method method : methods) {
                if(operate.equals(method.getName())) {
                    //1.统一获取请求参数
                    //获取当前方法的参数数组

                    Parameter[] parameters = method.getParameters();
//            parameters[0].getName()
                    Object[] parametersValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        //参数
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();

                        //如果参数名是request response session,那么就不是通过请求参数获取的
                        if(parameterName.equals("req")) {
                            parametersValues[i] = req;
                        } else if("session".equals(parameterName)) {
                            parametersValues[i] = req.getSession();
                        } else if("resp".equals(parameterName)) {
                            parametersValues[i] = resp;
                        } else {
                            //从请求中获取跟参数对应的value
                            String parameterValue = req.getParameter(parameterName);
                            //获取到String 但不一定能直接往values里面放
                            String typeName = parameter.getType().getName();

                            Object parameterObj = parameterValue;

                            if(parameterObj != null) {
                                if ("java.lang.Integer".equals(typeName)) {
                                    parameterObj = Integer.parseInt(parameterValue);
                                } else if ("java.math.BigDecimal".equals(typeName)) {
                                    parameterObj = new BigDecimal(parameterValue);
                                }
                            }
                            parametersValues[i] = parameterObj;
                        }
                    }
                    //2
                    //通过这次请求的 地址的controller和参数operate 找到 对应的controller的method
                    //Controller组件中的方法调用
                    //把private 打开
                    method.setAccessible(true);
                    //call method
                    Object methodReturnValueObj = method.invoke(controllerBeanObj, parametersValues);
                    //获取method 返回的值 判断进行客户端重定向还是内部转发
                    //again with thymelaef 内部转发都是thymeleaf渲染， 客户端重定向不是
                    String methodReturnValueStr = (String) methodReturnValueObj;

                    //3.视图处理
                    //核心控制器去进行转发和重定向
                    if(methodReturnValueStr.startsWith("redirect:")) {  //e.g redirect:fruit.do
                        String redirectStr = methodReturnValueStr.substring("redirect:".length());
                        resp.sendRedirect(redirectStr);
                    } else {
                        super.processTemplate(methodReturnValueStr, req, resp);
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
