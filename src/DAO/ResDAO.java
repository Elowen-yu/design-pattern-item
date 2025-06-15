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
//管理员窗口的用户表
public class ResDAO {
    public static final Object[] columnNames = {"序号", "用户名", "密码", "住址", "车牌"};
//    public static List<Object[]> adminResData = new ArrayList<>();
    private static final JDBCUtil jdbcUtil = JDBCUtil.getInstance();
    public static int maxId = 3;//目前不知道怎么解决

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

//    static {
//        //初始化数据内容
//        adminResData.add(new Object[]{1, "Mike", "11", "12-101", "粤A888"});
//        adminResData.add(new Object[]{2, "Jack", "11", "9-1501", "粤C666"});
//        adminResData.add(new Object[]{3, "Lucy", "11", "3-502", "京A999"});
//    }

    public static void updatePassword(String newpwd) {
        String sql = "update resident set password=? where username =?";
        List list = new ArrayList<>();
        list.add(newpwd);
        list.add(MainFrame.user.getUserName());
        jdbcUtil.executeUpdate(sql, list);
    }

    //查询数据库中resident的所有属性并放到一个数组中
//    public static Object[][] getAllData(){
    public static List<Object[]> getAllData(){
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
//        return toArray(list);
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

    //add方法
    public static void add(Object[] newData) {//Editlabel.panel=4
        String sql = "insert into resident(username,password,address,vehicle) values(?,?,?,?)";
        List list = new ArrayList<>();
        list.add(newData[1]);
        list.add(newData[2]);
        list.add(newData[3]);
        list.add(newData[4]);
        jdbcUtil.executeUpdate(sql, list);
    }
//    public static void add(Object[] newData) {
//        newData[0] = ++maxId;
//        adminResData.add(newData);
//    }

    //从数据库中findById
    public static Object[] findById(int selectedId){
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
            if (resultSet.next()){
                return new Object[]{resultSet.getInt("id"),resultSet.getString("username"),
                        resultSet.getString("password"),resultSet.getString("address"),
                        resultSet.getString("vehicle")};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            jdbcUtil.closeAll(resultSet,statement,connection);
        }
        return null;
    }

//    //findById 选中某一行后 得到它是哪个数组
//    public static Object[] findById(int selectedId) {
//        //遍历比较id
//        for (Object[] d : adminResData) {
//            if ((int) d[0] == selectedId) {
//                //返回该一维数组
//                return d;
//            }
//        }
//        return new Object[columnNames.length];
//
//    }


    //update
    public static void update(int id, Object[] newData){
        String sql = "update resident set username=?,password=?,address=?,vehicle=? where id =?";
        List list = new ArrayList();
        list.add(newData[1]);
        list.add(newData[2]);
        list.add(newData[3]);
        list.add(newData[4]);
        list.add(id);
        jdbcUtil.executeUpdate(sql, list);
    }
//    public static void update(int id, Object[] newData) {
//        //循环查找原数组
//        for (int i = 0; i < adminResData.size(); i++) {
//            if ((int) adminResData.get(i)[0] == id)
//                //替换该数组
//                adminResData.set(i, newData);
//        }
//    }

    //remove
    public static void remove(int id) {
        String sql = "delete from resident where id=?";
        List list = new ArrayList();
        list.add(id);
        jdbcUtil.executeUpdate(sql, list);
    }
//    public static void remove(int id) {
//        //查找原数组
//        for (int i = 0; i < adminResData.size(); i++) {
//            if ((int) adminResData.get(i)[0] == id) {
//                adminResData.remove(i);
//                break;
//            }
//        }
//    }

    //专门给repairDAO用
    public static String getAddress(){
        //查询语句
        String sql = "select address from resident where username=?";
        List list = new ArrayList();
        list.add(MainFrame.user.getUserName());
        //连接
        Connection connection =null;
        //执行语句
        PreparedStatement preparedStatement = null;
        //结果集
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
//        return toArray(list);
        return " ";
    }

    public static List<Object[]> getUserData() {
        //查询语句
        String sql = "select id,username,password,address,vehicle from resident where username=?";
        List list = new ArrayList();
        List mylist = new ArrayList();
        mylist.add(MainFrame.user.getUserName());//String->Object
        //连接
        Connection connection = null;
        //执行语句
        PreparedStatement preparedStatement = null;
        //结果集
        ResultSet resultSet = null;
        try {
            connection = jdbcUtil.open();
            preparedStatement = jdbcUtil.preparedStatement(sql, mylist, connection);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //遍历结果集 将结果集中的内容都赋给list->数组并返回
                list.add(new Object[]{resultSet.getInt("id"),resultSet.getString("username"),
                        resultSet.getString("password"),resultSet.getString("address"),
                        resultSet.getString("vehicle")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeAll(resultSet, preparedStatement, connection);
        }
//        return toArray(list);
        return list;
    }

}

