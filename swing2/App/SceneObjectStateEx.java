package App;
import java.awt.*;
import App.Core.*;
public class SceneObjectStateEx extends SceneObjectState{
    private Rectangle m_rect=new Rectangle();

    public static int DEF_FONT_SIZE=16;
    public SceneObjectStateEx(){
    }
    public SceneObjectStateEx(SceneObjectState ss){
        setName(ss.getName());
        setTrigger(ss.getTrigger());
        setNext(ss.getNext());
    }
    public Point getPos(){
        return m_rect.getLocation();
    }
    public void setPos(Point po){
        m_rect=new Rectangle(po.x,po.y,getName().length()*DEF_FONT_SIZE,DEF_FONT_SIZE);
    }
    public Rectangle getRect(){
        return m_rect;
    }
    public Point getCenterPos(){
        return new Point(m_rect.x+m_rect.width/2,m_rect.y+m_rect.height/2);
    }
    public void setName(String n){
        super.setName(n);
        m_rect.width=n.length()*DEF_FONT_SIZE;
    }
}
