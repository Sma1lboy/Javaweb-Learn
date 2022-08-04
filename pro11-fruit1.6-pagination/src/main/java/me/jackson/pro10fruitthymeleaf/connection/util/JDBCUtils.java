package me.jackson.pro10fruitthymeleaf.connection.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * using connection pool's utils class
 */
public class JDBCUtils {

    //获取c3p0数据库连接池
    //数据库连接池只需要一个
    private static ComboPooledDataSource source1 = new ComboPooledDataSource("helloc3p0");




    private static DataSource source3;
    static  {
        try {
            Properties pros = new Properties();
            FileInputStream is = new FileInputStream("src/main/resources/druid.properties");
            pros.load(is);
            source3 = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Druid connection pool
     */
    public static Connection getConnection3() {
        Connection conn = null;
        try {
            conn = source3.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    /**
     * Close connection and statement.
     * @param con connection
     * @param ps statement
     */
    public static void closeResource(Connection con, Statement ps){
        try {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeResource(Connection con, Statement ps, ResultSet rs){
        try {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if(rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用dbutils.jar提供的工具类 实现关闭
     * @param con
     * @param ps
     * @param rs
     */
    public static void closeResource1(Connection con, Statement ps, ResultSet rs){
        try {
            DbUtils.close(con);
            DbUtils.close(ps);
            DbUtils.close(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
