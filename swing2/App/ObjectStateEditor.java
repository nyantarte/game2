package App;

import java.util.*;
import java.util.logging.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import App.Core.*;

public class ObjectStateEditor extends JPanel implements ActionListener, 
    PopupMenuListener ,MouseListener{
    private EditOperationListener m_listener;
    private Point m_clickPos;
    private ArrayList<SceneObjectState> m_states = new ArrayList<>();

    public ObjectStateEditor(EditOperationListener l) {
        m_listener = l;

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.addPopupMenuListener(this);
        JMenuItem addM = new JMenuItem("Add");
        addM.addActionListener(this);
        addM.setActionCommand(Config.ADD_ACTION_CMD);
        popupMenu.add(addM);

        setComponentPopupMenu(popupMenu);
        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        for (SceneObjectState ss : m_states) {
            SceneObjectStateEx sse = (SceneObjectStateEx) ss;
            g.drawString(ss.getName(), sse.getPos().x, sse.getPos().y);
            Rectangle r=sse.getRect();
            g.drawRect(r.x,r.y-SceneObjectStateEx.DEF_FONT_SIZE,r.width,r.height);
        }
        for (SceneObjectState ss : m_states) {
            SceneObjectStateEx sse = (SceneObjectStateEx) ss;
            String nextName = sse.getNext();
            if (null != nextName) {
                for (int i = 0; i < m_states.size(); ++i) {
                    SceneObjectStateEx nextState = (SceneObjectStateEx) m_states.get(i);
                    if (nextName.equals(nextState.getName())) {
                        Point b = sse.getCenterPos();
                        Point e = nextState.getCenterPos();
                        g.drawLine(b.x, b.y-SceneObjectStateEx.DEF_FONT_SIZE, e.x, e.y-SceneObjectStateEx.DEF_FONT_SIZE);
                    }
                }
            }
        }
    }

    public void setStates(ArrayList<SceneObjectState> s) {
        m_states.clear();
        for (SceneObjectState ss : s) {
            if (ss instanceof SceneObjectStateEx) {
                m_states.add(ss);
            } else {
                m_states.add(new SceneObjectStateEx(ss));
            }
        }
    }

    public void setStates(SceneObjectState[] s) {
        m_states.clear();
        for (SceneObjectState ss : s) {
            if (ss instanceof SceneObjectStateEx) {
                m_states.add(ss);
            } else {
                m_states.add(new SceneObjectStateEx(ss));
            }
        }
        this.repaint();
    }

    public ArrayList<SceneObjectState> getStates() {
        return m_states;
    }

    public void actionPerformed(ActionEvent e) {
        if (Config.ADD_ACTION_CMD == e.getActionCommand()) {
            SceneObjectStateEx t = new SceneObjectStateEx();
            String newName = JOptionPane.showInputDialog(this, "Name", t.getName());
            // 名前の修正が拒否された場合は新規作成しない
            if (null != newName) {
                t.setName(newName);
                t.setPos(m_clickPos);//new Point(m_clickPos.x,m_clickPos.y-SceneObjectStateEx.DEF_FONT_SIZE));
                m_states.add(t);
                m_listener.propertyChanged();
                this.repaint();
            }
        }
    }

    public void popupMenuCanceled(PopupMenuEvent e) {
    }

    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
    }

    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        m_clickPos = this.getMousePosition();
        
    }
    public void	mouseClicked(MouseEvent e){
        for(SceneObjectState ss:m_states){
            SceneObjectStateEx sse=(SceneObjectStateEx)ss;
            if(sse.getRect().contains(e.getPoint().x,e.getPoint().y+SceneObjectStateEx.DEF_FONT_SIZE)){
                m_listener.changeAssetSelected(sse);
                break;
            }
        }
    }
    public void	mouseEntered(MouseEvent e){}
    public void	mouseExited(MouseEvent e){}
    public void	mousePressed(MouseEvent e){}
    public void	mouseReleased(MouseEvent e){}

}
