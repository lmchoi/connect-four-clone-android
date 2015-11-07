package com.codemonkeys.spike.libgdx.model;

import com.badlogic.gdx.graphics.Texture;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.Exception;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LolFaceTest {
    private float imgWidth = 2;
    private float imgHeight = 2;
    @Mock Texture img;

    @Before
    public void setUp() throws Exception {
        when(img.getWidth()).thenReturn(0);
        when(img.getHeight()).thenReturn(0);
    }

    @Test
    public void shouldNotUpdatePositionWhenFaceIsStill() throws Exception {
        int initialX = 10;
        int initialY = 20;
        LolFace lolFace = new LolFace(initialX, initialY, imgWidth, imgHeight);

        lolFace.update(1);

        assertThat(Float.compare(lolFace.getX(), initialX), is(0));
        assertThat(Float.compare(lolFace.getY(), initialY), is(0));
    }

    @Test
    public void shouldNotUpdateYPositionBelow0() throws Exception {
        int initialX = 10;
        int initialY = 0;
        LolFace lolFace = new LolFace(initialX, initialY, imgWidth, imgHeight);

        lolFace.update(1);

        assertThat(Float.compare(lolFace.getX(), initialX), is(0));
        assertThat(Float.compare(lolFace.getY(), initialY), is(0));
    }

    @Test
    public void shouldNotBeDroppingWhenMissed() throws Exception {
        int initialX = 10;
        int initialY = 20;
        LolFace lolFace = new LolFace(initialX, initialY, imgWidth, imgHeight);
        lolFace.processInput(0, 0);

        lolFace.update(1);

        assertThat(Float.compare(lolFace.getX(), initialX), is(0));
        assertThat(Float.compare(lolFace.getY(), initialY), is(0));
    }

    @Test
    public void shouldStartDroppingWhenHit() throws Exception {
        int initialX = 10;
        int initialY = 20;
        LolFace lolFace = new LolFace(initialX, initialY, imgWidth, imgHeight);
        lolFace.processInput(10, 20);

        lolFace.update(1);

        assertThat(Float.compare(lolFace.getX(), initialX), is(0));
        assertThat(lolFace.getY() < initialY, is(true));
    }

}
