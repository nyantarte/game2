package App;
import java.awt.event.*;
import javax.swing.*;

public class TileEditFieldPanel extends JPanel implements ActionListener{
    private ImageIcon m_tileImage;
    private JTextField m_nameField;
    private JCheckBox m_isMoveable;

    public TileEditFieldPanel(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JPanel p=new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.add(new JLabel("Name"));
        p.add(m_nameField=new JTextField());
        add(p);

        p=new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.add(new JLabel("Image"));
        p.add(new JLabel(m_tileImage=new ImageIcon()));
        JButton b=new JButton("Change");
        b.addActionListener(this);
        b.setActionCommand(Config.SELECT_IMAGE_ACTION_CMD);
        add(p);

        p=new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.add(m_isMoveable=new JCheckBox("Is movable"));
        add(p);
        

    }
    public void actionPerformed(ActionEvent e){
        JFileChooser dlg=new JFileChooser();
        dlg.setCurrentDirectory(GameSystem.getInstance().getImageDir());
        if(JFileChooser.APPROVE_OPTION==dlg.showOpenDialog(this)){
            
        }
    }
}
