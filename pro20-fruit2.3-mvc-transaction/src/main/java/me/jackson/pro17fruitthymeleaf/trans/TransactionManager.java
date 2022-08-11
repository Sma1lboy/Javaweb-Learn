package me.jackson.pro17fruitthymeleaf.trans;

import me.jackson.pro17fruitthymeleaf.fruit.dao.base.ConnUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/10
 */
public class TransactionManager {
    private  ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    //open transaction
    public static void beginTransaction() throws SQLException {
        ConnUtil.getConnection().setAutoCommit(false);

    }
    //commit
    public static void commit() throws SQLException {
        ConnUtil.getConnection().commit();
        ConnUtil.closeConnection();

    }
    //rollback
    public static void rollback() throws SQLException {
        ConnUtil.getConnection().rollback();
    }
}
