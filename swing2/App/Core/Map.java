package App.Core;

public class Map {
    private int[][] m_tiles;
    
    public Map(int w,int h){
        m_tiles=new int[h][];
        for(int i=0;i < m_tiles.length;++i){
            m_tiles[i]=new int[w];
        }
    }

    
    public void setTile(int x,int y,int t){
        m_tiles[y][x]=t;
    }

    public int getWidth(){
        return m_tiles[0].length;
    }
    public int getHeight(){
        return m_tiles.length;
    }
}
