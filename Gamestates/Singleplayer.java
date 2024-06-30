package Gamestates;

import Objekts.SnakeJon;
import Objekts.Utilities.ColissionControll;
import UIElement.Food;
import UIElement.PowerUp;
import imageLoader.ImageLoaderabstract;
import main.Game;
import main.Score;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Singleplayer extends State implements StartMethods {

    private ImageLoaderabstract imageLoader = new ImageLoaderabstract("/Gamebackground_.png");

    private SnakeJon snakeTest;
    private BufferedImage backgroundImg;
    private int singleX, singleY, singeWidth, singleHeight;
    private int scorep1 = 0;
    private Score score;
    private Food food;
    Random rand = new Random();
    private PowerUp pup;
    private boolean darkMode=false;
    private ColissionControll cc;


    public Singleplayer(Game game) {
        super(game);

        snakeTest = new SnakeJon(new Point(320,192),'R',"snake-graphics32.png");
        food = new Food(rand.nextInt(20)*32, rand.nextInt(20)*32 );
        pup= new PowerUp(new Point(rand.nextInt(20)*32, rand.nextInt(20)*32));
        loadBackground();
        cc = new ColissionControll(640,640);
        score = new Score();
        score.readScoreFile(scorep1);

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
        snakeTest.update();
        food.foodhit(snakeTest.getHitbox(),snakeTest);
        pup.onHitSP(snakeTest.getHitbox(),snakeTest,this);
        cc.checkHitp1(snakeTest.getBody());


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
            g.fillRect(0,0, 640, 640);
            snakeTest.drawHeadOnly(g);
        } else {
            g.drawImage(backgroundImg, 0, 0, 640, 640, null);

           // snake.draw(g);
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
                if (snakeTest.getDirection() != 'R') {
                    snakeTest.setDirection('L');
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (snakeTest.getDirection() != 'L') {
                    snakeTest.setDirection('R');
                }
                break;
            case KeyEvent.VK_UP:
                if (snakeTest.getDirection() != 'D') {
                    snakeTest.setDirection('U');
                }
                break;
            case KeyEvent.VK_DOWN:
                if (snakeTest.getDirection() != 'U') {
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

    public SnakeJon getSnakeTest() {
        return snakeTest;
    }

}

