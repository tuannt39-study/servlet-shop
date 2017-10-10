package vn.hkd.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.hkd.shop.domain.User;
import vn.hkd.shop.db.DBUtil;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAOImpl implements UserDAO {

    Connection conn;

    public UserDAOImpl(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public User findOne(String email, String password) {
        User user = null;
        
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.close(rs);
            DBUtil.close(ps);
        }

        return user;
    }

    @Override
    public boolean exists(String email) {
        boolean ret = false;
        
        String sql = "SELECT * FROM user WHERE email = ?";
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                ret = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.close(rs);
            DBUtil.close(ps);
        }

        return ret;
    }

    @Override
    public int save(User user) {
        int ret = -1;
        
        String sql = "INSERT INTO user(name, email, password) VALUES(?, ?, ?)";
        
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ret = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.close(ps);
        }

        return ret;
    }

}
