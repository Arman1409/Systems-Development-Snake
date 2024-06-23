package Gamestates;

import UIElement.SingleplayerButton;
import imageLoader.ImageLoaderabstract;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Menu extends State implements StartMethods {
    private SingleplayerButton[] buttons = new SingleplayerButton[3];
    private BufferedImage backgroundImg;
    private int menuX, menuY, menuWidth, menuHeight;
    private ImageLoaderabstract imageLoader = new ImageLoaderabstract("/ManuBackground.jpeg");

    public Menu(Game game) {
        super(game);
        Random random = new Random();
       // this.setPreferredSize(new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT));

    }
    private void loadBackground() {
        backgroundImg = imageLoader.getLoadedImage();
        menuWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
        menuHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
        menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int) (45 * Game.SCALE);

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {

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
