package main;

import Gamestates.Gamestate;

import javax.swing.JFrame;

public class Frame extends JFrame implements Runnable  {
    private static int FPS_set = 120;
    private final int UPS_set = 10;
    private int frames = 0;
    private Thread gameThread;
    private GamePanel gamePanel;
    /**
     * Creating a main.main.Frame and starting the game
     * @Author Dennis, Arman
     */
    Frame(){
        gamePanel = new GamePanel();
        this.add(gamePanel);
        this.setTitle("snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        startgameloop();

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
                gamePanel.updateGame();

            default:
                break;
        }
    }

    public void render(){
        switch (Gamestate.state){
            case MENU:

                break;

            case PLAYING:
                gamePanel.repaint();

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
                render();
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
