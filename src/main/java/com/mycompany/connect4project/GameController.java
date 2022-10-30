package com.mycompany.connect4project;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import com.mycompany.connect4project.Board.Piece;
import com.mycompany.connect4project.Board.PlaceResult;

public class GameController
{

    @FXML
    public void initialize()
    {
        newBoard();
    }

    // ============ EVENTS ============

    // not sure what event this should be
    @FXML
    public void onKeyTyped(KeyEvent keyEvent)
    {
        System.out.println(keyEvent);
    }

    @FXML
    public void onMouseClick(MouseEvent mouseEvent)
    {
        System.out.println(mouseEvent);
    }

    @FXML
    public void onMouseMove(MouseEvent mouseEvent)
    {
        System.out.println(mouseEvent);
    }

    /// restarts the current game and initializes an empty board, with size 7 X 6
    protected void newBoard()
    {
        board = new Board(7, 6);
    }

    protected int coordToColumn(int x, int y)
    {
        /// TODO: implement this
        return -1;
    }


    protected void drawGame()
    {
        /// TODO (PETER): implement this
    }

    protected void drawResult()
    {
        /// TODO: implement this
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
        }

    }

    protected Board board;
    protected Piece active_piece = Piece.Blue; // TODO: should be random?
}
