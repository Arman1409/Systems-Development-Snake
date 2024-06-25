package Gamestates;

import Objekts.Snake;
import imageLoader.ImageLoaderabstract;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Singleplayer extends State implements StartMethods {

    private ImageLoaderabstract imageLoader = new ImageLoaderabstract("/GameBackground.png");
    private Snake snake;
    private BufferedImage backgroundImg;
    private int singleX, singleY, singeWidth, singleHeight;
    private int scorep1 = 0;

    public Singleplayer(Game game) {
        super(game);
        snake = new Snake();


        loadBackground();
    }



    private void loadBackground() {
        backgroundImg = imageLoader.getLoadedImage();
        singeWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
        singleHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
        singleX = Game.GAME_WIDTH / 2 - singeWidth / 2;
        singleY = (int) (45 * Game.SCALE);

    }



    @Override
    public void update() {
    snake.update();

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0,480,480, null);

        snake.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (snake.getDirection() != 'R') {
                    snake.setDirection('L');
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (snake.getDirection() != 'L') {
                    snake.setDirection('R');
                }
                break;
            case KeyEvent.VK_UP:
                if (snake.getDirection() != 'D') {
                    snake.setDirection('U');
                }
                break;
            case KeyEvent.VK_DOWN:
                if (snake.getDirection() != 'U') {
                    snake.setDirection('D');
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
    }
}

