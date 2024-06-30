package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Gamestates.Gamestate;
import main.GamePanel;

/**
 * The KeyboardInputs class implements the KeyListener interface to handle keyboard inputs.
 * <p>
 * This class is responsible for handling key typed, key released, and key pressed events.
 * The actions performed in response to these events depend on the current game state.
 * </p>
 *
 * @author Dennis Sydow, Lucas Fabrius, Nina Brandt
 */

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
            case SINGLEPLAYER:
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            case LOKAL_MULTIPLAYER:
                gamePanel.getGame().getLocal_Multiplayer().keyReleased(e);
                break;
            default:
                break;

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case SINGLEPLAYER:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            case LOKAL_MULTIPLAYER:
                gamePanel.getGame().getLocal_Multiplayer().keyPressed(e);
                break;
            default:
                break;
        }
    }
}