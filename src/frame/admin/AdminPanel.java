package frame.admin;

import frame.LoginPanel;
import frame.MainFrame;
import util.SystemConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//管理员登录后窗口

/**
 * model
 */
public class AdminPanel extends JPanel {
    public static JDesktopPane adminDesktopPane = new JDesktopPane();//管理员唯一主面板
    public AdminPanel() {
    this.setLayout(new BorderLayout());
    this.setBounds(0,0, SystemConstants.FRAME_WIDTH,SystemConstants.FRAME_HEIGHT);

    adminDesktopPane.setSize(SystemConstants.FRAME_WIDTH-50,SystemConstants.FRAME_HEIGHT-50);
   /*
   系统菜单JMenu
     * 退出登录JMenuItem 修改密码JMenuItem
     * 住户信息JMenu
     * 管理员信息JMenu
     * 物业费用JMenu
     *  缴费项目JMenuItem
     *  缴费状态JMenuItem
     * 社区公告 JMenu
     * 报修管理 JMenu
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
                setContent(new NewPwdPanel());
            }
        });
        systemMenu.add(logoutMenuI);
        systemMenu.add(pwdMenuI);
        JMenu resMenu = new JMenu("住户信息");
        resMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new ResTablePanel());
            }
        });
        JMenu adminMenu = new JMenu("管理员信息");
        adminMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new AdminTablePanel());
            }
        });
        JMenu payMenu = new JMenu("物业费用");
        JMenuItem payAnnounceMenuI = new JMenuItem("缴费项目");
        payAnnounceMenuI.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new PayAnnouncePanel());
            }
        });
        JMenuItem payStatusMenuI = new JMenuItem("缴费状态");
        payStatusMenuI.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new PayStatusPanel());
            }
        });
        payMenu.add(payAnnounceMenuI);
        payMenu.add(payStatusMenuI);
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
                setContent(new ARepairPanel());
            }
        });
        menuBar.add(systemMenu);
        menuBar.add(resMenu);
        menuBar.add(adminMenu);
        menuBar.add(payMenu);
//        menuBar.add(noticeMenu);
        menuBar.add(repairMenu);
        this.add(menuBar,BorderLayout.NORTH);
        this.add(adminDesktopPane,BorderLayout.CENTER);

        adminDesktopPane.setVisible(true);
        this.setVisible(true);//可见 放最后
    }

    public static void setContent(JInternalFrame internalFrame){
        adminDesktopPane.removeAll();
        adminDesktopPane.repaint();
        adminDesktopPane.add(internalFrame);
    }
}
