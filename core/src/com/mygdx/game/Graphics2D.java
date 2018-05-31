package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Graphics2D {

    SpriteBatch batch;

    public Graphics2D(SpriteBatch spriteBatch) {
        batch = spriteBatch;
    }

    public void begin() {
        batch.begin();
    }

    public void end() {
        batch.end();
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
