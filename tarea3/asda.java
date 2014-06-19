asda.java
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;
import java.net.URL;

// Applet creates a TicBorad object
// and TicGame object (game) that uses the board
// The game object manages the game the applet
// handles events and drawing
// Computer plays O, user plays X
// Computer and user alternates moving first

public class TicTacToe extends Applet
{   public void init()
    {   codeBase = getCodeBase();
        setBoard();
        game = new TicGame(board);
        addMouseListener(new ClickHandler(this));
        newGame();
    }

    public void paint(Graphics g)
    {   g.setColor(Color.black);   // foreground color
        game.draw(g, this);
    }

//  Process user's move at pixel location x,y
    public void userMove(int x, int y)
    {   if ( game.ended() ) // setup new game
        {    newGame();
             return;   // event handled
        }
        // determine mouse position on board
        int c = board.col(x);
        int r = board.row(y);
        if ( ! move(c+r*3, TicGame.PLAYER_X) ) // X moves
            invalidMove(); // X move incorrect
        else if ( ! game.ended() )
        {  c = game.genMove(TicGame.PLAYER_O);
           if ( c != TicGame.NOMOVE )
                move(c, TicGame.PLAYER_O);     // O moves
        }
        return;
    }

    protected void invalidMove() {}
    protected void winner() {}
    protected void gameBegin() {}

// makes a move for indicated player, return false if invalid
    protected boolean move(int m, boolean player)
    {   int status = game.move(m, player);
        if ( status == TicGame.NOMOVE )
             return false;
        repaint();
        if ( status == TicGame.WIN_X ||
             status == TicGame.WIN_O )
             winner(); 
        return true;
     }

// Sets up new game
    protected void newGame()
    {   game.reset();
        if (first) 
           game.move(game.genMove(TicGame.PLAYER_O),
                      TicGame.PLAYER_O);
        first = !first;
        repaint();
        gameBegin(); 
    }

    protected void setBoard()
    {   board = new TicBoard(0, 0,
           getSize().width, getSize().height,
           getImage(codeBase, "images/o.gif"), // Load images
           getImage(codeBase, "images/x.gif") );
    }

    protected URL codeBase = null;
    protected TicBoard board;
    protected TicGame game = null;
    protected boolean first = false; // user moves first
}
