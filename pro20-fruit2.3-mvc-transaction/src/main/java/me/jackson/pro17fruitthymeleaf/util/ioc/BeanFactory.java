package me.jackson.pro17fruitthymeleaf.util.ioc;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/8
 */
public interface BeanFactory {
    Object getBean(String id);
}
