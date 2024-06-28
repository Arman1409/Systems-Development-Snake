package UIElement;

import Gamestates.Gamestate;
import Objekts.Snake;
import imageLoader.ImageLoaderabstract;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Food {
    private int xPos, yPos, index;
    private BufferedImage imgs;
    private final ImageLoaderabstract imageLoadersinge = new ImageLoaderabstract("/snake-graphics32.png");
    private Gamestate gamestate;
    private Snake snake;
    private Random random;
    private Rectangle2D.Float hitbox;

    public Food(int xPos, int yPos, int rowIndex){
    random = new Random();
    this.xPos = xPos;
    this.yPos = yPos;
    this.index = rowIndex;
    loadfood();
    hitbox = new Rectangle2D.Float(xPos, yPos, 32, 32);


    }



    public boolean foodhit(Rectangle2D.Float hitboxplayer) {

        if (hitbox.intersects(hitboxplayer)) {
            xPos = random.nextInt(1000 / 40) * 40;
            yPos = random.nextInt(1000 / 40) * 40;
            hitbox.x = xPos;
            hitbox.y = yPos;
            return true;
        }
    return false;
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

