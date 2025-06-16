package frame.builder;

import javax.swing.JInternalFrame;

public class PanelDirector {
    private PanelBuilder builder;
    
    public void setBuilder(PanelBuilder builder) {
        this.builder = builder;
    }
    
    public JInternalFrame constructPanel() {
        builder.createNewPanel();
        builder.buildBasicProperties();
        builder.buildTopPanel();
        builder.buildMainPanel();
        builder.buildBottomPanel();
        builder.addEventListeners();
        builder.setDataModel();
        return builder.getPanel();
    }
} 