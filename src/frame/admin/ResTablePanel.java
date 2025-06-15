package frame.admin;

import DAO.ResDAO;
import util.SystemConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//管理员 用户信息窗口 增删改查

/**
 * model
 */
public class ResTablePanel extends JInternalFrame{
    /*住户信息JMenu JInternalFrame JTable
     *  序号 用户名 密码 住址 车牌
     */

    public ResTablePanel() {
        super("住户信息",true,true,true,true);
        this.setSize(SystemConstants.INNERFRAME_WIDTH,SystemConstants.INNERFRAME_HEIGHT);
        this.setLayout(new BorderLayout());

        //容器 组件： tablePanel <-table <- tableModel
        //           topPanel: 查询文本 文本框 四个按钮
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(0,0,SystemConstants.INNERFRAME_WIDTH,SystemConstants.INNERFRAME_HEIGHT);

        JLabel[] labels = {new JLabel("用户名"),new JLabel("密码"),new JLabel("住址"),new JLabel("车牌")};
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
        JTable table = new JTable(){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        TableModel tableModel = new DefaultTableModel(ResDAO.toArray(ResDAO.getAllData()),
                ResDAO.columnNames);//添加两个数组进去
        table.setModel(tableModel);


        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               //切换面板
                AdminPanel.setContent(new ResEditPanel(labels,labels.length,-1));
            }
        });
        delButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int selectedId = (int)table.getValueAt(row,0);
                ResDAO.remove(selectedId);
                AdminPanel.setContent(new ResTablePanel());
            }
        });
        modifyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //没选中行 就啥都不执行
                if(table.getSelectedRow() == -1)
                    return;
                //选中行 得到该行的id 切换页面
                else{
                    int row = table.getSelectedRow();
                    int selectedId = (int)table.getValueAt(row,0);
                    AdminPanel.setContent(new ResEditPanel(labels,labels.length,selectedId));
                }

            }
        });
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //得到搜索框文本
                String searchText = searchField.getText();
                Object[][] searchData = ResDAO.search(1,searchText);
                //创建新DefaultTableModel并传入新二维数组和属性
                TableModel newTableModel = new DefaultTableModel(searchData, ResDAO.columnNames);
                //更新表格
                table.setModel(newTableModel);
            }
        });

        topPanel.add(addButton);
        topPanel.add(delButton);
        topPanel.add(modifyButton);
        topPanel.add(searchButton);

        //添加表头
        tablePanel.add(table.getTableHeader(),BorderLayout.NORTH);
        tablePanel.add(table,BorderLayout.CENTER);


        panel.add(topPanel,BorderLayout.NORTH);
        panel.add(tablePanel, BorderLayout.CENTER);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}
