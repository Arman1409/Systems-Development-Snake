package inputs;

import Gamestates.Gamestate;
import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (Gamestate.state) {
            case SINGLEPLAYER:
                gamePanel.getGame().getPlaying().mouseDragged(e);
                break;

            default:
                break;

        }

    }



    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseMoved(e);
                break;
            case SINGLEPLAYER:
                gamePanel.getGame().getPlaying().mouseMoved(e);
                break;

            case DEAD:
                gamePanel.getGame().getDead().mouseMoved(e);
                break;
            default:
                break;

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (Gamestate.state) {
            case SINGLEPLAYER:
                gamePanel.getGame().getPlaying().mouseClicked(e);
                break;

            case DEAD:
                gamePanel.getGame().getDead().mouseClicked(e);
                break;
            default:
                break;

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().mousePressed(e);
                break;
            case SINGLEPLAYER:
                gamePanel.getGame().getPlaying().mousePressed(e);
                break;

                case DEAD:
                    gamePanel.getGame().getDead().mousePressed(e);
                    break;
            default:

                break;

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseReleased(e);
                break;
            case SINGLEPLAYER:
                gamePanel.getGame().getPlaying().mouseReleased(e);
                break;
                case DEAD:
                    gamePanel.getGame().getDead().mouseReleased(e);
                    break;

            default:
                break;

        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}

