package App;
import java.awt.*;
import javax.swing.*;
import App.Core.*;
/**
 * @brief シーン編集用のUI
 */
public class SceneEditPanel extends JPanel implements EditOperationListener{
    private Scene m_targetScene;    /*! 現在編集中のシーン*/
    private Object m_assetSelected; /*! 現在選択中のアセット。
                                        このアセットがマップをクリック時に追加される*/

    private SceneEditLeftPanePanel m_leftPane;
    private EditMapPanel m_editMapPanel;
    private boolean m_isSaved=true; /*! m_targetSceneが保存済みならtrue,
                                        それ以外ならfalse
                                    */
    public SceneEditPanel(){
        setLayout(new BorderLayout());
        add(m_leftPane=new SceneEditLeftPanePanel(this),BorderLayout.WEST);
        add(m_editMapPanel=new EditMapPanel(this),BorderLayout.CENTER);
    }
    /**
     * @brief オブジェクトを新規追加する操作が行われたときに呼ばれる
     */
    public void addObject(){
        saveObject();
        //インスタンスを新規作成し、名前をつけて新規保存する
        m_targetScene=GameSystem.getInstance().createScene();
        if(null!=m_targetScene){
            String newName=JOptionPane.showInputDialog(this, "Name",m_targetScene.getName());
            //名前の修正が拒否された場合は新規作成しない
            if(null!=newName){
                m_targetScene.setName(newName);
                GameSystem.getInstance().saveScene(m_targetScene);
                m_leftPane.getSceneListPanel().updateList();
            }else{
                m_targetScene=null;
            }
        }
    }
    public void saveObject(){
        //未保存なら現在編集中の内容を保存する
        if(null!=m_targetScene && !m_isSaved){
            GameSystem.getInstance().saveScene(m_targetScene);
            m_isSaved=true;
        }
    }
    /**
     * @brief 編集対象のオブジェクトを変更する操作を行った場合に呼ばれる
     */
    public void changeEditObject(Object newObj){
        //未保存なら保存する
        if(null!=m_targetScene && !m_isSaved){
            GameSystem.getInstance().saveScene(m_targetScene);
            m_isSaved=true;
        }
        m_targetScene=(Scene)newObj;

    }
    /**
     * @brief 選択中のアセットを変更する操作を行った場合に呼ばれる
     */
    public void changeAssetSelected(Object newAsset){

        //選択される可能性あるアセットは
        //タイルか、オブジェクト
        m_assetSelected=newAsset;
    }
    public void configEditObject(){}
    /**
     * @brief マップ内のタイルがクリックされたときに呼ばれる
     */
    public void positionClick(int x,int y){

        //選択したアセットをマップ内に配置する
        if(null!=m_assetSelected){
            int gridX=x/m_targetScene.getMap().getWidth();
            int gridY=y/m_targetScene.getMap().getHeight();
            if(m_assetSelected instanceof Tile){
                int tileIdx=m_editMapPanel.getTileList().indexOf(m_assetSelected);
                if(-1==tileIdx){
                    m_editMapPanel.getTileList().add((Tile)m_assetSelected);
                    tileIdx=m_editMapPanel.getTileList().size()-1;
                }
                m_editMapPanel.getMap().setTile(gridX,gridY,tileIdx);
            }
        }
    }


    /**
     * @brief プロジェクトフォルダが変更されたときに呼ばれる
     */
    public void projDirChanged(){
        m_targetScene=null;
        m_leftPane.getSceneListPanel().updateList();
    }
    public void propertyChanged(){
        m_isSaved=null==m_targetScene;
    }

}
