package util;

import java.sql.*;
import java.util.List;

public class JDBCUtil {
    // 单例实例
    private static JDBCUtil instance;
    
    // 数据库连接配置
    private static final String url = "jdbc:mysql://localhost:3306/community_management?characterEncoding:utf8";
    private static final String user = "root";
    private static final String password = "root";
    
    // 私有构造函数
    private JDBCUtil() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // 获取单例实例的方法
    public static synchronized JDBCUtil getInstance() {
        if (instance == null) {
            instance = new JDBCUtil();
        }
        return instance;
    }
    
    // 打开数据库连接
    public static Connection open() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // 创建PreparedStatement
    public static PreparedStatement preparedStatement(String sql, List list, Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    statement.setObject(i + 1, list.get(i));
                }
            }
            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // 关闭所有数据库资源
    public static void closeAll(ResultSet resultSet, PreparedStatement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // 执行更新操作
    public static int executeUpdate(String sql, List list) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = open();
            statement = preparedStatement(sql, list, connection);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, statement, connection);
        }
        return 0;
    }
}
