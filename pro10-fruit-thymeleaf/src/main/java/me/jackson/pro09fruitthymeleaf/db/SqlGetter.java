package me.jackson.pro09fruitthymeleaf.db;

import me.jackson.pro09fruitthymeleaf.db.Fruit.Fruit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlGetter {
    private SqlSetter sqlSetter;


    Connection connection;
    public SqlGetter(SqlSetter sqlSetter) {
        sqlSetter = sqlSetter;
        connection = sqlSetter.getConnection();
    }
//    public void execute(String query) {
//        try {
//            ResultSet resultSet = connection.createStatement().executeQuery(query);
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public void addFruit(String name, double price, double stock, String comment) {
        String url = "insert into fruit(name, price, stock, comment) " +
                "values('" + name + "', " + price + "," + stock + ", '" + comment + "' ) ";
        try {
            //insert into fruit(name, price, stock, comment)
            connection.createStatement().execute(url);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //selet data from db, and assgin to each fruit
    public List<Fruit> getAllFruit(){
        List<Fruit> fruits = new ArrayList<>();
        try {
            ResultSet rs = selectAll();
            if(rs == null) {
                throw new SQLException();
            }
            while (rs.next()) {
                String name = rs.getString(2);
                double price = rs.getDouble(3);
                double stock = rs.getDouble(4);
                String comment = rs.getString(5);
                Fruit currFruit = new Fruit(name, price, stock, comment);
                fruits.add(currFruit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fruits;
    }
    public ResultSet selectAll() {
        try {
            return connection.createStatement().executeQuery("select * from fruit");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
