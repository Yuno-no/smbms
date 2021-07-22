package com.yuno.servlet;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import com.yuno.dao.user.UserDaoImpl;
import com.yuno.pojo.Role;
import com.yuno.pojo.User;
import com.yuno.service.role.RoleServiceImpl;
import com.yuno.service.user.UserService;
import com.yuno.service.user.UserServieImpl;
import com.yuno.util.Constants;
import com.yuno.util.PageSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yuno
 * @create 2021-07-18-17:36
 */

//实现servlet复用
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("savepwd") && method != null){
            this.savepwd(req, resp);
        }else if (method.equals("pwdmodify") && method != null){
            this.pwdmodify(req, resp);
        }else if (method.equals("query") && method != null){
            this.query(req, resp);
        }else if (method.equals("deluser") && method != null){
            this.deluser(req, resp);
        }else if (method.equals("add") && method != null){
            this.add(req, resp);
        }else if (method.equals("getrolelist") && method != null){
            this.getrolelist(req, resp);
        }else if (method.equals("ucexist") && method != null){
            this.ucexist(req, resp);
        }else if (method.equals("view") && method != null){
            this.view(req, resp, "userview.jsp");
        }else if (method.equals("modify") && method != null){
            this.view(req, resp, "usermodify.jsp");
        }else if (method.equals("modifyexe") && method != null){
            this.modifyexe(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    //修改密码
    public void savepwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从session里获取用户id
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");
        boolean flag = false;

        //进行判断
        if (o !=null && !StringUtils.isNullOrEmpty(newpassword)){
            UserServieImpl userServie = new UserServieImpl();
            flag = userServie.updatePwd(((User) o).getId(), newpassword);
            if (flag){
                req.setAttribute("message","修改密码成功，请退出使用新密码登录");
                //密码修改成功，移除session
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }else {
                req.setAttribute("message","密码修改失败");
            }
        }else {
            req.setAttribute("message","新密码不符合格式");
        }
        req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
    }

    //验证旧密码
    public void pwdmodify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //从session里获取用户id
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");

        //万能的map
        Map<String, String> resultMap = new HashMap<>();


        if (o == null){//Session失效或者过期了
            resultMap.put("result", "sessionerror");
        }else if(StringUtils.isNullOrEmpty(oldpassword)){
            resultMap.put("result", "error");
        }else {
            String userPassword = ((User)o).getUserPassword();//Session中的密码
            if (oldpassword.equals(userPassword)) {
                resultMap.put("result", "true");
            }else {
                resultMap.put("result", "false");
            }
        }
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        //JSONArray:阿里巴巴的json工具类,转换格式
        /*resultMap = ["result","true"]
          json格式:{"result":"true"}
        * */
        writer.write(JSONArray.toJSONString(resultMap));
        writer.flush();
        writer.close();
    }


    private void query(HttpServletRequest req, HttpServletResponse resp) throws  ServletException,IOException{
        //查询用户列表

        //从前端获取数据
        String queryUserName = req.getParameter("queryname");
        String temp = req.getParameter("queryUserRole");
        String pageIndex = req.getParameter("pageIndex");
        int queryUserRole = 0;

        //获取用户列表
        UserServieImpl userServie = new UserServieImpl();
        List<User> userList = null;
        //第一次走这个请求默认请求列表首页
        int pageSize = 5;//可以写到配置文件，方便以后修改
        int currentPageNo = 1;//默认第一页

        if (queryUserName == null){
            queryUserName = "";
        }
        if (temp != null && !temp.equals("")){
            queryUserRole = Integer.parseInt(temp);//给查询赋值：0,1,2,3
        }
        if (pageIndex != null){
            try {
                currentPageNo = Integer.parseInt(pageIndex);
            } catch (NumberFormatException e) {
                resp.sendRedirect("error.jsp");
            }
        }

        //获取用户的总数(分页：上一页和下一页)
        int totalCount = userServie.getUserCount(queryUserName, queryUserRole);
        //总页数支持
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(totalCount);
        int totalPageCount = pageSupport.getTotalPageCount();
        //控制首页和尾页
        if (totalCount < 1){//当页面下标小于1时，则默认访问第一页
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){//当前页面大于页面总数
            currentPageNo = totalPageCount;
        }

        //获取用户列表展示
        userList = userServie.getUserList(queryUserName, queryUserRole, currentPageNo, pageSize);
        req.setAttribute("userList", userList);

        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();
        req.setAttribute("roleList", roleList);
        req.setAttribute("totalPageCount", totalPageCount);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("currentPageNo", currentPageNo);
        req.setAttribute("queryUserName", queryUserName);
        req.setAttribute("queryUserRole", queryUserRole);

        //返回前端
        req.getRequestDispatcher("userlist.jsp").forward(req, resp);
    }

    //删除用户
    private void deluser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取前端参数
        String userid = req.getParameter("uid");
        Integer delId = 0;

        try {
            delId = Integer.parseInt(userid);
        } catch (NumberFormatException e) {
            delId = 0;
        }
        HashMap<String, String> resultMap = new HashMap<>();
        if (delId <= 0){
            resultMap.put("delResult", "notexist");
        }else {
            UserServieImpl userServie = new UserServieImpl();
            if (userServie.delUserById(delId)) {
                resultMap.put("delResult", "true");
            }else {
                resultMap.put("delResult", "false");
            }
        }

        //将resultMap转换成json对象输出
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(resultMap));
        writer.flush();
        writer.close();
    }

    //增加用户
    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String userCode = req.getParameter("userCode");
        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPassword");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String userRole = req.getParameter("userRole");

        User user = new User();
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setGender(Integer.valueOf(gender));
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(Integer.parseInt(userRole));
        try {
            user.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setCreationDate(new Date());
        user.setCreatedBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());

        UserServieImpl userServie = new UserServieImpl();
        if (userServie.addUser(user)){
            resp.sendRedirect(req.getContextPath() + "/jsp/user.do?method=query");
        }else {
            req.getRequestDispatcher("useradd.jsp").forward(req, resp);
        }
    }

    //获取角色列表
    private void getrolelist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(roleList));
        writer.flush();
        writer.close();
    }

    //判断用户名是否重复
    private void ucexist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userCode = req.getParameter("userCode");
        HashMap<String, String> resultMap = new HashMap<>();

        if (!StringUtils.isNullOrEmpty(userCode)){
            UserServieImpl userServie = new UserServieImpl();
            if (userServie.userExist(userCode)){
                resultMap.put("userCode", "exist");
            }
        }else {
            resultMap.put("userCode", "error");
        }
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(resultMap));
        writer.flush();
        writer.close();
    }

    //查看用户信息
    private void view(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        User user = new User();

        if (!StringUtils.isNullOrEmpty(uid)){
            UserServieImpl userServie = new UserServieImpl();
            user = userServie.getUserById(Integer.parseInt(uid));
        }
        req.setAttribute("user", user);
        req.getRequestDispatcher(url).forward(req, resp);
    }

    private void modifyexe(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String uid = req.getParameter("uid");
        String userName = req.getParameter("userName");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String userRole = req.getParameter("userRole");

        User user = new User();
        user.setId(Integer.parseInt(uid));
        user.setUserName(userName);
        user.setGender(Integer.parseInt(gender));
        try {
            user.setBirthday(new SimpleDateFormat("YYYY-MM-dd").parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(Integer.parseInt(userRole));
        user.setModifyBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        user.setModifyDate(new Date());

        UserServieImpl userServie = new UserServieImpl();

        if (userServie.modifyUser(user)){
            resp.sendRedirect(req.getContextPath()+"/jsp/user.do?method=query");
        }else {
            req.getRequestDispatcher("usermodify.jsp").forward(req, resp);
        }
    }

}
