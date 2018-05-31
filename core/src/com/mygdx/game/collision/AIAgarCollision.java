package com.mygdx.game.collision;

import java.util.ArrayList;
import com.mygdx.game.listeners.GameObjectEatenListener;
import com.mygdx.game.BasicCollisionGroup;
import com.mygdx.game.Sprite;
/**
 * Менеджер коллизий ИИБактерии с Агаром
 */
public class AIAgarCollision extends BasicCollisionGroup {

  /**
   * Слушатели сигнала GameObjectEaten говорящего о том, что Агар был съеден Вражеской бактерией
   */
  private ArrayList<GameObjectEatenListener> gameObjectEatenListeners = new ArrayList<GameObjectEatenListener>();

  /**
   * Конструктор класса менеджера коллизий
   */
  public AIAgarCollision() {
    pixelPerfectCollision = true;
  }

  /**
   * Метод выполняется при коллизии ИИБактерии с Агаром
   * Передает сигнал о том, что ИИБактерия съела Агар
   *
   * @param sprite спрайт ИИБактерии
   * @param sprite1 спрайт Агара
   */
  @Override
  public void collided(Sprite sprite, Sprite sprite1) {


      // При коллизии ИИБактерии с Агаром - отправить слушателям сигнал о том, что Агар был съеден Вражеской бактерией
      fireAgarEaten(sprite, sprite1);

  }

  /**
   * Добавляет слушателя сигнала GameObjectEaten
   *
   * @param gameObjectEatenListener слушатель сигнала GameObjectEaten
   */
  public void addAgarEatenListener(GameObjectEatenListener gameObjectEatenListener) {
    gameObjectEatenListeners.add(gameObjectEatenListener);
  }

  /**
   * Сообщает всем слушателям о том, что Агар был съеден Вражеской бактерией
   *
   * @param bacteriaSprite спрайт ИИБактерии
   * @param agarSprite спрайт Агара
   */
  private void fireAgarEaten(Sprite bacteriaSprite, Sprite agarSprite) {
    for (GameObjectEatenListener gameObjectEatenListener : gameObjectEatenListeners) {
      gameObjectEatenListener.agarEaten(bacteriaSprite, agarSprite);
    }
  }


}
