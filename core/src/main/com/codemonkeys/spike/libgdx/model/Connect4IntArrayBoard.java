package com.codemonkeys.spike.libgdx.model;

import java.util.Arrays;

public class Connect4IntArrayBoard extends Board {
    private Token[][] grid;

    public Connect4IntArrayBoard(int numOfColumns, int numOfRows) {
        super(numOfColumns, numOfRows);
        grid = new Token[numOfColumns][numOfRows];
    }

    @Override
    public void add(int column, Token token) throws InvalidMoveException {
        int row = getNumOfTokensInColumn(column);
        if (isValidMove(column, row))
            grid[column][row] = token;
    }

    @Override
    public boolean containsToken(int column, int row, Token token) {
        return grid[column][row] == token;
    }

    @Override
    public boolean isWinner(Token token) {
        // TODO-MC implement win check
        return (token == Token.RED);
    }

    @Override
    public void reset() {
        for( int i = 0; i < grid.length; i++ )
            Arrays.fill(grid[i], null);
    }

    private int getNumOfTokensInColumn(int column) {
        int count = 0;
        for(Token i : grid[column]) {
            if (i != null) count++;
        }

        return count;
    }
}
