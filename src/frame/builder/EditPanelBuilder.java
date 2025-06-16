package frame.builder;

import DAO.DAOProxy;
import util.SystemConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditPanelBuilder extends PanelBuilder {
    private String title;
    private JLabel[] labels;
    private int id;
    private DAOProxy daoProxy;
    private JTextField[] textFields;
    private JButton submitButton;
    
    public EditPanelBuilder(String title, JLabel[] labels, int id, DAOProxy daoProxy) {
        this.title = title;
        this.labels = labels;
        this.id = id;
        this.daoProxy = daoProxy;
    }
    
    @Override
    public void buildBasicProperties() {
        panel.setTitle(title);
        panel.setClosable(true);
        panel.setMaximizable(true);
        panel.setIconifiable(true);
        panel.setResizable(true);
        panel.setSize(SystemConstants.INNERFRAME_WIDTH, SystemConstants.INNERFRAME_HEIGHT);
    }
    
    @Override
    public void buildTopPanel() {
        // 编辑面板不需要顶部面板
    }
    
    @Override
    public void buildMainPanel() {
        JPanel mainPanel = new JPanel();
        Box boxLeft = Box.createVerticalBox();
        Box boxRight = Box.createVerticalBox();
        Box boxBase = Box.createHorizontalBox();
        
        // 添加标签
        for (JLabel label : labels) {
            boxLeft.add(label);
            boxLeft.add(Box.createVerticalStrut(8));
        }
        boxLeft.add(Box.createVerticalStrut(30));
        
        // 添加文本框
        textFields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            JTextField textField = new JTextField(10);
            boxRight.add(textField);
            boxRight.add(Box.createVerticalStrut(5));
            textFields[i] = textField;
        }
        
        // 添加提交按钮
        submitButton = new JButton("提交");
        boxRight.add(submitButton);
        
        boxBase.add(boxLeft);
        boxBase.add(boxRight);
        mainPanel.add(boxBase);
        
        panel.add(mainPanel);
    }
    
    @Override
    public void buildBottomPanel() {
        // 编辑面板不需要底部面板
    }
    
    @Override
    public void addEventListeners() {
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object[] newData = new Object[labels.length + 1];
                for (int i = 0; i < labels.length; i++) {
                    newData[i + 1] = textFields[i].getText();
                }
                
                if (id <= -1) {
                    daoProxy.add(newData);
                } else {
                    newData[0] = id;
                    daoProxy.update(id, newData);
                }
                
                // 子类实现具体逻辑
            }
        });
    }
    
    @Override
    public void setDataModel() {
        if (id > -1) {
            Object[] oldData = daoProxy.findById(id);
            for (int i = 0; i < labels.length; i++) {
                textFields[i].setText((String) oldData[i + 1]);
            }
        }
    }
    
    // 供子类使用的getter方法
    protected JTextField[] getTextFields() {
        return textFields;
    }
    
    protected JButton getSubmitButton() {
        return submitButton;
    }
    
    protected int getId() {
        return id;
    }
} 