package frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class BaseTablePanel extends BasePanel {
    protected JTable table;
    protected JTextField searchField;
    protected JButton addButton;
    protected JButton deleteButton;
    protected JButton modifyButton;
    protected JButton searchButton;
    protected String[] columnNames;
    
    public BaseTablePanel(String title, String[] columnNames) {
        super(title);
        this.columnNames = columnNames;
    }
    
    @Override
    protected void initComponents() {
        // 创建顶部面板
        JPanel topPanel = new JPanel();
        addButton = new JButton("增加");
        deleteButton = new JButton("删除");
        modifyButton = new JButton("修改");
        searchButton = new JButton("查询");
        searchField = new JTextField(10);
        
        topPanel.add(addButton);
        topPanel.add(deleteButton);
        topPanel.add(modifyButton);
        topPanel.add(new JLabel(getSearchLabel()));
        topPanel.add(searchField);
        topPanel.add(searchButton);
        
        // 创建表格
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        
        // 添加事件监听器
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleAdd();
            }
        });
        
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleDelete();
            }
        });
        
        modifyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleModify();
            }
        });
        
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleSearch(searchField.getText());
            }
        });
        
        // 添加组件到主面板
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // 刷新表格数据
        refreshTable();
    }
    
    protected abstract String getSearchLabel();
    
    protected abstract void refreshTable();
    
    protected abstract void handleAdd();
    
    protected abstract void handleModify();
    
    protected abstract void handleDelete();
    
    protected abstract void handleSearch(String searchText);
    
    protected DefaultTableModel createTableModel(Object[][] data) {
        return new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }
    
    protected int getSelectedId() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请先选择一条记录");
            return -1;
        }
        return (int) table.getValueAt(selectedRow, 0);
    }
} 