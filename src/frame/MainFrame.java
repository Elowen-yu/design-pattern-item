package frame;

import pojo.User;
import util.SystemConstants;

import javax.swing.*;
import java.awt.*;
//测试
public class MainFrame {
    public static JFrame frame = new JFrame();
    public static User user;
    public static void main(String[] args) {
        frame.setLayout(new BorderLayout());
        frame.setSize(SystemConstants.FRAME_WIDTH,SystemConstants.FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭
        frame.setLocationRelativeTo(null);//居中
         frame.setVisible(true);//可见放最后
        frame.setContentPane(new LoginPanel());


    }

    public static void setContent(JPanel panel){
        frame.setContentPane(panel);
    }
}
