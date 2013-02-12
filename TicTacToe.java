import acm.graphics.*;
import acm.program.GraphicsProgram;
import java.awt.Font;
import java.awt.event.MouseEvent;
/**
 * class TicTacToe is a sample
 * program which implements
 * two-player Tic Tac Toe as
 * discussed in class.
 * 
 * Note:  We did not add testing for a completed board
 * 
 * @author J. Smith
 * @version January 2013
 */
public class TicTacToe extends GraphicsProgram
{
    private static final int START_X = 100;
    private static final int START_Y = 75;
    private static final int LINE_LENGTH = 200;
    private static final int LINE_HEIGHT = 75;
    private static final int LINE_WIDTH = LINE_LENGTH/3;
    
    private boolean player;
    
    
    private GLabel [][] board;
    
    public void init ()
    {
        GLine across = new GLine (START_X, START_Y, START_X+LINE_LENGTH, START_Y);
        GLine across2 = new GLine (START_X, START_Y+LINE_HEIGHT, START_X+LINE_LENGTH, START_Y+LINE_HEIGHT);
        add(across);
        add(across2);
        GLine down = new GLine(START_X+LINE_WIDTH, START_Y-LINE_WIDTH, START_X+LINE_WIDTH, START_Y+LINE_LENGTH-LINE_WIDTH);
        GLine down2 = new GLine(START_X+2*LINE_WIDTH, START_Y-LINE_WIDTH, START_X+2*LINE_WIDTH, START_Y+LINE_LENGTH-LINE_WIDTH);
        add(down);
        add(down2);
        
        initializeBoard();
        
        player = true;
        addMouseListeners(); // pay attention to mouse events
    }
    
    private void initializeBoard ()
    {
        // make the blank labels to click on
        board = new GLabel[3][3]; // make the 3x3 grid
        String blank = "    ";
        double x = START_X + LINE_WIDTH/2.0;
        double y = START_Y - LINE_HEIGHT/2.0;
        for (int r=0; r < 3; r++)
        {
            for (int c=0; c < 3; c++)
            {
                board[r][c] = new GLabel(blank, x, y);
                board[r][c].setFont(new Font("Serif", Font.BOLD, 18));
                add(board[r][c]);
                x += LINE_WIDTH;
            }
            x = START_X + LINE_WIDTH/2.0; // reset
            y += LINE_HEIGHT;
        }
    }
    
    @Override
    public void mousePressed(MouseEvent event)
    {
        // check buttons for click
        for (int r=0; r < 3; r++)
        {
            for (int c=0; c < 3; c++)
            {
                if (board[r][c].contains(event.getX(), event.getY()))
                {
                    if (player)
                        board[r][c].setLabel("X");
                    else 
                        board[r][c].setLabel("O");
                    player = !player;
                }
            }
        }
    }
    
}
