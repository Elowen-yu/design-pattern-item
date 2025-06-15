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

public class ARepairDAO {
    //序号 项目 保修人 住址 故障 预计花费 实际花费 维修情况
    public static final Object[] columnNames = {"序号", "项目","报修人","住址",
            "故障","预计花费","实际花费","维修情况"};
    //序号 项目 故障 预计花费 实际花费 维修情况
    public static final Object[] usercolumnNames = {"序号", "项目",
            "故障","预计花费","实际花费","维修情况"};


    //getAllData
    public static List<Object[]> getAllData() {
        //查询语句
        String sql = "select id,item,resident,address,breakdown,predictExpense,expense,isSolve from arepair";
        List list = new ArrayList();
        //连接
        Connection connection = null;
        //执行语句
        PreparedStatement preparedStatement = null;
        //结果集
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.open();
            preparedStatement = JDBCUtil.preparedStatement(sql, list, connection);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //遍历结果集 将结果集中的内容都赋给list->数组并返回
                list.add(new Object[]{resultSet.getInt("id"), resultSet.getString("item"),
                        resultSet.getString("resident"), resultSet.getString("address"),
                        resultSet.getString("breakdown"),resultSet.getString("predictExpense"),
                        resultSet.getString("expense"),resultSet.getString("isSolve")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(resultSet, preparedStatement, connection);
        }
//        return toArray(list);
        return list;
    }

    public static List<Object[]> getUserData() {
        //查询语句
        String sql = "select id,item,breakdown,predictExpense,expense,isSolve from arepair where resident=?";
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
            connection = JDBCUtil.open();
            preparedStatement = JDBCUtil.preparedStatement(sql, mylist, connection);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //遍历结果集 将结果集中的内容都赋给list->数组并返回
                list.add(new Object[]{resultSet.getInt("id"), resultSet.getString("item"),
                        resultSet.getString("breakdown"),resultSet.getString("predictExpense"),
                        resultSet.getString("expense"),resultSet.getString("isSolve")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(resultSet, preparedStatement, connection);
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
        Object[][] array = new Object[columnNames.length][];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }



    //remove
    public static void remove(int id) {
        String sql = "delete from arepair where id=?";
        List list = new ArrayList();
        list.add(id);
        JDBCUtil.executeUpdate(sql,list);
    }

    //从数据库中findById
    public static Object[] findById(int selectedId){
        //查询sql语句
        String sql = "select id,item,resident,address,breakdown,predictExpense,expense,isSolve" +
                " from arepair where id=?";
        List list = new ArrayList<>();
        list.add(selectedId);
        //连接
        Connection connection = null;
        //执行语句
        PreparedStatement statement =null;
        //结果集
        ResultSet resultSet =null;
        try {
            connection = JDBCUtil.open();
            statement = JDBCUtil.preparedStatement(sql, list, connection);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                return new Object[]{resultSet.getInt("id"), resultSet.getString("item"),
                        resultSet.getString("resident"), resultSet.getString("address"),
                        resultSet.getString("breakdown"),resultSet.getString("predictExpense"),
                        resultSet.getString("expense"),resultSet.getString("isSolve")};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtil.closeAll(resultSet,statement,connection);
        }
        return null;
    }

    //update
    public static void update(int id, Object[] newData){
        //sql语句
        String sql = "update arepair set item=?,resident=?,address=?,breakdown=?,predictExpense=?,expense=?,isSolve=?" +
                " where id =?";
        //list
        List list = new ArrayList();

        list.add(newData[1]);
        list.add(newData[2]);
        list.add(newData[3]);
        list.add(newData[4]);
        list.add(newData[5]);
        list.add(newData[6]);
        list.add(newData[7]);
        list.add(id);
        //executeUpdate
        JDBCUtil.executeUpdate(sql,list);
    }

    //add方法
    public static void add(Object[] newData) {
        //增加sql语句 insert
        String sql = "insert into arepair(item,resident,address,breakdown,predictExpense,expense,isSolve) " +
                "values(?,?,?,?,?,?,?)";
        //list
        List list = new ArrayList<>();

        list.add(newData[1]);
        list.add(newData[2]);
        list.add(newData[3]);
        list.add(newData[4]);
        list.add(newData[5]);
        list.add(newData[6]);
        list.add(newData[7]);
        //executeUpdate
        JDBCUtil.executeUpdate(sql,list);
    }

    //专门为userRepair做的查询方法
    public static Object[][] searchUR(int columnIndex, String searchText) {
        //创建一个二维list
        ArrayList<Object[]> arrayList = new ArrayList<>();
        for (Object[] d : getUserData()) {//d是null
            if (((String) d[columnIndex]).contains(searchText)) {
                //数据元素包含查询文本 返回该一维数组
                arrayList.add(d);
            }
        }
        return toArrayUR(arrayList);
    }

    public static Object[][] toArrayUR(List<Object[]> list) {
        Object[][] array = new Object[usercolumnNames.length][];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static Object[] findByIdUR(int selectedId){
        //查询sql语句
        String sql = "select id,item,breakdown,predictExpense,expense,isSolve" +
                " from arepair where id=?";
        List list = new ArrayList<>();
        list.add(selectedId);
        //连接
        Connection connection = null;
        //执行语句
        PreparedStatement statement =null;
        //结果集
        ResultSet resultSet =null;
        try {
            connection = JDBCUtil.open();
            statement = JDBCUtil.preparedStatement(sql, list, connection);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                return new Object[]{resultSet.getInt("id"), resultSet.getString("item"),
                        resultSet.getString("breakdown"),resultSet.getString("predictExpense"),
                        resultSet.getString("expense"),resultSet.getString("isSolve")};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtil.closeAll(resultSet,statement,connection);
        }
        return null;
    }

    //add方法
    public static void addUR(Object[] newData) {
        //增加sql语句 insert
        String sql = "insert into arepair(item,resident,address,breakdown,predictExpense,expense,isSolve) " +
                "values(?,?,?,?,?,?,?)";
        //list
        List list = new ArrayList<>();

        list.add(newData[1]);
        list.add(MainFrame.user.getUserName());//resident
        list.add(ResDAO.getAddress());//address 读取resident数据库得到
        list.add(newData[2]);
        list.add(newData[3]);
        list.add(newData[4]);
        list.add(newData[5]);
        //executeUpdate
        JDBCUtil.executeUpdate(sql,list);
    }

    //update
    public static void updateUR(int id, Object[] newData){
        //sql语句
        String sql = "update arepair set item=?,resident=?,address=?,breakdown=?,predictExpense=?,expense=?,isSolve=?" +
                " where id =?";
        //list
        List list = new ArrayList();

        list.add(newData[1]);
        list.add(MainFrame.user.getUserName());//resident
        list.add(ResDAO.getAddress());//address 读取resident数据库得到
        list.add(newData[2]);
        list.add(newData[3]);
        list.add(newData[4]);
        list.add(newData[5]);
        list.add(id);
        //executeUpdate
        JDBCUtil.executeUpdate(sql,list);
    }

}

