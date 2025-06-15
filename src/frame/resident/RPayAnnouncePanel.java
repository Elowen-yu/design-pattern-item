package frame.resident;

import DAO.PADAO;
import frame.admin.AdminPanel;
import frame.admin.PayAnnounceEditPanel;
import util.SystemConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RPayAnnouncePanel extends JInternalFrame {
    //是一个tablepanel
    //缴费项目JMenuItem JTable
    //序号 项目 收费标准 截止日期
    public RPayAnnouncePanel() {
        super("缴费项目", true, true, true, true);
        this.setSize(SystemConstants.INNERFRAME_WIDTH, SystemConstants.INNERFRAME_HEIGHT);
        this.setLayout(new BorderLayout());

        //容器 组件： tablePanel <-table <- tableModel
        //           topPanel: 查询文本 文本框 四个按钮
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(0, 0, SystemConstants.INNERFRAME_WIDTH, SystemConstants.INNERFRAME_HEIGHT);

        //序号 项目 收费标准 截止日期
        //修改/添加窗口的文本数组
        JLabel[] labels = {new JLabel("项目"), new JLabel("收费标准"), new JLabel("截止日期")};
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("项目"));
        JTextField searchField = new JTextField(10);
        topPanel.add(searchField);
        JButton searchButton = new JButton("查询");

        //tablePanel
        JPanel tablePanel = new JPanel(new BorderLayout());
        JTable table = new JTable(){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        TableModel tableModel = new DefaultTableModel(PADAO.toArray(PADAO.getAllData()),
                PADAO.columnNames);//添加两个数组进去
        table.setModel(tableModel);


        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //得到搜索框文本
                String searchText = searchField.getText();
                Object[][] searchData = PADAO.search(1, searchText);
                //创建新DefaultTableModel并传入新二维数组和属性
                TableModel newTableModel = new DefaultTableModel(searchData, PADAO.columnNames);
                //更新表格
                table.setModel(newTableModel);
            }
        });


        topPanel.add(searchButton);

        //添加表头
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(table, BorderLayout.CENTER);


        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(tablePanel, BorderLayout.CENTER);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}
