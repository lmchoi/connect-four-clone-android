package com.codemonkeys.spike.libgdx.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(value = Parameterized.class)
public class BoardTest {

    private Board board;

    public BoardTest(Board board) {
        this.board = board;
    }

    @Parameterized.Parameters
    public static Collection data() {
        Object[][] data = new Object[][] {
            { new Connect4IntArrayBoard(7, 6) },
            { new Connect4BitBoard(7, 6) }
        };

        return Arrays.asList(data);
    }

    @Before
    public void setUp() throws Exception {
        // TODO-MC test for Board and Connect4BitBoard, extract common interface
        board = new Connect4IntArrayBoard(7, 6);
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
        board = new Connect4IntArrayBoard(2, 2);
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
