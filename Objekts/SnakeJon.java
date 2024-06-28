package Objekts;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class SnakeJon {
    ArrayList<Point> body = new ArrayList<>();
    int speed=2; //multiplier for speed,2 is normal
    char direction=1;
    int unitsize=32;
    BufferedImage[] tiles =new BufferedImage[20];

    public SnakeJon(Point p,char dir) {
        this.body.add(p);
        this.body.add(p);
        this.body.add(p);
        this.body.add(p);
        this.body.add(p);
        this.body.add(p);
        this.direction=dir;
        this.initializeTiles();
    }

    public void halfBody(){
        for(int i = 0;i<body.size()/2;i++ ){
            body.removeLast();
        }
    }

    private void initializeTiles() {
        BufferedImage  image;
        InputStream is1 = getClass().getResourceAsStream("/res/snake-graphics32.png");
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

    public void draw(Graphics g) {

        for (int a = 0; a < body.size(); a++) {
            g.drawImage(tiles[getCorrectTileNumber(a)], body.get(a).getLocation().x, body.get(a).getLocation().y, null);
        }
    }

        public void drawHeadOnly(Graphics g) {
                        g.drawImage(tiles[getCorrectTileNumber(0)],body.getFirst().getLocation().x, body.getFirst().getLocation().y, null);


    }

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
            body.addFirst(newHead);
            body.removeLast();
        }
    }

    public void update() {
        move();
    }

    public void setSpeed(int i) {

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

                speed = 2;
            }
        }).start();
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public char getDirection() {
        return direction;
    }
}
