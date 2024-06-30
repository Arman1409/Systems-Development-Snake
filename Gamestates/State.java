package Gamestates;

import UIElement.DeadButtons;
import main.Game;
import UIElement.SingleplayerButton;

import java.awt.event.MouseEvent;

/**
 * The State class represents the basic structure of a game state.
 * <p>
 * This class is used as a base for all game states, providing common properties and methods.
 * </p>
 *
 * @author
 */

public class State {
    protected Game game;

    /**
     * This constructor initializes a new instance of the State class.
     *
     * @param game This is the game instance.
     */

    public State(Game game) {
        this.game = game;
    }

    /**
     * This method is used to check if the mouse is within the bounds of a SingleplayerButton.
     *
     * @param e This is the mouse event object.
     * @param mb This is the SingleplayerButton to check.
     * @return boolean This returns true if the mouse is within the bounds of the SingleplayerButton, and false otherwise.
     */

    public boolean isIn(MouseEvent e, SingleplayerButton mb) {
        return mb.getBounds().contains(e.getX(), e.getY());
    }

    /**
     * This method is used to check if the mouse is within the bounds of a DeadButtons.
     *
     * @param e This is the mouse event object.
     * @param db This is the DeadButtons to check.
     * @return boolean This returns true if the mouse is within the bounds of the DeadButtons, and false otherwise.
     */

    public boolean isIn(MouseEvent e, DeadButtons db) {
        return db.getBounds().contains(e.getX(), e.getY());
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
