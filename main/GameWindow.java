package main;


import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

/**
 * The GameWindow class is responsible for creating the main game window.
 * <p>
 * This class creates a JFrame, sets its properties, and adds a GamePanel to it.
 * </p>
 *
 * @author Jan-Hendrik Heise, Nina Brandt
 */

    public class GameWindow {
        private JFrame jframe;

        public GameWindow(GamePanel gamePanel) {

            jframe = new JFrame();
            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jframe.add(gamePanel);
            jframe.setLocationRelativeTo(null);
            jframe.setResizable(false);
            jframe.pack();
            jframe.setVisible(true);


        }

    }

