package com.yuno.service.user;

import com.mysql.jdbc.StringUtils;
import com.yuno.pojo.Role;
import com.yuno.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yuno
 * @create 2021-07-15-23:26
 */
public interface UserService {
    //用户登录
    public User login(String userCode, String userpassword);
    //修改密码
    public Boolean updatePwd(int id, String password);
    //查询记录数
    public int getUserCount(String username, int userRole);

    //查询用户列表
    public List<User> getUserList(String username, int userRole, int currenPageNo, int pageSize);

    //删除用户
    public Boolean delUserById(int userId);

    //增加用户
    public Boolean addUser(User user);

    //查询用户编码是否存在
    public Boolean userExist(String userCode);

    //查询用户信息
    public User getUserById(int id);

    //更新用户信息
    public Boolean modifyUser(User user);
}
