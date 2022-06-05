import java.awt.*;
public interface BuildMapInterface {
    public void draw(Graphics2D g);
    public void defineSize(int row, int col);
    public void setBricksValue(int value, int row, int col);
}
