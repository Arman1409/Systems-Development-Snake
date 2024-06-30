package Objekts.Utilities;

import Gamestates.Gamestate;
import Objekts.Snake;
import Objekts.SnakeJon;
import main.Score;

import java.awt.*;
import java.util.ArrayList;


public class ColissionControll {

    private Gamestate gamestate;
    //private Snake snake;
    private SnakeJon snake;
    private Score score;

    private int WIDTH;
    private int HEIGHT;


    public ColissionControll(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    public void checkHitp1(int length, int[][] player ) {


        // check if head run into its body
        for (int i = length; i > 0; i--) {
            if (player[0][0] == player[0][i] && player[1][0] == player[1][i]) {
                Gamestate.state = Gamestate.DEAD;

                ArrayList<Point> body = snake.getBody();
                int finalLength = body.size();
                System.out.println(score.calcScore(finalLength));


           }
        }
        // check if head run into walls
       if (player[0][0] < 0 || player[0][0] > WIDTH || player[1][0] < 0 || player[1][0] > HEIGHT) {
            Gamestate.state = Gamestate.DEAD;

           ArrayList<Point> body = snake.getBody();
           int finalLength = body.size();
           System.out.println(score.calcScore(finalLength));

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
