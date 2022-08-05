package me.jackson.pro13fruitthymeleaf.connection;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidTest {
    @Test
    public void testGetConnection() throws Exception {


        Properties pros = new Properties();
        FileInputStream is = new FileInputStream("src/main/resources/druid.properties");
        pros.load(is);
        DataSource source = DruidDataSourceFactory.createDataSource(pros);

        Connection conn = source.getConnection();
        System.out.println(conn);


    }
}
