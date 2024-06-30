package Objekts.Utilities;

import Gamestates.Gamestate;
import Objekts.SnakeJon;

import java.awt.*;
import java.util.ArrayList;


public class ColissionControll {

    /**
     * The ColissionControll class is responsible for managing the collision control in the game.
     * <p>
     * This class checks if the player's head runs into its body or the walls.
     * </p>
     *
     * @author
     */

    private Gamestate gamestate;

    private int WIDTH;
    private int HEIGHT;

    /**
     * This constructor initializes a new instance of the ColissionControll class.
     * It sets the width and height for the collision control.
     *
     * @param WIDTH This is the width for the collision control.
     * @param HEIGHT This is the height for the collision control.
     */

    public ColissionControll(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    /**
     * This method checks if the player's head runs into its body or the walls.
     *
     * @param player This is the player instance.
     */

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


    /**
     * This method checks if the player's head runs into its body, the walls, or other players.
     *
     * @param player1 This is the first player instance.
     * @param player2 This is the second player instance.
     * @param p1 This is the first SnakeJon instance.
     * @param p2 This is the second SnakeJon instance.
     */


    public void checkHitp2(ArrayList<Point> player1 , ArrayList<Point> player2, SnakeJon p1 , SnakeJon p2) {
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


        // Collision with other Players
        int size = 0;
        if (player1.size()>= player2.size()) {
            size = player2.size()-1;
        }else for (int i = player1.size()-1; i > 0; i--) {
            size = player1.size()-1;
        }
        for (int i = size; i > 0; i--) {
         if(p1.isHit(player2.getFirst().getLocation())) {
             Gamestate.state = Gamestate.DEAD;
             System.out.println("P2 lost");
         }else if (p2.isHit(player1.getFirst().getLocation())){
             Gamestate.state = Gamestate.DEAD;
             System.out.println("P1 lost");
         }

        }




        }
    }


