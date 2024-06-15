import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {
    static final int WIDTH = 500;
    static final int HEIGHT = 500;
    static final int UNIT_SIZE = 40;
    static final int NUMBER_OF_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    // hold x and y coordinates for body parts of the snake
    final int player1[][] = new int[2][NUMBER_OF_UNITS];
    final int player2[][] = new int[2][NUMBER_OF_UNITS];
    // initial length of the snake
    int[] length = {5, 5};
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

    //immage
    private BufferedImage apple,subapple;
    private BufferedImage snake1head,subsnake1head, snake1body,subsnake1body,snake1tail,subsnake1tail;
    private BufferedImage snake2head,subsnake2head, snake2body,subsnake2body,snake2tail,subsnake2tail;

    private BufferedImage[] snakeheadani = new BufferedImage[4];
    private int aniTick,aniIndex, aniSpeed = 70;

    private ArrayList<BufferedImage> snake1 = new ArrayList<BufferedImage>();

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter(this,true));
        this.addKeyListener(new MyKeyAdapter(this,false));
        this.setFocusable(true);
        play();
        snake1.add(null);
    }

    public void play() {
        //festlegen der startposi
        newsnakepart();
        snakeHeadAnimationsetup();
        player1[0][0] = player1[0][0] + 100;
        player1[1][0] = player1[1][0] + 100;
        addFood();
        addPowerup();
        running = true;
        timer = new Timer(80, this);
        timer.start();
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
        InputStream is = getClass().getResourceAsStream("/snake-graphics32.png");
        try {
            apple = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        subapple = apple.getSubimage(0*32, 3*32,32,32);
        graphics.drawImage(subapple,foodX,foodY,40,35,null);

    }

    public void drawpowerup(Graphics graphics) {
        graphics.setColor(new Color(40, 67, 200));
        graphics.fillRect(powerUPX, powerUPY, UNIT_SIZE, UNIT_SIZE);
    }

    private void snakeHeadAnimationsetup(){
       loadImage();
            snakeheadani[0] = snake1head.getSubimage(3*32,0*32,32,32);
            snakeheadani[1] = snake1head.getSubimage(4*32,0*32,32,32);
            snakeheadani[2] = snake1head.getSubimage(3*32,1*32,32,32);
            snakeheadani[3] = snake1head.getSubimage(4*32,1*32,32,32);

           for (int i = 0; i < length[0]; i++){
            snake1.add(snake1head.getSubimage(2 * 32, 1 * 32, 32, 32));
        }
    }
    private void loadImage() {
        InputStream is1 = getClass().getResourceAsStream("/snake-graphics32.png");
        try {
            snake1head = ImageIO.read(is1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void snakeHeadanimation(){

        switch (direction){
            case 'D':
                aniIndex = 3;
                snake1.set(0, snakeheadani[3]);
                break;

                case 'U':
                    aniIndex = 0;
                    snake1.set(0, snakeheadani[0]);
                    break;

                    case 'L':
                        aniIndex = 2;
                        snake1.set(0, snakeheadani[2]);
                        break;

                        case 'R':
                            aniIndex = 1;
                            snake1.set(0, snakeheadani[1]);
                            break;
        }
    }

    private void newsnakepart(){
        loadImage();

      snake1.add(snake1head.getSubimage(2*32,1*32,32,32));

    }

    private void checkturn(){
        loadImage();
        char lastdirection = 'D';

        if (lastdirection != direction){

        if (direction == 'L') {
           if(lastdirection == 'D') {
               snake1.set(1, snake1head.getSubimage(2 * 32, 0 * 32, 32, 32));
           }else if (lastdirection == 'U') {
               snake1.set(1, snake1head.getSubimage(2 * 32, 2 * 32, 32, 32));
           }
        } else if (direction == 'R') {

        }else if (direction == 'U') {

        }
        }else {
            lastdirection = direction;
        }
    }

    private void cycle(){

        for (int i = 1; i < snake1.toArray().length-1; i++) {

            snake1.set(i+1, snake1.get(i));

        }

    }

    public void drawsnake1(Graphics graphics) {
        loadImage();
        newsnakepart();
        snakeHeadanimation();
        checkturn();
        cycle();
        graphics.drawImage(snake1.getFirst(),player1[0][0],player1[1][0],40,40,null);


        for (int i = 1; i < snake1.toArray().length; i++) {
           graphics.drawImage(snake1.get(i),player1[0][i],player1[1][i],40,40,null);
        }


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
        foodX = random.nextInt((int) (WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        foodY = random.nextInt((int) (HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void addPowerup() {
        powerUPX = random.nextInt((int) (WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        powerUPY = random.nextInt((int) (HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
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
            timer.stop();
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

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (running) {
            movep2();
            movep1();
            checkFood();
            checkpowerup();
            checkHit();

        }

    }
}