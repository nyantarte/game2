package App;
import java.awt.*;
import javax.swing.*;
public class ObjectEditLeftPanePanel extends JPanel{
    private ObjectListPanel m_listPanel;
    private ListControlPanel m_controlPanel;
    public ObjectEditLeftPanePanel(EditOperationListener l){
        setLayout(new BorderLayout());
        add(m_listPanel= new ObjectListPanel(l),BorderLayout.CENTER);
        add(m_controlPanel=new ListControlPanel(l),BorderLayout.SOUTH);
    }

    public ObjectListPanel getObjectListPanel(){
        return m_listPanel;
    }
}
