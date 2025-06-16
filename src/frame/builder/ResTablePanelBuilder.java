package frame.builder;

import DAO.DAOProxy;
import DAO.ResDAO;
import frame.admin.AdminPanel;
import frame.admin.ResEditPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResTablePanelBuilder extends TablePanelBuilder {
    private static final JLabel[] LABELS = {
        new JLabel("用户名"),
        new JLabel("密码"),
        new JLabel("住址"),
        new JLabel("车牌")
    };
    
    public ResTablePanelBuilder() {
        super("住户信息", ResDAO.columnNames, ResDAO.getProxy());
    }
    
    @Override
    public void addEventListeners() {
        super.addEventListeners();
        
        // 重写增加按钮的事件处理
        getAddButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AdminPanel.setContent(new ResEditPanel(LABELS, LABELS.length, -1));
            }
        });
        
        // 重写修改按钮的事件处理
        getModifyButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = getTable().getSelectedRow();
                if (row == -1) return;
                int selectedId = (int) getTable().getValueAt(row, 0);
                AdminPanel.setContent(new ResEditPanel(LABELS, LABELS.length, selectedId));
            }
        });
        
        // 重写删除按钮的事件处理
        getDeleteButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = getTable().getSelectedRow();
                if (row == -1) return;
                int selectedId = (int) getTable().getValueAt(row, 0);
                getDaoProxy().remove(selectedId);
                refreshTable();
            }
        });
        
        // 重写查询按钮的事件处理
        getSearchButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String searchText = getSearchField().getText();
                Object[][] searchData = getDaoProxy().search(1, searchText);
                getTable().setModel(new DefaultTableModel(searchData, ResDAO.columnNames));
            }
        });
    }
    
    // 获取DAO代理的getter方法
    protected DAOProxy getDaoProxy() {
        return (DAOProxy) super.getDaoProxy();
    }
} 