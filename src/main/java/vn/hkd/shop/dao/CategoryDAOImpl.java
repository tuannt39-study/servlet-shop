package vn.hkd.shop.dao;

import vn.hkd.shop.db.DBConnection;
import vn.hkd.shop.db.DBUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vn.hkd.shop.domain.Category;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDAOImpl implements CategoryDAO {

    Connection conn;
    
    public CategoryDAOImpl(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        
        String sql = "SELECT * FROM category";
        
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            Category category;
            while (rs.next()) {
                category = new Category();
                category.setId(rs.getInt(1));
                category.setName(rs.getString(2));
                categories.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.close(rs);
            DBUtil.close(stmt);
        }
        
        return categories;
    }

}
