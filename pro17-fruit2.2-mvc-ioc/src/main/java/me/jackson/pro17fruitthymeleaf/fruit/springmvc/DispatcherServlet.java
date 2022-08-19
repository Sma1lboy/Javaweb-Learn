package me.jackson.pro17fruitthymeleaf.fruit.springmvc;

import me.jackson.pro17fruitthymeleaf.util.StringUtil;
import me.jackson.pro17fruitthymeleaf.util.io.BeanFactory;
import me.jackson.pro17fruitthymeleaf.util.io.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    public DispatcherServlet() {

    }
    public void init() throws ServletException {
        super.init();
        beanFactory = new ClassPathXmlApplicationContext();
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
        Object controllerBeanObj = beanFactory.getBean(servletPath);
        //通过xml获取到该对象的值后， 再通过object class 的反射到该位置的 method

        String operate = req.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }

        try {
            //methods的原因是 当我们获取特定的method的时候 我们需要对method参数传入特定的class
            //但是我们不知道我们访问的是哪一个method， 每个method传入的值不一样， 所以我们需要通过iteration去找到
            //我们的method

            //通过反射 获取到current controller组件
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for(Method method : methods) {
                //找到method
                if(operate.equals(method.getName())) {
                    //1.统一获取请求参数
                    //获取当前方法的参数数组
                    Parameter[] parameters = method.getParameters();
                    //为每个参数数组填入相应的value
                    Object[] parametersValues = new Object[parameters.length];
                    //go through every parameter index
                    for (int i = 0; i < parameters.length; i++) {
                        //获取参数
                        Parameter parameter = parameters[i];
                        //获取参数的名字
                        //注意 需要通过java compiler 添加参数 -parameters 去获取参数的世纪名字
                        //要不就会显示"arg"+ [i]
                        //加了以后会显示参数 类 地址 e.g java.lang.Integer
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
                            //注意 获取到的value都是 String
                            String parameterValue = req.getParameter(parameterName);
                            //获取到String 但不一定能直接往values里面放
                            String typeName = parameter.getType().getName();

                            //获取的value不一定是String 所以我们需要把它转换成我们需要的类型
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
                    //call method 执行方法
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
