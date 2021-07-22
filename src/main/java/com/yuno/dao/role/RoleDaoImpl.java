package com.yuno.dao.role;

import com.yuno.dao.BaseDao;
import com.yuno.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuno
 * @create 2021-07-19-11:33
 */
public class RoleDaoImpl implements RoleDao{
    @Override
    public List<Role> getRoleList(Connection connection) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Role> roleList = new ArrayList<>();

        if (connection != null){
            String sql = "select * from smbms_role";
            Object[] parms = {};
            try {
                rs = BaseDao.execute(connection, pstm, rs, sql, parms);
                while (rs.next()){
                    Role _role = new Role();
                    _role.setId(rs.getInt("id"));
                    _role.setRoleCode(rs.getString("roleCode"));
                    _role.setRoleName(rs.getString("roleName"));
                    /*_role.setCreatedBy(rs.getInt("createdBy"));
                    _role.setCreationDate(rs.getDate("creationDate"));
                    _role.setModifyBy(rs.getInt("modifyBy"));
                    _role.setModifyDate(rs.getDate("modifyDate"));*/
                    roleList.add(_role);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                BaseDao.closeResource(null, pstm, rs);
            }
        }
        return roleList;
    }
}
