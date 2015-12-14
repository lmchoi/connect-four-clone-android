package com.codemonkeys.spike.libgdx.model;

import java.util.Arrays;

public class Board {
    private final int numOfColumns;
    private final int numOfRows;
    public static final int EMPTY = 0;
    public static final int YELLOW = 1;
    public static final int RED = 2;
    private int[][] grid;

    public Board(int numOfColumns, int numOfRows) {
        this.numOfColumns = numOfColumns;
        this.numOfRows = numOfRows;
        grid = new int[numOfColumns][numOfRows];
    }

    public void add(int column, int player) throws InvalidMoveException {
        int row = getNumOfTokensInColumn(column);
        if (isValidMove(column, row))
            grid[column][row] = player;
    }

    public boolean containsToken(int column, int row, int player) {
        return grid[column][row] == player;
    }

    private int getNumOfTokensInColumn(int column) {
        return Arrays.stream(grid[column]).sum();
    }

    private boolean isValidMove(int column, int row) throws InvalidMoveException {
        if (!isColumnAvailable(column)) throw new InvalidMoveException("Invalid column...");
        if (!isRowAvailable(row)) throw new InvalidMoveException("Invalid row...");
        return true;
    }

    private boolean isRowAvailable(int row) {
        return row < numOfRows;
    }

    private boolean isColumnAvailable(int column) {
        return column < numOfColumns;
    }

    public boolean isWinner(int player) {

        return true;
    }
}
