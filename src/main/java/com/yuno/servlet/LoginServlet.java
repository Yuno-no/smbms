package com.yuno.servlet;

import com.yuno.pojo.User;
import com.yuno.service.user.UserServieImpl;
import com.yuno.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yuno
 * @create 2021-07-18-15:55
 */
public class LoginServlet extends HttpServlet {
    //控制层调用业务层代码

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户名和密码
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        //和数据中的密码进行校验
        UserServieImpl userServie = new UserServieImpl();
        User user = userServie.login(userCode, userPassword);//这里已经把登录的人查出来了

        if (user!=null){//若用户存在
            if (user.getUserPassword().equals(userPassword)){
                //将用户的信息放到session用
                req.getSession().setAttribute(Constants.USER_SESSION,user);
                //跳转到主页
                resp.sendRedirect("jsp/frame.jsp");
            }else {
                req.setAttribute("error","用户名或者密码错误");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }

        }else {//若用户不存在
            req.setAttribute("error","用户不存在");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
