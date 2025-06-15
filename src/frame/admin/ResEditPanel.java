package frame.admin;

import DAO.ResDAO;
import frame.admin.AdminPanel;
import frame.admin.ResTablePanel;
import frame.resident.ResPanel;
import frame.resident.SelfInfoPanel;
import util.SystemConstants;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//model
public class ResEditPanel extends JInternalFrame {//所有编辑页面都可以用这个窗口
    public ResEditPanel(JLabel[] labels, int number, Object selectedId) {
        super("编辑",true,true,true,true);
        this.setSize(SystemConstants.INNERFRAME_WIDTH,SystemConstants.INNERFRAME_HEIGHT);
        int id =(int)selectedId;


        JPanel panel = new JPanel();
        //Box
        Box boxLeft = Box.createVerticalBox();//垂直
        Box boxRight= Box.createVerticalBox();
        Box boxBase = Box.createHorizontalBox();//水平

        for (int i = 1;i <= number;i++){//左边添加说明文本
            boxLeft.add(labels[i-1]);
            boxLeft.add(Box.createVerticalStrut(8));
        }
        boxLeft.add(Box.createVerticalStrut(30));

        JTextField[] textsField = new JTextField[number];
        for (int i = 1;i <= number;i++){//右边添加文本框
            JTextField textField = new JTextField(10);
            boxRight.add(textField);
            boxRight.add(Box.createVerticalStrut(5));
            textsField[i-1] = textField;//把文本框对象都加到数组里
        }
        JButton button = new JButton("提交");
        //selectedId <= -1 add
        Object[] newData = new Object[number+1];
//        if (selectedId <= -1) {
//
//
//        }
//        //selectedId > -1 modify
        if (id > -1){//修改
            //得到选中的数组 并放进文本框
            Object[] oldData = ResDAO.findById(id);
            for (int i = 1;i <= number;i++) {
                textsField[i-1].setText((String) oldData[i]);
            }
        }
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("提交编辑");
                for (int i = 1;i <= number;i++) {
                    //文本添加到newData中
                    newData[i] = textsField[i - 1].getText();
                    System.out.println(newData[i]);
                }
                if(id <= -1){
                    ResDAO.add(newData);
                    AdminPanel.setContent(new ResTablePanel());

                }
                else {//修改
                newData[0] = id;
                ResDAO.update(id,newData);
                //更换页面
                AdminPanel.setContent(new ResTablePanel());
                ResPanel.setContent(new SelfInfoPanel());
                }
            }
        });

        boxRight.add(button);
        boxBase.add(boxLeft);
        boxBase.add(boxRight);
        panel.add(boxBase);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}
