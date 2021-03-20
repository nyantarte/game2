package App;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MainWindow extends JFrame implements ActionListener{
    private JTabbedPane m_tabPane;
    private SceneEditPanel m_sceneEditPanel;
    private ObjectEditPanel m_objectEditPanel;
    public MainWindow(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(Config.MAIN_WIN_W,Config.MAIN_WIN_H);

        m_tabPane=new JTabbedPane();
        m_tabPane.addTab("Scene",m_sceneEditPanel=new SceneEditPanel());
        m_tabPane.addTab("Tiles",new TileEditPanel());
        m_tabPane.addTab("Objects",m_objectEditPanel= new ObjectEditPanel());
        m_tabPane.addTab("Assets",new AssetEditPanel());
        add(m_tabPane,BorderLayout.CENTER);


        JMenuBar mBar=new JMenuBar();
        JMenu fileM=new JMenu("File");
        JMenuItem openProjM=new JMenuItem("Open project");
        openProjM.addActionListener(this);
        openProjM.setActionCommand(Config.OPEN_PROJ_ACTION_CMD);
        fileM.add(openProjM);
        mBar.add(fileM);
        JMenuItem closeProjM=new JMenuItem("Close project");
        fileM.add(closeProjM);
        setJMenuBar(mBar);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(Config.OPEN_PROJ_ACTION_CMD==e.getActionCommand()){
            JFileChooser dlg=new JFileChooser(System.getProperty("user.dir"));
            dlg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(JFileChooser.APPROVE_OPTION==dlg.showOpenDialog(this)){
                GameSystem.getInstance().setRootDir(dlg.getSelectedFile());
                m_sceneEditPanel.projDirChanged();
                m_objectEditPanel.projDirChanged();
            }
            
        }
    }
}
