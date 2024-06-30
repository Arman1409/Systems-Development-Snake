package UIElement;

import Gamestates.Gamestate;
import Objekts.SnakeJon;
import imageLoader.ImageLoaderabstract;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * The Food class is responsible for managing the food in the game.
 * <p>
 * This class creates food, checks if it's hit by the player, and loads the food image.
 * </p>
 *
 * @author Jan Jonathan Lucas Nina
 */

public class Food {
    private int xPos, yPos, index;
    private BufferedImage imgs;
    private final ImageLoaderabstract imageLoadersinge = new ImageLoaderabstract("/snake-graphics32.png");
    private Gamestate gamestate;
    private Random random;
    private Rectangle2D.Float hitbox;
    private SnakeJon snakejon;

    /**
     * This constructor initializes a new instance of the Food class.
     * It sets the position for the food and initializes the hitbox.
     *
     * @param xPos This is the x position for the food.
     * @param yPos This is the y position for the food.
     */

    public Food(int xPos, int yPos){
    random = new Random();
    this.xPos = xPos;
    this.yPos = yPos;
    loadfood();
    hitbox = new Rectangle2D.Float(xPos, yPos, 32, 32);

    }

    /**
     * This method checks if the food is hit by the player.
     * If hit, it repositions the food and adds a part to the snake.
     *
     * @param hitboxp1 This is the hitbox of the player.
     * @param snakejon This is the instance of the snake.
     */



    public void foodhit(Rectangle2D.Float hitboxp1, SnakeJon snakejon) {

        if (hitbox.intersects(hitboxp1)) {
            xPos = random.nextInt(20) * 32;
            yPos = random.nextInt(20) * 32;
            hitbox.x = xPos;
            hitbox.y = yPos;
            snakejon.addpart();
            snakejon.Scoreup();
        }
    }

    /**
     * This method loads the food image.
     */

    public void loadfood(){
        BufferedImage temp = imageLoadersinge.getLoadedImage();
        imgs = temp.getSubimage(0, 3*32,32,32);
    }

    /**
     * This method draws the food on the screen.
     *
     * @param g This is the graphics instance to draw the food.
     */

    public void draw(Graphics g) {
        g.drawImage(imgs, xPos, yPos,32,32, null);
    }

    /**
     * This method updates the food state.
     */

    public void update() {

    }
}

