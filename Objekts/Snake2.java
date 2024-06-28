package Objekts;


import Gamestates.Singleplayer;
import imageLoader.ImageLoaderabstract;
import main.Game;
import main.GamePanel;
//import main.SnakeGamePanle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Snake2 {

    //  private SnakeGamePanle snakeGamePanle;

    private GamePanel gamePanel;
    private ImageLoaderabstract imageLoader = new ImageLoaderabstract("/snake-graphics32.png");

    static final int WIDTH = 480;
    static final int HEIGHT = 480;
    static final int UNIT_SIZE = 40;
    static final int NUMBER_OF_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);

    final int[][] player2 = new int[2][NUMBER_OF_UNITS];
    private int length = 5;

    private final ArrayList<BufferedImage> snake2 = new ArrayList<BufferedImage>();
    private final BufferedImage[] snakeheadani = new BufferedImage[4];
    private BufferedImage snake2image;
    char direction = 'D';
    private int aniIndex;
    private char currentHeadDirection = 'H';
    private char lastdirection = 'D';

    private int indexsnakee = 1;
    private int test = 0;
    private int test2 = 2;
    private int test3 = 0;


    public Snake2() {
        player2[0][0] = player2[0][0] + 100;
        player2[1][0] = player2[1][0] + 100;
        snake2image = imageLoader.getLoadedImage();
        snakeHeadAnimationsetup();
        newsnakepart();
    }


    public void movep2() {
        for (int i = length; i > 0; i--) {
            // shift the snake one unit to the desired direction to create a move
            player2[0][i] = player2[0][i - 1];
            player2[1][i] = player2[1][i - 1];
        }
        if (direction == 'L') {
            player2[0][0] = player2[0][0] - UNIT_SIZE;
        } else if (direction == 'R') {
            player2[0][0] = player2[0][0] + UNIT_SIZE;
        } else if (direction == 'U') {
            player2[1][0] = player2[1][0] - UNIT_SIZE;
        } else {
            player2[1][0] = player2[1][0] + UNIT_SIZE;
        }

    }


    public void update() {
        movep2();
    }

    public void draw(Graphics graphics) {
        drawsnake2(graphics);
    }
    private void snakeHeadAnimationsetup(){

        snakeheadani[0] = snake2image.getSubimage(3*32, 0,32,32);
        snakeheadani[1] = snake2image.getSubimage(4*32, 0,32,32);
        snakeheadani[2] = snake2image.getSubimage(3*32, 32,32,32);
        snakeheadani[3] = snake2image.getSubimage(4*32, 32,32,32);

        for (int i = 0; i < length; i++){
            snake2.add(snake2image.getSubimage(2 * 32, 32, 32, 32));
        }
    }
    private void newsnakepart(){


        snake2.add(snake2image.getSubimage(2*32, 32,32,32));

    }

    private void snakeHeadanimation(){

        switch (direction){
            case 'D':
                aniIndex = 3;
                snake2.set(0, snakeheadani[3]);
                currentHeadDirection = 'H';
                break;

            case 'U':
                aniIndex = 0;
                snake2.set(0, snakeheadani[0]);
                currentHeadDirection = 'H';
                break;

            case 'L':
                aniIndex = 2;
                snake2.set(0, snakeheadani[2]);
                currentHeadDirection = 'V';
                break;

            case 'R':
                aniIndex = 1;
                snake2.set(0, snakeheadani[1]);
                currentHeadDirection = 'V';
                break;
        }
    }

    private  void checkturn(){

        if (lastdirection != direction){

            if (direction == 'L') {
                if(lastdirection == 'D') {
                    snake2.set(1, snake2image.getSubimage(2 * 32, 0, 32, 32));
                    // istured = true;
                    //  indexsnakee = 3;
                    lastdirection = direction;
                }else if (lastdirection == 'U') {
                    snake2.set(1, snake2image.getSubimage(2 * 32, 2 * 32, 32, 32));
                }
            } else if (direction == 'R') {

            }else if (direction == 'U') {

            }
        }else {
            lastdirection = direction;
        }
    }

    public void cycle(int indexsnake){



        if(indexsnake <= length-1) {
            BufferedImage temp = snake2.get(indexsnake);
            if(snake2.get(indexsnake) == snake2image.getSubimage(2 * 32, 32, 32, 32) || snake2.get(indexsnake) == snake2image.getSubimage(32, 0, 32, 32)) {
                if (currentHeadDirection == 'H') {
                    snake2.set(1, snake2image.getSubimage(32, 0, 32, 32));
                } else if (currentHeadDirection == 'V') {
                    snake2.set(1, snake2image.getSubimage(2 * 32, 32, 32, 32));
                }
            }else {
                snake2.set(indexsnake, snake2.get(indexsnake + 1));
            }
            snake2.set(indexsnake + 1, temp);

          /* if (test3 == 2) {
               test3 = 0;
               if (currentHeadDirection == 'H') {
                   snake2.set(1, snake2image.getSubimage(32, 0, 32, 32));
                   istured = false;
               } else if (currentHeadDirection == 'V') {
                   snake2.set(1, snake2image.getSubimage(2 * 32, 32, 32, 32));
                   istured = false;
               } else {
                   istured = false;
               }
           }else {
               test3++;
           }

           */
        }





    }

    public void drawsnake2(Graphics graphics) {

        snakeHeadanimation();
        checkturn();
        graphics.drawImage(snake2.get(0),player2[0][0],player2[1][0],40,40,null);

        for (int i = 1; i < snake2.toArray().length; i++) {
            graphics.drawImage(snake2.get(i),player2[0][i],player2[1][i],40,40,null);
        }

        if (test2 >= 10) {
            if (test == 1) {
                cycle(indexsnakee);
                if (indexsnakee < snake2.size() - 1) {
                    indexsnakee++;
                } else {
                    indexsnakee = 1;
                }
                test = 0;

            } else {
                test++;
            }
        }
        test2++;
    }

    public char getDirection2() {
        return direction;
    }

    public void setDirection2(char direction) {
        this.direction = direction;
    }
}
