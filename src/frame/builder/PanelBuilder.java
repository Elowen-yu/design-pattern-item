package frame.builder;

import javax.swing.*;
import java.awt.*;

public abstract class PanelBuilder {
    protected JInternalFrame panel;
    
    public void createNewPanel() {
        panel = new JInternalFrame();
    }
    
    public JInternalFrame getPanel() {
        return panel;
    }
    
    // 设置面板基本属性
    public abstract void buildBasicProperties();
    
    // 创建顶部面板
    public abstract void buildTopPanel();
    
    // 创建主面板
    public abstract void buildMainPanel();
    
    // 创建底部面板
    public abstract void buildBottomPanel();
    
    // 添加事件监听器
    public abstract void addEventListeners();
    
    // 设置数据模型
    public abstract void setDataModel();
} 