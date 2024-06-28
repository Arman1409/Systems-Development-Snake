package UIElement;

import Gamestates.Multiplayer;
import Gamestates.Singleplayer;
import Objekts.SnakeJon;
import imageLoader.ImageLoaderabstract;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class PowerUp {
    Point loc;
    private BufferedImage imgs;
    private final ImageLoaderabstract imageLoadersinge = new ImageLoaderabstract("/snake-graphics32.png");

    public PowerUp (Point p) {
        this.loc=p;
        BufferedImage temp = imageLoadersinge.getLoadedImage();
        imgs = temp.getSubimage(0, 3*32,32,32);
            }

    public void draw(Graphics g) {
        g.drawImage(imgs, loc.getLocation().x, loc.getLocation().y, null);
    }

    public void onHitSP(SnakeJon hitter, Singleplayer sp) {
        Random rand = new Random();
        int n =rand.nextInt(4);
        switch (n) {
            case 0:
                hitter.setSpeed(4);
               break;
            case 1:
                hitter.setSpeed(1);
                break;
            case 2:
                hitter.halfBody();
                break;
            case 3:
                sp.setDarkMode();
                break;
        }
    }

    public void onHitMP(SnakeJon hitter, SnakeJon enemy, Multiplayer mp) {
        Random rand = new Random();
        int n =rand.nextInt(4);
        switch (n) {
            case 0:
                hitter.setSpeed(4);
                break;
            case 1:
                enemy.setSpeed(1);
                break;
            case 2:
                enemy.halfBody();
                break;
            case 3:
                mp.setDarkMode();
                break;
        }
    }

}
