package com.mygdx.game.gameobject;


import com.mygdx.game.Sprite;

/**
 * Препятствие
 */
public class Obstacle extends DishObject {

  int type ;
  /**
   * Конструктор класса с параметром
   *
   * @param obstacleSprite спрайт Препятствия
   */
  public Obstacle(Sprite obstacleSprite) {

    dishObjectSprite = obstacleSprite;
  }

  public void setType(int i){
    type = i;
  }
}
