package main;

import Gamestates.*;
import Gamestates.Menu;

import javax.swing.JFrame;
import java.awt.*;

public class Game extends JFrame implements Runnable  {

    private static int FPS_set = 120;
    private final int UPS_set = 10;
    private int frames = 0;
    private Thread gameThread;
    private SnakeGamePanle snakeGamePanle;

    private Playing playing;
    private Menu menu;

    private GamePanel gamePanel;
    private GameWindow gameWindow;

    public final static float SCALE = 1f;
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static int TILES_IN_WIDTH = 14;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;;
    /**
     * Creating a main.main.Frame and starting the game
     * @Author Dennis, Arman
     */
    Game(){
        initClasses();

       // gamePanel = new GamePanel(this);
       // gameWindow = new GameWindow(gamePanel);
        startgameloop();

    }

    private void initClasses() {
       // menu = new Menu(this);
        snakeGamePanle = new SnakeGamePanle();
        this.add(snakeGamePanle);
        this.setTitle("snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void startgameloop(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    public void update(){

        switch (Gamestate.state){
            case MENU:

            break;

            case PLAYING:
                snakeGamePanle.updateGame();

            break;

            case MULTIPLAYER:
                // Lucas
                break;

            default:
                break;
        }
    }

    public void render(Graphics g){
        switch (Gamestate.state){
            case MENU:
                menu.draw(g);
                break;

            case PLAYING:
                snakeGamePanle.repaint();
                break;

            case MULTIPLAYER:
                // Lucas
                break;

            default:
                break;
        }
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_set;
        double timePerUpdate = 1000000000.0 / UPS_set;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastChecked = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true){

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1){
                update();
                updates ++;
                deltaU--;
            }

            if (deltaF >= 1) {
                //gamePanel.repaint();
                snakeGamePanle.repaint();
                frames++;
                deltaF--;

            }
            if (System.currentTimeMillis() - lastChecked >= 1000) {
                lastChecked = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;

            }
        }

    }

}