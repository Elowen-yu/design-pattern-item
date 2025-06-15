package frame.resident;


import DAO.ResDAO;
import frame.LoginPanel;
import frame.MainFrame;
import util.SystemConstants;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//用户修改密码窗口
public class RNewPwdPanel extends JInternalFrame {
    public RNewPwdPanel() {
        super("修改密码",true,true,true,true);
        this.setSize(SystemConstants.INNERFRAME_WIDTH,SystemConstants.INNERFRAME_HEIGHT);

        //容器 组件：新密码文本 密码框 确认修改按钮
        JPanel panel = new JPanel();

        panel.add(new JLabel("新密码"));
        JPasswordField newPwdField = new JPasswordField(10);
        panel.add(newPwdField);
        JButton confirmButton = new JButton("确认修改");
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //得到新密码
                String newPassword = new String(newPwdField.getPassword());
                //改变当前user里的旧密码
//                AdminDAO.user.setPassword(newPassword);
                ResDAO.updatePassword(newPassword);
                //返回登录页面
                MainFrame.setContent(new LoginPanel());
            }
        });
        panel.add(confirmButton);

        this.add(panel);
        this.setVisible(true);
    }
}
