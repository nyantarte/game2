package App;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import App.Core.*;
public class SceneListPanel extends JPanel implements ActionListener{
    private DefaultListModel m_model;
    private JList m_fileList;
    private EditOperationListener m_listener;
    public SceneListPanel(EditOperationListener l){
        m_listener=l;
        setLayout(new BorderLayout());
        m_model=new DefaultListModel<>();

        m_fileList=new JList(m_model);
        add(m_fileList,BorderLayout.CENTER);
        ListControlPanel cPanel=new ListControlPanel(m_listener);
        add(cPanel,BorderLayout.SOUTH);
        updateList();

    }
    public void updateList(){
        m_model.clear();
        for(File f:GameSystem.getInstance().getSceneFiles()){
            m_model.addElement(f.getName());
        }
    }

    public void actionPerformed(ActionEvent e){}

    public void addObject(Scene s){
        m_model.addElement(s);
    }
}
