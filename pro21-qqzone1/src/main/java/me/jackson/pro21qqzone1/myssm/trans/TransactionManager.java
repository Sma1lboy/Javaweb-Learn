package me.jackson.pro21qqzone1.myssm.trans;

import me.jackson.pro21qqzone1.myssm.util.JDBCUtils;

import java.sql.Connection;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/10
 */
public class TransactionManager {
    private  ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    //open transaction
    public static void beginTransaction() throws Exception {
        JDBCUtils.getConnection().setAutoCommit(false);

    }
    //commit
    public static void commit() throws Exception {
        JDBCUtils.getConnection().commit();
        JDBCUtils.getConnection().close();

    }
    //rollback
    public static void rollback() throws Exception {
        JDBCUtils.getConnection().rollback();
    }
}
