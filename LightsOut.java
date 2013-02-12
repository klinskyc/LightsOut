import acm.graphics.*;
import acm.program.GraphicsProgram;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.lang.Math;
import java.net.URLConnection;
import java.net.URL;
import java.io.*;
import javax.sound.sampled.*;

/**
 * Write a description of class LightsOut here.
 * 
 * @author (Cavan Klinsky) 
 * @version (1.0)
 * Citations for Sound
 * Found a basic example here http://stackoverflow.com/questions/2416935/how-to-play-wav-files-with-java
 * This let me to the Javax Sound Sampled API http://docs.oracle.com/javase/1.4.2/docs/api/javax/sound/sampled/package-summary.html
 * Found an example .Wav Here http://pscode.org/media/#sound
 * Loop .Wav found in Game is http://www.looperman.com/loops/detail/60384
 * .Wav converted by Audacity http://audacity.sourceforge.net/
 * Victory is Mine .Wav http://jb5353.tripod.com/familyguy/damnuall.wav
 */
public class LightsOut extends GraphicsProgram
{
    // instance variables - replace the example below with your own
    private int x;
    private static final int START_X = 150;
    private static final int START_Y = 75;
    private static final int RECT_LENGTH = 50;
    private static final int RECT_HEIGHT = 50;
    private static final int RECT_WIDTH = 50;
    private  int clicks = 0;
    private  String totclicks = clicks +"";
    private GRect [][] board;
    private GRect reset = new GRect(300.0,200.0, 100.0, 100.0);

    private GLabel allClicks = new GLabel (totclicks,300,350);
    private GLabel winning = new GLabel("You Have Yet To Win", 300, 400)  ;
    /**
     * Constructor for objects of class LightsOut
     */
    public  void init () 
    {
        // initialise instance variables
        GRect reset = new GRect(300.0,200.0, 100.0, 100.0);
        reset.setFillColor(Color.orange);
        reset.setFilled(true);
        GLabel resetText = new GLabel ("Reset", 350, 250 );
        add(reset);
        add(resetText);
        GRect across = new GRect (START_X,START_Y + 150,RECT_LENGTH,RECT_WIDTH);
        GRect across2 = new GRect (START_X - 50,START_Y + 150,RECT_LENGTH,RECT_WIDTH);
        GRect across3 = new GRect (START_X - 100,START_Y + 150,RECT_LENGTH,RECT_WIDTH);
        add(across);
        add(across2);
        add(across3);
        GRect across4 = new GRect (START_X,START_Y + 50,RECT_LENGTH,RECT_WIDTH);
        GRect across5 = new GRect (START_X - 50,START_Y + 50,RECT_LENGTH,RECT_WIDTH);
        GRect across6 = new GRect (START_X - 100,START_Y + 50,RECT_LENGTH,RECT_WIDTH);
        add(across4);
        add(across5);
        add(across6);
        GRect across7 = new GRect (START_X,START_Y + 100,RECT_LENGTH,RECT_WIDTH);
        GRect across8 = new GRect (START_X - 50,START_Y + 100,RECT_LENGTH,RECT_WIDTH);
        GRect across9 = new GRect (START_X - 100,START_Y + 100,RECT_LENGTH,RECT_WIDTH);
        add(across7);
        add(across8);
        add(across9);
        add(allClicks);
        add(winning);
        String musicFile = "http://www.clayloomis.com/Sounds/mail1/mail140.wav";
        playMusic( musicFile);
        initializeBoard();
        addMouseListeners(); //

    }

    private static void playMusic(String musicFile)  
    {
        String selectedlink = "chill.wav";

        try
        {
            Clip clip = AudioSystem.getClip();
            Clip victory = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(selectedlink)));

            
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            
            
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    }

    private static void playVictory(String musicFile)  
    {
        String selectedlink = "chill.wav";
        String victorylink = "victory.wav";
        try
        {
            Clip clip = AudioSystem.getClip();
            Clip victory = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(selectedlink)));
            victory.open(AudioSystem.getAudioInputStream(new File(victorylink)));

            victory.start();
            

        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    }

    private void initializeBoard () 
    {

        board = new GRect[3][3]; // make the 3x3 grid
        String blank = "You have yet to win";
        winning.setLabel(blank);
        GLabel allClicks = new GLabel(totclicks, 300, 350)  ;

        double y = START_Y + RECT_WIDTH;
        double x = START_X;
        for (int r=0; r < 3; r++)
        {
            for (int c=0; c < 3; c++)
            {
                int randomlight = (int)(Math.random() * 2);
                board[r][c] = new GRect(x, y,RECT_LENGTH,RECT_WIDTH );
                if(randomlight != 0)
                {
                    board[r][c].setFillColor(Color.red);
                    board[r][c].setFilled(true);
                    add(board[r][c]);

                }
                else
                {
                    board[r][c].setFillColor(Color.blue);
                    board[r][c].setFilled(true);
                    add(board[r][c]);
                }

                x -= RECT_WIDTH;
                System.out.println("x"+x + "y"+y);
            }
            x = START_X; // reset
            y += RECT_HEIGHT;

        }
    }

    public  boolean checkDone()
    {
        int won = 0;

        for (int r=0; r < 3; r++)
        {
            for (int c=0; c < 3; c++)
            {
                if(board[r][c].getFillColor() == Color.blue)
                {
                    won = won + 1;
                }
            }
            System.out.println(won);

        }
        if(won == 9)
        {
            return true;

        }
        else
        {
            return false;

        }

    }

    @Override
    public void mousePressed(MouseEvent event)
    {
        if (reset.contains(event.getX(), event.getY()) )
        {
            System.out.println("hello");
            initializeBoard();
            clicks = 0;
            totclicks = clicks + ""; 
            allClicks.setLabel(totclicks);

        }
        if (checkDone() == false)
        {

            System.out.println(reset);
            System.out.println(event.getX());
            System.out.println(event.getY());

            for (int r=0; r < 3; r++)
            {

                for (int c=0; c < 3; c++)
                {

                    if (board[r][c].contains(event.getX(), event.getY()) )
                    {
                        clicks++; 

                        System.out.println(clicks + "clicks");
                        totclicks = clicks + ""; 

                        allClicks.setLabel(totclicks);

                        if(checkDone() == true)
                        {
                            winning.setLabel("You Have Won");
                            System.out.println("you won");
                        }

                        if(board[r][c].getFillColor() == Color.red)
                        {

                            if(board[r][c].getFillColor() == Color.red )
                            {
                                board[r][c].setFillColor(Color.blue);
                                board[r][c].setFilled(true);

                            }
                            if(r <= 1)
                            {
                                if(board[r+1][c].getFillColor()==Color.red)
                                {
                                    board[r+1][c].setFillColor(Color.blue);
                                    board[r+1][c].setFilled(true);
                                }
                                else
                                {
                                    board[r+1][c].setFillColor(Color.red);
                                    board[r+1][c].setFilled(true);
                                }
                            }
                            if(r >= 1)
                            {
                                if(board[r-1][c].getFillColor()==Color.red)
                                {
                                    board[r-1][c].setFillColor(Color.blue);
                                    board[r-1][c].setFilled(true);
                                }
                                else
                                {
                                    board[r-1][c].setFillColor(Color.red);
                                    board[r-1][c].setFilled(true);
                                }
                            }
                            if(c <= 1)
                            {

                                if(board[r][c+1].getFillColor()==Color.red)
                                {
                                    board[r][c+1].setFillColor(Color.blue);
                                    board[r][c+1].setFilled(true);
                                }
                                else
                                {
                                    board[r][c+1].setFillColor(Color.red);
                                    board[r][c+1].setFilled(true);
                                }
                            }
                            if(c >= 1)
                            {
                                if(board[r][c-1].getFillColor()==Color.red)
                                {
                                    board[r][c-1].setFillColor(Color.blue);
                                    board[r][c-1].setFilled(true);
                                }
                                else
                                {
                                    board[r][c-1].setFillColor(Color.red);
                                    board[r][c-1].setFilled(true);
                                }
                            }

                        }
                        else
                        {

                            if(board[r][c].getFillColor() == Color.blue )
                            {
                                board[r][c].setFillColor(Color.red);
                                board[r][c].setFilled(true);
                            }
                            if(r <= 1)
                            {
                                if(board[r+1][c].getFillColor()==Color.blue)
                                {
                                    board[r+1][c].setFillColor(Color.red);
                                    board[r+1][c].setFilled(true);
                                }
                                else
                                {
                                    board[r+1][c].setFillColor(Color.blue);
                                    board[r+1][c].setFilled(true);
                                }
                            }
                            if(r >= 1)
                            {
                                if(board[r-1][c].getFillColor()==Color.blue)
                                {
                                    board[r-1][c].setFillColor(Color.red);
                                    board[r-1][c].setFilled(true);
                                }
                                else
                                {
                                    board[r-1][c].setFillColor(Color.blue);
                                    board[r-1][c].setFilled(true);
                                }
                            }
                            if(c <= 1)
                            {

                                if(board[r][c+1].getFillColor()==Color.blue)
                                {
                                    board[r][c+1].setFillColor(Color.red);
                                    board[r][c+1].setFilled(true);
                                }
                                else
                                {
                                    board[r][c+1].setFillColor(Color.blue);
                                    board[r][c+1].setFilled(true);
                                }
                            }
                            if(c >= 1)
                            {
                                if(board[r][c-1].getFillColor()==Color.blue)
                                {
                                    board[r][c-1].setFillColor(Color.red);
                                    board[r][c-1].setFilled(true);
                                }
                                else
                                {
                                    board[r][c-1].setFillColor(Color.blue);
                                    board[r][c-1].setFilled(true);
                                }
                            }

                        }

                    }

                }
                if(checkDone()==true)
                {
                    String musicFile ="victory.wav";
                    playVictory(musicFile);
                    winning.setLabel("You Have Won");
                    System.out.println("you won");
                }
            }
        }

    }
}
