package App;
import java.awt.*;
import javax.swing.*;
public class AssetListPanel extends JPanel{

    public AssetListPanel(EditOperationListener l){
        setLayout(new BorderLayout());
        JTabbedPane tPane=new JTabbedPane();
        tPane.addTab("Tiles", new TileListPanel(l));
        tPane.addTab("Objects", new ObjectListPanel(l));
        add(tPane,BorderLayout.CENTER);
    }
}
