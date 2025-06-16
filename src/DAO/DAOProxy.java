package DAO;

import util.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class DAOProxy implements IDAO<Object> {
    private final IDAO<Object> realDAO;
    private final Logger logger = Logger.getLogger(DAOProxy.class.getName());
    private final JDBCUtil jdbcUtil = JDBCUtil.getInstance();
    
    public DAOProxy(IDAO<Object> realDAO) {
        this.realDAO = realDAO;
    }
    
    private void logOperation(String operation, String details) {
        logger.log(Level.INFO, "执行操作: {0}, 详情: {1}", new Object[]{operation, details});
    }
    
    private boolean checkPermission(String operation) {
        // 这里可以添加权限检查逻辑
        return true;
    }
    
    @Override
    public List<Object[]> getAllData() {
        if (!checkPermission("查询")) {
            logger.log(Level.WARNING, "没有查询权限");
            return new ArrayList<>();
        }
        logOperation("查询", "获取所有数据");
        return realDAO.getAllData();
    }
    
    @Override
    public Object[] findById(int id) {
        if (!checkPermission("查询")) {
            logger.log(Level.WARNING, "没有查询权限");
            return null;
        }
        logOperation("查询", "根据ID查询: " + id);
        return realDAO.findById(id);
    }
    
    @Override
    public void add(Object[] data) {
        if (!checkPermission("添加")) {
            logger.log(Level.WARNING, "没有添加权限");
            return;
        }
        logOperation("添加", "添加新数据");
        realDAO.add(data);
    }
    
    @Override
    public void update(int id, Object[] data) {
        if (!checkPermission("更新")) {
            logger.log(Level.WARNING, "没有更新权限");
            return;
        }
        logOperation("更新", "更新ID为 " + id + " 的数据");
        realDAO.update(id, data);
    }
    
    @Override
    public void remove(int id) {
        if (!checkPermission("删除")) {
            logger.log(Level.WARNING, "没有删除权限");
            return;
        }
        logOperation("删除", "删除ID为 " + id + " 的数据");
        realDAO.remove(id);
    }
    
    @Override
    public Object[][] search(int columnIndex, String searchText) {
        if (!checkPermission("搜索")) {
            logger.log(Level.WARNING, "没有搜索权限");
            return new Object[0][];
        }
        logOperation("搜索", "在列 " + columnIndex + " 中搜索: " + searchText);
        return realDAO.search(columnIndex, searchText);
    }
} 