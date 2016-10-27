/**
 * Created by Loewe on 10/22/2016.
 */

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Racquet {
    int score1 = 0; //score for player 1
    int score2 = 0; //score for player 2
    private boolean player;    //determines the player
    private static final int Y1 = 420;  //Y coordinate for player 1
    private static final int Y2 = 30;   //Y coordinate for player 2
    private static final int WITH = 40;  //WIDTH of the racquet
    private static final int HEIGHT = 10;   //HEIGHT of the racquet
    int x = 200;    //x coordinate of the racquet
    int xa = 0; //speed
    private Game game;
    //constructor
    public Racquet(Game game,boolean player) {
        this.game = game;
        this.player = player;
    }

    public int getScore1() { return score1; } //returns score for player 1
    public int getScore2() { return score2; }   //returns score for player 2

    public void move() {
        if(score1 == 3) {   //when score for the player 1 is 3 gameover
            game.gameOver();
        }
        if(score2 == 3){    //when score for player 2 is 3 game over
            game.gameOver();
        }
        if (x + xa > 0 && x + xa < game.getWidth() - WITH) {//stops at the leftmost or rightmost part of the frame
            x = x + xa;     //movement of the racquet
        }
    }
    //painting the racquet
    public void paint(Graphics2D g) {
        if(player) {
            g.setColor(java.awt.Color.RED);
            g.fillRect(x, Y1, WITH, HEIGHT);
        } else {
            g.setColor(java.awt.Color.BLUE);
            g.fillRect(x, Y2, WITH, HEIGHT);
        }
    }
    //returns rectangle in order to determine where the ball will collide
    public Rectangle getBounds(boolean playerturn) {
        if (playerturn) {
            return new Rectangle(x, Y1, WITH, HEIGHT);
        } else {
            return new Rectangle(x, Y2, WITH, HEIGHT);
        }
    }
    //returns below or top  of the rectangle
    public int getTopY(boolean playerturn) {
        if(playerturn)
            return Y1 - HEIGHT;
        else
            return HEIGHT - Y2;
    }
}
