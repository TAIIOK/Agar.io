package com.mygdx.game.gameobject;


import com.mygdx.game.Sprite;

/**
 * Объект игры
 */
public abstract class DishObject {

  /**
   * Спрайт объекта игры
   */

  protected Sprite dishObjectSprite;

  /***
   * Возвращает спрайт объекта игры
   *
   * @return спрайт объекта игры
   */
  public Sprite sprite() {
    return dishObjectSprite;
  }
}
