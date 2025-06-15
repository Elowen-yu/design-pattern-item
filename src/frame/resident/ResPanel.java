package frame.resident;

import javax.swing.*;

import frame.LoginPanel;
import frame.MainFrame;
import frame.admin.*;
import util.SystemConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//管理员登录后窗口

/**
 * model
 */
public class ResPanel extends JPanel {
    public static JDesktopPane resDesktopPane = new JDesktopPane();//用户唯一主面板
    public ResPanel() {
        this.setLayout(new BorderLayout());
        this.setBounds(0,0, SystemConstants.FRAME_WIDTH,SystemConstants.FRAME_HEIGHT);

        resDesktopPane.setSize(SystemConstants.FRAME_WIDTH-50,SystemConstants.FRAME_HEIGHT-50);
   /*
    用户界面JPanel
    系统菜单
        退出登录 修改密码
    个人信息
        序号 用户名 密码 住址 车牌
    物业费用
        缴费项目(共用界面)
        缴费状态
            序号 项目 缴费情况
    社区公告(共用界面)
    报修管理
        序号 项目 故障 预计花费 实际花费 维修情况
                                    已/等待处理

     */
        JMenuBar menuBar = new JMenuBar();

        JMenu systemMenu = new JMenu("系统菜单");
        JMenuItem logoutMenuI = new JMenuItem("退出登录");
        logoutMenuI.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainFrame.setContent(new LoginPanel());
            }
        });
        JMenuItem pwdMenuI = new JMenuItem("修改密码");
        pwdMenuI.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //打开修改密码窗口
                setContent(new RNewPwdPanel());
            }
        });
        systemMenu.add(logoutMenuI);
        systemMenu.add(pwdMenuI);
        JMenu resMenu = new JMenu("个人信息");
        resMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new SelfInfoPanel());
            }
        });

        JMenu payMenu = new JMenu("物业费用");
        JMenuItem payAnnounceMenuI = new JMenuItem("缴费项目");
        payAnnounceMenuI.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new RPayAnnouncePanel());
            }
        });
//        JMenuItem payStatusMenuI = new JMenuItem("缴费状态");
//        payStatusMenuI.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                setContent(new PayStatusPanel());
//            }
//        });
        payMenu.add(payAnnounceMenuI);
//        payMenu.add(payStatusMenuI);
//        JMenu noticeMenu = new JMenu("社区公告");
//        noticeMenu.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                super.mousePressed(e);
//            }
//        });
        JMenu repairMenu = new JMenu("报修管理");
        repairMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new RepairPanel());
            }
        });
        menuBar.add(systemMenu);
        menuBar.add(resMenu);
        menuBar.add(payMenu);
//        menuBar.add(noticeMenu);
        menuBar.add(repairMenu);
        this.add(menuBar,BorderLayout.NORTH);
        this.add(resDesktopPane,BorderLayout.CENTER);

        resDesktopPane.setVisible(true);
        this.setVisible(true);//可见 放最后
    }

    public static void setContent(JInternalFrame internalFrame){
        resDesktopPane.removeAll();
        resDesktopPane.repaint();
        resDesktopPane.add(internalFrame);
    }
}