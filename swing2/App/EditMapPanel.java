package App;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.time.Duration;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import App.Core.*;
public class EditMapPanel extends JPanel implements MouseListener{
    private Dimension m_mapSize;
    private EditOperationListener m_listener;
    private App.Core.Map m_targetMap;
    private ArrayList<Tile> m_tileList=new ArrayList<>();
    public EditMapPanel(EditOperationListener l){
        m_listener=l;
        m_mapSize=new Dimension(Config.DEFAULT_MAP_W,Config.DEAFULT_MAP_H);

        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g){

        super.paint(g);
        int gW=this.getWidth()/m_mapSize.width;
        int gH=this.getHeight()/m_mapSize.height;

        g.setColor(Color.BLACK);

        for(int i=0;i < m_mapSize.height;++i){
            for(int j=0;j < m_mapSize.width;++j){
                g.drawRect(gW*j, gH*i, gW,gH);
            }
        }
    }

    public void mouseClicked(MouseEvent e){
        m_listener.positionClick(e.getX(), e.getY());
    }
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public App.Core.Map getMap(){
        return m_targetMap;
    }
    public ArrayList<Tile> getTileList(){
        return m_tileList;
    }
}
