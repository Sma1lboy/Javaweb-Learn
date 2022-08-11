package me.jackson.pro17fruitthymeleaf.fruit.dao.impl;

import me.jackson.pro17fruitthymeleaf.fruit.dao.FruitDAO;
import me.jackson.pro17fruitthymeleaf.fruit.dao.base.BaseDAO;
import me.jackson.pro17fruitthymeleaf.fruit.exceptions.FruitDAOException;
import me.jackson.pro17fruitthymeleaf.fruit.pojo.Fruit;

import java.sql.SQLException;
import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {

    public FruitDAOImpl() throws ClassNotFoundException {
    }

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {

        try {
            return super.executeQuery("select fruit_id id, name, price, stock, comment from fruit where name like ? or comment like ? order by fruit_id limit 5 offset ?", "%"+keyword+"%", "%"+keyword+"%", (pageNo - 1) * 5);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FruitDAOException("Fruit DAO issue");
        }
    }

    @Override
    public Fruit getFruitById(int id) {
        try {
            return super.load("select fruit_id id, name, price, stock, comment from fruit where fruit_id = ?", id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FruitDAOException("Fruit DAO issue");
        }
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        int count = 0;
        try {
            String sql = "insert into fruit(name, price, stock, comment) values(?,?,?,?)";
            count = super.executeUpdate(sql,fruit.getName(),fruit.getPrice(),fruit.getStock(),fruit.getComment());
            //insert语句返回的是自增列的值，而不是影响行数
            //System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FruitDAOException("Fruit DAO issue");
        }
        return count>0;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        try {
            String sql = "update fruit set name = ?, price = ?, stock = ?, comment = ? where fruit_id = ? " ;
            return super.executeUpdate(sql,fruit.getName(), fruit.getPrice(), fruit.getStock(), fruit.getComment(),fruit.getId())>0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FruitDAOException("Fruit DAO issue");
        }
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        try {
            return super.load("select * from fruit where name like ? ",fname);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FruitDAOException("Fruit DAO issue");
        }
    }

    @Override
    public boolean delFruit(String fname) {
        try {
            String sql = "delete from fruit where name like ? " ;
            return super.executeUpdate(sql,fname)>0;
        }catch (Exception e) {
            e.printStackTrace();
            throw new FruitDAOException("Fruit DAO issue");
        }
    }

    @Override
    public void delById(int id) {
        try {
            String sql = "delete from fruit where fruit_id = ?";
            super.executeUpdate(sql, id);
        }catch (Exception e) {
            e.printStackTrace();
            throw new FruitDAOException("Fruit DAO issue");
        }
    }



    @Override
    public long getFruitCount(String keyword) {
        try {
            return (Long) super.executeComplexQuery("select count(*) from fruit where name like ? or comment like ?", "%"+keyword+"%", "%"+keyword+"%")[0];
        } catch (Exception e) {
            e.printStackTrace();
            throw new FruitDAOException("Fruit DAO issue");
        }
    }

}