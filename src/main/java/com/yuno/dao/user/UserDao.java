package com.yuno.dao.user;

import com.yuno.pojo.Role;
import com.yuno.pojo.User;
import com.yuno.util.Constants;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author Yuno
 * @create 2021-07-15-22:53
 */
public interface UserDao {
    //得到要登录的用户
    public User getloginuser(Connection connection, String userCode) throws SQLException;

    //修改密码
    public int updatePwd(Connection connection, int id, String password) throws SQLException;

    //根据用户名或者角色名查询用户总数
    public int getUserCount(Connection connection, String username, int userRole) throws SQLException;

    //用户列表查询
    public List<User> getUserList(Connection connection, String username, int userRole, int currentPageNo, int pageSize) throws SQLException;

    //删除用户
    public int userDel(Connection connection, int userid) throws SQLException;

    //增加用户
    public int userAdd(Connection connection, User user) throws SQLException;

    //查询用户编码是否存在
    public Boolean userExist(Connection connection, String userCode) throws SQLException;

    //查看用户信息
    public User getUserById(Connection connection, int id) throws SQLException;

    //更新用户信息
    public int modifyUser(Connection connection, User user) throws SQLException;
}
