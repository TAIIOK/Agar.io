package com.mygdx.game.collision;


import com.mygdx.game.BasicCollisionGroup;
import com.mygdx.game.Sprite;

/**
 * Менеджер коллизий Бактерии игрока и Препятствия
 */
public class PlayerObstacleCollisionDOWN extends BasicCollisionGroup {

  /**
   * Конструктор класса менеджера коллизий
   */
  public PlayerObstacleCollisionDOWN() {

    pixelPerfectCollision = true;

  }

  /**
   * Метод выполняется при коллизии Бактерией игрока с Препятствием
   *
   * @param s1 спрайт Бактерии игрока
   * @param s2 спрайт Препятствия
   */
  @Override
  public void collided(Sprite s1, Sprite s2) {

    // При столкновении Бактерии игрока с Препятствием  - переместить Бактерию игрока на старую позицию,
    // до столкновения с Препятствием

    s1.setX(s1.getOldX());
    if(s1.getY()>s1.getOldY()){
      s1.setY(s1.getOldY());}
    else{
      s1.setY(s1.getOldY()-10);
    }

  }

}