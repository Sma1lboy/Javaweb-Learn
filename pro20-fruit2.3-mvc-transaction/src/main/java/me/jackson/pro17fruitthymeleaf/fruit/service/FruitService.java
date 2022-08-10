package me.jackson.pro17fruitthymeleaf.fruit.service;

import me.jackson.pro17fruitthymeleaf.fruit.pojo.Fruit;

import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/8
 */
public interface FruitService {
    //展示
    List<Fruit> getFruitList(String keyword, Integer pageNo);

    void addFruit(Fruit fruit);

    Fruit getFruitById(Integer id);

    void delFruit(Integer id);

    Long getPageCount(String keyword);

    void updateFruit(Fruit fruit);

}
