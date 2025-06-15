package frame.resident;

import DAO.ResDAO;
import frame.admin.ResEditPanel;
import util.SystemConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//管理员 用户信息窗口 删改

/**
 * model
 */
public class SelfInfoPanel extends JInternalFrame{
    /*个人信息JMenu JInternalFrame JTable
     *  序号 用户名 密码 住址 车牌
     */

    public SelfInfoPanel() {
        super("个人信息",true,true,true,true);
        this.setSize(SystemConstants.INNERFRAME_WIDTH,SystemConstants.INNERFRAME_HEIGHT);
        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(0,0,SystemConstants.INNERFRAME_WIDTH,SystemConstants.INNERFRAME_HEIGHT);

        JLabel[] labels = {new JLabel("用户名"),new JLabel("密码"),new JLabel("住址"),new JLabel("车牌")};
        JPanel topPanel = new JPanel();

        JButton modifyButton = new JButton("修改");

        //tablePanel
        JPanel tablePanel = new JPanel(new BorderLayout());
        JTable table = new JTable(){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };

        TableModel tableModel = new DefaultTableModel(ResDAO.toArray(ResDAO.getUserData()),
                ResDAO.columnNames);//添加两个数组进去
        table.setModel(tableModel);


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
                    ResPanel.setContent(new ResEditPanel(labels,labels.length,selectedId));
                }

            }
        });

        topPanel.add(modifyButton);


        //添加表头
        tablePanel.add(table.getTableHeader(),BorderLayout.NORTH);
        tablePanel.add(table,BorderLayout.CENTER);


        panel.add(topPanel,BorderLayout.NORTH);
        panel.add(tablePanel, BorderLayout.CENTER);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}