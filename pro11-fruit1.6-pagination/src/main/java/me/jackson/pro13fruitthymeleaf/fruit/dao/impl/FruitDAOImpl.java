package me.jackson.pro13fruitthymeleaf.fruit.dao.impl;

import me.jackson.pro13fruitthymeleaf.fruit.dao.FruitDAO;
import me.jackson.pro13fruitthymeleaf.fruit.dao.base.BaseDAO;
import me.jackson.pro13fruitthymeleaf.fruit.pojo.Fruit;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {
    @Override
    public List<Fruit> getFruitList(Integer pageNo) {
        return super.executeQuery("select fruit_id id, name, price, stock, comment from fruit order by fruit_id limit 5 offset ?", (pageNo - 1) * 5);
    }

    @Override
    public Fruit getFruitById(int id) {
        return super.load("select fruit_id id, name, price, stock, comment from fruit where fruit_id = ?", id);
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        String sql = "insert into fruit(name, price, stock, comment) values(?,?,?,?)";
        int count = super.executeUpdate(sql,fruit.getName(),fruit.getPrice(),fruit.getStock(),fruit.getComment()) ;
        //insert语句返回的是自增列的值，而不是影响行数
        //System.out.println(count);
        return count>0;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        String sql = "update fruit set name = ?, price = ?, stock = ?, comment = ? where fruit_id = ? " ;
        return super.executeUpdate(sql,fruit.getName(), fruit.getPrice(), fruit.getStock(), fruit.getComment(),fruit.getId())>0;
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        return super.load("select * from fruit where name like ? ",fname);
    }

    @Override
    public boolean delFruit(String fname) {
        String sql = "delete from fruit where name like ? " ;
        return super.executeUpdate(sql,fname)>0;
    }

    @Override
    public void delById(int id) {
        String sql = "delete from fruit where fruit_id = ?";
        super.executeUpdate(sql, id);
    }

    @Override
    public long getFruitCount() {
        return (Long) super.executeComplexQuery("select count(*) from fruit")[0];
    }
}