package App;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import App.Core.SceneObject;
public class ObjectListPanel extends JPanel implements ListSelectionListener{
    
    private DefaultListModel m_model;
    private JList m_objList;
    private EditOperationListener m_listener;
    public ObjectListPanel(EditOperationListener l){
        m_listener=l;
        setLayout(new BorderLayout());
        m_model=new DefaultListModel();
        m_objList=new JList(m_model);
        m_objList.addListSelectionListener(this);
        add(m_objList,BorderLayout.CENTER);
        updateList();
    }
    public void updateList(){
        m_model.clear();
        for(File f:GameSystem.getInstance().getObjFiles()){
            m_model.addElement(f.getName());
        }
    }

    public void valueChanged(ListSelectionEvent e){
        String fileName=(String)m_model.getElementAt(e.getFirstIndex());
        SceneObject o=GameSystem.getInstance().getObject(fileName);
        m_listener.changeEditObject(o);
    }
}
