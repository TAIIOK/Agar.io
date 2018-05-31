package com.mygdx.game.gameobject;


import com.mygdx.game.Sprite;

/**
 * ИИБактерия
 */
public class AIBacteria extends Bacteria {

  /**
   * Конструктор класса с параметром
   *
   * @param aiBacteriaSprite спрайт ИИБактерии
   */
  public AIBacteria(Sprite aiBacteriaSprite) {
    dishObjectSprite = aiBacteriaSprite;
  }

}
