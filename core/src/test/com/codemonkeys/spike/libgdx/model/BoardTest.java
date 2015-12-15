package com.codemonkeys.spike.libgdx.model;

import org.junit.After;
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

    @After
    public void tearDown() throws Exception {
        board.reset();
    }

    @Test
    public void shouldNotContainTokenWhenNoneIsAdded() throws Exception {
        assertThat(board.containsToken(0, 0, Token.RED), is(false));
    }

    @Test
    public void shouldBeAbleToHoldToken() throws Exception {
        board.add(1, Token.RED);

        assertThat(board.containsToken(0, 0, Token.RED), is(false));
        assertThat(board.containsToken(1, 0, Token.RED), is(true));
    }

    @Test
    public void shouldBeAbleToStackTokens() throws Exception {
        board.add(0, Token.RED);
        board.add(0, Token.RED);
        board.add(0, Token.RED);
        board.add(0, Token.RED);

        assertThat(board.containsToken(0, 0, Token.RED), is(true));
        assertThat(board.containsToken(0, 1, Token.RED), is(true));
        assertThat(board.containsToken(0, 2, Token.RED), is(true));
        assertThat(board.containsToken(0, 3, Token.RED), is(true));
    }

    @Test (expected = InvalidMoveException.class)
    public void shouldBeAbleToCheckIfColumnIsFull() throws Exception {
        board = new Connect4IntArrayBoard(2, 2);
        board.add(0, Token.RED);
        board.add(0, Token.RED);
        board.add(0, Token.RED);
    }

    @Test
    public void shouldBeAbleToCheckForVerticalWin() throws Exception {
        board.add(3, Token.RED);
        board.add(3, Token.RED);
        board.add(3, Token.RED);
        board.add(3, Token.RED);

        boolean actual = board.isWinner(Token.RED);

        assertThat(actual, is(true));
    }

    @Test
    public void shouldBeAbleToCheckForHorizontalWin() throws Exception {
        board.add(1, Token.RED);
        board.add(2, Token.RED);
        board.add(3, Token.RED);
        board.add(4, Token.RED);

        boolean actual = board.isWinner(Token.RED);

        assertThat(actual, is(true));
    }

    @Test
    public void shouldBeAbleToCheckForForwardDiagonalWin() throws Exception {
        board.add(1, Token.RED);
        board.add(1, Token.RED);
        board.add(1, Token.RED);
        board.add(1, Token.RED);

        board.add(2, Token.RED);
        board.add(2, Token.RED);
        board.add(2, Token.RED);

        board.add(3, Token.RED);
        board.add(3, Token.RED);

        board.add(4, Token.RED);

        assertThat(board.isWinner(Token.YELLOW), is(false));
        assertThat(board.isWinner(Token.RED), is(true));
    }

    @Test
    public void shouldBeAbleToCheckForBackwardDiagonalWin() throws Exception {
        board.add(4, Token.RED);
        board.add(4, Token.RED);
        board.add(4, Token.RED);
        board.add(4, Token.RED);

        board.add(3, Token.RED);
        board.add(3, Token.RED);
        board.add(3, Token.RED);

        board.add(2, Token.RED);
        board.add(2, Token.RED);

        board.add(1, Token.RED);

        assertThat(board.isWinner(Token.YELLOW), is(false));
        assertThat(board.isWinner(Token.RED), is(true));
    }
}
