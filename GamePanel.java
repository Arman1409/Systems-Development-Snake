
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    /**
     * Setting Up the class GamePanel as JPanel and creating the parameters for the game
     * @Author Dennis,Nina
     */
    static final int WIDTH = 500;
    static final int HEIGHT = 500;
    static final int UNIT_SIZE = 20;
    static final int NUMBER_OF_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);

    // hold x and y coordinates for body parts of the snake
    final int player1[][] = new int[2][NUMBER_OF_UNITS];
    final int player2[][] = new int[2][NUMBER_OF_UNITS];
    // initial length of the snake
    int[] length ={5,5};
    int foodEaten;
    int foodX;
    int foodY;

    int powerUPX;

    int powerUPY;
    char direction = 'D';
    char player2direction = 'D';

    boolean spacepressed = false;
    boolean running = false;
    Random random;
    Timer timer;

    //FPS
    private final int FPS_SET = 120;
    private Thread gameThread;


    /**
     * Creating an initalizer for the class gamePanel
     * Stetting up some few important game parameters
     * @Author Dennis,Johnatan
     */
    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter2());
        this.addKeyListener(new MyKeyAdapter());
        play();
        startgameloop();
    }
    /**
     * The first function that is called
     * defining the start point of player 1 and adds food and power ups
     * @Author Dennis,Lucas
     */
    public void play() {
        //festlegen der startposi
        player1[0][0]= player1[0][0]+100;
        player1[1][0]= player1[1][0]+100;
        addFood();
        addPowerup();
        running = true;

    }
    /**
     * important for drawing graphics
     * @Author Nina
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);

    }
    /**
     * Setting up the move functions for p1 and p2
     * in the for loop the snakes will get moved
     * in the if clause the direction is getting defined
     * @Author Lucas, Dennis
     */
    public void movep1() {
        for (int i = length[0]; i > 0; i--) {
            // shift the snake one unit to the desired direction to create a move
            player1[0][i] = player1[0][i-1];
            player1[1][i] = player1[1][i-1];
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
            player2[0][i] = player2[0][i-1];
            player2[1][i] = player2[1][i-1];
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
    /**
     * checking if the food and power up is picked up by one of the player
     * @Author Nina
     */
    public void checkFood() {
        if(player1[0][0] == foodX && player1[1][0] == foodY) {
            length[0]++;
            foodEaten++;
            addFood();
        }else if (player2[0][0] == foodX && player2[1][0] == foodY){
            length[1]++;
            foodEaten++;
            addFood();
        }
    }
    public void checkpowerup(){
        if(player1[0][0] == powerUPX && player1[1][0] == foodY){
              // drawblue();
               addPowerup();
        }
    }
    /**
     * WIP
     * @Author Dennis
     */
    public void drawblue(){
        for (int i = 1; i < length[0]; i++) {

        }
    }
    /**
     * Setting up the draw Functions and creating the drawing for the snakes and food
     * @Author Lucas,Jan
     */
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

    public void drawfood(Graphics graphics){
        graphics.setColor(new Color(210, 115, 90));
        graphics.fillOval(foodX, foodY, UNIT_SIZE, UNIT_SIZE);
    }
    public void drawpowerup(Graphics graphics){
        graphics.setColor(new Color(40, 67, 200));
        graphics.fillRect(powerUPX, powerUPY, UNIT_SIZE, UNIT_SIZE);
    }

    public void drawsnake1(Graphics graphics){
        graphics.setColor(Color.white);
        graphics.fillRect(player1[0][0], player1[1][0], UNIT_SIZE, UNIT_SIZE);

        for (int i = 1; i < length[0]; i++) {
            graphics.setColor(new Color(40, 200, 150));
            graphics.fillRect(player1[0][i],player1[1][i], UNIT_SIZE, UNIT_SIZE);
        }
    }

    public void drawsnake2(Graphics graphics){
        graphics.setColor(Color.white);
        graphics.fillRect(player2[0][0], player2[1][0], UNIT_SIZE, UNIT_SIZE);

        for (int i = 1; i < length[1]; i++) {
            graphics.setColor(new Color(255, 215, 14));
            graphics.fillRect(player2[0][i],player2[1][i], UNIT_SIZE, UNIT_SIZE);
        }
    }
    /**
     * Adds Food and the powerup
     * @Author Dennis,Nina
     */

    public void addFood() {
        foodX = random.nextInt((int)(WIDTH / UNIT_SIZE))*UNIT_SIZE;
        foodY = random.nextInt((int)(HEIGHT / UNIT_SIZE))*UNIT_SIZE;
    }

    public void addPowerup(){
        powerUPX = random.nextInt((int)(WIDTH / UNIT_SIZE))*UNIT_SIZE;
        powerUPY = random.nextInt((int)(HEIGHT / UNIT_SIZE))*UNIT_SIZE;
    }

    /**
     * Checking if one of the player is hitting a Wall or themselsfs
     * WIP: Hittin a other snake
     * @Author Dennis,Nina
     */
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

        for (int i = length[0]; i > 0 ; i--) {
            if (player1[0][i] == player2[0][i] && player1[1][i] == player2[1][i]){
               System.out.println(player1[0][i]);
            }

        }

        if(!running) {

        }
    }
    /**
     * Setting Up the class GamePanel as JPanel and
     * @Author Lucas, Jan
     */
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

    /**
     * Creating action listener and the KeyListner
     * @Author Johnathan
     */
    public void action() {
        if (running) {
            movep2();
            movep1();
            checkFood();
            checkpowerup();
            checkHit();
        }

    }

    public void startgameloop(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    public void run() {
        double timeFrame  = 1000000000.0/ FPS_SET;
        long lastframe = System.nanoTime();
        long now = System.nanoTime();

        do {
            now = System.nanoTime();
            if (now - lastframe >= timeFrame) {
                action();
                repaint();
                lastframe = now;
            }

        }while (running);

    }


    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;

                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
                }
            }
        }
    public class MyKeyAdapter2 extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_A:
                    if (player2direction != 'C') {
                        player2direction = 'Y';
                    }
                    break;

                case KeyEvent.VK_D:
                    if (player2direction != 'Y') {
                        player2direction = 'C';
                    }
                    break;

                case KeyEvent.VK_W:
                    if (player2direction != 'D') {
                        player2direction = 'X';
                    }
                    break;

                case KeyEvent.VK_S:
                    if (player2direction != 'X') {
                        player2direction = 'D';
                    }
                    break;
            }
        }
    }
    }

































