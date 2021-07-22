package com.yuno.dao.user;

import com.mysql.jdbc.MiniAdmin;
import com.mysql.jdbc.StringUtils;
import com.yuno.dao.BaseDao;
import com.yuno.pojo.Role;
import com.yuno.pojo.User;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSInput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Yuno
 * @create 2021-07-15-22:57
 */
public class UserDaoImpl implements UserDao{

    //得到要登录的用户
    @Override
    public User getloginuser(Connection connection, String userCode) throws SQLException {
        // TODO Auto-generated method stub
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        if(null != connection){
            String sql = "select * from smbms_user where userCode=?";
            Object[] params = {userCode};
            try {
                rs = BaseDao.execute(connection, pstm, rs, sql, params);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getTimestamp("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getTimestamp("modifyDate"));
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return user;
    }

    //修改密码
    @Override
    public int updatePwd(Connection connection, int id, String password) throws SQLException {
        PreparedStatement pstm = null;
        int execute = 0;
        if (connection != null){
            String sql = "update smbms_user set userPassword = ? where id = ?";
            Object parms[] = {password, id};
            try {
                execute = BaseDao.execute(connection, pstm, sql, parms);
            } catch (Exception e) {
                e.printStackTrace();
            }
            BaseDao.closeResource(null, pstm, null);
        }
        return execute;
    }

    ////根据用户名或者角色名查询用户总数
    @Override
    public int getUserCount(Connection connection, String username, int userRole) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;

        if (connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select count(*) as count from smbms_user u, smbms_role r where u.userRole = r.id");
            ArrayList<Object> list = new ArrayList<>();
            if (!StringUtils.isNullOrEmpty(username)){
                sql.append(" and u.userName like ?");
                list.add("%" + username + "%");//index:0
            }
            if (userRole > 0){
                sql.append(" and u.userRole like ?");
                list.add(userRole);
            }

            //把list转换成数组
            Object[] parms = list.toArray();
            //输出完整的sql语句，方便调试
            System.out.println("UserDaoImpl -> getUserCount:" + sql.toString());

            try {
                rs = BaseDao.execute(connection, pstm, rs, sql.toString(), parms);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (rs.next()){
                count = rs.getInt("count");
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return count;
    }

    @Override
    public List<User> getUserList(Connection connection, String username, int userRole, int currentPageNo, int pageSize) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        if (connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select u.*, r.roleName from smbms_user u, smbms_role r where u.userRole = r.id");
            List<Object> parms = new ArrayList<>();
            if (!StringUtils.isNullOrEmpty(username)){
                sql.append(" and u.userName like ?");
                parms.add("%" + username + "%");
            }
            if (userRole > 0){
                sql.append(" and u.userRole like ?");
                parms.add(userRole);
            }

            //在数据库中，分页使用limit -> StartIndex,Size  总数
            sql.append(" order by creationDate DESC limit ?, ?");
            currentPageNo = (currentPageNo - 1) * pageSize;
            parms.add(currentPageNo);
            parms.add(pageSize);

            Object[] objects = parms.toArray();

            System.out.println("UserDaoImpl->getUserList sql:" + sql.toString());

            try {
                rs = BaseDao.execute(connection, pstm, rs, sql.toString(), objects);
                while (rs.next()){
                    User _user = new User();
                    _user.setId(rs.getInt("id"));
                    _user.setUserCode(rs.getString("userCode"));
                    _user.setUserName(rs.getString("userName"));
                    _user.setGender(rs.getInt("gender"));
                    _user.setBirthday(rs.getDate("birthday"));
                    _user.setPhone(rs.getString("phone"));
                    _user.setUserRole(rs.getInt("userRole"));
                    _user.setUserRoleName(rs.getString("roleName"));
                    userList.add(_user);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                BaseDao.closeResource(null, pstm, rs);
            }
        }
        return userList;
    }

    @Override
    public int userDel(Connection connection, int userid) throws SQLException {
        //准备查询参数及调用jdbc
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String msg = "";
        int execute = 0;

        String sql = "delete from smbms_user where id = ?";
        Object[] params = {userid};
        try {
            execute = BaseDao.execute(connection, pstm, sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }

    @Override
    public int userAdd(Connection connection, User user) throws SQLException {
        PreparedStatement pstm = null;
        int execute = 0;
        if (connection != null){
            String sql = "insert into smbms_user (userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Object[] params = {user.getUserCode(), user.getUserName(), user.getUserPassword(), user.getGender(), user.getBirthday(), user.getPhone(), user.getAddress(), user.getUserRole(), user.getCreateBy(), user.getCreationDate()};
            try {
                execute = BaseDao.execute(connection, pstm, sql, params);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                BaseDao.closeResource(null, pstm, null);
            }
        }
        return execute;
    }

    @Override
    public Boolean userExist(Connection connection, String userCode) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Boolean flag = false;
        if (connection != null){
            String sql = "select * from smbms_user where userCode = ?";
            Object[] params = {userCode};
            try {
                rs = BaseDao.execute(connection, pstm, rs, sql, params);
                if (rs.next()){
                    flag = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                BaseDao.closeResource(null, pstm, rs);
            }
        }
        return flag;
    }

    @Override
    public User getUserById(Connection connection, int id) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = new User();

        if (connection != null){
            String sql = "select u.id, u.userCode, u.userName, u.gender, u.birthday, u.phone, u.address, r.roleName from smbms_user u, smbms_role r where u.id = ? and u.userRole = r.id";
            Object[] params = {id};
            try {
                rs = BaseDao.execute(connection, pstm, rs, sql, params);
                while (rs.next()){
                    user.setId(rs.getInt("id"));
                    user.setUserCode(rs.getString("userCode"));
                    user.setUserName(rs.getString("userName"));
                    user.setGender(rs.getInt("gender"));
                    user.setBirthday(rs.getDate("birthday"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setUserRoleName(rs.getString("roleName"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                BaseDao.closeResource(null, pstm, rs);
            }
        }
        return user;
    }

    @Override
    public int modifyUser(Connection connection, User user) throws SQLException {
        PreparedStatement pstm = null;
        int execute = 0;

        if (connection != null){
            String sql = "update smbms_user set userName = ?, gender = ?, birthday = ?, phone = ?, address = ?, modifyBy = ?, modifyDate = ? where id = ?";
            Object[] params = {user.getUserName(), user.getGender(), user.getBirthday(), user.getPhone(), user.getAddress(), user.getModifyBy(), user.getModifyDate(), user.getId()};
            try {
                execute = BaseDao.execute(connection, pstm, sql, params);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                BaseDao.closeResource(null, pstm, null);
            }
        }

        return execute;
    }


}
