package App;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ListControlPanel extends JPanel implements ActionListener{
    private JButton m_configButton;
    private JButton m_addButton;
    private JButton m_saveButton;
    private EditOperationListener m_listener;
    public ListControlPanel(EditOperationListener l){
        m_listener=l;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        m_configButton=new JButton("Config");
        m_configButton.addActionListener(this);
        m_configButton.setActionCommand(Config.CONFIG_ACTION_CMD);
        add(m_configButton);

        m_addButton=new JButton("Add");
        m_addButton.addActionListener(this);
        m_addButton.setActionCommand(Config.ADD_ACTION_CMD);
        add(m_addButton);

        m_saveButton=new JButton("Save");
        m_saveButton.addActionListener(this);
        m_saveButton.setActionCommand(Config.SAVE_ACTION_CMD);
        add(m_saveButton);

    }

    public void actionPerformed(ActionEvent e){
        String cmd=e.getActionCommand();
        if(Config.CONFIG_ACTION_CMD==cmd){
            m_listener.configEditObject();
        }else if(Config.ADD_ACTION_CMD==cmd){
            m_listener.addObject();
        }else if(Config.SAVE_ACTION_CMD==cmd){
            m_listener.saveObject();
        }
    }    
}
