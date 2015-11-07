package com.codemonkeys.spike.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.codemonkeys.spike.libgdx.model.LolFace;

public class LibGdxSpike extends ApplicationAdapter {
    private final int targetWidth;
    private final int targetHeight;
    private OrthographicCamera camera;
    private SpriteBatch batch;
	private Texture img;
    private LolFace lolface;

    // this is only used locally to transform the input position,
    // but to prevent GC, just create it once here
    private final Vector3 touchPosition = new Vector3();

    public LibGdxSpike(int targetWidth, int targetHeight) {
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
    }

    @Override
	public void create () {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, targetWidth, targetHeight);

        batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        lolface = spawnLolFace(imgWidth, imgHeight);
    }

    @Override
	public void render () {
        processInput();
        update();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(img, lolface.getX(), lolface.getY());
        batch.end();
    }

    @Override public void dispose () {
        img.dispose();
        batch.dispose();
    }

    private LolFace spawnLolFace(int imgWidth, int imgHeight) {
        int faceX = targetWidth / 2 - imgWidth / 2; // middle of the screen
        int faceY = targetHeight - imgHeight; // top of the screen
        return new LolFace(faceX, faceY, imgWidth, imgHeight);
    }

    private void processInput() {
        if (Gdx.input.isTouched()) {
            updateTouchPosition();
            lolface.processInput(touchPosition.x, touchPosition.y);
        }
    }

    private void updateTouchPosition() {
        int inputX = Gdx.input.getX();
        int inputY = Gdx.input.getY();
        touchPosition.set(inputX, inputY, 0);
        camera.unproject(touchPosition);
    }

    private void update() {
        lolface.update(Gdx.graphics.getDeltaTime());
    }
}
