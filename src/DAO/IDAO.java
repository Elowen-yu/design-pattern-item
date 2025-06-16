package DAO;

import java.util.List;

public interface IDAO<T> {
    // 查询所有数据
    List<Object[]> getAllData();
    
    // 根据ID查询
    Object[] findById(int id);
    
    // 添加数据
    void add(Object[] data);
    
    // 更新数据
    void update(int id, Object[] data);
    
    // 删除数据
    void remove(int id);
    
    // 搜索数据
    Object[][] search(int columnIndex, String searchText);
} 