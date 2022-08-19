package me.jackson.pro17fruitthymeleaf.util.filters;

import me.jackson.pro17fruitthymeleaf.util.StringUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/8
 */
@WebFilter(
        urlPatterns = {"*.do"},
        initParams = {
                @WebInitParam(
                        name = "encoding",
                        value = "UTF-8")
        }
)
public class CharacterEncodingFilter implements Filter {

    private String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingStr = filterConfig.getInitParameter("encoding");
        if(StringUtil.isNotEmpty(encodingStr)) {
            this.encoding = encodingStr;
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ((HttpServletRequest) request).setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }
}
