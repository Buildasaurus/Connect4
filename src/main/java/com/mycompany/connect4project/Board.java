package com.mycompany.connect4project;

import java.util.Arrays;

public class Board {

    /// represents a single connect 4 piece
    public enum Piece
    {
        Empty,
        Red,
        Blue
    }

    /// represents the result of a place function call
    public enum PlaceResult
    {
        Illegal,
        Legal,
        Winning
    }

    // construct a board of size width x height
    public Board(int width, int height)
    {
        // construct board and fill with empty pieces

        board_pieces = new Piece[width][height];

        for(Piece[] column : board_pieces)
            Arrays.fill(column, Piece.Empty);

        this.height = height;

        // the number of free slots in a column, will be equal to the height of the board.

        free_slots_count = new int[width];
        Arrays.fill(free_slots_count, height);
    }

    public int width() { return board_pieces.length; }
    public int height() { return height; }

    public Piece viewPiece(int column, int row) { return board_pieces[column][row];}

    // places a piece into the specified board column
    // if the piece placement results in a win, Winning will be returned
    // if there is no space for an additional piece, Illegal will be returned
    // if there is space for a piece, Legal will be returned
    public PlaceResult place(Piece piece, int column)
    {
        // check if there are any free slots left
        // if there are, place a piece at the lowest column index, which still is empty,
        // and decrement the free slot count for the passed column
        if(free_slots_count[column] <= 0)
            return PlaceResult.Illegal;

        free_slots_count[column]--;
        board_pieces[column][free_slots_count[column]] = piece;

        // finally, check if the piece resulted in a winning move.

        return checkWin(column) ? PlaceResult.Winning : PlaceResult.Legal;
    }

    // returns a width x height matrix with the following values:
    //  ' ': Empty slot
    //  'O': red slot
    //  'X': blue slot
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        // we add 2 * width line characters, as there should be a line in between each piece.
        str.append("┌─" + "┬─".repeat(width() - 1) + "┐\n");

        for(int i = 0; i < height(); i++)
        {
            str.append("│");
            for(int j = 0; j < width(); j++)
            {
                char c = ' ';

                switch(viewPiece(j, i))
                {
                    case Red:
                        c = 'X';
                        break;
                    case Blue:
                        c = 'O';
                        break;
                }

                str.append(c + "│");
            }

            str.append('\n');

            if(i < height() - 1)
                str.append("├─" + "┼─".repeat(width() - 1) + "┤\n");
        }


        str.append("└─" + "┴─".repeat(width() - 1) + "┘\n");

        return str.toString();
    }

    /// TODO: implement this
    protected boolean checkWin(int column)
    {
        return false;
    }

    protected Piece[][] board_pieces;
    protected int[] free_slots_count;
    protected int height;
}
