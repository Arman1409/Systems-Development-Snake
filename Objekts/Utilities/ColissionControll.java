package Objekts.Utilities;

import Gamestates.Gamestate;
import Objekts.Snake;

import java.awt.*;
import java.util.ArrayList;


public class ColissionControll {

    private Gamestate gamestate;
    private Snake snake;

    private int WIDTH;
    private int HEIGHT;


    public ColissionControll(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    public void checkHitp1(ArrayList<Point> player) {


        // check if head run into its body
        for (int i = player.size()-1; i > 0; i--) {
            if (player.get(0).x == player.get(i).x && player.get(0).y == player.get(i).y) {
                Gamestate.state = Gamestate.DEAD;

           }
        }
        // check if head run into walls
       if (player.get(0).x < 0 || player.get(0).x > WIDTH || player.get(0).y < 0 ||player.get(0).y > HEIGHT) {
            Gamestate.state = Gamestate.DEAD;

        }

    }




   /*
    public void checkHitp2() {
        // check if head run into its body
        for (int i = length[1]; i > 0; i--) {
            if (player1[0][0] == player1[0][i] && player1[1][0] == player1[1][i]) {
                running = false;
            } else if (player2[0][0] == player2[0][i] && player2[1][0] == player2[1][i]) {
                running = false;
            }
        }
        // check if head run into walls
        if (player1[0][0] < 0 || player1[0][0] > WIDTH || player1[1][0] < 0 || player1[1][0] > HEIGHT) {
            running = false;
        } else if (player2[0][0] < 0 || player2[0][0] > WIDTH || player2[1][0] < 0 || player2[1][0] > HEIGHT) {
            running = false;
        }


        for (int i = length[0]; i > 0; i--) {
            if (player1[0][i] == player2[0][i] && player1[1][i] == player2[1][i]) {
                System.out.println(player1[0][i]);
            }

        }

        if (!running) {

        }
    }

    */
}
