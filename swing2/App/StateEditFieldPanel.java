package App;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import App.Core.*;
import App.Core.SceneObjectState.TRIGGER_TYPE;
public class StateEditFieldPanel extends JPanel implements CaretListener,ItemListener{

    private EditOperationListener m_listener;
    private SceneObjectState m_target;
    private JTextField m_nameField;
    private JComboBox m_triggerField;
    private JTextField m_nextField;
    public StateEditFieldPanel(EditOperationListener l){
        m_listener=l;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel p=new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.add(new JLabel("Name"));
        p.add(m_nameField=new JTextField());
        m_nameField.addCaretListener(this);
        add(p);

        p=new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.add(new JLabel("Trigger"));
        p.add(m_triggerField=new JComboBox<>(SceneObjectState.TRIGGER_TYPE.values()));
        m_triggerField.addItemListener(this);
        add(p);

        p=new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.add(new JLabel("Next"));
        p.add(m_nextField=new JTextField());
        m_nextField.addCaretListener(this);
        add(p);


    }
    public void setTarget(SceneObjectState ss){
        m_target=ss;
        m_nameField.setText(m_target.getName());
        m_triggerField.setSelectedItem(m_target.getTrigger());
        m_nextField.setText(m_target.getNext());
    }
    public SceneObjectState getTarget(){
        return m_target;
    }
    public void caretUpdate(CaretEvent e){
        if(m_nameField== e.getSource()){
            m_target.setName(m_nameField.getText());
        }else if(m_nextField==e.getSource()){
            m_target.setNext(m_nextField.getText());
        }
        m_listener.propertyChanged();
    }
    public void itemStateChanged(ItemEvent e){
        if(m_triggerField==e.getSource()){
            m_target.setTrigger((TRIGGER_TYPE) m_triggerField.getSelectedItem());
        }        
        m_listener.propertyChanged();

    }
}
