package App;

public interface EditOperationListener {
    void addObject();
    void saveObject();
    void changeEditObject(Object newObj);
    void changeAssetSelected(Object newAsset);
    void configEditObject();
    void positionClick(int x,int y);
    void propertyChanged();
}
