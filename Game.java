/**
 * Created by Michael Loewe Alivio on 10/21/2016.
 */

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class Game extends JPanel {  //instantiating classes
    Ball ball = new Ball(this);
    Racquet racquet1 = new Racquet(this,true);
    Racquet racquet2 = new Racquet(this,false);
    ImageLightning img1 = new ImageLightning(this);

    public Game() { //keylisteners
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
                racquet1.xa = 0;    //if released nothing racquet will not move
                racquet2.xa = 0;
            }
            @Override
            public void keyPressed(KeyEvent e) {    //if pressed it will move at the speed of 3 pixels
                if(e.getKeyCode() == KeyEvent.VK_A) {racquet2.xa = -3;}
                if(e.getKeyCode() == KeyEvent.VK_D) {racquet2.xa = 3;}
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {racquet1.xa = -3;}
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) {racquet1.xa = 3;}
            }
        });
        setFocusable(true);
    }
    //move functions...
    private void move() {
        racquet1.move();
        racquet2.move();
        ball.move();
        img1.randEnt();
    }
    //painting or drawing on the frame
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        ball.paint(g2d);    //calls the paint function on ball
        racquet1.paint(g2d);    //calls the paint function on racquet
        racquet2.paint(g2d);
        img1.paint(g2d);        //calls the paint function in the image
        //g.drawImage(bkgimg, 0 ,0, null);
        //drawing scores
        g2d.setColor(Color.RED);            //sets the font color red
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString(String.valueOf(racquet1.getScore1()), 10, 200);  //drawing the score for player 1
        g2d.setColor(Color.BLUE);       //sets the font color blue
        g2d.drawString(String.valueOf(racquet2.getScore2()), 340, 200); //drawing the score for player 2
        this.setBackground(Color.BLACK);    //sets the background into black
    }

    //if the game is over, a Dialog box appears
    public void gameOver() {
        Sound.UNDTALE.stop();   //background music
        String[] option = {"YES","NO"};
        String disp = "";
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel("Would you like to play again?");
        panel.add(lbl);

        if(racquet1.getScore1() == 3){
            disp += "PLAYER 1 WINS";
        } else {
            disp += "PLAYER 2 WINS";
        }
        //returns an integer when the dialog box appears with buttons yes and no
        int again = JOptionPane.showOptionDialog(this,panel,disp,
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
        if(again == 0){ //if yes.. resets the score
            racquet1.score1 = 0;
            racquet2.score2 = 0;
            Sound.UNDTALE.loop();
        } else {
            System.exit(ABORT); //if no exit
        }
    }

    public void Instructions(){
        String[] option = {"OK"};
        JPanel panel= new JPanel();
        JTextArea texta = new JTextArea("\tRED: PLAYER 1\nLeft Arrow Key to turn left\n" +
                "Right Arrow Key to turn right\n" +
                "\tBLUE: PLAYER 2\nA to turn left\n" +
                "D to turn right\n" +
                "      GET THE LIGHTNING TO SPEED UP THE BALL!\n\tCredits to Toby Fox");
        texta.setEditable(false);
        panel.add(texta);

        JOptionPane.showOptionDialog(this,panel,"Instructions",
                JOptionPane.YES_OPTION,JOptionPane.INFORMATION_MESSAGE,null,option,option[0]);
    }

    //main function
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Mini Tennis");
        Game game = new Game();
        //setting game and the frame
        frame.add(game);
        frame.setSize(400, 500);    //set size frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        game.Instructions();
        Sound.UNDTALE.loop();   //background music
        while (true) {
                game.move();
                game.repaint();
                Thread.sleep(10);
            }
    }
}
