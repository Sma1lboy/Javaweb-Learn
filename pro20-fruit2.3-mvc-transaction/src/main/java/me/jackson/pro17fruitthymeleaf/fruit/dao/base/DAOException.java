package me.jackson.pro17fruitthymeleaf.fruit.dao.base;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/10
 */
public class DAOException extends  RuntimeException{
    public DAOException(String msg) {
        super(msg);
    }
}
