package UIElement;

import Gamestates.Local_Multiplayer;
import Gamestates.Multiplayer;
import Gamestates.Singleplayer;
import Objekts.SnakeJon;
import imageLoader.ImageLoaderabstract;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class PowerUp {
    Point loc;
    private BufferedImage imgs;
    private final ImageLoaderabstract imageLoadersinge = new ImageLoaderabstract("/Powerups.PNG");

    Random rand = new Random();
    int n ;

    private Rectangle2D.Float hitbox;

    public PowerUp (Point p) {
        this.loc=p;
        BufferedImage temp = imageLoadersinge.getLoadedImage();
        switch (n) {
            case 0:
                imgs = temp.getSubimage(80, 60,40,40);
                hitbox = new Rectangle2D.Float(p.x, p.y, 40,40);

                break;
            case 1:
                imgs = temp.getSubimage(80, 20,40,40);
                hitbox = new Rectangle2D.Float(p.x, p.y, 40,40);

                break;
            case 2:
                imgs = temp.getSubimage(40, 60,40,40);
                hitbox = new Rectangle2D.Float(p.x, p.y, 40,40);

                break;
            case 3:
                imgs = temp.getSubimage(40, 20,40,40);
                hitbox = new Rectangle2D.Float(p.x, p.y, 40,40);

                break;
        }
        /*

        imgs = temp.getSubimage(40, 20,40,40); //für Mond
        imgs = temp.getSubimage(40, 60,40,40); //für Schere
        imgs = temp.getSubimage(80, 60,40,40); //für Blitz

         */
    }

    /*
    public void reloadloadImages(){
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
        //n = rand.nextInt(4);
    }

     */

    public void draw(Graphics g) {

        g.drawImage(imgs, loc.getLocation().x, loc.getLocation().y, null);
    }


    public void onHitSP(Rectangle2D.Float hitboxp1, SnakeJon hitter, Singleplayer sp) {
        BufferedImage temp = imageLoadersinge.getLoadedImage();

        if (hitbox.intersects(hitboxp1)) {
            loc.x = rand.nextInt(20) * 32;
            loc.y = rand.nextInt(20) * 32;
            hitbox.x = loc.x;
            hitbox.y = loc.y;

            switch (n) {
                case 0:
                    hitter.setSpeed(1.05);
                    n = rand.nextInt(4);
                    break;
                case 1:
                    hitter.setSpeed(0.5);
                    n = rand.nextInt(4);
                    break;
                case 2:
                    hitter.halfBody();
                    n = rand.nextInt(4);
                    break;
                case 3:
                    sp.setDarkMode();
                    n = rand.nextInt(4);
                    break;
            }

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

        }
    }


    public void onHitMP(Rectangle2D.Float hitboxp1, SnakeJon hitter, SnakeJon enemy, Local_Multiplayer mp) {
        BufferedImage temp = imageLoadersinge.getLoadedImage();

        if (hitbox.intersects(hitboxp1)) {
            loc.x = rand.nextInt(20) * 32;
            loc.y = rand.nextInt(20) * 32;
            hitbox.x = loc.x;
            hitbox.y = loc.y;

            switch (n) {
                case 0:
                    hitter.setSpeed(1.05);
                    break;
                case 1:
                    enemy.setSpeed(0.5);
                    break;
                case 2:
                    enemy.halfBody();
                    break;
                case 3:
                    mp.setDarkMode();
                    break;
            }
        }

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
    }
}