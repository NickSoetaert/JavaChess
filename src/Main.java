/*
 * Nick Soetaert
 * December 17, 2017
 */

/*
Puzzle game idea: attempt to de-highlight all squares with bishop/rook movement, etc.
If a piece passes through a square, it flips its highlight.
Certain squares start out highlighted.
*/

import java.awt.*;
import javax.swing.*;

public class Main { 
  public static void main(String args[])
  { 
    MainFrame mainFrame = new MainFrame();
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    mainFrame.setVisible(true); 
  }
}

//Contains the game board, and any additional info such as player name, etc.
class MainFrame extends JFrame{
    private final static int DEFAULT_WIDTH = 800;
    private final static int DEFAULT_HEIGHT = 870; //WARNING: Overrides GameBoard's size. Find out how to not do that
    
    public MainFrame(){
        this.setResizable(false);
        this.setTitle("Java Chess");
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setLayout(new BorderLayout());
        GameBoard board = new GameBoard();
        //idea: Game class that takes a gameboard as an arg. 
        this.add(board, BorderLayout.CENTER);
    }
}