package App;
import java.awt.*;
import javax.swing.*;
public class SceneEditLeftPanePanel extends JPanel{
    private SceneListPanel m_list;
    public SceneEditLeftPanePanel(EditOperationListener l){
        setLayout(new BorderLayout());
        add(new AssetListPanel(l),BorderLayout.NORTH);
        add(m_list=new SceneListPanel(l),BorderLayout.SOUTH);
    }
    public SceneListPanel getSceneListPanel(){
        return m_list;
    }
}
