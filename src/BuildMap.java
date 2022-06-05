import java.awt.*;

public class BuildMap implements BuildMapInterface {
    int brWidthVal = 487;
    int brHeightVal = 130;
    public int brickMap[][];
    public int brWidth;
    public int brHeight;
    public void draw(Graphics2D g) {
        for (int rw=0; rw<brickMap.length; rw++) {
            for (int cl=0; cl<brickMap[0].length; cl++) {
                if(brickMap[rw][cl] > 0) {
                    g.setColor(Color.yellow);
                    if (rw % 2 != 0) g.setColor(Color.red);
                    g.fillRect(cl*brWidth, rw*brHeight, brWidth, brHeight);
                    g.setStroke(new BasicStroke(2));
                    g.setColor(Color.white);
                    g.drawRect(cl*brWidth, rw*brHeight, brWidth, brHeight);
                }
            }
        }
    }
    public void defineSize(int row, int col) {
        brWidth = brWidthVal/col;
        brHeight = brHeightVal/row;
    }
    public BuildMap(int row, int col) {
        brickMap = new int[row][col];
        for (int []map1 : brickMap) {
            for (int cl=0; cl<brickMap[0].length; cl++) {
                map1[cl] = 1;
            }
        }
        defineSize(row, col);
    }
    public void setBricksValue(int value, int row, int col) {
        brickMap[row][col] = value;
    }
}