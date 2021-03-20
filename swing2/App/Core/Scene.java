package App.Core;

public class Scene {
    private String m_name;
    private Map m_map;

    public Scene(String n,Map m){
        setName(n);
        setMap(m);
    }
    public void setName(String n){
        m_name=n;
    }
    public String getName(){
        return m_name;
    }
    public void setMap(Map m){
        m_map=m;
    }
    public Map getMap(){
        return m_map;
    }
}
