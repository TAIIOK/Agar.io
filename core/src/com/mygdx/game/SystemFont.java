package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import java.awt.Color;

public class SystemFont {

  BitmapFont bmpFont;

  public SystemFont(String fontName, int attrs, int size, Color clr) {
    bmpFont = new BitmapFont();
    bmpFont.setColor(new com.badlogic.gdx.graphics.Color(
        clr.getRed() / 255.0f,
        clr.getGreen() / 255.0f,
        clr.getBlue() / 255.0f,
        clr.getAlpha() / 255.0f
    ));
  }

  public void drawString(Graphics2D g, String data, int x, int y) {
    float px = x + (MyGdxGame.currentCamera.position.x - Gdx.graphics.getWidth() / 2);
    float py = (Gdx.graphics.getHeight() - y)
        + (MyGdxGame.currentCamera.position.y - Gdx.graphics.getHeight() / 2);
    bmpFont.draw(g.getBatch(), data, px, py);
  }
}
