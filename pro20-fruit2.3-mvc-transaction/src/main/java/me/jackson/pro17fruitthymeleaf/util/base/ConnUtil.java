package me.jackson.pro17fruitthymeleaf.util.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/10
 */
public class ConnUtil {

    public static final String DRIVER = "org.postgresql.Driver" ;
    public static final String URL = "jdbc:postgresql://localhost:5432/fruit";
    public static final String USER = "jacksonchen";
    public static final String PWD = "password" ;
    private static   ThreadLocal<Connection> threadLocal = new ThreadLocal<>();


    public static Connection createConnection(){
        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null ;
    }
    public static Connection getConnection(){

        Connection conn = threadLocal.get();
        if (conn == null) {
            conn = ConnUtil.createConnection();
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }
    public static void closeConnection() throws SQLException {
        Connection conn = threadLocal.get();
        if(conn == null) {
            return;
        }
        if(!conn.isClosed()) {
            conn.close();
            threadLocal.set(null);
        }
    }
}
