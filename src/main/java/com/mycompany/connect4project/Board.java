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

    /// construct a board of size width x height (in pieces, not pixels)
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

    /// places a piece into the specified board column
    /// if the piece placement results in a win, Winning will be returned
    /// if there is no space for an additional piece, Illegal will be returned
    /// if there is space for a piece, Legal will be returned
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

    /// checks if the game is currently a draw
    /// does this by checking if any pieces are left, and assumes the game was not won.
    protected boolean isDraw()
    {
        for(int slots : free_slots_count)
            if(slots != 0)
                return false;

        return true;
    }

    /// checks if the board has a winning row (row of same coloured pieces, with a count of at least 4)
    /// the column where the new piece has been placed should be passed, so the method only checks necessary pieces.
    protected boolean checkWin(int column)
    {
        //assuming that free_slots_count has been updated before checkwin is called.
        
        //start by checking if there are 4 on top of each other
        int yplacement = free_slots_count[column];
 
        int[][][] directions = {
            {{1,0}, {-1,0}},
            {{0,1}},
            {{1,1}, {-1,-1}},
            {{-1,1}, {1, -1}},
        };

        for(int i = 0; i < directions.length; i++)
        {
            int count = 0;
            for(int j = 0; j < directions[i].length; j++)
            {
                count += checkDirection(directions[i][j][0], 
                        directions[i][j][1], column, yplacement);

                if (count >= 3)
                    return true;

            }
        }
        return false;
    }


    private int checkDirection(int x_off, int y_off, int column, int yplacement) //x and y should be either 0, -1 or 1
    {
        int x = column + x_off;
        int y = yplacement + y_off;

        int count = 0;
        //Start by checking if you can go the direction
        if(x >= width()  || x < 0 ||  y >= height() || y < 0)
        {
            return count;
        }
        while(count<4)
        {
            if(board_pieces[column][yplacement] == board_pieces[x][y])
            {
                count++;

                x += x_off;
                y += y_off;

                if(x >= width() || x < 0 ||  y >= height() || y < 0)
                {
                    return count;
                }
            }
            else
            {
                return count;
            }
        }
        return -1;
    }

    protected Piece[][] board_pieces;
    protected int[] free_slots_count;
    protected int height;
}
