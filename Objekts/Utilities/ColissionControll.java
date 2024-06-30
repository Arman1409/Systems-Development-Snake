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





    public void checkHitp2(ArrayList<Point> player1 ,ArrayList<Point> player2) {
        // check if head run into its body
        for (int i = player1.size()-1; i > 0; i--) {
            if (player1.get(0).x == player1.get(i).x && player1.get(0).y == player1.get(i).y) {
                Gamestate.state = Gamestate.DEAD;
            }
        }
        for (int i = player2.size()-1; i > 0; i--) {
            if (player2.get(0).x == player2.get(i).x && player2.get(0).y == player2.get(i).y) {
                Gamestate.state = Gamestate.DEAD;
            }
        }
        // check if head run into walls
        if (player1.get(0).x < 0 || player1.get(0).x > WIDTH || player1.get(0).y < 0 ||player1.get(0).y > HEIGHT) {
            Gamestate.state = Gamestate.DEAD;
        } else  if (player2.get(0).x < 0 || player2.get(0).x > WIDTH || player2.get(0).y < 0 ||player2.get(0).y > HEIGHT) {
        Gamestate.state = Gamestate.DEAD;
        }

        int size = 0;
        if (player1.size()>= player2.size()) {
            size = player2.size()-1;
        }else for (int i = player1.size()-1; i > 0; i--) {
            size = player1.size()-1;
        }
        for (int i = size; i > 0; i--) {
         if(player1.getFirst().x == player2.get(i).x && player1.getFirst().y == player2.get(i).y) {
             Gamestate.state = Gamestate.DEAD;
             System.out.println("P1 lost");
         }else if (player2.getFirst().x == player1.get(i).x && player2.getFirst().y == player1.get(i).y) {
             Gamestate.state = Gamestate.DEAD;
             System.out.println("P2 lost");
         }

        }




        }
    }


