package Objekts.Utilities;

import Gamestates.Gamestate;
import Gamestates.Multiplayer;
import Objekts.SnakeJon;
import org.junit.jupiter.api.BeforeEach;

import java.awt.*;
import java.awt.geom.Point2D;

import static Gamestates.Gamestate.MULTIPLAYER;
import static Gamestates.Gamestate.SINGLEPLAYER;
import static org.junit.jupiter.api.Assertions.*;

class ColissionControllTest {
    ColissionControll cc;
    @BeforeEach
    void setUp() {
        cc    = new ColissionControll(640,640);
    }

    @org.junit.jupiter.api.Test
    void checkHitp1() {
        Gamestate.state=SINGLEPLAYER;
        SnakeJon s1 = new SnakeJon(new Point(-96,96),'R',"snake-graphics32.png");
        s1.move();
        cc.checkHitp1(s1.getBody());
        assertEquals(Gamestate.DEAD,Gamestate.state,"Snake outside left boundaries");
        Gamestate.state=SINGLEPLAYER;
        SnakeJon s2 = new SnakeJon(new Point(96,-96),'R',"snake-graphics32.png");
        s2.move();
        cc.checkHitp1(s2.getBody());
        assertEquals(Gamestate.DEAD,Gamestate.state,"Snake outside upper boundaries");
        Gamestate.state=SINGLEPLAYER;
        SnakeJon s3 = new SnakeJon(new Point(8888,96),'R',"snake-graphics32.png");
        s3.move();
        cc.checkHitp1(s3.getBody());
        assertEquals(Gamestate.DEAD,Gamestate.state,"Snake outside right boundaries");
        Gamestate.state=SINGLEPLAYER;
        SnakeJon s4 = new SnakeJon(new Point(96,7777),'R',"snake-graphics32.png");
        s4.move();
        cc.checkHitp1(s4.getBody());
        assertEquals(Gamestate.DEAD,Gamestate.state,"Snake outside lower boundaries");
        Gamestate.state=SINGLEPLAYER;
        SnakeJon s5 = new SnakeJon(new Point(96,96),'R',"snake-graphics32.png");
        s5.setSpeed(1);
        s5.move();
        s5.setDirection('D');
        s5.move();
        s5.setDirection('L');
        s5.move();
        s5.setDirection('U');
        s5.move();
        cc.checkHitp1(s5.getBody());
        assertEquals(Gamestate.DEAD,Gamestate.state,"Snake hits itself in confusion");
    }

    @org.junit.jupiter.api.Test
    void checkHitp2() {
        Gamestate.state= MULTIPLAYER;
        SnakeJon s1 = new SnakeJon(new Point(64,64),'R',"snake-graphics32.png");
        SnakeJon s2 = new SnakeJon(new Point(96,128),'U',"snake-graphics32.png");
        s1.move();
        s2.move();
        s1.move();
        s2.move();
        cc.checkHitp2(s1.getBody(),s2.getBody(),s1,s2);
        assertEquals(Gamestate.DEAD,Gamestate.state,"Player 2 lost");

        Gamestate.state= MULTIPLAYER;
        SnakeJon s3 = new SnakeJon(new Point(64,64),'D',"snake-graphics32.png");
        SnakeJon s4 = new SnakeJon(new Point(64,128),'L',"snake-graphics32.png");
        s3.move();
        s4.move();
        s3.move();
        s4.move();
        cc.checkHitp2(s3.getBody(),s4.getBody(),s3,s4);
        assertEquals(Gamestate.DEAD,Gamestate.state,"Player 1 lost");
    }
}