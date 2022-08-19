package me.jackson.pro17fruitthymeleaf.util.filters;

import me.jackson.pro17fruitthymeleaf.util.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/10
 */
@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            TransactionManager.beginTransaction();
//            System.out.println("开启事务");
            chain.doFilter(request, response);
            TransactionManager.commit();
//            System.out.println("提交事务");
        } catch (Exception e) {
            try {

                TransactionManager.rollback();
//                System.out.println("回滚事务");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }
}
