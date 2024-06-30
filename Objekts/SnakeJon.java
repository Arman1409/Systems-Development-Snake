package Objekts;

import Objekts.Utilities.ColissionControll;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * The SnakeJon class represents a snake in the game.
 * <p>
 * This class manages the snake's body, speed, direction, and hitbox. It also handles the snake's movement and drawing.
 * </p>
 *
 * @author Dennis Sydow, Lucas Fabricius, Jonathan Heiher
 */


public class SnakeJon {
    static final int WIDTH = 640;
    static final int HEIGHT = 640;
    static final int UNIT_SIZE = 32;
    static final int NUMBER_OF_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private int Scorefinal = 0;
    boolean isformultiplayer = false;

    ArrayList<Point> body = new ArrayList<>();
    double speed=1; //multiplier for speed
    int direction=1;//1=left, 2=right, 3=up, 4=down
    int unitsize=32;
    BufferedImage[] tiles =new BufferedImage[20];
    private String res;

    private Rectangle2D.Float Hitbox;

    /**
     * This constructor initializes a new instance of the SnakeJon class.
     * It sets the initial body, direction, and resource for the snake.
     *
     * @param p This is the initial point for the snake's body.
     * @param dir This is the initial direction for the snake.
     * @param res This is the resource for the snake's image.
     */

    public SnakeJon(Point p,char dir,String res) {
        this.res=res;
        this.direction=dir;
        this.body.add(p);
        this.body.add(p);
        this.body.add(p);
        this.body.add(p);
        this.body.add(p);
        this.body.add(p);
        this.direction=dir;
        this.initializeTiles();
        Hitbox = new Rectangle2D.Float(body.get(0).x,body.get(0).y,unitsize,unitsize);
    }

    /**
     * This method halves the snake's body.
     */

    public void halfBody(){
        for(int i = 0;i<body.size()/2;i++ ){
            body.removeLast();
        }
    }

    /**
     * This method initializes the tiles for the snake's image.
     */

    private void initializeTiles() {
        BufferedImage  image;
        InputStream is1 = getClass().getResourceAsStream("/res/"+res);
        try {
            image = ImageIO.read(is1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int columns=5;
        int rows=4;
        int subsize=32;
        int current_img = 0;
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                // Creating sub image
                tiles[current_img] = new BufferedImage(subsize, subsize, image.getType());
                Graphics2D img_creator = tiles[current_img].createGraphics();

                // coordinates of source image
                int src_first_x = subsize * j;
                int src_first_y = subsize * i;

                // coordinates of sub-image
                int dst_corner_x = subsize * j + subsize;
                int dst_corner_y = subsize * i + subsize;

                img_creator.drawImage(image, 0, 0, subsize, subsize, src_first_x, src_first_y, dst_corner_x, dst_corner_y, null);
                current_img++;
            }
        }
    }

    /**
     * This method gets the correct tile number for the snake's body position.
     *
     * @param bodypos This is the body position for the snake.
     * @return int This returns the correct tile number.
     */

    private int getCorrectTileNumber(int bodypos){

        if (bodypos==0) {
            switch(direction){
                case 'L':
                    return 8;

                case 'R':
                    return 4;

                case 'U':
                    return 3;

                case 'D':
                    return 9;

            }
        return 0;
        }
        int preX=body.get(bodypos).getLocation().x-body.get(bodypos-1).getLocation().x;
        int preY=body.get(bodypos).getLocation().y-body.get(bodypos-1).getLocation().y;
        if (bodypos == body.size()-1){
            if (preX<0){
                return 14;
            } else if (preX>0){
                return 18;
            } else if(preY<0){
                return 19;
            } else if(preY>0) {
                return 13;
            }
            return 0;
        }
        int sucX=body.get(bodypos).getLocation().x-body.get(bodypos+1).getLocation().x;
        int sucY=body.get(bodypos).getLocation().y-body.get(bodypos+1).getLocation().y;
        if ((preX<0 && sucX>0)||(preX>0 && sucX<0)){
            return 1;
        } else if ((preX<0 && sucY>0)||(sucX<0 && preY>0)){
            return 5;
        } else if ((preX<0 && sucY<0)||(sucX<0 && preY<0)){
            return 0;
        }  else if ((preX>0 && sucY>0)||(sucX>0 && preY>0)){
            return 12;
        } else if ((preX>0 && sucY<0)||(sucX>0 && preY<0)){
            return 2;
        }else if ((preY<0 && sucY>0)||(preY>0 && sucY<0)){
            return 7;
        }
        return 0;
    }

    /**
     * This method draws the snake's body.
     *
     * @param g This is the graphics instance to draw the snake.
     */

    public void draw(Graphics g) {

        for (int a = 0; a < body.size(); a++) {
            g.drawImage(tiles[getCorrectTileNumber(a)], body.get(a).getLocation().x, body.get(a).getLocation().y, null);
        }
    }

    /**
     * This method draws the snake's head only.
     *
     * @param g This is the graphics instance to draw the snake's head.
     */

    public void drawHeadOnly(Graphics g) {
        g.drawImage(tiles[getCorrectTileNumber(0)],body.getFirst().getLocation().x, body.getFirst().getLocation().y, null);


    }
    /**
     * This method moves the snake.
     */


    public void move(){
        for (int i = 0; i <  this.speed ; i++){
            Point newHead = new Point(body.getFirst());
            if (direction == 'L') {
                newHead.translate(-unitsize, 0);
            } else if (direction == 'R') {
                newHead.translate(unitsize, 0);
            } else if (direction == 'U') {
                newHead.translate(0, -unitsize);
            } else {
                newHead.translate(0, unitsize);
            }
            body.add(0, newHead);
            body.removeLast();
            Hitbox.x = body.get(0).x;
            Hitbox.y = body.get(0).y;
        }
    }

    /**
     * This method updates the snake's movement.
     */

    public void update() {
        move();
    }
    /**
     * This method sets the snake's speed.
     *
     * @param i This is the new speed for the snake.
     */

    public void setSpeed(double i) {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                speed = i;

                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                speed = 1;
            }
        }).start();
    }

    public void addpart(){
        body.addLast(body.getLast().getLocation());
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public ArrayList<Point> getBody() {
        return body;
    }

    public Rectangle2D.Float getHitbox() {
        return Hitbox;
    }
    public boolean isHit(Point p) {
        return body.contains(p);
    }

    public int getScorefinal() {
        return Scorefinal;
    }

    public boolean isIsformultiplayer() {
        return isformultiplayer;
    }

    public void setIsformultiplayer(boolean isformultiplayer) {
        this.isformultiplayer = isformultiplayer;
    }

    public void Scoreup() {
        Scorefinal++;
    }
}
