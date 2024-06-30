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

/**
 * The Singleplayer class represents the state of the game when the game is in single player mode.
 * <p>
 * This class is responsible for updating and drawing the single player state of the game.
 * It also handles mouse and key events in this state.
 * </p>
 *
 * @author
 */

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

    /**
     * This constructor initializes a new instance of the Singleplayer class.
     *
     * @param game This is the game instance.
     */


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

    /**
     * This method is used to load the background image.
     */



    private void loadBackground() {
        backgroundImg = imageLoader.getLoadedImage();
        singeWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
        singleHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
        singleX = Game.GAME_WIDTH / 2 - singeWidth / 2;
        singleY = (int) (45 * Game.SCALE);

    }

    /**
     * This method is used to update the single player state.
     */



    @Override
    public void update() {
        snakeTest.update();
        food.foodhit(snakeTest.getHitbox(),snakeTest);
        pup.onHitSP(snakeTest.getHitbox(),snakeTest,this);
        cc.checkHitp1(snakeTest.getBody());


    }

    /**
     * This method is used to set the dark mode.
     */


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

    /**
     * This method is triggered when the mouse is clicked.
     *
     * @param e This is the mouse event object.
     */

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

    /**
     * This method is triggered when the mouse is pressed.
     *
     * @param e This is the mouse event object.
     */

    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * This method is triggered when the mouse is released.
     *
     * @param e This is the mouse event object.
     */

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * This method is triggered when the mouse is moved.
     *
     * @param e This is the mouse event object.
     */

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * This method is triggered when a key is pressed.
     *
     * @param e This is the key event object.
     */

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
    /**
     * This method is triggered when a key is released.
     *
     * @param e This is the key event object.
     */

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * This method is triggered when the mouse is dragged.
     *
     * @param e This is the mouse event object.
     */

    public void mouseDragged(MouseEvent e) {
    }

    public SnakeJon getSnakeTest() {
        return snakeTest;
    }

}

