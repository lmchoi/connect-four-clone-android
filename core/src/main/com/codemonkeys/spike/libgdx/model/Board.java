package com.codemonkeys.spike.libgdx.model;

public abstract class Board {
    protected final int numOfColumns;
    protected final int numOfRows;

    public Board(int numOfColumns, int numOfRows) {
        this.numOfColumns = numOfColumns;
        this.numOfRows = numOfRows;
    }

    abstract void add(int column, Token token) throws InvalidMoveException;

    abstract boolean containsToken(int column, int row, Token token);

    abstract boolean isWinner(Token token);

    protected boolean isValidMove(int column, int row) throws InvalidMoveException {
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

    public abstract void reset();
}
