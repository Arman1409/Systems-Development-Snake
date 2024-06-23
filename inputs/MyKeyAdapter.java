package inputs;

import java.awt.event.*;

import main.GamePanel;
import main.*;


 public class MyKeyAdapter extends KeyAdapter {


     private GamePanel gamePanel;
     private boolean arrowKeys;
    public MyKeyAdapter(GamePanel gamePanel, boolean arrowKeys){
        this.gamePanel = gamePanel;
        this.arrowKeys = arrowKeys;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (arrowKeys) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (gamePanel.getDirection() != 'R') {
                        gamePanel.setDirection('L');
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (gamePanel.getDirection() != 'L') {
                        gamePanel.setDirection('R');
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (gamePanel.getDirection() != 'D') {
                        gamePanel.setDirection('U');
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (gamePanel.getDirection() != 'U') {
                        gamePanel.setDirection('D');
                    }
                    break;
            }
        } else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    if (gamePanel.player2direction != 'C') {
                        gamePanel.player2direction = 'Y';
                    }
                    break;
                case KeyEvent.VK_D:
                    if (gamePanel.player2direction != 'Y') {
                        gamePanel.player2direction = 'C';
                    }
                    break;
                case KeyEvent.VK_W:
                    if (gamePanel.player2direction != 'D') {
                        gamePanel.player2direction = 'X';
                    }
                    break;
                case KeyEvent.VK_S:
                    if (gamePanel.player2direction != 'X') {
                        gamePanel.player2direction = 'D';
                    }
                    break;
            }
        }
    }
}
