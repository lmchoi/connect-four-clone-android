package com.codemonkeys.spike.libgdx.model;

public class Connect4BitBoard extends Board {
    private final int columnOffset;
    private final int backwardDiagonalOffset;
    private final byte height[]; // holds bit index of lowest free square
    private long[] board;

    public Connect4BitBoard(int numOfColumns, int numOfRows) {
        super(numOfColumns, numOfRows);
        this.height = new byte[numOfColumns];

        this.columnOffset = numOfRows + 1;
        this.backwardDiagonalOffset = numOfRows + 2;

        board = new long[2];
    }

    @Override
    public void add(int column, int player) throws InvalidMoveException {
        isValidMove(column, height[column]);

        board[player - 1] = getBoard(player) ^ (1L << height[column]++) << (column * columnOffset);
    }

    @Override
    public boolean containsToken(int column, int row, int player) {
        return (((1L << (column * columnOffset)) + row) & getBoard(player)) != 0;
    }

    @Override
    public boolean isWinner(int player) {
        if (forwardDiagonalWinCheck(getBoard(player))) return true;
        else if (horizontalWinCheck(getBoard(player))) return true;
        else if (backwardDiagonalWinCheck(getBoard(player))) return true;
        return verticalWinCheck(getBoard(player));
    }

    private long getBoard(int player) {
        return board[player - 1];
    }

    private boolean forwardDiagonalWinCheck(long board) {
        long temp = board & (board >> numOfRows);
        return (temp & (temp >> 2 * numOfRows)) != 0; // check diagonal \
    }

    private boolean backwardDiagonalWinCheck(long board) {
        long temp = board & (board >> backwardDiagonalOffset);
        return (temp & (temp >> 2 * backwardDiagonalOffset)) != 0; // check diagonal /
    }

    private boolean horizontalWinCheck(long board) {
        long tempBoard = board & (board >> columnOffset);
        return (tempBoard & (tempBoard >> 2 * columnOffset)) != 0;
    }

    private boolean verticalWinCheck(long board) {
        long tempBoard = board & (board >> 1);
        return (tempBoard & (tempBoard >> 2)) != 0;
    }
}
