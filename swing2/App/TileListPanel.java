package App;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TileListPanel extends JPanel implements ListSelectionListener{
    private DefaultListModel m_model;
    private JList m_tileList;
    private EditOperationListener m_listener;
    public TileListPanel(EditOperationListener l){
        m_listener=l;
        setLayout(new BorderLayout());
        m_model=new DefaultListModel<>();
        m_tileList=new JList(m_model);
        m_tileList.addListSelectionListener(this);
        add(m_tileList,BorderLayout.CENTER);
        updateList();
    }

    private void updateList(){
        m_model.clear();
        for(File f:GameSystem.getInstance().getTileFiles()){
            m_model.addElement(f);
        }
    }

    public void valueChanged(ListSelectionEvent e){
        if(-1!=e.getFirstIndex()){
            Object o=m_model.get(e.getFirstIndex());
            m_listener.changeAssetSelected(o);
        }
    }
}
