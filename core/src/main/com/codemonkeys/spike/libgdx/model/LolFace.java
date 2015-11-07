package com.codemonkeys.spike.libgdx.model;

import com.badlogic.gdx.math.Rectangle;

public class LolFace {
    private final static int SPEED = 32;
    private final Rectangle box;

    private boolean isDropping;

    public LolFace(float x, float y, float width, float height) {
        box = new Rectangle();
        box.x = x;
        box.y = y;
        box.width = width;
        box.height = height;

        this.isDropping = false;
    }

    public void update(float timeDelta) {
        // TODO-MC drop speed..
        if (isDropping) {
            box.y -= SPEED * timeDelta;
            box.y = box.y < 0 ? 0 : box.y;
        }
    }

    public float getX() {
        return box.x;
    }

    public float getY() {
        return box.y;
    }

    public void processInput(float x, float y) {
        if (box.contains(x, y)) {
            isDropping = true;
        }
    }
}
