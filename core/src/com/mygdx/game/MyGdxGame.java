package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;




public class MyGdxGame extends ApplicationAdapter {

	static Camera currentCamera;
	public int nextGameID = 0;
	OrthographicCamera camera;
	Graphics2D ctx;
	SpriteBatch batch;

	public GameObject getGame(int game) {
		return null;
	}

	@Override
	public void create() {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
		camera.update();
		currentCamera = camera;

		batch = new SpriteBatch();
		ctx = new Graphics2D(batch);

		this.initResources();
	}

	@Override
	public void render() {
		camera.update();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		update((long) (Gdx.graphics.getDeltaTime() * 1000.0));

		ctx.getBatch().setProjectionMatrix(camera.combined);

		ctx.begin();
		ctxRender(ctx);
		ctx.end();

	}

	@Override
	public void dispose() {
		TextureManager.disposeTextures();
	}

	public void initResources() {
	}

	public void update(long l) {

	}

	public void ctxRender(Graphics2D graphics2D) {

	}

	public int getMouseX() {
		return Gdx.input.getX() - Gdx.graphics.getWidth() / 2;
	}

	/**
	 * Получение координаты Y курсора мыши в окне
	 * @return координата Y курсора в окне
	 */
	public int getMouseY() {
		return (Gdx.graphics.getHeight() / 2 - Gdx.input.getY());
	}
}
