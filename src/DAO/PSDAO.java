package DAO;

import frame.MainFrame;
import pojo.User;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PSDAO {
    //序号 用户名 地址 是否缴清
    public static final Object[] columnNames = {"序号", "用户名","地址","是否缴清"};
    private static final JDBCUtil jdbcUtil = JDBCUtil.getInstance();

    //getAllData
    public static List<Object[]> getAllData() {
        //查询语句
        String sql = "select id,username,address,is_pay_off from paystatus";
        List list = new ArrayList();
        //连接
        Connection connection = null;
        //执行语句
        PreparedStatement preparedStatement = null;
        //结果集
        ResultSet resultSet = null;
        try {
            connection = jdbcUtil.open();
            preparedStatement = jdbcUtil.preparedStatement(sql, list, connection);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //遍历结果集 将结果集中的内容都赋给list->数组并返回
                list.add(new Object[]{resultSet.getInt("id"), resultSet.getString("username"),
                        resultSet.getString("address"), resultSet.getString("is_pay_off")
                });
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
        //创建一个二维list
        ArrayList<Object[]> arrayList = new ArrayList<>();
        for (Object[] d : getAllData()) {//d是null
            if (((String) d[columnIndex]).contains(searchText)) {
                //数据元素包含查询文本 返回该一维数组
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
        String sql = "delete from paystatus where id=?";
        List list = new ArrayList();
        list.add(id);
        jdbcUtil.executeUpdate(sql,list);
    }

    //从数据库中findById
    public static Object[] findById(int selectedId){
        //查询sql语句
        String sql = "select id,username,address,is_pay_off from paystatus where id=?";
        List list = new ArrayList<>();
        list.add(selectedId);
        //连接
        Connection connection = null;
        //执行语句
        PreparedStatement statement =null;
        //结果集
        ResultSet resultSet =null;
        try {
            connection = jdbcUtil.open();
            statement = jdbcUtil.preparedStatement(sql, list, connection);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                return new Object[]{resultSet.getInt("id"),resultSet.getString("username"),
                        resultSet.getString("address"),resultSet.getString("is_pay_off")};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            jdbcUtil.closeAll(resultSet,statement,connection);
        }
        return null;
    }

    //update
    public static void update(int id, Object[] newData){
        //sql语句
        String sql = "update paystatus set username=?,address=?,is_pay_off=? " +
                "where id =?";
        //list
        List list = new ArrayList();

        list.add(newData[1]);
        list.add(newData[2]);
        list.add(newData[3]);
        list.add(id);
        //executeUpdate
        jdbcUtil.executeUpdate(sql,list);
    }

    //add方法
    public static void add(Object[] newData) {
        //增加sql语句 insert
        String sql = "insert into paystatus(username,address,is_pay_off) values(?,?,?)";
        //list
        List list = new ArrayList<>();

        list.add(newData[1]);
        list.add(newData[2]);
        list.add(newData[3]);
        //executeUpdate
        jdbcUtil.executeUpdate(sql,list);
    }

}

