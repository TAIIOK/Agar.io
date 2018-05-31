package com.mygdx.game;


import java.util.ArrayList;


public class SpriteGroup {

  String name;
  ArrayList<Sprite> sprites;

  public SpriteGroup(String s) {
    name = s;
    sprites = new ArrayList<Sprite>();
  }

  public void add(Sprite sprite) {
    sprites.add(sprite);
  }

  public Sprite[] getSprites() {
    return sprites.toArray(new Sprite[0]);
  }

  public ArrayList<Sprite> getArrayListSprites() {
    return sprites;
  }

  public void remove(Sprite sprite) {
      sprites.remove(sprite);
  }

  public void update(long elapsed) {
    sprites.stream().forEach((s) -> {
      s.update(elapsed);
    });
  }

  public void render(Graphics2D g) {
    sprites.stream().forEach((s) -> {
      s.render(g);
    });
  }
}
