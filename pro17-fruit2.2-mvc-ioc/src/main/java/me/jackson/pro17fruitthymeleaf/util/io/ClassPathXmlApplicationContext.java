package me.jackson.pro17fruitthymeleaf.util.io;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/8
 */
public class ClassPathXmlApplicationContext implements  BeanFactory{
    private Map<String, Object> beanMap = new HashMap<>();

    public ClassPathXmlApplicationContext(){
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
                    //创建bean实例
                    Class beanClass = Class.forName(className);
                    Object beanObj = beanClass.getDeclaredConstructor().newInstance();

                    //放入到map当中
                    beanMap.put(beanId, beanObj);
                    //bean和bnea的依赖关系还没有设置
                }
            }
            //5. 组装bean之间的依赖关系
            for(int i = 0; i < beanNodeLists.getLength(); i++) {
                Node beanNode = beanNodeLists.item(i);
                if(beanNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    NodeList beanChildNodeList = beanElement.getChildNodes();
                    for (int j = 0; j < beanChildNodeList.getLength(); j++) {
                        Node beanChildNode = beanChildNodeList.item(j);
                        if(beanChildNode.getNodeType()== Node.ELEMENT_NODE && "property".equals(beanChildNode.getNodeName())) {
                            //强转到Element 其实转不转无所谓 为了代码规范得转
                            Element propertyElement = (Element) beanChildNode;
                            String propertyName = propertyElement.getAttribute("name");
                            String propertyRef = propertyElement.getAttribute("ref");
                            //找到ref对应的实例，
                            Object refObj = beanMap.get(propertyRef);
                            //2. 将ref  设置到bean对应的实例property上面去
                            Object beanObj = beanMap.get(beanId);
                            Field field = beanObj.getClass().getDeclaredField(propertyName);
                            field.setAccessible(true);
                            field.set(beanObj, refObj);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
