package frame.admin;

import DAO.DAOProxy;
import DAO.ResDAO;
import util.SystemConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
//管理员 用户信息窗口 增删改查

/**
 * model
 */
public class ResTablePanel extends JInternalFrame {
    /*住户信息JMenu JInternalFrame JTable
     *  序号 用户名 密码 住址 车牌
     */

    private final DAOProxy resDAOProxy;

    public ResTablePanel() {
        super("住户信息", true, true, true, true);
        this.setSize(SystemConstants.INNERFRAME_WIDTH, SystemConstants.INNERFRAME_HEIGHT);
        this.setLayout(new BorderLayout());

        // 获取ResDAO的代理实例
        resDAOProxy = ResDAO.getProxy();

        //容器 组件： tablePanel <-table <- tableModel
        //           topPanel: 查询文本 文本框 四个按钮
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(0, 0, SystemConstants.INNERFRAME_WIDTH, SystemConstants.INNERFRAME_HEIGHT);

        JLabel[] labels = {new JLabel("用户名"), new JLabel("密码"), new JLabel("住址"), new JLabel("车牌")};
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("用户名"));
        JTextField searchField = new JTextField(10);
        topPanel.add(searchField);
        JButton addButton = new JButton("增加");
        JButton delButton = new JButton("删除");
        JButton modifyButton = new JButton("修改");
        JButton searchButton = new JButton("查询");

        //tablePanel
        JPanel tablePanel = new JPanel(new BorderLayout());
        JTable table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // 使用代理获取数据
        List<Object[]> data = resDAOProxy.getAllData();
        TableModel tableModel = new DefaultTableModel(ResDAO.toArray(data), ResDAO.columnNames);
        table.setModel(tableModel);

        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AdminPanel.setContent(new ResEditPanel(labels, labels.length, -1));
            }
        });

        delButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int selectedId = (int) table.getValueAt(row, 0);
                resDAOProxy.remove(selectedId);
                AdminPanel.setContent(new ResTablePanel());
            }
        });

        modifyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() == -1)
                    return;
                else {
                    int row = table.getSelectedRow();
                    int selectedId = (int) table.getValueAt(row, 0);
                    AdminPanel.setContent(new ResEditPanel(labels, labels.length, selectedId));
                }
            }
        });

        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String searchText = searchField.getText();
                Object[][] searchData = resDAOProxy.search(1, searchText);
                TableModel newTableModel = new DefaultTableModel(searchData, ResDAO.columnNames);
                table.setModel(newTableModel);
            }
        });

        topPanel.add(addButton);
        topPanel.add(delButton);
        topPanel.add(modifyButton);
        topPanel.add(searchButton);

        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(table, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(tablePanel, BorderLayout.CENTER);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}
