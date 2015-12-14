package com.codemonkeys.spike.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class ConnectFourApp extends ApplicationAdapter {
    // TODO-MC make this configurable based on board size
    private static final int APP_WIDTH = 9; // 7 columns with 1 padding on both side
    private static final int APP_HEIGHT = 8; // 6 rows with 1 padding above and below

    private OrthographicCamera camera;
    private SpriteBatch batch;
	private Texture img;
    private Sprite lolSprite;

    // this is only used locally to transform the input position,
    // but to prevent GC, just create it once here
    private final Vector3 touchPosition = new Vector3();

    @Override
	public void create () {
        // Refer to: https://github.com/libgdx/libgdx/wiki/Orthographic-camera
        img = new Texture("badlogic.jpg");
        lolSprite = new Sprite(img);
        lolSprite.setPosition(1, 7);
        lolSprite.setSize(1, 1);

        camera = new OrthographicCamera(APP_WIDTH, APP_HEIGHT);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        batch = new SpriteBatch();
    }

    @Override
	public void render () {
        processInput();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        lolSprite.draw(batch);
        batch.end();
    }

    @Override public void resize (int width, int height) {
        camera.viewportWidth = APP_WIDTH;
        camera.viewportHeight = APP_HEIGHT; // should probably multiply by height/width
        camera.update();
    }

    @Override public void dispose () {
        img.dispose();
        batch.dispose();
    }

    private void processInput() {
        if (Gdx.input.isTouched()) {
            updateTouchPosition();
            lolSprite.setPosition((int)touchPosition.x, (int)touchPosition.y);
        }
    }

    private void updateTouchPosition() {
        int inputX = Gdx.input.getX();
        int inputY = Gdx.input.getY();
        touchPosition.set(inputX, inputY, 0);
        camera.unproject(touchPosition);
    }
}
