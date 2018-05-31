package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.GameView;
import com.mygdx.game.MyGdxGame;


import java.awt.Dimension;

public class DesktopLauncher {

	final static int FPS = 60;

	MyGdxGame gameEngine = new GameView();


	LwjglApplicationConfiguration appConfig;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 1280;
		config.height = 1024;
		config.foregroundFPS = FPS;
		config.resizable = true;
		config.title = "Agar";


		new LwjglApplication(new GameView(), config);
	}
}

/*

    GameLoader gameLoader = new GameLoader();
    gameLoader.setup(new GameView(), GameView.GAME_WINDOW, false);
    gameLoader.start();

 */




