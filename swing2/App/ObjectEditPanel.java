package App;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;

import javax.swing.*;
import App.Core.*;
public class ObjectEditPanel extends JPanel implements EditOperationListener{
    private ObjectEditLeftPanePanel m_leftPane;
    private ObjectStateEditor m_stateEditor;
    private StateEditFieldPanel m_fieldPanel;
    private SceneObject m_targetObject;
    private boolean m_isSaved=true;
    public ObjectEditPanel(){
        setLayout(new BorderLayout());
        add(m_leftPane=new ObjectEditLeftPanePanel(this),BorderLayout.WEST);
        add(m_stateEditor=new ObjectStateEditor(this),BorderLayout.CENTER);
        add(m_fieldPanel=new StateEditFieldPanel(this),BorderLayout.EAST);
        
    }
    public void addObject(){

        saveObject();
        //インスタンスを新規作成し、名前をつけて新規保存する
        m_targetObject=GameSystem.getInstance().createObject();
        if(null!=m_targetObject){
            String newName=JOptionPane.showInputDialog(this, "Name",m_targetObject.getName());
            //名前の修正が拒否された場合は新規作成しない
            if(null!=newName){
                m_targetObject.setName(newName);
                GameSystem.getInstance().saveObject(m_targetObject);
                m_leftPane.getObjectListPanel().updateList();
            }else{
                m_targetObject=null;
            }
        }

    }
    public void saveObject(){
        //未保存なら現在編集中の内容を保存する
        if(null!=m_targetObject && !m_isSaved){
            SceneObjectState[] tmpA=new SceneObjectState[m_stateEditor.getStates().size()];
            m_stateEditor.getStates().toArray(tmpA);
            m_targetObject.setStates(tmpA);
            GameSystem.getInstance().saveObject(m_targetObject);
            m_isSaved=true;
        }
    }
    public void changeEditObject(Object newObj){
        m_targetObject=(SceneObject)newObj;
        m_stateEditor.setStates(m_targetObject.getStates());
        m_isSaved=true;
    }
    public void changeAssetSelected(Object newAsset){
        SceneObjectStateEx sse=(SceneObjectStateEx)newAsset;
        m_fieldPanel.setTarget(sse);

    }
    public void configEditObject(){}
    public void positionClick(int x,int y){}
     /**
     * @brief プロジェクトフォルダが変更されたときに呼ばれる
     */
    public void projDirChanged(){
        m_targetObject=null;
        m_leftPane.getObjectListPanel().updateList();
    }
    public void propertyChanged(){
        m_isSaved=null== m_targetObject;
        repaint();
        Logger.getGlobal().info(String.format("Save state %b",m_isSaved));

    }
}
