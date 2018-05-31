package com.mygdx.game.gameobject;


import com.mygdx.game.Sprite;

/**
 * Бактерия игрока
 */
public class PlayerBacteria extends Bacteria {

  /**
   * Количество съеденных ИИБактерий
   */
  private int eatenAICount = 0;

  public int status = -1;
  /**
   * Конструктор класса с параметром
   *
   * @param playerBacteriaSprite спртайт Бактерии игрока
   */
  public PlayerBacteria(Sprite playerBacteriaSprite) {
    dishObjectSprite = playerBacteriaSprite;
  }

  /**
   * Возвращает количество съеденных ИИБактерий
   *
   * @return количество съеденных ИИБактерий
   */
  public int getEatenAiCount() {

    return eatenAICount;
  }

  /**
   * Увеличивает количество съеденных ИИБактерий
   */
  public void increaseEatenAICount() {

    this.eatenAICount = eatenAICount++;
  }


}
