package Gamestates;

import Objekts.SnakeJon;
import UIElement.DeadButtons;
import UIElement.DeadButtons;
import UIElement.SingleplayerButton;
import imageLoader.ImageLoaderabstract;
import main.* ;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * The DEAD class represents the state of the game when the player is dead.
 * <p>
 * This class is responsible for updating and drawing the dead state of the game.
 * It also handles mouse events in this state.
 * </p>
 *
 * @author
 */

public class DEAD extends State implements StartMethods {
    private DeadButtons[] buttons = new DeadButtons[2];
    private BufferedImage backgroundImg;
    private int deadX, deadY, deadWidth, deadHeight;
    private final ImageLoaderabstract imageLoader = new ImageLoaderabstract("/Deadscreen.png");
    int snake;
    int snake1;
    int snake2;
    boolean multiplayer = false;
    Score score;
    int ii = 0;
    int MaxScore = 0;

    /**
     * This constructor initializes a new instance of the DEAD class.
     *
     * @param game This is the game instance.
     */


    public DEAD(Game game) {
        super(game);
        loadBackground();
        loadButtons();
        score = new Score();


    }

    /**
     * This method is used to load the background image.
     */

    private void loadBackground() {
        backgroundImg = imageLoader.getLoadedImage();
        deadWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
        deadHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
        deadX = Game.GAME_WIDTH / 2 - deadWidth / 2;
        deadY = (int) (45 * Game.SCALE);

    }

    /**
     * This method is used to load the buttons.
     */

    private void loadButtons() {
        buttons[0] = new DeadButtons((Game.GAME_WIDTH / 2) - 150, (int) (400 * Game.SCALE), 0);
        buttons[1] = new DeadButtons((Game.GAME_WIDTH / 2) + 280, (int) (400 * Game.SCALE), 1);

    }

    public void getscore(){
         snake = getGame().getPlaying().getSnakeTest().getScorefinal();
         snake1 = getGame().getLocal_Multiplayer().getSnake1().getScorefinal();
         snake2 = getGame().getLocal_Multiplayer().getSnake2().getScorefinal();
        MaxScore = score.readScoreFile(snake);
         if (getGame().getLocal_Multiplayer().getSnake1().isIsformultiplayer()){
        multiplayer = true;
       MaxScore = score.readScoreFile(snake1);
    }

    }
    /**
     * This method is used to update the state.
     */

    @Override
    public void update() {

        if(ii ==0){
            getscore();
            ii++;
        }
        for(DeadButtons button : buttons){
            button.update();
        }

        checkifpressed();

    }

    /**
     * This method is used to draw the state.
     *
     * @param g This is the graphics object to draw on.
     */

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, 640, 640, null);

        for(DeadButtons button : buttons){
            button.draw(g);
        }
        g.setColor(Color.white);
        g.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 30));
        if (multiplayer){
            g.drawString("Score: " + snake1 , (Game.GAME_WIDTH / 2)-100, (int) (450 * Game.SCALE));
            g.drawString("Score: " + snake2 , (Game.GAME_WIDTH / 2)-100, (int) (500 * Game.SCALE));
            g.drawString("Max Score"+ MaxScore, (Game.GAME_WIDTH / 2)-120, (int) (550 * Game.SCALE));
        } else {
            g.drawString("Score: " + snake , (Game.GAME_WIDTH / 2)-100, (int) (400 * Game.SCALE));
            g.drawString("Max Score: "+ MaxScore, (Game.GAME_WIDTH / 2)-120, (int) (450 * Game.SCALE));
        }

    }

    /**
     * This method is used to handle mouse click events.
     *
     * @param e This is the mouse event.
     */

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * This method is used to handle mouse press events.
     *
     * @param e This is the mouse event.
     */

    @Override
    public void mousePressed(MouseEvent e) {
        for(DeadButtons db : buttons){
            if(isIn(e,db)){

                db.setMousePressed(true);

                break;
            }
        }
    }

    /**
     * This method is used to handle mouse release events.
     *
     * @param e This is the mouse event.
     */

    @Override
    public void mouseReleased(MouseEvent e) {
        for(DeadButtons db : buttons){
            if(isIn(e,db)){
                if (db.isMousePressed());

                db.applyGamestate();

                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (DeadButtons db : buttons)
            db.resetBools();

    }

    /**
     * This method is used to handle mouse move events.
     *
     * @param e This is the mouse event.
     */

    @Override
    public void mouseMoved(MouseEvent e) {
        for (DeadButtons db : buttons)
            db.setMouseOver(false);

        for (DeadButtons db : buttons)
            if (isIn(e, db)) {
                db.setMouseOver(true);
                break;
            }
    }

    /**
     * This method is used to handle key press events.
     *
     * @param e This is the key event.
     */

    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * This method is used to handle key release events.
     *
     * @param e This is the key event.
     */

    @Override
    public void keyReleased(KeyEvent e) {

    }
    private void checkifpressed() {
        if (buttons[0].isMousePressed()) {
            game.gcmaker();
        }else if (buttons[1].isMousePressed()) {
            System.exit(0);
        }
    }

    public void setIi(int ii) {
        this.ii = ii;
    }
}
