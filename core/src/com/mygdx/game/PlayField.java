package com.mygdx.game;


import java.util.ArrayList;

public class PlayField {

  ImageBackground bg;

  ArrayList<SpriteGroup> spriteGroups = new ArrayList<SpriteGroup>();

  ArrayList<BasicCollisionGroup> basicCollisionGroups = new ArrayList<BasicCollisionGroup>();


  public PlayField(ImageBackground _bg) {
    bg = _bg;
  }

  public SpriteGroup addGroup(SpriteGroup var1) {
    spriteGroups.add(var1);
    return var1;
  }

  public void addCollisionGroup(SpriteGroup spriteGroup, SpriteGroup spriteGroup1,
      BasicCollisionGroup basicCollisionGroup) {

    basicCollisionGroup.setCollisionGroup(spriteGroup, spriteGroup1);

    basicCollisionGroups.add(basicCollisionGroup);

  }

  public void update(long l) {
    bg.update(l);

    for (SpriteGroup spriteGroup: spriteGroups) {
      spriteGroup.update(l);
    }

    for (BasicCollisionGroup basicCollisionGroup : basicCollisionGroups) {
      basicCollisionGroup.checkCollision();

    }

  }

  public void render(Graphics2D graphics2D) {
    bg.render(graphics2D);

    for (SpriteGroup spriteGroup: spriteGroups) {
      spriteGroup.render(graphics2D);
    }
  }
}
