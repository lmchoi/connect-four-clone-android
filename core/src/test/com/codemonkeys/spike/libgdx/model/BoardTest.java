package com.codemonkeys.spike.libgdx.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board(2, 2);

    }

    @Test
    public void shouldBeAbleToHoldToken() throws Exception {
        board.add(0, Board.YELLOW);

        assertThat(board.containsToken(0, 0), is(true));
    }

    @Test
    public void shouldBeAbleToStackTokens() throws Exception {
        board.add(0, Board.YELLOW);
        board.add(0, Board.YELLOW);

        assertThat(board.containsToken(0, 0), is(true));
        assertThat(board.containsToken(0, 1), is(true));
    }

    @Test (expected = InvalidMoveException.class)
    public void shouldBeAbleToCheckIfColumnIsFull() throws Exception {
        board.add(0, Board.YELLOW);
        board.add(0, Board.YELLOW);
        board.add(0, Board.YELLOW);
    }


    @Test
    public void shouldBeAbleToCheckIfPlayerIsWinner() throws Exception {
        board.add(0, Board.YELLOW);
        board.add(0, Board.YELLOW);

        boolean actual = board.isWinner(Board.YELLOW);

        assertThat(actual, is(true));
    }
}
