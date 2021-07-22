package com.yuno.filter;

import com.yuno.pojo.User;
import com.yuno.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;

/**
 * @author Yuno
 * @create 2021-07-18-16:42
 */
public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //从session中获取用户
        User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);

        if (user == null){//说明用户已被移除或者注销或者未登录
            response.sendRedirect("/smbms/error.jsp");
        }else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
