package Gamestates;

import UIElement.SingleplayerButton;
import imageLoader.ImageLoaderabstract;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * The Menu class represents the state of the game when the game is in the menu screen.
 * <p>
 * This class is responsible for updating and drawing the menu state of the game.
 * It also handles mouse events in this state.
 * </p>
 *
 * @author
 */

public class Menu extends State implements StartMethods {
    private SingleplayerButton[] buttons = new SingleplayerButton[4];
    private BufferedImage backgroundImg;
    private int menuX, menuY, menuWidth, menuHeight;
    private final ImageLoaderabstract imageLoader = new ImageLoaderabstract("/Menubackground_.png");

    /**
     * This constructor initializes a new instance of the Menu class.
     *
     * @param game This is the game instance.
     */

    public Menu(Game game) {
        super(game);
        loadBackground();
        loadButtons();


    }

    /**
     * This method is used to reset the menu.
     */

    public void reset(){
        loadBackground();
        loadButtons();
    }

    /**
     * This method is used to load the background image.
     */

    private void loadBackground() {
        backgroundImg = imageLoader.getLoadedImage();
        menuWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
        menuHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
        menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int) (45 * Game.SCALE);

    }

    /**
     * This method is used to load the buttons.
     */

    private void loadButtons() {
        buttons[0] = new SingleplayerButton((Game.GAME_WIDTH / 2)+40, (int) (200 * Game.SCALE), 0, Gamestate.SINGLEPLAYER);
        buttons[1] = new SingleplayerButton((Game.GAME_WIDTH / 2)+40, (int) (300 * Game.SCALE), 1, Gamestate.LOKAL_MULTIPLAYER);
        buttons[2] = new SingleplayerButton((Game.GAME_WIDTH / 2)+40, (int) (400 * Game.SCALE), 2, Gamestate.MULTIPLAYER);
        buttons[3] = new SingleplayerButton((Game.GAME_WIDTH / 2)+40, (int) (500 * Game.SCALE), 3, Gamestate.EXIT);
    }

    /**
     * This method is used to update the menu state.
     */

    @Override
    public void update() {
        for (SingleplayerButton mb : buttons)
            mb.update();

        checkifpressed();
    }

    /**
     * This method is used to draw the menu state.
     *
     * @param g This is the graphics object to draw on.
     */

    @Override
    public void draw(Graphics g) {

        g.drawImage(backgroundImg, 0, 0,640,640, null);

        for (SingleplayerButton mb : buttons)
            mb.draw(g);
    }

    /**
     * This method is triggered when the mouse is clicked.
     *
     * @param e This is the mouse event object.
     */

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
        for(SingleplayerButton mb : buttons){
            if(isIn(e,mb)){
            mb.setMousePressed(true);
            break;
        }
        }

    }

    /**
     * This method is triggered when the mouse is released.
     *
     * @param e This is the mouse event object.
     */

    @Override
    public void mouseReleased(MouseEvent e) {
        for(SingleplayerButton mb : buttons){
            if(isIn(e,mb)){
                if (mb.isMousePressed());
                    mb.applyGamestate();
                    break;
            }
        }
        resetButtons();
    }
    private void resetButtons() {
        for (SingleplayerButton mb : buttons)
            mb.resetBools();

    }

    /**
     * This method is triggered when the mouse is moved.
     *
     * @param e This is the mouse event object.
     */

    @Override
    public void mouseMoved(MouseEvent e) {
        for (SingleplayerButton mb : buttons)
            mb.setMouseOver(false);

        for (SingleplayerButton mb : buttons)
            if (isIn(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
    }

    /**
     * This method is triggered when a key is pressed.
     *
     * @param e This is the key event object.
     */

    @Override
    public void keyPressed(KeyEvent e) {

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
     * This method is used to check if a button is pressed.
     */

    private void checkifpressed() {
        if (buttons[0].isMousePressed()) {
            Gamestate.state = Gamestate.SINGLEPLAYER;
        }
    }

}
