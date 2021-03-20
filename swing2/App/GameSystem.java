package App;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import java.awt.*;
import App.Core.*;
import App.Core.SceneObjectState.TRIGGER_TYPE;

import java.util.List;
public class GameSystem {
    private static GameSystem s_self=new GameSystem();
    private File m_rootDir;
    private File m_sceneDir;
    private File m_tileDir;
    private File m_objDir;
    private File m_imageDir;
    public static GameSystem getInstance(){
        return s_self;
    }

    public void setRootDir(File dir){
        m_rootDir=dir;

        m_sceneDir=new File(m_rootDir,Config.SCENE_DIR_FROM_ROOT);
        if(!m_sceneDir.exists()){
            m_sceneDir.mkdir();
        }
        m_tileDir=new File(m_rootDir,Config.TILE_DIR_FROM_ROOT);
        if(!m_tileDir.exists()){
            m_tileDir.mkdir();
        }
        m_objDir=new File(m_rootDir,Config.OBJ_DIR_FROM_ROOT);
        if(!m_objDir.exists()){
            m_objDir.mkdir();
        }

        m_imageDir=new File(m_rootDir,Config.IMAGE_DIR_FROM_ROOT);
        if(!m_imageDir.exists()){
            m_imageDir.mkdir();
        }
    }

    public List<File> getSceneFiles(){
        ArrayList<File> list=new ArrayList<>();
        if(null!=m_sceneDir){
            for(String fileName:m_sceneDir.list()){
                if(fileName.endsWith(".csv")){
                    list.add(new File(m_sceneDir,fileName));
                }
            }
        }
        return list;
    }

    public List<File> getTileFiles(){
        ArrayList<File> list=new ArrayList<>();
        if(null!=m_tileDir){
            for(String fileName:m_tileDir.list()){
                if(fileName.endsWith(".csv")){
                    list.add(new File(m_tileDir,fileName));
                }
            }
        }
        return list;
    }
    public List<File> getObjFiles(){
        ArrayList<File> list=new ArrayList<>();
        if(null!=m_objDir){
            for(String fileName:m_objDir.list()){
                if(fileName.endsWith(".csv")){
                    list.add(new File(m_objDir,fileName));
                }
            }
        }
        return list;
    }

    public Scene createScene(){
        if(null!=m_sceneDir){
            return new Scene("New scene",new App.Core.Map(Config.DEFAULT_MAP_W,Config.DEAFULT_MAP_H));
        }
        return null;
    }
    public void saveScene(Scene s){
        if(null!=m_sceneDir){
            File f=new File(m_sceneDir,String.format("%s.csv",s.getName()));
            try{
                BufferedWriter w=new BufferedWriter(new FileWriter(f));
                w.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public File getImageDir(){
        return m_imageDir;
    }

    public SceneObject createObject(){
        if(null!=m_objDir){
            return new SceneObject();
        }
        return null;
    }
    public SceneObject getObject(String fileName){
        return getObject(new File(m_objDir,fileName));
    }
    public SceneObject getObject(File f){
        try{
            SceneObject obj=new SceneObject();
            ArrayList<SceneObjectState> ssList=new ArrayList<>();
            BufferedReader r=new BufferedReader(new FileReader(f));
            String line;
            while(null!=(line=r.readLine())){
                String[] params=line.split(",");
                if("type".equals(params[0])){

                }else if("name".equals(params[0])){
                    obj.setName(params[1]);
                }else if("state".equals(params[0])){
                    SceneObjectState ss=new SceneObjectState();
                    ss.setName(params[1]);
                    ss.setTrigger(TRIGGER_TYPE.valueOf(params[2]));
                    ss.setNext(params[3]);
                    ssList.add(ss);
                }else if("state_ex".equals(params[0])){
                    SceneObjectStateEx sse=new SceneObjectStateEx();
                    sse.setName(params[1]);
                    int x=Integer.valueOf(params[2]);
                    int y=Integer.valueOf(params[3]);
                    sse.setPos(new Point(x,y));
                    sse.setTrigger(TRIGGER_TYPE.valueOf(params[4]));
                    sse.setNext(params[5]);
                    ssList.add(sse);
                }
            }
            r.close();
            SceneObjectState[] ssA=new SceneObjectState[ssList.size()];
            ssList.toArray(ssA);
            obj.setStates(ssA);
            Logger.getGlobal().info(String.format("object %s loaded.",obj.getName()));
            return obj;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void saveObject(SceneObject o){
        if(null!=m_objDir){
            File f=new File(m_objDir,String.format("%s.csv",o.getName()));
            try{
                BufferedWriter w=new BufferedWriter(new FileWriter(f));
                w.write("type,object");
                w.newLine();
                w.write(String.format("name,%s",o.getName()));
                if (null != o.getStates()) {
                    for (SceneObjectState ss : o.getStates()) {
                        w.newLine();
                        if (ss instanceof SceneObjectStateEx) {
                            SceneObjectStateEx sse = (SceneObjectStateEx) ss;
                            w.write(String.format("state_ex,%s,%d,%d,%s,%s", sse.getName(), sse.getPos().x, sse.getPos().y,sse.getTrigger().toString(),sse.getNext()));
                        } else {
                            w.write(String.format("state,%s,%s,%s", ss.getName(),ss.getTrigger().toString(),ss.getName()));
                        }

                    }
                }
                w.close();
                Logger.getGlobal().info(String.format("object %s is saved.",o.getName()));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
