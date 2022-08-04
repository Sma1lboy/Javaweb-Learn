package me.jackson.pro10fruitthymeleaf.fruit.dao;

import me.jackson.pro10fruitthymeleaf.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    //查询获取制定页码上的库存列表信息, 每页显示5条
    List<Fruit> getFruitList(Integer pageNo);

    Fruit getFruitById(int id);
    //新增库存
    boolean addFruit(Fruit fruit);

    //修改库存
    boolean updateFruit(Fruit fruit);

    //根据名称查询特定库存
    Fruit getFruitByFname(String fname);

    //删除特定库存记录
    boolean delFruit(String fname);

    void delById(int id);

    //查询总记录条数
    long getFruitCount();
}
