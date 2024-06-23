package UIElement;

import Gamestates.Gamestate;
import imageLoader.ImageLoaderabstract;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SingleplayerButton {
    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = 140;
    private Gamestate state;
    private BufferedImage[] imgs;
    private final ImageLoaderabstract imageLoadersinge = new ImageLoaderabstract("/Singleplayer.png");
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;


    public SingleplayerButton(int xPos, int yPos, int rowIndex, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, 140, 56);

    }
    private void loadImgs() {
        imgs = new BufferedImage[3];
        BufferedImage temp = imageLoadersinge.getLoadedImage();
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i*110, 0, 110, 36);
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, 140, 56, null);


    }

    public void update() {
        index = 0;
        if (mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;
    }
    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void applyGamestate() {
        Gamestate.state = state;
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

}