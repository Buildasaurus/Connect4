package com.mycompany.connect4project;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import com.mycompany.connect4project.Board.Piece;
import com.mycompany.connect4project.Board.PlaceResult;
import javafx.scene.paint.Color;

import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class GameController
{

    public Canvas canvas;
    
    @FXML
    Button restartGameButton = new Button();
    @FXML
    Text gameDrawn = new Text();
    @FXML
    Text blueWin = new Text();
    @FXML
    Text redWin = new Text();
    
    
    
    //@FXML
    public void initialize()
    {
        gc = canvas.getGraphicsContext2D();
        newGame();
    }

    protected Board board;
    /// true if the game is currently active, and user inputs should be used to modify the game board.
    protected boolean game_active = true;
    /// holds the piece colour of the active player.
    protected Piece active_piece = randomPiece();
    /// stores the graphics context of the controllers canvas.
    protected GraphicsContext gc;
    protected int slot_size = 75;
    protected int piece_size = 50;
    protected int top_height = 2;
    protected int mouse_x = -1;
    protected int width_margin = 137;

    /// restarts the current game and initializes an empty board, with size 7 X 6
    @FXML
    protected void newGame()
    {
        board = new Board(7, 6);
        game_active = true;
        active_piece = randomPiece();
        restartGameButton.setVisible(false);
        redWin.setVisible(false);
        blueWin.setVisible(false);
        gameDrawn.setVisible(false);
        drawGame();
    }


    protected int coordToColumn(int x)
    {
        //calculates the column, starting on 0.
        return Math.min(Math.max(0, (int)((x - width_margin) / slot_size)), board.width() - 1);
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

        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.DARKBLUE);
        gc.fillRect(width_margin, top_height * slot_size,
                board.width() * slot_size, board.height() * slot_size);


        // draw the board pieces

        for(int x = 0; x < board.width(); x++)
        {
            for(int y = 0; y < board.height(); y++)
            {
                drawPiece(x, y + top_height, board.viewPiece(x, y));
            }
        }

        // the hovering piece should only be drawn if game_active is true.

        if(game_active)
        {
            drawPiece(coordToColumn(mouse_x), 0, active_piece);
        }

    }

    void drawPiece(int c, int r, Piece piece)
    {
        switch (piece)
        {
            case Empty:
                gc.setFill(Color.LIGHTBLUE);
                break;
            case Red:
                gc.setFill(Color.RED);
                break;
            case Blue:
                gc.setFill(Color.BLUE);
                break;
        }

        int x = c * slot_size + width_margin;
        int y = r * slot_size;
        int margin = (slot_size - piece_size) / 2;

        x -= margin / 2;
        y -= margin / 2;

        gc.fillOval(x + margin, y + margin, slot_size - margin, slot_size - margin);

    }

    protected void drawResult()
    {
        /// TODO: implement this
        /// should probably display "RED/BLUE WINS" / "DRAW"
        /// followed by a restart button or something.
        restartGameButton.setVisible(true);
        switch (active_piece){
            case Red:
                redWin.setVisible(true);
                        break;
            case Blue:
                blueWin.setVisible(true);
                        break;
                
            case Empty:
                gameDrawn.setVisible(true);
                break;
                
        }
    }

    /// places a piece from the currently active colour,
    // if the placement is legal, the active player is also swapped
    protected void place(int column)
    {
        PlaceResult place_result = board.place(active_piece, column);

        switch(place_result)
        {
            case Legal:
                if(board.isDraw())
                {
                    System.out.println("DRAW");
                    active_piece = Piece.Empty;
                    game_active = false;
                    drawResult();
                }
                else
                {
                    active_piece = active_piece == Piece.Red ? Piece.Blue : Piece.Red;
                }
                break;
            case Winning:
                drawResult();
                game_active = false;
                break;
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
    public void onMouseMove(MouseEvent mouse_event)
    {
        mouse_x = (int)mouse_event.getX();
        drawGame();
    }
    
    private Piece randomPiece()
    {
        int rand = new Random().nextInt(2);
        return rand == 0 ? Piece.Red : Piece.Blue;
    }
    
    private void test_function() //spiller et spil 4 på stribe
    {
        System.out.println(board.place(Piece.Blue, 3));
        System.out.println(board);
        System.out.println(board.place(Piece.Blue, 4));
        System.out.println(board);
        System.out.println(board.place(Piece.Blue, 4));
        System.out.println(board);
        System.out.println(board.place(Piece.Red, 4));
        System.out.println(board);
        System.out.println(board.place(Piece.Red, 3));
        System.out.println(board);
        System.out.println(board.place(Piece.Red, 2));
        System.out.println(board);

        
    }
}
