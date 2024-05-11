import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TwoPlayerSnake extends JFrame implements KeyListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int UNIT_SIZE = 20;
    private int player1X, player1Y, player2X, player2Y;
    private int player1Direction, player2Direction;
    private ArrayList<Point> player1Body, player2Body;
    private Timer timer;
    private boolean gameOver;

    public TwoPlayerSnake() {
        setTitle("2 Player Snake Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        addKeyListener(this);

        player1X = WIDTH / 4;
        player1Y = HEIGHT / 2;
        player2X = 3 * WIDTH / 4;
        player2Y = HEIGHT / 2;
        player1Direction = KeyEvent.VK_RIGHT;
        player2Direction = KeyEvent.VK_A;

        player1Body = new ArrayList<>();
        player2Body = new ArrayList<>();
        player1Body.add(new Point(player1X, player1Y));
        player2Body.add(new Point(player2X, player2Y));

        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    movePlayer1();
                    movePlayer2();
                    checkCollision();
                    repaint();
                }
            }
        });
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.GREEN);
        for (Point point : player1Body) {
            g.fillRect(point.x, point.y, UNIT_SIZE, UNIT_SIZE);
        }
        g.setColor(Color.RED);
        for (Point point : player2Body) {
            g.fillRect(point.x, point.y, UNIT_SIZE, UNIT_SIZE);
        }
    }

    private void movePlayer1() {
        Point head = player1Body.get(0);
        Point newHead = new Point(head.x, head.y);
        switch (player1Direction) {
            case KeyEvent.VK_UP:
                newHead.y -= UNIT_SIZE;
                break;
            case KeyEvent.VK_DOWN:
                newHead.y += UNIT_SIZE;
                break;
            case KeyEvent.VK_LEFT:
                newHead.x -= UNIT_SIZE;
                break;
            case KeyEvent.VK_RIGHT:
                newHead.x += UNIT_SIZE;
                break;
        }
        player1Body.add(0, newHead);
        if (player1Body.size() > 1) {
            player1Body.remove(player1Body.size() - 1);
        }
    }

    private void movePlayer2() {
        Point head = player2Body.get(0);
        Point newHead = new Point(head.x, head.y);
        switch (player2Direction) {
            case KeyEvent.VK_W:
                newHead.y -= UNIT_SIZE;
                break;
            case KeyEvent.VK_S:
                newHead.y += UNIT_SIZE;
                break;
            case KeyEvent.VK_A:
                newHead.x -= UNIT_SIZE;
                break;
            case KeyEvent.VK_D:
                newHead.x += UNIT_SIZE;
                break;
        }
        player2Body.add(0, newHead);
        if (player2Body.size() > 1) {
            player2Body.remove(player2Body.size() - 1);
        }
    }

    private void checkCollision() {
        Point player1Head = player1Body.get(0);
        Point player2Head = player2Body.get(0);

        if (player1Head.equals(player2Head) || player1Head.x < 0 || player1Head.x >= WIDTH ||
                player1Head.y < 0 || player1Head.y >= HEIGHT ||
                player2Head.x < 0 || player2Head.x >= WIDTH ||
                player2Head.y < 0 || player2Head.y >= HEIGHT ||
                player1Body.contains(player2Head) || player2Body.contains(player1Head)) {
            gameOver = true;
            timer.stop();
            JOptionPane.showMessageDialog(this, "Game Over!");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (!gameOver) {
            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
                player1Direction = key;
            } else if (key == KeyEvent.VK_W || key == KeyEvent.VK_S || key == KeyEvent.VK_A || key == KeyEvent.VK_D) {
                player2Direction = key;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TwoPlayerSnake game = new TwoPlayerSnake();
            game.setVisible(true);
        });
    }
}