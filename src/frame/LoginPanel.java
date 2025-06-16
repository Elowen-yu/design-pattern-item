package frame;

import DAO.AdminDAO;
import DAO.ResDAO;
import frame.admin.AdminPanel;
import frame.resident.ResPanel;
import pojo.User;
import strategy.AdminAuthenticationStrategy;
import strategy.AuthenticationContext;
import strategy.ResidentAuthenticationStrategy;
import util.SystemConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//登录界面
public class LoginPanel extends JPanel {
    //用于判断
    private User loginUser =null;
    private JPanel newPanel = null;
    private final AuthenticationContext authContext;

    public LoginPanel() {
        this.setBounds(0,0, SystemConstants.FRAME_WIDTH,SystemConstants.FRAME_HEIGHT);
        //this.setLayout(new BorderLayout());
        //用户名 密码 单选框 登录按钮
        this.add(new JLabel("用户名"));
        TextField userField = new TextField(10);
        this.add(userField);
        this.add(new JLabel("密码"));
        JPasswordField passwordField = new JPasswordField(10);
        this.add(passwordField);
        JRadioButton adminRadio = new JRadioButton("管理员",true);//默认选中
        JRadioButton resRadio = new JRadioButton("业主");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(adminRadio);
        buttonGroup.add(resRadio);
        this.add(adminRadio);
        this.add(resRadio);
        JButton loginButton = new JButton("登录");
        this.setVisible(true);//可见 放最后
        authContext = new AuthenticationContext();

        //登录的click处理
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("登录");
                //得到输入内容
                String username = userField.getText();
                String password = new String(passwordField.getPassword());

                if(adminRadio.isSelected()){
                    authContext.setStrategy(new AdminAuthenticationStrategy());
                    newPanel = new AdminPanel();
                }
                else if (resRadio.isSelected()) {
                    authContext.setStrategy(new ResidentAuthenticationStrategy());
                    newPanel = new ResPanel();
                }

                loginUser = authContext.authenticate(username, password);
                if(loginUser == null) {
                    JOptionPane.showMessageDialog(loginButton.getParent(), "用户名或密码错误",
                            "系统提示", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    MainFrame.setContent(newPanel);
                    MainFrame.user = loginUser;
                }
            }
        });
        this.add(loginButton);



    }
}
