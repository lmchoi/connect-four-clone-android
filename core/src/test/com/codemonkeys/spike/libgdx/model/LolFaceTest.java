package com.codemonkeys.spike.libgdx.model;

import org.junit.Test;

import java.lang.Exception;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LolFaceTest {
    @Test
    public void shouldNotUpdatePositionWhenFaceIsStil() throws Exception {
        int initialX = 10;
        int initialY = 20;
        LolFace lolFace = new LolFace(initialX, initialY);
        lolFace.update();

        assertThat(lolFace.getX(), is(initialX));
        assertThat(lolFace.getY(), is(initialY));
    }
}