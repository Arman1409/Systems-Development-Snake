import javax.swing.JFrame;

public class Frame extends JFrame implements Runnable  {
    private static int FPS_set = 120;
    private int frames = 0;
    private Thread gameThread;
    /**
     * Creating a Frame and starting the game
     * @Author Dennis, Arman
     */
    Frame(){
        GamePanel gamePanel = new GamePanel();
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

    @Override
    public void run() {
        double timeFrame  = 1000000.0/ FPS_set;
        long lastframe = System.nanoTime();
        long now = System.nanoTime();

        while (true){
            now = System.nanoTime();
            frames++;
            if (now - lastframe >= timeFrame) {
                repaint();
                lastframe = now;
                System.out.println("FPS: " + frames);
                frames = 0;
            }

        }

    }
}
