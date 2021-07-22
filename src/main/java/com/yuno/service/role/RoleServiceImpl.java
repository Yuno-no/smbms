package com.yuno.service.role;

import com.yuno.dao.BaseDao;
import com.yuno.dao.role.RoleDao;
import com.yuno.dao.role.RoleDaoImpl;
import com.yuno.dao.user.UserDao;
import com.yuno.dao.user.UserDaoImpl;
import com.yuno.pojo.Role;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuno
 * @create 2021-07-19-11:36
 */
public class RoleServiceImpl implements RoleService{
    //业务层都会调用dao层，所以我们要引入dao层
    private RoleDao roleDao;
    public RoleServiceImpl(){
        roleDao = new RoleDaoImpl();
    }

    @Override
    public List<Role> getRoleList() {
        Connection connection = null;
        List<Role> roleList = new ArrayList<>();

        try {
            connection = BaseDao.getConnection();
            roleList = roleDao.getRoleList(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return roleList;
    }

    @Test
    public void test(){
        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();
        for (int i = 0; i < roleList.size(); i++) {
            System.out.println(roleList.get(i).getId());

        }
    }
}
