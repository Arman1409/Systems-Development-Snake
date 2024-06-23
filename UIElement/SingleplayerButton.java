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
    private ImageLoaderabstract imageLoader = new ImageLoaderabstract("/Singleplayer.png");
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
        BufferedImage temp = imageLoader.getLoadedImage();
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i * 140, rowIndex * 56, 140, 56);
    }

    public Rectangle getBounds() {
    return bounds;
    }
}
