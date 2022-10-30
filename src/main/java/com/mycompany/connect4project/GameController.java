package com.mycompany.connect4project;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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

    protected Board board;
}
