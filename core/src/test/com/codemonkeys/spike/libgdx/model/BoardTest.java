package com.codemonkeys.spike.libgdx.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BoardTest {

    private Connect4BitBoard board;

    @Before
    public void setUp() throws Exception {
        // TODO-MC test for Board and Connect4BitBoard, extract common interface
        board = new Connect4BitBoard(7, 6);
    }

    @Test
    public void shouldNotContainTokenWhenNoneIsAdded() throws Exception {
        assertThat(board.containsToken(0, 0, Board.YELLOW), is(false));
    }

    @Test
    public void shouldBeAbleToHoldToken() throws Exception {
        board.add(1, Board.YELLOW);

        assertThat(board.containsToken(0, 0, Board.YELLOW), is(false));
        assertThat(board.containsToken(1, 0, Board.YELLOW), is(true));
    }

    @Test
    public void shouldBeAbleToStackTokens() throws Exception {
        board.add(0, Board.YELLOW);
        board.add(0, Board.YELLOW);
        board.add(0, Board.YELLOW);
        board.add(0, Board.YELLOW);

        assertThat(board.containsToken(0, 0, Board.YELLOW), is(true));
        assertThat(board.containsToken(0, 1, Board.YELLOW), is(true));
        assertThat(board.containsToken(0, 2, Board.YELLOW), is(true));
        assertThat(board.containsToken(0, 3, Board.YELLOW), is(true));
    }

    @Test (expected = InvalidMoveException.class)
    public void shouldBeAbleToCheckIfColumnIsFull() throws Exception {
        board = new Connect4BitBoard(2, 2);
        board.add(0, Board.YELLOW);
        board.add(0, Board.YELLOW);
        board.add(0, Board.YELLOW);
    }

    @Test
    public void shouldBeAbleToCheckForVerticalWin() throws Exception {
        board.add(3, Board.YELLOW);
        board.add(3, Board.YELLOW);
        board.add(3, Board.YELLOW);
        board.add(3, Board.YELLOW);

        boolean actual = board.isWinner(Board.YELLOW);

        assertThat(actual, is(true));
    }

    @Test
    public void shouldBeAbleToCheckForHorizontalWin() throws Exception {
        board.add(1, Board.YELLOW);
        board.add(2, Board.YELLOW);
        board.add(3, Board.YELLOW);
        board.add(4, Board.YELLOW);

        boolean actual = board.isWinner(Board.YELLOW);

        assertThat(actual, is(true));
    }

    @Test
    public void shouldBeAbleToCheckForForwardDiagonalWin() throws Exception {
        board.add(1, Board.RED);
        board.add(1, Board.RED);
        board.add(1, Board.RED);
        board.add(1, Board.YELLOW);

        board.add(2, Board.RED);
        board.add(2, Board.RED);
        board.add(2, Board.YELLOW);

        board.add(3, Board.RED);
        board.add(3, Board.YELLOW);

        board.add(4, Board.YELLOW);

        assertThat(board.isWinner(Board.RED), is(false));
        assertThat(board.isWinner(Board.YELLOW), is(true));
    }

    @Test
    public void shouldBeAbleToCheckForBackwardDiagonalWin() throws Exception {
        board.add(4, Board.RED);
        board.add(4, Board.RED);
        board.add(4, Board.RED);
        board.add(4, Board.YELLOW);

        board.add(3, Board.RED);
        board.add(3, Board.RED);
        board.add(3, Board.YELLOW);

        board.add(2, Board.RED);
        board.add(2, Board.YELLOW);

        board.add(1, Board.YELLOW);

        assertThat(board.isWinner(Board.RED), is(false));
        assertThat(board.isWinner(Board.YELLOW), is(true));
    }
}
