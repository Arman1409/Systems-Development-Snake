package main;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
/**
 * The GamePanel class extends JPanel and is responsible for setting up the game panel.
 * <p>
 * This class is responsible for setting the panel size, adding key and mouse listeners, and rendering the game.
 * </p>
 *
 * @author Lucas Dennis
 */

public class GamePanel extends JPanel {


    private MouseInputs mouseInputs;
    private KeyboardInputs keyboardInputs;
    private Game game;

    /**
     * This constructor initializes a new instance of the GamePanel class.
     * It sets up the game panel, including setting the panel size and adding key and mouse listeners.
     *
     * @param game This is the game instance.
     */

    public GamePanel(Game game) {
        mouseInputs = new MouseInputs(this);
        keyboardInputs = new KeyboardInputs(this);
        this.game = game;
        setPanelSize();
        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    /**
     * This method is used to set the size of the game panel.
     */

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }

/**
 * This method is used to update the game.
 */

 public void updateGame() {

    }

    /**
     * This method is used to render the game.
     *
     * @param g This is the graphics object to draw on.
     */

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    /**
     * This method is used to get the game instance.
     *
     * @return Game This returns the game instance.
     */

    public Game getGame() {
        return game;
    }

}