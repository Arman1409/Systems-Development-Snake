package Gamestates;

import main.Game;
import UIElement.SingleplayerButton;

import java.awt.event.MouseEvent;

public class State {
    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public boolean isIn(MouseEvent e, SingleplayerButton mb) {
        return mb.getBounds().contains(e.getX(), e.getY());
    }


    public Game getGame() {
        return game;
    }
}
