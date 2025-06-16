package frame.builder;

import DAO.DAOProxy;
import util.SystemConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TablePanelBuilder extends PanelBuilder {
    private String title;
    private String[] columnNames;
    private DAOProxy daoProxy;
    private JTable table;
    private JTextField searchField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton modifyButton;
    private JButton searchButton;
    
    public TablePanelBuilder(String title, String[] columnNames, DAOProxy daoProxy) {
        this.title = title;
        this.columnNames = columnNames;
        this.daoProxy = daoProxy;
    }
    
    @Override
    public void buildBasicProperties() {
        panel.setTitle(title);
        panel.setClosable(true);
        panel.setMaximizable(true);
        panel.setIconifiable(true);
        panel.setResizable(true);
        panel.setSize(SystemConstants.INNERFRAME_WIDTH, SystemConstants.INNERFRAME_HEIGHT);
        panel.setLayout(new BorderLayout());
    }
    
    @Override
    public void buildTopPanel() {
        JPanel topPanel = new JPanel();
        searchField = new JTextField(10);
        addButton = new JButton("增加");
        deleteButton = new JButton("删除");
        modifyButton = new JButton("修改");
        searchButton = new JButton("查询");
        
        topPanel.add(new JLabel("用户名"));
        topPanel.add(searchField);
        topPanel.add(addButton);
        topPanel.add(deleteButton);
        topPanel.add(modifyButton);
        topPanel.add(searchButton);
        
        panel.add(topPanel, BorderLayout.NORTH);
    }
    
    @Override
    public void buildMainPanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(table, BorderLayout.CENTER);
        
        panel.add(tablePanel, BorderLayout.CENTER);
    }
    
    @Override
    public void buildBottomPanel() {
        // 表格面板不需要底部面板
    }
    
    @Override
    public void addEventListeners() {
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 子类实现具体逻辑
            }
        });
        
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row == -1) return;
                int selectedId = (int) table.getValueAt(row, 0);
                daoProxy.remove(selectedId);
                refreshTable();
            }
        });
        
        modifyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row == -1) return;
                int selectedId = (int) table.getValueAt(row, 0);
                // 子类实现具体逻辑
            }
        });
        
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String searchText = searchField.getText();
                Object[][] searchData = daoProxy.search(1, searchText);
                table.setModel(new DefaultTableModel(searchData, columnNames));
            }
        });
    }
    
    @Override
    public void setDataModel() {
        refreshTable();
    }
    
    protected void refreshTable() {
        List<Object[]> data = daoProxy.getAllData();
        table.setModel(new DefaultTableModel(convertToArray(data), columnNames));
    }
    
    protected Object[][] convertToArray(List<Object[]> data) {
        Object[][] array = new Object[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            array[i] = data.get(i);
        }
        return array;
    }
    
    // 供子类使用的getter方法
    protected JTable getTable() {
        return table;
    }
    
    protected JTextField getSearchField() {
        return searchField;
    }
    
    protected JButton getAddButton() {
        return addButton;
    }
    
    protected JButton getDeleteButton() {
        return deleteButton;
    }
    
    protected JButton getModifyButton() {
        return modifyButton;
    }
    
    protected JButton getSearchButton() {
        return searchButton;
    }
    
    // 获取DAO代理的getter方法
    protected Object getDaoProxy() {
        return daoProxy;
    }
} 