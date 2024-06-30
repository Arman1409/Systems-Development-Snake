package UIElement;

import Gamestates.Gamestate;
import imageLoader.ImageLoaderabstract;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DeadButtons {
    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = 140;
    private Gamestate state;
    private BufferedImage[] imgs;
    private final ImageLoaderabstract imageLoadersinge = new ImageLoaderabstract("/Dead screen buttons_.png");
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    public DeadButtons(int x, int y, int rowIndex) {
        this.xPos = x;
        this.yPos = y;
        this.rowIndex = rowIndex;

        initBounds();
       loadImgs();

    }
    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, 150, 150);

    }
    private void loadImgs() {
        imgs = new BufferedImage[2];
        BufferedImage temp = imageLoadersinge.getLoadedImage();
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(50+(i*22)*20, rowIndex*500, 500, 500);
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, 150, 150, null);


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
