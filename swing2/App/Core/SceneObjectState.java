package App.Core;

public class SceneObjectState {
    private String m_name;
    private String m_next;
    public enum TRIGGER_TYPE{
        NONE,
        ROOT,
        HIT_CHECK,
        DAMAGE_CHECK,
        LEFT_MOVE,
        RIGHT_MOVE,
        UP_MOVE,
        DOWN_MOVE,

    }
    private TRIGGER_TYPE m_trigger=TRIGGER_TYPE.NONE;
    public String getName(){
        return m_name;

    }
    public void setName(String n){
        m_name=n;
    }
    public String getNext(){
        return m_next;
    }
    public void setNext(String n){
        m_next=n;
    }
    public TRIGGER_TYPE getTrigger(){
        return m_trigger;
    }
    public void setTrigger(TRIGGER_TYPE t){
        m_trigger=t;
    }

    
}
