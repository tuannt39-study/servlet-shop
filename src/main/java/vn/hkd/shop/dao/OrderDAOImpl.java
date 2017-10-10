package vn.hkd.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import vn.hkd.shop.domain.Order;
import vn.hkd.shop.db.DBUtil;
import vn.hkd.shop.domain.Item;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAOImpl implements OrderDAO {

    Connection conn;
    
    public OrderDAOImpl(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public int save(Order order) {
        int ret = -1;
        
        String sql = "INSERT INTO orders(name, phone, address, note, created, user_id) "
                   + "VALUES(?, ?, ?, ?, ?, ?)";
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, order.getName());
            ps.setString(2, order.getPhone());
            ps.setString(3, order.getAddress());
            ps.setString(4, order.getNote());
            ps.setTimestamp(5, new Timestamp(order.getCreated().getTime()));
            ps.setInt(6, order.getUser().getId());
            ps.executeUpdate();

            // Get inserted order ID
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ret = rs.getInt(1);
                
                // Save items
                sql = "INSERT INTO item(product_id, quantity, price, order_id) VALUES ";
                int count;
                for (count = 0; count < order.getItems().size(); count++) {
                    if (count != order.getItems().size() - 1) {
                        sql += "(?, ?, ?, ?), ";
                    } else {
                        sql += "(?, ?, ?, ?)";
                    }
                }
                ps = conn.prepareStatement(sql);
                count = 1;
                for (Item item : order.getItems()) {
                    ps.setInt(count++, item.getProduct().getId());
                    ps.setInt(count++, item.getQuantity());
                    ps.setInt(count++, item.getProduct().getPrice());
                    ps.setInt(count++, ret);
                }
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.close(rs);
            DBUtil.close(ps);
        }
        
        return ret;
    }

}
