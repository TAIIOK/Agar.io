package com.mygdx.game;

import java.util.List;

public class BasicCollisionGroup {

  public boolean pixelPerfectCollision = true;

  SpriteGroup group1;

  SpriteGroup group2;

  public void checkCollision() {
    if (group1 != null && group2 != null) {
      List<Sprite> sprites1 = group1.getArrayListSprites();
      List<Sprite> sprites2 = group2.getArrayListSprites();
      for (int i = 0; i < sprites1.size(); i++) {
        for (int j = 0; j < sprites2.size(); j++) {
          Sprite s1 = sprites1.get(i);
          Sprite s2 = sprites2.get(j);
          if (s1 != s2) {
            if (s1.getCollisionShape().collidesWith(s2.getCollisionShape())) {
              this.collided(s1, s2);
            }
          }
        }
      }
    }
  }

  public void collided(Sprite first, Sprite second) {

  }
  public void setCollisionGroup(SpriteGroup spriteGroup1, SpriteGroup spriteGroup2) {
    group1 = spriteGroup1;
    group2 = spriteGroup2;
  }

}
