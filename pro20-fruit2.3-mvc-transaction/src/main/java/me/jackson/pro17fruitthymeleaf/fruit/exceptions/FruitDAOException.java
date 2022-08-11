package me.jackson.pro17fruitthymeleaf.fruit.exceptions;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/10
 */
public class FruitDAOException extends  RuntimeException{
    public FruitDAOException(String msg) {
        super(msg);
    }
}
