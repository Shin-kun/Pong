/**
 * Created by Michael Loewe on 10/22/2016.
 */ 

import java.awt.*;
import java.util.Random;

//ball functions
public class Ball {
    private boolean chngcolor;  //represents what color to change if it collides with lightning...
    private static int speed = 3;   //ball moves at 4 pixels
    private static final int DIAMETER = 20; //diameter 20

    private int x = 200;    //initially at x - coordinate 200
    private int y = 200;    //initially at y - coordinate 200
    private int xa = 1;     //speed
    private int ya = 1;
    private Game game;
    //constructor
    public Ball(Game game) {
        this.game = game;
        chngcolor = true;
    }

    public int getDiameter() { return DIAMETER; }

    void move() {
        boolean changeDirection = true;
        if (x + xa < 0) {   //if the ball collides on the leftmost part
            xa = speed;
        } else if (x + xa > game.getWidth() - DIAMETER) {   //if the ball collides on the rightmost part
            xa = -speed;
        } else if (y + ya < 0) {    //if the ball collides on the uppermost part
            chngcolor = true;
            game.racquet1.score1++; //+ 1 score for player 1
            resetStats();           //resetting the position and speed of ball
            ya = speed;             //moves downwards

        } else if (y + ya > game.getHeight() - DIAMETER) {  //if the ball collides on the lowermost part
            chngcolor = true;
            game.racquet2.score2++; //+ 1 score for player 2
            resetStats();   //same as above
            ya = -speed;    //moves upward

        } else if (collision(true)){
            chngcolor = true;   //setting the color of the ball to true if it collides with the racquet
            ya = -speed;  //moving up
            y = game.racquet1.getTopY(true) - getDiameter();
            xa = randInt();

        } else if (collision(false)) {
            chngcolor = true;       //same as above
            ya = speed;             //when colliding... its goes back down
            y = getDiameter() - game.racquet2.getTopY(false);
            xa = randInt();

        } else if (collisionIm()) {
            speed++;                //everytime it his the lightning.. speed is added by 1
            chngcolor = false;      //signifies when to change color

        } else {
            changeDirection = false;
        }
        x = x + xa;
        y = y + ya;

        if (changeDirection){
            Sound.BALL.play();
        }
    }

    private void resetStats(){
        x = 150;        //sets the position
        y = 150;
        speed = 2;
        chngcolor = true;
        xa = randInt(); //to determine where the ball will move
    }

    //paint function
    public void paint(Graphics2D g){
        if(chngcolor) {
            g.setColor(Color.WHITE);
        } else{
            g.setColor(Color.YELLOW);
        }
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }
    //collides with the racquets
    public boolean collision(boolean player){
        if(player) {
            return game.racquet1.getBounds(true).intersects(getBounds());
        } else {
            return game.racquet2.getBounds(false).intersects(getBounds());
        }
    }
    //returns either -1 or 1
    public int randInt() {
        Random rand = new Random();
        int randomNum = rand.nextInt(2);
        randomNum = -1 + (randomNum * 2);
        return randomNum;
    }

    //collides with the image
    public boolean collisionIm(){
        return game.img1.getBoundsIm().intersects(getBounds());
    }
    //returns Rectangle bounds
    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
}
