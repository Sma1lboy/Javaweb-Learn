package me.jackson.pro17fruitthymeleaf.fruit.service.impl;

import me.jackson.pro17fruitthymeleaf.fruit.dao.FruitDAO;
import me.jackson.pro17fruitthymeleaf.fruit.pojo.Fruit;
import me.jackson.pro17fruitthymeleaf.fruit.service.FruitService;

import java.util.List;

/**
 * 因为BO层过于简单 所以不能非常清楚的跟 DAO层分开 其实是不一样的
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/8
 */
public class FruitServiceImpl implements FruitService {
    private FruitDAO dao = null;
    private final int PAGE_COUNT = 5;
    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {

        return dao.getFruitList(keyword, pageNo);
    }


    @Override
    public void addFruit(Fruit fruit) {
        dao.addFruit(fruit);
    }

    @Override
    public Fruit getFruitById(Integer id) {
        return dao.getFruitById(id);
    }

    @Override
    public void delFruit(Integer id) {
        dao.delById(id);
    }

    @Override
    public Long getPageCount(String keyword) {
        long fruitCount = dao.getFruitCount(keyword);
        long pageCount = fruitCount / PAGE_COUNT;
        if (fruitCount % 5 != 0) {
            pageCount++;
        }
        return pageCount;
    }

    @Override
    public void updateFruit(Fruit fruit) {
        dao.updateFruit(fruit);
    }
}
