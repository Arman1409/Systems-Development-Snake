package main;

import inputs.MyKeyAdapter;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    static final int WIDTH = 500;
    static final int HEIGHT = 500;
    static final int UNIT_SIZE = 40;
    static final int NUMBER_OF_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    // hold x and y coordinates for body parts of the snake
    final int[][] player1 = new int[2][NUMBER_OF_UNITS];
    final int[][] player2 = new int[2][NUMBER_OF_UNITS];
    // initial length of the snake
    int[] length = {5, 5};
    int foodEaten;
    int foodX;
    int foodY;
    int powerUPX;
    int powerUPY;

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    char direction = 'D';
    public char player2direction = 'D';
    boolean spacepressed = false;
    boolean running = false;
    Random random;


    //immage
    private BufferedImage apple,subapple;
    private BufferedImage snake1image;
    private BufferedImage snake2head,subsnake2head, snake2body,subsnake2body,snake2tail,subsnake2tail;

    private final BufferedImage[] snakeheadani = new BufferedImage[4];
    private int aniTick;
    private int aniIndex;
    private final int aniSpeed = 70;

    private final ArrayList<BufferedImage> snake1 = new ArrayList<BufferedImage>();
    private char lastdirection = 'D';
    private char currentHeadDirection = 'H';
    private boolean istured = false;
    private int indexsnakee = 1;
    private int test = 0;
    private int test2 = 2;
    private int test3 = 0;

    SnakeGamePanle() {
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter(this,true));
        this.addKeyListener(new MyKeyAdapter(this,false));
        this.setFocusable(true);
        play();

    }

    public void play() {
        //festlegen der startposi
        loadImage();
        newsnakepart();
        snakeHeadAnimationsetup();
        player1[0][0] = player1[0][0] + 100;
        player1[1][0] = player1[1][0] + 100;
        addFood();
        addPowerup();
        running = true;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void movep1() {
        for (int i = length[0]; i > 0; i--) {
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

    public void movep2() {
        for (int i = length[1]; i > 0; i--) {
            // shift the snake one unit to the desired direction to create a move
            player2[0][i] = player2[0][i - 1];
            player2[1][i] = player2[1][i - 1];
        }
        if (player2direction == 'Y') {
            player2[0][0] = player2[0][0] - UNIT_SIZE;
        } else if (player2direction == 'C') {
            player2[0][0] = player2[0][0] + UNIT_SIZE;
        } else if (player2direction == 'X') {
            player2[1][0] = player2[1][0] - UNIT_SIZE;
        } else {
            player2[1][0] = player2[1][0] + UNIT_SIZE;
        }
    }

    public void checkFood() {
        if (player1[0][0] == foodX && player1[1][0] == foodY) {
            length[0]++;
            foodEaten++;
            addFood();
            newsnakepart();
        } else if (player2[0][0] == foodX && player2[1][0] == foodY) {
            length[1]++;
            foodEaten++;
            addFood();
        }
    }

    public void checkpowerup() {
        if (player1[0][0] == powerUPX && player1[1][0] == foodY) {
            // drawblue();
            addPowerup();
        }
    }

    public void drawblue() {
        for (int i = 1; i < length[0]; i++) {
        }
    }

    public void draw(Graphics graphics) {
        if (running) {
            drawfood(graphics);
            drawpowerup(graphics);
            drawsnake1(graphics);
            drawsnake2(graphics);
            graphics.setColor(Color.white);
            graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: " + foodEaten, (WIDTH - metrics.stringWidth("Score: " + foodEaten)) / 2, graphics.getFont().getSize());
        } else {
            gameOver(graphics);
        }
    }

    public void drawfood(Graphics graphics) {

        subapple = snake1image.getSubimage(0, 3*32,32,32);
        graphics.drawImage(subapple,foodX,foodY,40,35,null);

    }

    public void drawpowerup(Graphics graphics) {
        graphics.setColor(new Color(40, 67, 200));
        graphics.fillRect(powerUPX, powerUPY, UNIT_SIZE, UNIT_SIZE);
    }

    private void snakeHeadAnimationsetup(){

            snakeheadani[0] = snake1image.getSubimage(3*32, 0,32,32);
            snakeheadani[1] = snake1image.getSubimage(4*32, 0,32,32);
            snakeheadani[2] = snake1image.getSubimage(3*32, 32,32,32);
            snakeheadani[3] = snake1image.getSubimage(4*32, 32,32,32);

           for (int i = 0; i < length[0]; i++){
            snake1.add(snake1image.getSubimage(2 * 32, 32, 32, 32));
        }
    }
    private void loadImage() {
        InputStream is1 = getClass().getResourceAsStream("/snake-graphics32.png");
        try {
            snake1image = ImageIO.read(is1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    private void newsnakepart(){


      snake1.add(snake1image.getSubimage(2*32, 32,32,32));

    }

    private  void checkturn(){




        if (lastdirection != direction){

        if (direction == 'L') {
           if(lastdirection == 'D') {
               snake1.set(1, snake1image.getSubimage(2 * 32, 0, 32, 32));
               istured = true;
               indexsnakee = 3;
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



        if(indexsnake <= length[0]-1) {
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

    public void drawsnake2(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(player2[0][0], player2[1][0], UNIT_SIZE, UNIT_SIZE);
        for (int i = 1; i < length[1]; i++) {
            graphics.setColor(new Color(255, 215, 14));
            graphics.fillRect(player2[0][i], player2[1][i], UNIT_SIZE, UNIT_SIZE);
        }
    }

    public void addFood() {
        foodX = random.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
        foodY = random.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }

    public void addPowerup() {
        powerUPX = random.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
        powerUPY = random.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }

    public void checkHit() {
        // check if head run into its body
        for (int i = length[1]; i > 0; i--) {
            if (player1[0][0] == player1[0][i] && player1[1][0] == player1[1][i]) {
                running = false;
            } else if (player2[0][0] == player2[0][i] && player2[1][0] == player2[1][i]) {
                running = false;
            }
        }
        // check if head run into walls
        if (player1[0][0] < 0 || player1[0][0] > WIDTH || player1[1][0] < 0 || player1[1][0] > HEIGHT) {
            running = false;
        } else if (player2[0][0] < 0 || player2[0][0] > WIDTH || player2[1][0] < 0 || player2[1][0] > HEIGHT) {
            running = false;
        }


        for (int i = length[0]; i > 0; i--) {
            if (player1[0][i] == player2[0][i] && player1[1][i] == player2[1][i]) {
                System.out.println(player1[0][i]);
            }

        }

        if (!running) {

        }
    }

    public void gameOver(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 50));
        FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Game Over", (WIDTH - metrics.stringWidth("Game Over")) / 2, HEIGHT / 2);
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
        metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Score: " + foodEaten, (WIDTH - metrics.stringWidth("Score: " + foodEaten)) / 2, graphics.getFont().getSize());
    }


        public void updateGame(){
            if (running) {
                 movep2();
                movep1();
                checkFood();
                checkpowerup();
                // checkHit();

            }
        }


}