package Gamestates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * The StartMethods interface defines the methods that need to be implemented by the game states.
 * <p>
 * This interface is used to ensure that all game states have the necessary methods for updating and drawing the state,
 * as well as handling mouse and key events.
 * </p>
 *
 * @author
 */

public interface StartMethods {

    public void update();

    public void draw(Graphics g);

    public void mouseClicked(MouseEvent e);

    public void mousePressed(MouseEvent e);

    public void mouseReleased(MouseEvent e);

    public void mouseMoved(MouseEvent e);

    public void keyPressed(KeyEvent e);

    public void keyReleased(KeyEvent e);

}
