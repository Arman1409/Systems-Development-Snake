package Gamestates;

import Objekts.Snake;
import Objekts.SnakeJon;
import UIElement.Food;
import UIElement.PowerUp;
import imageLoader.ImageLoaderabstract;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Singleplayer extends State implements StartMethods {

    private ImageLoaderabstract imageLoader = new ImageLoaderabstract("/Gamebackground_.png");
    private SnakeJon snake;
    private SnakeJon snakeTest;
    private BufferedImage backgroundImg;
    private int singleX, singleY, singeWidth, singleHeight;
    private int scorep1 = 0;
    private Food food;
    Random rand = new Random();
    private PowerUp pup;
    private boolean darkMode=false;

    public Singleplayer(Game game) {
        super(game);

        snake = new SnakeJon(new Point(200,200),'R');
        snakeTest = new SnakeJon(new Point(100,100),'L');
        food = new Food(rand.nextInt(20)*32, rand.nextInt(20)*32 );
        pup= new PowerUp(new Point(rand.nextInt(20)*32, rand.nextInt(20)*32));
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
    public void setDarkMode(){
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                darkMode=true;

                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                darkMode=false;
            }
        }).start();
    }

    @Override
    public void draw(Graphics g) {
        if (darkMode) {
            g.setColor(Color.black);
            g.fillRect(0,0, singeWidth, singleHeight);
            snake.drawHeadOnly(g);
            snakeTest.drawHeadOnly(g);
        } else {
            g.drawImage(backgroundImg, 0, 0, singeWidth, singleHeight, null);

            snake.draw(g);
            snakeTest.draw(g);
            pup.draw(g);
            food.draw(g);
        }
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

