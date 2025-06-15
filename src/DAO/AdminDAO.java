package DAO;

import frame.MainFrame;
import pojo.User;
import pojo.UserFactory;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    //序号 用户名 密码 性别 年龄 电话
    public static final Object[] columnNames = {"序号", "用户名", "密码", "性别", "年龄", "电话"};
    
    private static final JDBCUtil jdbcUtil = JDBCUtil.getInstance();

    //通过用户名在数据库中找账号密码
    public static User getUserByName(String username) {
        String sql = "select id,username,password,gender,age,phone from admin where username=?";
        List list = new ArrayList<>();
        list.add(username);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = jdbcUtil.open();
            statement = jdbcUtil.preparedStatement(sql, list, connection);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return UserFactory.createAdminUser(
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("gender"),
                    resultSet.getString("age"),
                    resultSet.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeAll(resultSet, statement, connection);
        }
        return null;
    }

    //updatePassword
    public static void updatePassword(String newpwd) {
        String sql = "update admin set password=? where username =?";
        List list = new ArrayList();
        list.add(newpwd);
        list.add(MainFrame.user.getUserName());
        jdbcUtil.executeUpdate(sql, list);
    }

    //getAllData
    public static List<Object[]> getAllData() {
        String sql = "select id,username,password,gender,age,phone from admin";
        List list = new ArrayList();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = jdbcUtil.open();
            preparedStatement = jdbcUtil.preparedStatement(sql, list, connection);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Object[]{resultSet.getInt("id"), resultSet.getString("username"),
                        resultSet.getString("password"), resultSet.getString("gender"),
                        resultSet.getString("age"), resultSet.getString("phone")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeAll(resultSet, preparedStatement, connection);
        }
        return list;
    }

    //查询方法
    public static Object[][] search(int columnIndex, String searchText) {
        ArrayList<Object[]> arrayList = new ArrayList<>();
        for (Object[] d : getAllData()) {
            if (((String) d[columnIndex]).contains(searchText)) {
                arrayList.add(d);
            }
        }
        return toArray(arrayList);
    }

    //list->数组 row
    public static Object[][] toArray(List<Object[]> list) {
        Object[][] array = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    //remove
    public static void remove(int id) {
        String sql = "delete from admin where id=?";
        List list = new ArrayList();
        list.add(id);
        jdbcUtil.executeUpdate(sql, list);
    }

    //从数据库中findById
    public static Object[] findById(int selectedId) {
        String sql = "select id,username,password,gender,age,phone from admin where id=?";
        List list = new ArrayList<>();
        list.add(selectedId);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = jdbcUtil.open();
            statement = jdbcUtil.preparedStatement(sql, list, connection);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Object[]{resultSet.getInt("id"), resultSet.getString("username"),
                        resultSet.getString("password"), resultSet.getString("gender"),
                        resultSet.getString("age"), resultSet.getString("phone")};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeAll(resultSet, statement, connection);
        }
        return null;
    }

    //update
    public static void update(int id, Object[] newData) {
        String sql = "update admin set username=?,password=?,gender=?,age=?,phone=? where id =?";
        List list = new ArrayList();
        list.add(newData[1]);
        list.add(newData[2]);
        list.add(newData[3]);
        list.add(newData[4]);
        list.add(newData[5]);
        list.add(id);
        jdbcUtil.executeUpdate(sql, list);
    }

    //add方法
    public static void add(Object[] newData) {
        String sql = "insert into admin(username,password,gender,age,phone) values(?,?,?,?,?)";
        List list = new ArrayList<>();
        list.add(newData[1]);
        list.add(newData[2]);
        list.add(newData[3]);
        list.add(newData[4]);
        list.add(newData[5]);
        jdbcUtil.executeUpdate(sql, list);
    }
}
