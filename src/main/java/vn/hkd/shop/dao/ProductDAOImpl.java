package vn.hkd.shop.dao;

import vn.hkd.shop.db.DBConnection;
import vn.hkd.shop.db.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vn.hkd.shop.domain.Category;
import vn.hkd.shop.domain.Product;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAOImpl implements ProductDAO {

    Connection conn;
    
    public ProductDAOImpl(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public List<Product> findAll(int max) {
        List<Product> products = new ArrayList<>();
        
        String sql = "SELECT * FROM product ORDER BY id DESC LIMIT ?";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, max);
            rs = ps.executeQuery();
            Product product;
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setImage(rs.getString("image"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.close(rs);
            DBUtil.close(ps);
        }
        
        return products;
    }

    @Override
    public List<Product> findByCategory(int id) {
        List<Product> products = new ArrayList<>();
        
        String sql = "SELECT * FROM product WHERE category_id = ? ORDER BY id DESC";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Product product;
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setImage(rs.getString("image"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.close(rs);
            DBUtil.close(ps);
        }
        
        return products;
    }

    @Override
    public List<Product> search(String q) {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM product WHERE name LIKE ? ORDER BY id DESC";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(sql);
            StringBuilder sb = new StringBuilder();
            sb.append("%").append(q).append("%");
            ps.setString(1, sb.toString());
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setImage(rs.getString("image"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.close(rs);
            DBUtil.close(ps);
        }
        
        return products;
    }

    @Override
    public Product findOne(int id) {
        Product product = null;

        String sql = "SELECT product.*, category.name AS category_name "
                   + "FROM product "
                   + "LEFT JOIN category "
                   + "ON product.category_id = category.id "
                   + "WHERE product.id = ?";
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                product.setImage(rs.getString("image"));
                product.setCategory(
                    new Category(rs.getInt("category_id"), rs.getString("category_name")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.close(rs);
            DBUtil.close(ps);
        }

        return product;
    }

}
