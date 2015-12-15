package com.codemonkeys.spike.libgdx.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() throws Exception {
        // TODO-MC test for Board and Connect4BitBoard, extract common interface
        board = new Board(7, 6);
    }

    @Test
    public void shouldNotContainTokenWhenNoneIsAdded() throws Exception {
        assertThat(board.containsToken(0, 0, Board.RED), is(false));
    }

    @Test
    public void shouldBeAbleToHoldToken() throws Exception {
        board.add(1, Board.RED);

        assertThat(board.containsToken(0, 0, Board.RED), is(false));
        assertThat(board.containsToken(1, 0, Board.RED), is(true));
    }

    @Test
    public void shouldBeAbleToStackTokens() throws Exception {
        board.add(0, Board.RED);
        board.add(0, Board.RED);
        board.add(0, Board.RED);
        board.add(0, Board.RED);

        assertThat(board.containsToken(0, 0, Board.RED), is(true));
        assertThat(board.containsToken(0, 1, Board.RED), is(true));
        assertThat(board.containsToken(0, 2, Board.RED), is(true));
        assertThat(board.containsToken(0, 3, Board.RED), is(true));
    }

    @Test (expected = InvalidMoveException.class)
    public void shouldBeAbleToCheckIfColumnIsFull() throws Exception {
        board = new Board(2, 2);
        board.add(0, Board.RED);
        board.add(0, Board.RED);
        board.add(0, Board.RED);
    }

    @Test
    public void shouldBeAbleToCheckForVerticalWin() throws Exception {
        board.add(3, Board.RED);
        board.add(3, Board.RED);
        board.add(3, Board.RED);
        board.add(3, Board.RED);

        boolean actual = board.isWinner(Board.RED);

        assertThat(actual, is(true));
    }

    @Test
    public void shouldBeAbleToCheckForHorizontalWin() throws Exception {
        board.add(1, Board.RED);
        board.add(2, Board.RED);
        board.add(3, Board.RED);
        board.add(4, Board.RED);

        boolean actual = board.isWinner(Board.RED);

        assertThat(actual, is(true));
    }

    @Test
    public void shouldBeAbleToCheckForForwardDiagonalWin() throws Exception {
        board.add(1, Board.RED);
        board.add(1, Board.RED);
        board.add(1, Board.RED);
        board.add(1, Board.RED);

        board.add(2, Board.RED);
        board.add(2, Board.RED);
        board.add(2, Board.RED);

        board.add(3, Board.RED);
        board.add(3, Board.RED);

        board.add(4, Board.RED);

        assertThat(board.isWinner(Board.YELLOW), is(false));
        assertThat(board.isWinner(Board.RED), is(true));
    }

    @Test
    public void shouldBeAbleToCheckForBackwardDiagonalWin() throws Exception {
        board.add(4, Board.RED);
        board.add(4, Board.RED);
        board.add(4, Board.RED);
        board.add(4, Board.RED);

        board.add(3, Board.RED);
        board.add(3, Board.RED);
        board.add(3, Board.RED);

        board.add(2, Board.RED);
        board.add(2, Board.RED);

        board.add(1, Board.RED);

        assertThat(board.isWinner(Board.YELLOW), is(false));
        assertThat(board.isWinner(Board.RED), is(true));
    }
}
