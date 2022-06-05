import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameObj extends JPanel implements KeyListener, ActionListener, GameObjInterface {
    private boolean inGame = false;
    private int bricksCount = 32;
    private Timer timer;
    private int dl = 3;
    private int paddleX = 310;
    private int ballPositionX = 120;
    private int ballPositionY = 350;
    private int ballDirectionX = -2;
    private int ballDirectionY = -3;
    private BuildMap map;
    private Image img;
    int textX = 150; // X-axis text location
    int textY = 300; // Y-axis text location
    int secondTextX = 100; // Second line text X-axis location
    int secondTextY = 340; // Second line text Y-axis location
    int fontSize = 30; // Font size

    public GameObj() {
        map = new BuildMap(4, 8);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(dl, this);
        timer.start();
    }

    public void createBall() {
        img = new ImageIcon(GameObj.class.getResource("ball.png")).getImage();
    }


    public void paint(Graphics gr) {
        gr.setColor(Color.white);
        gr.fillRect(1, 1, 480, 600);
        map.draw((Graphics2D) gr);

        gr.setColor(Color.black);
        gr.fillRect(paddleX, 480,150,8);

        createBall();
        gr.drawImage(img, ballPositionX, ballPositionY, 30,20,null);

        if(ballPositionY > 570) {
            inGame = false;
            ballDirectionX = ballDirectionY = 0;
            gr.setColor(Color.red);
            gr.setFont(new Font("sans-serif", Font.BOLD,fontSize));
            gr.drawString("Game Over :(", textX, textY);

            gr.setFont(new Font("sans-serif", Font.BOLD, fontSize));
            gr.drawString("(ENTER) - Restart", secondTextX, secondTextY);
        }
        if(bricksCount == 0) {
            inGame = false;
            ballDirectionY = -3;
            ballDirectionX = -2;
            gr.setColor(Color.black);
            gr.setFont(new Font("sans-serif", Font.BOLD, fontSize));
            gr.drawString("Good Game!", textX, textY);
            gr.setFont(new Font("sans-serif", Font.BOLD, fontSize));
            gr.drawString("(ENTER) - Restart", secondTextX, secondTextY);
        }
        gr.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(inGame) {
            if(new Rectangle(ballPositionX, ballPositionY, 30, 20).intersects(new Rectangle(paddleX, 480, 150, 8))) {
                ballDirectionY = -ballDirectionY;
            }
            GAME:
            for(int i=0; i<map.brickMap.length; i++) {
                for(int j=0; j<map.brickMap[0].length; j++) {
                    if(map.brickMap[i][j]>0) {
                        int brickX = j * map.brWidth;
                        int brickY = i * map.brHeight;
                        int brickWt = map.brWidth;
                        int brickHt = map.brHeight;

                        Rectangle rectangle = new Rectangle(brickX, brickY, brickWt, brickHt);
                        Rectangle ball = new Rectangle(ballPositionX, ballPositionY, 20, 20);
                        Rectangle brickRectangle = rectangle;

                        if(ball.intersects(brickRectangle)) {
                            map.setBricksValue(0, i, j);
                            bricksCount--;
                            if(ballPositionX + 19 <= brickRectangle.x || ballPositionX+1 >= brickRectangle.x+brickWt) {
                                ballDirectionX =- ballDirectionX;
                            } else {
                                ballDirectionY =- ballDirectionY;
                            }
                            break GAME;
                        }
                    }
                }
            }
            ballPositionX = ballPositionX + ballDirectionX;
            ballPositionY = ballPositionY + ballDirectionY;


            if (ballPositionX < 0) { ballDirectionX = -ballDirectionX; }
            if (ballPositionY < 0) { ballDirectionY = -ballDirectionY; }
            if (ballPositionX > 460) { ballDirectionX =- ballDirectionX; }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if(paddleX >= 330) {
                    paddleX = 320;
                } else { moveRight(); }
        }
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if(paddleX < 10) {
                    paddleX = 10;
                } else { moveLeft(); }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!inGame) {
                ballPositionX = 120;
                ballPositionY = 350;
                ballDirectionX = -2;
                ballDirectionY = -3;
                paddleX = 310;
                bricksCount = 32;
                map = new BuildMap(4, 8);
                repaint();
            }
        }
    }
    @Override
    public void moveRight() {
        inGame = true;
        paddleX += 25;
    }
    @Override
    public void moveLeft() {
        inGame = true;
        paddleX -= 25;
    }
}