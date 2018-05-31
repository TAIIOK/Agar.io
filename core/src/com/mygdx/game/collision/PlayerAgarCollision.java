package com.mygdx.game.collision;

import java.util.ArrayList;
import com.mygdx.game.listeners.GameObjectEatenListener;
import com.mygdx.game.BasicCollisionGroup;
import com.mygdx.game.Sprite;

/**
 * Менеджер коллизий Бактерии игрока с Агаром
 */
public class PlayerAgarCollision extends BasicCollisionGroup {

  /**
   * Слушатели сигнала GameObjectEaten говорящего о том, что был съеден какой-либо объект игры
   */
  private ArrayList<GameObjectEatenListener> gameObjectEatenListeners = new ArrayList<GameObjectEatenListener>();

  /**
   * Конструктор класса менеджера коллизий
   */
  public PlayerAgarCollision() {
    pixelPerfectCollision = true;
  }

  /**
   * Метод выполняется при коллизии Бактерии игрока с Агаром
   * Передает сообщение о том, что Бактерия игрока съела Агар
   *
   * @param sprite  спрайт Бактерии игрока
   * @param sprite1 спрайт Агара
   */
  @Override
  public void collided(Sprite sprite, Sprite sprite1) {

    fireAgarEaten(sprite, sprite1);

  }

  /**
   * Добавляет слушателя сигнала GameObjectEaten
   *
   * @param agarEatenListener слушатель сигнала GameObjectEaten
   */
  public void addAgarEatenListener(GameObjectEatenListener agarEatenListener) {
    gameObjectEatenListeners.add(agarEatenListener);
  }

  /**
   * Сообщает всем слушателям о том, что Агар был съеден Игроком
   *
   * @param bacteriaSprite спрайт Бактерии игрока
   * @param agarSprite     спрайт Агара
   */
  private void fireAgarEaten(Sprite bacteriaSprite, Sprite agarSprite) {
    for (GameObjectEatenListener gameObjectEatenListener : gameObjectEatenListeners) {
      gameObjectEatenListener.agarEaten(bacteriaSprite, agarSprite);
    }
  }

}
