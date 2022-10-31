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
    
    
    
    @FXML
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
    /// the size, in pixels, of a single slot on the board
    protected int slot_size = 75;
    /// the size of a piece on the board
    protected int piece_size = 50;
    /// how many pieces that should be made room for, above the board
    protected int top_height = 2;
    /// x position of mouse, relative to canvas
    protected int mouse_x = -1;
    /// how much space is left to the sides of the board, when drawn.
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

        // clear canvas and draw board
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
        // set the piece colour and draw the piece.
        // the piece is slightly offset relative to the slot, so it fits exactly in the middle of said slot.
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
        // displays "RED/BLUE WINS" / "DRAW"
        // followed by a restart button.
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
    /// if the placement is legal, the active player is also swapped
    protected void place(int column)
    {
        PlaceResult place_result = board.place(active_piece, column);

        switch(place_result)
        {
            case Legal:
                if(board.isDraw()) // a legal move can result in a draw, so only check it here
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
    
    private void test_function() //plays a game of connect 4, for testing the board class
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
