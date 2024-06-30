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
    private final ImageLoaderabstract imageLoadersinge = new ImageLoaderabstract("/Powerups.PNG");

    Random rand = new Random();
    int n =rand.nextInt(4);

    public PowerUp (Point p) {
        this.loc=p;
        BufferedImage temp = imageLoadersinge.getLoadedImage();

        switch (n) {
            case 0:
                imgs = temp.getSubimage(80, 60,40,40);
                break;
            case 1:
                imgs = temp.getSubimage(80, 20,40,40);
                break;
            case 2:
                imgs = temp.getSubimage(40, 60,40,40);
                break;
            case 3:
                imgs = temp.getSubimage(40, 20,40,40);
                break;
        }
        /*

        imgs = temp.getSubimage(40, 20,40,40); //für Mond
        imgs = temp.getSubimage(40, 60,40,40); //für Schere
        imgs = temp.getSubimage(80, 60,40,40); //für Blitz

         */
    }

    public void draw(Graphics g) {

        g.drawImage(imgs, loc.getLocation().x, loc.getLocation().y, null);
    }

    public void onHitSP(SnakeJon hitter, Singleplayer sp) {

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