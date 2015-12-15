package com.codemonkeys.spike.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class ConnectFourApp extends ApplicationAdapter {
    // TODO-MC make this configurable based on board size
    private static final int NUM_OF_COLUMNS = 7;
    private static final int NUM_OF_ROWS = 6;

    // TODO make this configurable
    private static final int PADDING = 1;

    private static final int APP_WIDTH = NUM_OF_COLUMNS + 2 * PADDING; // 7 columns with 1 PADDING on both side
    private static final int APP_HEIGHT = NUM_OF_ROWS + 2 * PADDING; // 6 rows with 1 PADDING above and below

    private OrthographicCamera camera;
    private SpriteBatch batch;
	private Texture sprite;
    private Sprite redSprite;
    private Sprite yellowSprite;
    private TextureRegion emptySlotTxRegion;

    // this is only used locally to transform the input position,
    // but to prevent GC, just create it once here
    private final Vector3 touchPosition = new Vector3();
    private ExtendViewport viewport;

    @Override
	public void create () {
        // Refer to: https://github.com/libgdx/libgdx/wiki/Orthographic-camera
        sprite = new Texture("c4_sprite.png");

        redSprite = new Sprite(sprite, 0, 0, 64, 64);
        redSprite.setPosition(1, 7);
        redSprite.setSize(1, 1);

        yellowSprite = new Sprite(sprite, 64, 0, 64, 64);
        yellowSprite.setPosition(7, 7);
        yellowSprite.setSize(1, 1);

        emptySlotTxRegion = new TextureRegion(sprite, 128, 0, 64, 64);

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(APP_WIDTH, APP_HEIGHT, camera);
        viewport.apply();

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

        for (int y = 0; y < NUM_OF_ROWS; y++) {
            for (int x = 0; x < NUM_OF_COLUMNS; x++) {
                batch.draw(emptySlotTxRegion, x + PADDING, y + PADDING, 1, 1);
            }
        }

        redSprite.draw(batch);
        yellowSprite.draw(batch);
        batch.end();
    }

    @Override public void resize (int width, int height) {
        viewport.update(width, height);

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);

    }

    @Override public void dispose () {
        sprite.dispose();
        batch.dispose();
    }

    private void processInput() {
        if (Gdx.input.isTouched()) {
            updateTouchPosition();
            redSprite.setPosition((int) touchPosition.x, (int) touchPosition.y);
        }
    }

    private void updateTouchPosition() {
        int inputX = Gdx.input.getX();
        int inputY = Gdx.input.getY();
        touchPosition.set(inputX, inputY, 0);
        camera.unproject(touchPosition);
    }
}
