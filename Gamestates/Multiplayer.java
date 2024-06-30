package Gamestates;

import Objekts.SnakeJon;
import UIElement.Food;
import UIElement.PowerUp;
import imageLoader.ImageLoaderabstract;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 * The Multiplayer class represents the state of the game when the game is in multiplayer mode.
 * <p>
 * This class is responsible for updating and drawing the multiplayer state of the game.
 * It also handles mouse and key events in this state.
 * </p>
 *
 * @author
 */

public class Multiplayer extends State implements StartMethods {
    private ImageLoaderabstract imageLoader = new ImageLoaderabstract("/WIP.jpeg");

    private BufferedImage backgroundImg;
    private int singleX, singleY, singeWidth, singleHeight;
    private int scorep1 = 0;
    private static final String SERVER_ADDRESS = "localhost";  // Change to server's IP if on a different machine
    private static final int SERVER_PORT = 12346;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean isFirstClient;
    private boolean darkMode = false;
    private Food food;
    Random rand = new Random();
    private PowerUp pup;
    private SnakeJon snake;
    private SnakeJon snake2;

    /**
     * This constructor initializes a new instance of the Multiplayer class.
     *
     * @param game This is the game instance.
     */

    public Multiplayer(Game game) throws IOException {
        super(game);
        loadBackground();
      //  food = new Food(rand.nextInt(20) * 32, rand.nextInt(20) * 32);
      //  pup = new PowerUp(new Point(rand.nextInt(20) * 32, rand.nextInt(20) * 32));
      //  snake= new SnakeJon(new Point(100,100),'R',"snake-graphics32.png");
      //  snake2 = new SnakeJon(new Point(100,100),'R',"snake2-graphics32.png");
        //startConnection();
        //setID();
    }

    /**
     * This method is used to load the background image.
     */

    private void loadBackground() {
        backgroundImg = imageLoader.getLoadedImage();
        singeWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
        singleHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
        singleX = Game.GAME_WIDTH / 2 - singeWidth / 2;
        singleY = (int) (45 * Game.SCALE);

    }

    /**
     * Establishes a Connection with the server And Initializes the Socket, outputStream and InputStream
     * @throws IOException
     */

    public void startConnection() throws IOException {
        socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /**
     * When a Firrst is recieved From the server it sets the isFirstClient Boolean to true.
     * With that thee code can differantiate between player one and two
     * @throws IOException
     */

    public void setID() throws IOException {
        String clientId = in.readLine();
        if ("FIRST".equals(clientId)) isFirstClient = true;
        else if ("SECOND".equals(clientId)) {
            isFirstClient = false;
        }
    }

    public boolean getIsFirstClient() {
        return isFirstClient;
    }

    /**
     * reads Messages rom the server and sets the snake direction for the snake that is played from
     * the other Client. The Messages are directions recieved from the server.
     * @throws IOException
     */

    public void getDirection2() throws IOException {// gets the direction from other snake recieved from server
        String message;
        while ((message = in.readLine()) != null) {
            if (isFirstClient) {
                switch (message) {
                    case "Y" -> snake2.setDirection('Y');
                    case "C" -> snake2.setDirection('C');
                    case "X" -> snake2.setDirection('X');
                    case "D" -> snake2.setDirection('D');
                }
            } else if (!isFirstClient) {
                if (message.equals("L")) {
                    snake.setDirection('L');
                }
                if (message.equals("R")) {
                    snake.setDirection('R');
                }
                if (message.equals("U")) {
                    snake.setDirection('U');
                }
                if (message.equals("D")) {
                    snake.setDirection('D');
                }

            }
        }
    }

    /**
     * This method is used to update the multiplayer state.
     */

    @Override
    public void update() {
      /*
        try {
            getDirection2();
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        //if both connected

       */
    }

    @Override
    public void draw(Graphics g) {

    g.drawImage(backgroundImg, 0, 0,640,640, null);

       /*
        if (darkMode) {
            g.setColor(Color.black);
            g.fillRect(0, 0, singeWidth, singleHeight);
            snake.drawHeadOnly(g);
            snake2.drawHeadOnly(g);
        } else {
            g.drawImage(backgroundImg, 0, 0, singeWidth, singleHeight, null);

            snake.draw(g);
            snake2.draw(g);
            pup.draw(g);
            food.draw(g);
        }
        //if both connected

        */
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * Sets the Direction of a Snake when an arrowKey is pressed.
     * After the direction is set it sends the Direction to the server.
     * @param e An event which indicates that a keystroke occurred in a component.
     */

    @Override
    public void keyPressed(KeyEvent e) {
        /*
        //looks for which snake is played and translates the arrowkey presses
        if (getIsFirstClient() == true) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != 'R') {
                        snake.setDirection('L');
                        out.println('L');
                    }
                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != 'L') {
                        snake.setDirection('R');
                        out.println('R');
                    }
                case KeyEvent.VK_UP:
                    if (snake.getDirection() != 'D') {
                        snake.setDirection('U');
                        out.println('U');
                    }
                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != 'U') {
                        snake.setDirection('D');
                        out.println('D');
                    }
            }
        }
        if (getIsFirstClient() == false) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (snake2.getDirection() != 'C') {
                        snake2.setDirection('Y');
                        out.println('y');
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake2.getDirection() != 'Y') {
                        snake2.setDirection('C');
                        out.println('c');
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake2.getDirection() != 'D') {
                        snake2.setDirection('X');
                        out.println('x');
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake2.getDirection() != 'X') {
                        snake2.setDirection('D');
                        out.println('d');
                    }
                    break;


            }

         */


        }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setDarkMode() {
    /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                darkMode = true;

                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                darkMode = false;
            }


        }

     */
    }
}