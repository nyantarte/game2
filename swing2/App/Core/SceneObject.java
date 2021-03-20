package App.Core;
public class SceneObject{
    private String m_name;
    private SceneObjectState m_current;
    private SceneObjectState[] m_states;
    public String getName(){
        return m_name;
    }
    public void setName(String n){
        m_name=n;
    }
    public SceneObjectState[] getStates(){
        return m_states;
    }
    public void setStates(SceneObjectState[] s){
        m_states=s;
    }
}