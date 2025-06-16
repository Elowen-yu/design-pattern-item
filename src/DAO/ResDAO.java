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

public  class ResDAO implements IDAO<Object> {
    public static final String[] columnNames = {"序号", "用户名", "密码", "住址", "车牌"};
    private static final JDBCUtil jdbcUtil = JDBCUtil.getInstance();
    private static ResDAO instance;
    private static DAOProxy proxy;
    
    private ResDAO() {}
    
    public static ResDAO getInstance() {
        if (instance == null) {
            instance = new ResDAO();
            proxy = new DAOProxy(instance);
        }
        return instance;
    }
    
    public static DAOProxy getProxy() {
        if (proxy == null) {
            getInstance();
        }
        return proxy;
    }

    public static User getUserByName(String username) {
        String sql = "select id,username,password,address,vehicle from resident where username=?";
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
                return UserFactory.createResidentUser(
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("address"),
                    resultSet.getString("vehicle")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeAll(resultSet, statement, connection);
        }
        return null;
    }

    public static void updatePassword(String newpwd) {
        String sql = "update resident set password=? where username =?";
        List list = new ArrayList<>();
        list.add(newpwd);
        list.add(MainFrame.user.getUserName());
        jdbcUtil.executeUpdate(sql, list);
    }

    @Override
    public List<Object[]> getAllData() {
        String sql = "select id,username,password,address,vehicle from resident";
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
                        resultSet.getString("password"), resultSet.getString("address"),
                        resultSet.getString("vehicle")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeAll(resultSet, preparedStatement, connection);
        }
        return list;
    }

    @Override
    public Object[][] search(int columnIndex, String searchText) {
        ArrayList<Object[]> arrayList = new ArrayList<>();
        for (Object[] d : getAllData()) {
            if (((String) d[columnIndex]).contains(searchText)) {
                arrayList.add(d);
            }
        }
        return toArray(arrayList);
    }

    @Override
    public Object[] findById(int selectedId) {
        String sql = "select id,username,password,address,vehicle from resident where id=?";
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
                        resultSet.getString("password"), resultSet.getString("address"),
                        resultSet.getString("vehicle")};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeAll(resultSet, statement, connection);
        }
        return null;
    }

    @Override
    public void add(Object[] newData) {
        String sql = "insert into resident(username,password,address,vehicle) values(?,?,?,?)";
        List list = new ArrayList<>();
        list.add(newData[1]);
        list.add(newData[2]);
        list.add(newData[3]);
        list.add(newData[4]);
        jdbcUtil.executeUpdate(sql, list);
    }

    @Override
    public void update(int id, Object[] newData) {
        String sql = "update resident set username=?,password=?,address=?,vehicle=? where id =?";
        List list = new ArrayList();
        list.add(newData[1]);
        list.add(newData[2]);
        list.add(newData[3]);
        list.add(newData[4]);
        list.add(id);
        jdbcUtil.executeUpdate(sql, list);
    }

    @Override
    public void remove(int id) {
        String sql = "delete from resident where id=?";
        List list = new ArrayList();
        list.add(id);
        jdbcUtil.executeUpdate(sql, list);
    }

    public static Object[][] toArray(List<Object[]> list) {
        Object[][] array = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static String getAddress(){
        String sql = "select address from resident where username=?";
        List list = new ArrayList();
        list.add(MainFrame.user.getUserName());
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = jdbcUtil.open();
            preparedStatement = jdbcUtil.preparedStatement(sql,list,connection);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getString("address");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            jdbcUtil.closeAll(resultSet,preparedStatement,connection);
        }
        return " ";
    }

    public static List<Object[]> getUserData() {
        String sql = "select id,username,password,address,vehicle from resident where username=?";
        List list = new ArrayList();
        List mylist = new ArrayList();
        mylist.add(MainFrame.user.getUserName());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = jdbcUtil.open();
            preparedStatement = jdbcUtil.preparedStatement(sql, mylist, connection);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Object[]{resultSet.getInt("id"),resultSet.getString("username"),
                        resultSet.getString("password"),resultSet.getString("address"),
                        resultSet.getString("vehicle")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeAll(resultSet, preparedStatement, connection);
        }
        return list;
    }
}

