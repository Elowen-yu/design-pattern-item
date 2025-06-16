package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class BaseEditPanel extends BasePanel {
    protected JLabel[] labels;
    protected JTextField[] textFields;
    protected JButton submitButton;
    protected int id;
    
    public BaseEditPanel(String title, JLabel[] labels, int id) {
        super(title);
        this.labels = labels;
        this.id = id;
    }
    
    @Override
    protected void initComponents() {
        // 创建主面板
        JPanel panel = new JPanel();
        
        // 创建Box布局
        Box boxLeft = Box.createVerticalBox();
        Box boxRight = Box.createVerticalBox();
        Box boxBase = Box.createHorizontalBox();
        
        // 添加标签
        for (int i = 0; i < labels.length; i++) {
            boxLeft.add(labels[i]);
            boxLeft.add(Box.createVerticalStrut(8));
        }
        boxLeft.add(Box.createVerticalStrut(30));
        
        // 创建文本框
        textFields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            JTextField textField = new JTextField(10);
            boxRight.add(textField);
            boxRight.add(Box.createVerticalStrut(5));
            textFields[i] = textField;
        }
        
        // 创建提交按钮
        submitButton = new JButton("提交");
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleSubmit();
            }
        });
        
        // 如果是修改模式，加载现有数据
        if (id > -1) {
            loadData();
        }
        
        // 添加组件到面板
        boxRight.add(submitButton);
        boxBase.add(boxLeft);
        boxBase.add(boxRight);
        panel.add(boxBase);
        
        // 设置主面板
        mainPanel.add(panel, BorderLayout.CENTER);
    }
    
    // 加载数据
    protected abstract void loadData();
    
    // 处理提交
    protected abstract void handleSubmit();
    
    // 获取文本框数据
    protected Object[] getTextFieldData() {
        Object[] data = new Object[textFields.length + 1];
        for (int i = 0; i < textFields.length; i++) {
            data[i + 1] = textFields[i].getText();
        }
        return data;
    }
    
    // 设置文本框数据
    protected void setTextFieldData(Object[] data) {
        for (int i = 0; i < textFields.length; i++) {
            textFields[i].setText((String) data[i + 1]);
        }
    }
} 