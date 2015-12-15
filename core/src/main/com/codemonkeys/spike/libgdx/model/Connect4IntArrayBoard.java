package com.codemonkeys.spike.libgdx.model;

public class Connect4IntArrayBoard extends Board {
    private int[][] grid;

    public Connect4IntArrayBoard(int numOfColumns, int numOfRows) {
        super(numOfColumns, numOfRows);
        grid = new int[numOfColumns][numOfRows];
    }

    @Override
    public void add(int column, int player) throws InvalidMoveException {
        int row = getNumOfTokensInColumn(column);
        if (isValidMove(column, row))
            grid[column][row] = player;
    }

    @Override
    public boolean containsToken(int column, int row, int player) {
        return grid[column][row] == player;
    }

    @Override
    public boolean isWinner(int player) {
        // TODO-MC implement win check
        return (player == RED);
    }

    private int getNumOfTokensInColumn(int column) {
        int count = 0;
        for(int i : grid[column]) {
            if (i != EMPTY) count++;
        }

        return count;
    }
}
