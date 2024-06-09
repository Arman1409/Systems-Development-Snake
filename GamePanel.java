import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {
    static final int WIDTH = 500;
    static final int HEIGHT = 500;
    static final int UNIT_SIZE = 20;
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
    private BufferedImage apple;

    GamePanel() {
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

        graphics.drawImage(apple.getSubimage(0*32, 3*32,32,32),0,0,null);
    }

    public void drawpowerup(Graphics graphics) {
        graphics.setColor(new Color(40, 67, 200));
        graphics.fillRect(powerUPX, powerUPY, UNIT_SIZE, UNIT_SIZE);
    }

    public void drawsnake1(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(player1[0][0], player1[1][0], UNIT_SIZE, UNIT_SIZE);
        for (int i = 1; i < length[0]; i++) {
            graphics.setColor(new Color(40, 200, 150));
            graphics.fillRect(player1[0][i], player1[1][i], UNIT_SIZE, UNIT_SIZE);
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