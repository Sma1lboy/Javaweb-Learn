package me.jackson.pro19filterlistener.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/8
 */

@WebFilter("*.do")
public class Filter02 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("B");
        //放行
        chain.doFilter(req,resp);
        System.out.println("B2");
    }
}
