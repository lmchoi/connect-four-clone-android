package com.codemonkeys.spike.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class LibGdxSpike extends ApplicationAdapter {
    // target resolution, confirm if these values should match the application config? (see DesktopLauncher)
    public final static float TARGET_WIDTH = 640;
    public final static float TARGET_HEIGHT = 480;

    private OrthographicCamera camera;
    private SpriteBatch batch;
	private Texture img;

    private int speed = 32;

    private Rectangle lolface;
    private boolean hasDropped = false;
    private boolean isDropping = false;

	@Override
	public void create () {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, TARGET_WIDTH, TARGET_HEIGHT);

        // set up graphics
        batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();

        // set up the lol face
        lolface = new Rectangle();
        lolface.x = TARGET_WIDTH / 2 - imgWidth / 2;
        lolface.y = TARGET_HEIGHT - imgHeight;
        lolface.setWidth(imgWidth);
        lolface.setHeight(imgHeight);
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, lolface.x, lolface.y);
		batch.end();

        if (!hasDropped && Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            int inputX = Gdx.input.getX();
            int inputY = Gdx.input.getY();
            touchPos.set(inputX, inputY, 0);
            camera.unproject(touchPos);

            // confusingly enough, the y-axis points upwards for some things but not others..
            // (it's configurable, but I'm lazy)
            if (lolface.contains(inputX, TARGET_HEIGHT - inputY)) {
                isDropping = true;
            }
        }

        if (isDropping) {
            if (lolface.y > 0) {
                lolface.y -= speed * Gdx.graphics.getDeltaTime();
            }
        }
	}

    @Override public void dispose () {
        img.dispose();
        batch.dispose();
    }
}
