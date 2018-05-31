package com.mygdx.game.collision;


import java.util.ArrayList;
import com.mygdx.game.listeners.AIObstacleCollisionListener;
import com.mygdx.game.BasicCollisionGroup;
import com.mygdx.game.Sprite;


public class AIObstacleCollision extends BasicCollisionGroup {


  private ArrayList<AIObstacleCollisionListener> aiObstacleCollisionListeners = new ArrayList<AIObstacleCollisionListener>();


  public AIObstacleCollision() {
    pixelPerfectCollision = true;
  }

  @Override
  public void collided(Sprite s1, Sprite s2) {

    if (((s1.getX() == s2.getX() - 10) || (s1.getX() == s2.getX() + 10)) &&
        ((s1.getY() == s2.getY() - 10) || (s1.getY() == s2.getY() + 10))) {

      s1.setX(s2.getX() + 20);
      s2.setY(s2.getY() + 20);

    } else {

      s1.setX(s1.getOldX());
      s1.setY(s1.getOldY());

      fireAIObstacleCollided(s1, s2);
    }
  }

  public void addAiObstacleCollisionListener(
      AIObstacleCollisionListener aiObstacleCollisionListener) {
    aiObstacleCollisionListeners.add(aiObstacleCollisionListener);
  }

  private void fireAIObstacleCollided(Sprite s1, Sprite s2) {
    for (AIObstacleCollisionListener aiObstacleCollisionListener : aiObstacleCollisionListeners) {
      aiObstacleCollisionListener.aiCollidedWithObstacle(s1, s2);
    }
  }
}
