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
 * The Local_Multiplayer class represents the state of the game when the game is in local multiplayer mode.
 * <p>
 * This class is responsible for updating and drawing the local multiplayer state of the game.
 * It also handles mouse and key events in this state.
 * </p>
 *
 * @author
 */

public class Local_Multiplayer extends State implements StartMethods{
    private ImageLoaderabstract imageLoader = new ImageLoaderabstract("/Gamebackground_.png");
    private SnakeJon snake1;
    private SnakeJon snake2;
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
     * This constructor initializes a new instance of the Local_Multiplayer class.
     *
     * @param game This is the game instance.
     */

    public Local_Multiplayer(Game game){
        super(game);
        snake1 = new SnakeJon(new Point(320,192),'R',"snake-graphics32.png");
        snake2 = new SnakeJon(new Point(320,384),'L',"snake2-graphics32.png");
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
     * This method is used to update the local multiplayer state.
     */

    @Override
    public void update() {
        snake1.update();
        snake2.update();
       food.foodhit(snake1.getHitbox(), snake1);
       food.foodhit(snake2.getHitbox(), snake2);
       pup.onHitMP(snake1.getHitbox(),snake1,snake2,this);
       pup.onHitMP(snake2.getHitbox(),snake2,snake1,this);
       cc.checkHitp2(snake1.getBody(),snake2.getBody(),snake1,snake2);
       snake1.setIsformultiplayer(true);

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
     * This method is used to draw the local multiplayer state.
     *
     * @param g This is the graphics object to draw on.
     */

    @Override
    public void draw(Graphics g) {
        if (darkMode) {
            g.setColor(Color.black);
            g.fillRect(0,0, 640, 640);
            snake1.drawHeadOnly(g);
            snake2.drawHeadOnly(g);
        } else {
            g.drawImage(backgroundImg, 0, 0, 640, 640, null);

            snake1.draw(g);
            snake2.draw(g);
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
                if (snake1.getDirection() != 'R') {
                    snake1.setDirection('L');
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (snake1.getDirection() != 'L') {
                    snake1.setDirection('R');
                }
                break;
            case KeyEvent.VK_UP:
                if (snake1.getDirection() != 'D') {
                    snake1.setDirection('U');
                }
                break;
            case KeyEvent.VK_DOWN:
                if (snake1.getDirection() != 'U') {
                    snake1.setDirection('D');
                }
                break;
            case KeyEvent.VK_A:
                if (snake2.getDirection() != 'R') {
                    snake2.setDirection('L');
                }
                break;
            case KeyEvent.VK_D:
                if (snake2.getDirection() != 'L') {
                    snake2.setDirection('R');
                }
                break;
            case KeyEvent.VK_W:
                if (snake2.getDirection() != 'D') {
                    snake2.setDirection('U');
                }
                break;
            case KeyEvent.VK_S:
                if (snake2.getDirection() != 'U') {
                    snake2.setDirection('D');
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

    public SnakeJon getSnake1() {
        return snake1;
    }

    public SnakeJon getSnake2() {
        return snake2;
    }
}
