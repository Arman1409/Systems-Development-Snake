package Gamestates;

import UIElement.SingleplayerButton;
import imageLoader.ImageLoaderabstract;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class Menu extends State implements StartMethods {
    private SingleplayerButton[] buttons = new SingleplayerButton[1];
    private BufferedImage backgroundImg;
    private int menuX, menuY, menuWidth, menuHeight;
    private final ImageLoaderabstract imageLoader = new ImageLoaderabstract("/ManuBackground.png");

    public Menu(Game game) {
        super(game);
        loadBackground();
        loadButtons();


    }
    private void loadBackground() {
        backgroundImg = imageLoader.getLoadedImage();
        menuWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
        menuHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
        menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int) (45 * Game.SCALE);

    }
    private void loadButtons() {
        buttons[0] = new SingleplayerButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, Gamestate.SINGLEPLAYER);
       // buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (220 * Game.SCALE), 1, Gamestate.OPTIONS);
       // buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290 * Game.SCALE), 2, Gamestate.QUIT);
    }




    @Override
    public void update() {
        for (SingleplayerButton mb : buttons)
            mb.update();

        checkifpressed();
    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(backgroundImg, 0, 0,480,480, null);

        for (SingleplayerButton mb : buttons)
            mb.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(SingleplayerButton mb : buttons){
            if(isIn(e,mb)){
            mb.setMousePressed(true);
            break;
        }
        }

    }

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

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void checkifpressed() {
        if (buttons[0].isMousePressed()) {
            Gamestate.state = Gamestate.SINGLEPLAYER;
        }
    }

}
