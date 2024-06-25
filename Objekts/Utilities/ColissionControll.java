package Objekts.Utilities;

import Gamestates.Gamestate;
import Objekts.Snake;

import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;

public class ColissionControll {

    private Gamestate gamestate;
    private Snake snake;


    public ColissionControll() {

    }

    public void checkHitp1() {


        // check if head run into its body
        for (int i = snake.getLength(); i > 0; i--) {
            if (snake.getPlayer1()[0][0] == snake.getPlayer1()[0][i] && snake.getPlayer1()[1][0] == snake.getPlayer1()[1][i]) {
                Gamestate.state = Gamestate.DEAD;

            }
        }
        // check if head run into walls
        if (snake.getPlayer1()[0][0] < 0 || snake.getPlayer1()[0][0] > WIDTH || snake.getPlayer1()[1][0] < 0 || snake.getPlayer1()[1][0] > HEIGHT) {
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
