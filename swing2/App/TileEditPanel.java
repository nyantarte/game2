package App;
import java.awt.*;
import javax.swing.*;

public class TileEditPanel extends JPanel implements EditOperationListener{
   private TileEditFieldPanel m_editFieldPanel;
    private TileEditLeftPanePanel m_leftPanePanel;
   public TileEditPanel(){

        setLayout(new BorderLayout());
        add(m_leftPanePanel=new TileEditLeftPanePanel(this),BorderLayout.WEST);
        add(m_editFieldPanel=new TileEditFieldPanel(),BorderLayout.CENTER);
    }
    public void addObject(){}
    public void saveObject(){}
    public void changeEditObject(Object newObj){}
    public void changeAssetSelected(Object newAsset){}
    public void configEditObject(){}
    public void positionClick(int x,int y){}
    public void propertyChanged(){

    }

}
