package Objekts;


import Gamestates.Singleplayer;
import Objekts.Utilities.ColissionControll;
import UIElement.Food;
import imageLoader.ImageLoaderabstract;
import main.Game;
import main.GamePanel;
//import main.SnakeGamePanle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Snake {
  //  private SnakeGamePanle snakeGamePanle;

    private GamePanel gamePanel;
    private ImageLoaderabstract imageLoader = new ImageLoaderabstract("/snake-graphics32.png");

    static final int WIDTH = 1000;
    static final int HEIGHT = 1000;
    static final int UNIT_SIZE = 40;
    static final int NUMBER_OF_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);

    final int[][] player1 = new int[2][NUMBER_OF_UNITS];

    private int length = 5;
    private ColissionControll cc;
    private Food food;

    private final ArrayList<BufferedImage> snake1 = new ArrayList<BufferedImage>();
    private final BufferedImage[] snakeheadani = new BufferedImage[4];
    private BufferedImage snake1image;

    char direction = 'D';
    private int aniIndex;
    private char currentHeadDirection = 'H';
    private char lastdirection = 'D';

    private int indexsnakee = 1;
    private int test = 0;
    private int test2 = 2;
    private int test3 = 0;

   public Snake() {
       player1[0][0] = player1[0][0] + 100;
       player1[1][0] = player1[1][0] + 100;
    snake1image = imageLoader.getLoadedImage();
    snakeHeadAnimationsetup();
    length = 5;
    cc = new ColissionControll(WIDTH,
            HEIGHT);
    food = new Food(500,400);
    newsnakepart();
    }
    public void movep1() {
        for (int i = length; i > 0; i--) {
            // shift the snake one unit to the desired direction to create a move
            player1[0][i] = player1[0][i - 1];
            player1[1][i] = player1[1][i - 1];
        }
        if (direction == 'L') {
            player1[0][0] = player1[0][0] - UNIT_SIZE;
        } else if (direction == 'R') {
            player1[0][0] = player1[0][0] + UNIT_SIZE;
        } else if (direction == 'U') {
            player1[1][0] = player1[1][0] - UNIT_SIZE;
        } else {
            player1[1][0] = player1[1][0] + UNIT_SIZE;
        }

    }

    public void update() {
    movep1();
    cc.checkHitp1(length,
            player1);
    food.foodhit(player1);


    }

    public void draw(Graphics graphics) {
        drawsnake1(graphics);
        food.draw(graphics);
    }
    private void snakeHeadAnimationsetup(){

        snakeheadani[0] = snake1image.getSubimage(3*32, 0,32,32);
        snakeheadani[1] = snake1image.getSubimage(4*32, 0,32,32);
        snakeheadani[2] = snake1image.getSubimage(3*32, 32,32,32);
        snakeheadani[3] = snake1image.getSubimage(4*32, 32,32,32);

        for (int i = 0; i < length; i++){
            snake1.add(snake1image.getSubimage(2 * 32, 32, 32, 32));
        }
    }
    private void newsnakepart(){


        snake1.add(snake1image.getSubimage(2*32, 32,32,32));

    }

    private void snakeHeadanimation(){

        switch (direction){
            case 'D':
                aniIndex = 3;
                snake1.set(0, snakeheadani[3]);
                currentHeadDirection = 'H';
                break;

            case 'U':
                aniIndex = 0;
                snake1.set(0, snakeheadani[0]);
                currentHeadDirection = 'H';
                break;

            case 'L':
                aniIndex = 2;
                snake1.set(0, snakeheadani[2]);
                currentHeadDirection = 'V';
                break;

            case 'R':
                aniIndex = 1;
                snake1.set(0, snakeheadani[1]);
                currentHeadDirection = 'V';
                break;
        }
    }

    private  void checkturn(){

        if (lastdirection != direction){

            if (direction == 'L') {
                if(lastdirection == 'D') {
                    snake1.set(1, snake1image.getSubimage(2 * 32, 0, 32, 32));
                   // istured = true;
                  //  indexsnakee = 3;
                    lastdirection = direction;
                }else if (lastdirection == 'U') {
                    snake1.set(1, snake1image.getSubimage(2 * 32, 2 * 32, 32, 32));
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
            BufferedImage temp = snake1.get(indexsnake);
            if(snake1.get(indexsnake) == snake1image.getSubimage(2 * 32, 32, 32, 32) || snake1.get(indexsnake) == snake1image.getSubimage(32, 0, 32, 32)) {
                if (currentHeadDirection == 'H') {
                    snake1.set(1, snake1image.getSubimage(32, 0, 32, 32));
                } else if (currentHeadDirection == 'V') {
                    snake1.set(1, snake1image.getSubimage(2 * 32, 32, 32, 32));
                }
            }else {
                snake1.set(indexsnake, snake1.get(indexsnake + 1));
            }
            snake1.set(indexsnake + 1, temp);

          /* if (test3 == 2) {
               test3 = 0;
               if (currentHeadDirection == 'H') {
                   snake1.set(1, snake1image.getSubimage(32, 0, 32, 32));
                   istured = false;
               } else if (currentHeadDirection == 'V') {
                   snake1.set(1, snake1image.getSubimage(2 * 32, 32, 32, 32));
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

    public void drawsnake1(Graphics graphics) {

        snakeHeadanimation();
        checkturn();
        graphics.drawImage(snake1.get(0),player1[0][0],player1[1][0],40,40,null);

        for (int i = 1; i < snake1.toArray().length; i++) {
            graphics.drawImage(snake1.get(i),player1[0][i],player1[1][i],40,40,null);
        }

        if (test2 >= 10) {
            if (test == 1) {
                cycle(indexsnakee);
                if (indexsnakee < snake1.size() - 1) {
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

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public int[][] getPlayer1() {
        return player1;
    }
    public void setPlayer1(int[][] player1) {

    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }




}
