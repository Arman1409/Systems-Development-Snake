package Gamestates;

import UIElement.DeadButtons;
import UIElement.DeadButtons;
import UIElement.SingleplayerButton;
import imageLoader.ImageLoaderabstract;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class DEAD extends State implements StartMethods {
    private DeadButtons[] buttons = new DeadButtons[2];
    private BufferedImage backgroundImg;
    private int deadX, deadY, deadWidth, deadHeight;
    private final ImageLoaderabstract imageLoader = new ImageLoaderabstract("/Deadscreen.png");

    public DEAD(Game game) {
        super(game);
        loadBackground();
        loadButtons();


    }
    private void loadBackground() {
        backgroundImg = imageLoader.getLoadedImage();
        deadWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
        deadHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
        deadX = Game.GAME_WIDTH / 2 - deadWidth / 2;
        deadY = (int) (45 * Game.SCALE);

    }
    private void loadButtons() {
        buttons[0] = new DeadButtons((Game.GAME_WIDTH / 2) - 50, (int) (400 * Game.SCALE), 0,Gamestate.EXIT);
        buttons[1] = new DeadButtons((Game.GAME_WIDTH / 2) + 150, (int) (400 * Game.SCALE), 1,Gamestate.EXIT);

    }

    @Override
    public void update() {

        for(DeadButtons button : buttons){
            button.update();
        }
        checkifpressed();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, 640, 640, null);

        for(DeadButtons button : buttons){
            button.draw(g);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(DeadButtons db : buttons){
            if(isIn(e,db)){
                db.setMousePressed(true);
                break;
            }
        }
    }

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
