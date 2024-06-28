package Gamestates;

import UIElement.DeadButtons;
import UIElement.DeadButtons;
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

    public DEAD(Game game){
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
        buttons[0] = new DeadButtons((Game.GAME_WIDTH / 2) - 50, (int) (600 * Game.SCALE), 0);
        buttons[1] = new DeadButtons((Game.GAME_WIDTH / 2) + 150, (int) (600 * Game.SCALE), 1);

    }

    @Override
    public void update() {

        for(DeadButtons button : buttons){
            button.update();
        }

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, deadWidth, deadHeight, null);

        for(DeadButtons button : buttons){
            button.draw(g);
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

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
