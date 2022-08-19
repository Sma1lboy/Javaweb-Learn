package me.jackson.pro21qqzone1.myssm.util;


import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * operation DB util.
 * @author jacksonchen
 * @version
 *
 *
 */
public class JDBCUtils {

    /**
     * get connection from db and get info from config
     * @return connection of the db
     * @throws Exception if connection is null
     */
    public static Connection getConnection() throws Exception {
        //get info from config
//        FileReader reader = new FileReader("/jdbc.properties");
        InputStream reader = Class.forName("me.jackson.pro21qqzone1.myssm.util.JDBCUtils").getResourceAsStream("/jdbc.properties");
        Properties properties = new Properties();

        properties.load(reader);
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driverClass");

        //2. loading class
        Class.forName(driver);
        //3. getConnection
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
    @Test
    public void testGetConnection(){
        try {
            Connection conn = getConnection();
            System.out.println(conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
}

