package com.codemonkeys.spike.libgdx.model;

import java.util.Arrays;

public class Connect4BitBoard extends Board {
    private final int columnOffset;
    private final int backwardDiagonalOffset;
    private final byte height[]; // holds bit index of lowest free square
    private final long[] board;

    public Connect4BitBoard(int numOfColumns, int numOfRows) {
        super(numOfColumns, numOfRows);
        this.height = new byte[numOfColumns];

        this.columnOffset = numOfRows + 1;
        this.backwardDiagonalOffset = numOfRows + 2;

        this.board = new long[2];
    }

    @Override
    public void add(int column, Token token) throws InvalidMoveException {
        isValidMove(column, height[column]);

        board[getBoardId(token)] ^= (1L << height[column]++) << (column * columnOffset);
    }

    @Override
    public boolean containsToken(int column, int row, Token token) {
        return (((1L << (column * columnOffset)) + row) & board[getBoardId(token)]) != 0;
    }

    @Override
    public boolean isWinner(Token token) {
        long board = this.board[getBoardId(token)];
        if (forwardDiagonalWinCheck(board)) return true;
        else if (horizontalWinCheck(board)) return true;
        else if (backwardDiagonalWinCheck(board)) return true;
        return verticalWinCheck(board);
    }

    @Override
    public void reset() {
        Arrays.fill(height, (byte) 0);
        Arrays.fill(board, 0);
    }

    private int getBoardId(Token token) {
        return token.ordinal();
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
