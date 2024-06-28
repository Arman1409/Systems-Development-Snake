package UIElement;

import Gamestates.Gamestate;
import Objekts.Snake;
import imageLoader.ImageLoaderabstract;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Food {
    private int xPos, yPos, index;
    private BufferedImage imgs;
    private final ImageLoaderabstract imageLoadersinge = new ImageLoaderabstract("/snake-graphics32.png");
    private Gamestate gamestate;
    private Snake snake;
    private Random random;

    public Food(int xPos, int yPos, int rowIndex){
    this.xPos = xPos;
    this.yPos = yPos;
    this.index = rowIndex;
    loadfood();



    }

    public void foodhit(int[][] player) {

        if (player[0][0] == xPos && player[1][0] == yPos) {
            xPos = random.nextInt(1000 / 40) * 40;
            yPos = random.nextInt(1000 / 40) * 40;

        }
    }

    public void loadfood(){
        BufferedImage temp = imageLoadersinge.getLoadedImage();
        imgs = temp.getSubimage(0, 3*32,32,32);
    }

    public void draw(Graphics g) {
    g.drawImage(imgs, xPos, yPos,50,50, null);
    }

    public void update() {

    }

}

