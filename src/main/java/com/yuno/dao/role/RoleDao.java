package com.yuno.dao.role;

import com.yuno.pojo.Role;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yuno
 * @create 2021-07-19-11:32
 */
public interface RoleDao {
    //角色列表查询
    public List<Role> getRoleList(Connection connection) throws SQLException;
}
