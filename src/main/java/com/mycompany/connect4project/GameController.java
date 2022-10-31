package com.mycompany.connect4project;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import com.mycompany.connect4project.Board.Piece;
import com.mycompany.connect4project.Board.PlaceResult;
import java.util.Random;

public class GameController
{

    public Canvas canvas;


    @FXML
    public void initialize()
    {
        newGame();
        gc = canvas.getGraphicsContext2D();
    }

    protected Board board;
    /// true if the game is currently active, and user inputs should be used to modify the game board.
    protected boolean game_active = true;
    /// holds the piece colour of the active player.
    protected Piece active_piece = randomPiece();
    /// stores the graphics context of the controllers canvas.
    protected GraphicsContext gc;

    /// restarts the current game and initializes an empty board, with size 7 X 6
    protected void newGame()
    {
        board = new Board(7, 6);
        game_active = true;
        active_piece = randomPiece();
    }


    protected int coordToColumn(int x)
    {
        //calculates the column, starting on 0.
        return (int)(x * board.width() / canvas.getWidth());
    }


    protected void drawGame()
    {        
        /// TODO: implement this
        /// FOR PERSON IMPLEMENTING:
        // base this implementation around the size of the board object
        // (board.width() and board.height() methods should be used)
        //
        // also remember to add some small margins to the left right and bottom, and a larger margin at the top,
        // to make place for the hovering piece.

        // the hovering piece should only be drawn if game_active is true.

    }

    protected void drawResult()//assuming active piece is the winning piece.
    {
        switch(active_piece)
        {
            case Red:
                gc.strokeText("Hello Canvas", 200, 20);

            case Blue:
                gc.strokeText("Hello Canvas", 100, 200);
        }


        /// TODO: implement this
        /// should probably display "RED/BLUE WINS" / "DRAW"
        /// followed by a restart button or something.
    }

    /// places a piece from the currently active colour,
    // if the placement is legal, the active player is also swapped
    protected void place(int column)
    {
        PlaceResult place_result = board.place(active_piece, column);

        switch(place_result)
        {
            case Legal:
                active_piece = active_piece == Piece.Red ? Piece.Blue : Piece.Red;
                break;
            case Winning:
                drawResult();
                game_active = false;
        }
    }

    // ============ EVENTS ============

    // not sure what event this should be
    @FXML
    public void onKeyTyped(KeyEvent keyEvent)
    {
        System.out.println(keyEvent);
    }

    @FXML
    public void onMouseClick(MouseEvent mouse_event)
    {
        if(game_active)
            place(coordToColumn((int)mouse_event.getX()));

        drawGame();
    }

    @FXML
    public void onMouseMove(MouseEvent mouseEvent)
    {
        drawResult();
    }
    
    private Piece randomPiece()
    {
        int rand = new Random().nextInt(2);
        return rand == 0 ? Piece.Red : Piece.Blue;
    }
}
