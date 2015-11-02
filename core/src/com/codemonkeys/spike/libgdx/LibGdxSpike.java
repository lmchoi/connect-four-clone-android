package com.codemonkeys.spike.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LibGdxSpike extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

    int currentXpos;
    int currentYpos;

    int speed = 5;

    boolean hasDropped = false;
    boolean isDropping = false;
	
	@Override
	public void create () {
        // set up graphics
        batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

        // set up game entities
        currentXpos = Gdx.graphics.getWidth() / 2 - img.getWidth() / 2;
        currentYpos = Gdx.graphics.getHeight() - img.getHeight();
	}

	@Override
	public void render () {
        // set to white
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, currentXpos, currentYpos);
		batch.end();

        if (!hasDropped && Gdx.input.isTouched()) {
            isDropping = true;
        }

        if (isDropping) {
            if (currentYpos > 0) {
                currentYpos -= speed * Gdx.graphics.getDeltaTime();
            }
        }
	}
}
