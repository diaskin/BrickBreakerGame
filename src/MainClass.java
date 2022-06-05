import javax.swing.JFrame;

public class MainClass {
    public static void main (String[] args) {
        JFrame frame = new JFrame();
        GameObj gameObj = new GameObj();
        frame.setBounds(10, 10, 493, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Brick");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(gameObj);
    }
}