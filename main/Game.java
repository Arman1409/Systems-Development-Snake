package main;

import Gamestates.*;
import Gamestates.Menu;

import javax.swing.JFrame;
import java.awt.*;
import java.io.IOException;

/**
 * The Game class extends JFrame and implements Runnable to create the main game window and game loop.
 * <p>
 * This class is responsible for initializing the game states, handling game updates and rendering, and managing the game loop.
 * </p>
 *
 * @author Lucas Fabricius, Dennis Sydow, Jonathan Heiher, Nina Brandt
 */

public class Game extends JFrame implements Runnable  {

    public boolean runninggame;
    private static int FPS_set = 120;
    private final int UPS_set = 10;
    private int frames = 0;
    private Thread gameThread;
    private Thread serverThrea;
  //  private SnakeGamePanle snakeGamePanle;

    private Singleplayer singleplayer;
    private Menu menu;
    private DEAD dead;
    private EXIT exit;
    private Local_Multiplayer local_Multiplayer;
    private Multiplayer multiplayer;
    private Retry retry;

    private GamePanel gamePanel;
    private GameWindow gameWindow;

    public final static float SCALE = 1f;
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static int TILES_IN_WIDTH = 20;
    public final static int TILES_IN_HEIGHT = 20;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;;

    /**
     * Creating a main.main.Frame and starting the game
     * @Author Dennis, Arman
     */

    Game(){
        runninggame = true;
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startgameloop();

        //nina ist dooof 30 cm Riesen Dildo aa

    }

    private void initClasses() {
        menu = new Menu(this);
       singleplayer = new Singleplayer(this);
       local_Multiplayer = new Local_Multiplayer(this);
       dead = new DEAD(this);
       retry =  new Retry(this);
      // exit = new EXIT();
        try {
            multiplayer = new Multiplayer(this);
        } catch (IOException e) {
           System.out.println(e.getMessage());
        }

       /*
        snakeGamePanle = new SnakeGamePanle();
        this.add(snakeGamePanle);
        this.setTitle("snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    */
    }
    public void gcmaker(){
        Gamestate.state = Gamestate.EXIT;
        initClasses();
        Gamestate.state = Gamestate.MENU;
    }

    private void startserver (){
        serverThrea = new Thread(this);
        serverThrea.start();
    }



    private void startgameloop(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    public void update(){

        switch (Gamestate.state){
            case MENU:
            menu.update();
            break;

            case SINGLEPLAYER:
                singleplayer.update();

            break;

            case MULTIPLAYER:
                //multiplayer.update();
                break;

            case DEAD:
                dead.update();
                break;

            case EXIT:
                exit.update();
                break;

            case LOKAL_MULTIPLAYER:
                local_Multiplayer.update();
                break;

            case RETRY:
                retry.update();
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

            case SINGLEPLAYER:
                singleplayer.draw(g);
                break;

            case MULTIPLAYER:
                multiplayer.draw(g);
                break;

                case DEAD:
                    dead.draw(g);
                break;

            case EXIT:
                exit.draw(g);
                break;
            case LOKAL_MULTIPLAYER:
                local_Multiplayer.draw(g);
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
                gamePanel.repaint();
               // snakeGamePanle.repaint();
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

    public Menu getMenu() {
        return menu;
    }

    public Singleplayer getPlaying() {
        return singleplayer;
    }

    public boolean isRunninggame() {
        return runninggame;
    }

    public DEAD getDead() {
        return dead;
    }

    public EXIT getExit() {
        return exit;
    }

    public Local_Multiplayer getLocal_Multiplayer() {
        return local_Multiplayer;
    }

    public void setSingleplayer(Singleplayer singleplayer) {
        this.singleplayer = singleplayer;
    }

    public void setLocal_Multiplayer(Local_Multiplayer local_Multiplayer) {
        this.local_Multiplayer = local_Multiplayer;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
