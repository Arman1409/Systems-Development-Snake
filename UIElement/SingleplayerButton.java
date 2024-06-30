package UIElement;

import Gamestates.Gamestate;
import imageLoader.ImageLoaderabstract;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The SingleplayerButton class is responsible for managing the single player button in the game menu.
 * <p>
 * This class loads the button image, checks if it's hit by the mouse, and changes the game state to single player mode when clicked.
 * </p>
 *
 * @author Jan Dennis Nina Arman
 */

public class SingleplayerButton {
    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = 140;
    private Gamestate state;
    private BufferedImage[] imgs;
    private final ImageLoaderabstract imageLoadersinge = new ImageLoaderabstract("/Buttons.png");
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    /**
     * This constructor initializes a new instance of the SingleplayerButton class.
     * It sets the position for the button, loads the button images, and initializes the hitbox.
     *
     * @param xPos This is the x position for the button.
     * @param yPos This is the y position for the button.
     * @param rowIndex This is the row index for the button image in the sprite sheet.
     * @param state This is the game state to be applied when the button is clicked.
     */
    public SingleplayerButton(int xPos, int yPos, int rowIndex, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
        initBounds();
    }

    /**
     * This method initializes the hitbox for the button.
     */

    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, 200, 100);

    }

    /**
     * This method loads the button images from the sprite sheet.
     */
    private void loadImgs() {
        imgs = new BufferedImage[3];
        BufferedImage temp = imageLoadersinge.getLoadedImage();
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i*1350, rowIndex*500, 1300, 500);
    }

    /**
     * This method draws the button on the screen.
     *
     * @param g This is the graphics instance to draw the button.
     */

    public void draw(Graphics g) {
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, 200, 100, null);


    }

    /**
     * This method updates the button state based on mouse interactions.
     */

    public void update() {
        index = 0;
        if (mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;
    }

    /**
     * This method checks if the mouse is over the button.
     *
     * @return boolean This returns true if the mouse is over the button, false otherwise.
     */
    public boolean isMouseOver() {
        return mouseOver;
    }

    /**
     * This method sets the mouse over state of the button.
     *
     * @param mouseOver This is the new mouse over state to be set.
     */

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    /**
     * This method checks if the mouse is pressing the button.
     *
     * @return boolean This returns true if the mouse is pressing the button, false otherwise.
     */

    public boolean isMousePressed() {
        return mousePressed;
    }

    /**
     * This method sets the mouse pressed state of the button.
     *
     * @param mousePressed This is the new mouse pressed state to be set.
     */

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    /**
     * This method gets the bounds of the button.
     *
     * @return Rectangle This returns the bounds of the button.
     */

    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * This method applies the game state when the button is clicked.
     */

    public void applyGamestate() {
        Gamestate.state = state;
    }

    /**
     * This method resets the boolean states of the button.
     */

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

}