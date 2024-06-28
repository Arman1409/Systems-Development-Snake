package Gamestates;

import Objekts.Snake;
import Objekts.SnakeJon;
import UIElement.Food;
import imageLoader.ImageLoaderabstract;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Singleplayer extends State implements StartMethods {

    private ImageLoaderabstract imageLoader = new ImageLoaderabstract("/Gamebackground_.png");
    private Snake snake;
    private SnakeJon snakeTest;
    private BufferedImage backgroundImg;
    private int singleX, singleY, singeWidth, singleHeight;
    private int scorep1 = 0;


    public Singleplayer(Game game) {
        super(game);

        snake = new Snake();
        snakeTest = new SnakeJon(new Point(100,100),1);
        loadBackground();

    }



    private void loadBackground() {
        backgroundImg = imageLoader.getLoadedImage();
        singeWidth = (int) (backgroundImg.getWidth() * Game.SCALE)*2;
        singleHeight = (int) (backgroundImg.getHeight() * Game.SCALE)*2;
        singleX = Game.GAME_WIDTH / 2 - singeWidth / 2;
        singleY = (int) (45 * Game.SCALE);

    }



    @Override
    public void update() {
    snake.update();
    snakeTest.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0,singeWidth,singleHeight, null);

        snake.draw(g);
        snakeTest.draw(g);
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
                    snakeTest.setDirection('L');
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (snake.getDirection() != 'L') {
                    snake.setDirection('R');
                    snakeTest.setDirection('R');
                }
                break;
            case KeyEvent.VK_UP:
                if (snake.getDirection() != 'D') {
                    snake.setDirection('U');
                    snakeTest.setDirection('U');
                }
                break;
            case KeyEvent.VK_DOWN:
                if (snake.getDirection() != 'U') {
                    snake.setDirection('D');
                    snakeTest.setDirection('D');
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

