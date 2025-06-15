package DAO;

import observer.PropertyFeeNotificationCenter;
import pojo.PropertyFee;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PropertyFeeDAO {
    private static final JDBCUtil jdbcUtil = JDBCUtil.getInstance();
    private static final PropertyFeeNotificationCenter notificationCenter = new PropertyFeeNotificationCenter();

    public static PropertyFeeNotificationCenter getNotificationCenter() {
        return notificationCenter;
    }

    // 添加新的物业费用通知
    public static void addPropertyFee(PropertyFee propertyFee) {
        String sql = "INSERT INTO property_fee (title, content, amount, due_date, status, resident_id) VALUES (?, ?, ?, ?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(propertyFee.getTitle());
        params.add(propertyFee.getContent());
        params.add(propertyFee.getAmount());
        params.add(propertyFee.getDueDate());
        params.add(propertyFee.getStatus());
        params.add(propertyFee.getResidentId());

        jdbcUtil.executeUpdate(sql, params);
        // 发布新的物业费用通知
        notificationCenter.publishNewFee(propertyFee);
    }

    // 获取所有物业费用通知
    public static List<PropertyFee> getAllPropertyFees() {
        String sql = "SELECT * FROM property_fee";
        List<PropertyFee> fees = new ArrayList<>();
        
        try (Connection conn = jdbcUtil.open();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                PropertyFee fee = new PropertyFee();
                fee.setId(rs.getInt("id"));
                fee.setTitle(rs.getString("title"));
                fee.setContent(rs.getString("content"));
                fee.setAmount(rs.getDouble("amount"));
                fee.setDueDate(rs.getDate("due_date"));
                fee.setStatus(rs.getString("status"));
                fee.setResidentId(rs.getInt("resident_id"));
                fees.add(fee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return fees;
    }

    // 更新物业费用状态
    public static void updatePropertyFeeStatus(int id, String status) {
        String sql = "UPDATE property_fee SET status = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(status);
        params.add(id);
        
        jdbcUtil.executeUpdate(sql, params);
    }

    // 获取特定居民的物业费用通知
    public static List<PropertyFee> getPropertyFeesByResidentId(int residentId) {
        String sql = "SELECT * FROM property_fee WHERE resident_id = ?";
        List<PropertyFee> fees = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        params.add(residentId);
        
        try (Connection conn = jdbcUtil.open();
             PreparedStatement stmt = jdbcUtil.preparedStatement(sql, params, conn);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                PropertyFee fee = new PropertyFee();
                fee.setId(rs.getInt("id"));
                fee.setTitle(rs.getString("title"));
                fee.setContent(rs.getString("content"));
                fee.setAmount(rs.getDouble("amount"));
                fee.setDueDate(rs.getDate("due_date"));
                fee.setStatus(rs.getString("status"));
                fee.setResidentId(rs.getInt("resident_id"));
                fees.add(fee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return fees;
    }
} 