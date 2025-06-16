package frame;

import javax.swing.*;
import java.awt.*;

public abstract class BasePanel extends JInternalFrame {
    protected JPanel mainPanel;
    
    public BasePanel(String title) {
        super(title, true, true, true, true);
        this.setSize(800, 600);
        
        mainPanel = new JPanel(new BorderLayout());
        this.setContentPane(mainPanel);
        
        initComponents();
        this.setVisible(true);
    }
    
    protected abstract void initComponents();
} 