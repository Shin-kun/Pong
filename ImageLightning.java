/**
 * Created by Loewe on 10/26/2016.
 */
import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.awt.Rectangle;
/*
    implementing the bonus
    this is creates the Image Lightning...
    when the ball collides with the lightning image, it will speed up...
 */
public class ImageLightning {
    private Game game;
    private boolean imgmove = false;
    private int imgx;   //its x coordinate
    private int imgy;   //its y coordinate
    final static int WID = 30;  //width of the image
    final static int HEIT = 50; //height of the image
    ImageIcon img = new ImageIcon(this.getClass().getResource("lightning.png"));

    ImageLightning(Game game){
        this.game = game;
    }

    public int eX(){
        Random r = new Random();
        int low1 = 110;
        int high1 = 280;
        return r.nextInt(high1 - low1) + low1;
    }

    public int waY(){
        Random r = new Random();
        int low2 = 130;
        int high2 = 260;
        return r.nextInt(high2 - low2) + low2;
    }
    //getting the coordinates of the lightning image
    public void randEnt(){
        if(!imgmove){
            imgx = eX();
            imgy = waY();
            imgmove = true;
        }
    }
    //painting the lightning image in the frame
    public void paint(Graphics2D g){
        if(!game.ball.collisionIm()){
            img.paintIcon(game,g,imgx,imgy);
        } else {
            game.ball.paint(g);
            imgmove = false;
            g.setColor(Color.BLACK);
            g.fillRect(imgx,imgy,WID,HEIT);
        }
    }

    public Rectangle getBoundsIm(){ return new Rectangle(imgx,imgy,WID,HEIT); }
}
