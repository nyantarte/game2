package App;
import java.awt.*;
import javax.swing.*;
public class TileEditLeftPanePanel extends JPanel{
    private TileListPanel m_listPanel;
    public TileEditLeftPanePanel(EditOperationListener l){
        setLayout(new BorderLayout());
        add(m_listPanel=new TileListPanel(l),BorderLayout.CENTER);
        add(new ListControlPanel(l),BorderLayout.SOUTH);
    }
}
