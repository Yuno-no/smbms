package com.yuno.service.user;

import com.yuno.dao.BaseDao;
import com.yuno.dao.user.UserDao;
import com.yuno.dao.user.UserDaoImpl;
import com.yuno.pojo.User;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuno
 * @create 2021-07-15-23:36
 */
public class UserServieImpl implements UserService{
    //业务层都会调用dao层，所以我们要引入dao层
    private UserDao userDao;
    public UserServieImpl(){
        userDao = new UserDaoImpl();
    }


    @Override
    public User login(String userCode, String userpassword) {
        Connection connection = null;
        User user = null;
        try {
            connection = BaseDao.getConnection();
            //通过业务层调用对应的具体的数据库操作
            user = userDao.getloginuser(connection, userCode);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return user;
    }

    @Override
    public Boolean updatePwd(int id, String password) {
        Connection connection = null;
        Boolean flag = false;

        try {
            connection = BaseDao.getConnection();
            if (userDao.updatePwd(connection,id,password) > 0){
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public int getUserCount(String username, int userRole) {
        Connection connection = null;
        int count = 0;

        try {
            connection = BaseDao.getConnection();
            count = userDao.getUserCount(connection, username, userRole);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return count;
    }

    @Override
    public List<User> getUserList(String username, int userRole, int currenPageNo, int pageSize) {
        Connection connection = null;
        List<User> userList = new ArrayList<>();
        connection = BaseDao.getConnection();
        try {
            userList = userDao.getUserList(connection, username, userRole, currenPageNo, pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return userList;
    }

    @Override
    public Boolean delUserById(int userId) {
        Connection connection = null;
        Boolean flag = false;


        try {
            //开启jdbc事务
            connection.setAutoCommit(false);
            connection = BaseDao.getConnection();
            if (userDao.userDel(connection, userId) > 0){
                flag = true;
            }
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public Boolean addUser(User user) {
        Connection connection = null;
        Boolean flag = false;

        try {
            connection = BaseDao.getConnection();
            //开启jdbc事务
            connection.setAutoCommit(false);
            int updaterows = userDao.userAdd(connection, user);
            connection.commit();
            if (updaterows > 0){
                flag = true;
                System.out.println("add success!");
            }else {
                System.out.println("add fail!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public Boolean userExist(String userCode) {
        Connection connection = null;
        Boolean flag = false;


        try {
            connection = BaseDao.getConnection();
            flag= userDao.userExist(connection, userCode);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public User getUserById(int id) {
        Connection connection = null;
        User user = new User();
        connection = BaseDao.getConnection();
        try {
            user = userDao.getUserById(connection, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return user;
    }

    @Override
    public Boolean modifyUser(User user) {
        Connection connection = null;
        Boolean flag = false;

        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            int modifyrows = userDao.modifyUser(connection, user);
            connection.commit();
            if (modifyrows > 0){
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Test
    public void test(){
        UserServieImpl userServie = new UserServieImpl();
        List<User> userList = userServie.getUserList(null, 0, 1, 5);
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i).getUserRoleName());
        }
    }

}
