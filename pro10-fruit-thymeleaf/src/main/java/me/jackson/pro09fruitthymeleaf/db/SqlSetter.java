package me.jackson.pro09fruitthymeleaf.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlSetter {
    private String dbType = "postgresql";
    private String address = "localhost:5432";
    private String url = "jdbc:" + dbType + "://" + address + "/fruit";
    Connection connection;
    public SqlSetter() {
        try{

            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        startConnect();
    }
    private void startConnect() {
        if(!isConnection()) {
            try {

                DriverManager.registerDriver(new org.postgresql.Driver());

                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/fruit",
                        "jacksonchen",
                        "password");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean isConnection(){
        return connection != null;
    }

    public Connection getConnection() {
        return connection;
    }
    public void closeConnection() {
        if(isConnection()) {
            try {
            connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
