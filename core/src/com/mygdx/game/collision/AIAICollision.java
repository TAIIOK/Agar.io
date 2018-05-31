package com.mygdx.game.collision;

import java.awt.Point;
import java.util.ArrayList;
import com.mygdx.game.listeners.GameObjectEatenListener;
import com.mygdx.game.utils.GameMath;
import com.mygdx.game.BasicCollisionGroup;
import com.mygdx.game.Sprite;

/**
 * Менеджер коллизий Бактерии игрока и ИИБактерии
 */
public class AIAICollision extends BasicCollisionGroup {

  /**
   * Слушатели сигнала GameObjectEaten говорящего о том, что был съеден какой-либо объект игры
   */
  private ArrayList<GameObjectEatenListener> gameObjectEatenListeners = new ArrayList<GameObjectEatenListener>();

  /**
   * Конструктор класса менеджера коллизий
   */
  public AIAICollision() {

    pixelPerfectCollision = true;
  }

  /**
   * Метод выполняется при коллизии Бактерии игрока с ИИБактерией
   *
   * @param sprite спрайт Бактерии игрока
   * @param sprite1 спрайт ИИБактерии
   */
  @Override
  public void collided(Sprite sprite, Sprite sprite1) {

    // Позиции Бактерии игрока и ИИБактерии на поле во время коллизии

    Point playerPosition = new Point((int) sprite.getX(), (int) sprite.getY());
    Point aiPosition = new Point((int) sprite1.getX(), (int) sprite1.getY());

    double distanceToEat = sprite.getWidth() * 0.75;

    // Если расстояние между бактериями позволяет одной Бактерии съесть другую...

    if (GameMath.distance(playerPosition, aiPosition) <= distanceToEat) {

      // ... отправить сигнал о том, что одна Бактерия съела другую

      fireBacteriaEaten(sprite, sprite1);
    }

  }

  /**
   * Добавляет слушателя сигнала GameObjectEaten
   *
   * @param bacteriaEatenListener слушатель сигнала GameObjectEaten
   */
  public void addPlayerEatenListener(GameObjectEatenListener bacteriaEatenListener) {
    gameObjectEatenListeners.add(bacteriaEatenListener);
  }

  /**
   * Сообщает всем слушателям о том, что одна бактерия съела другую
   *
   * @param playerBacteria спрайт Бактерии игрока
   * @param aiBacteria спрайт ИИБактерии
   */
  private void fireBacteriaEaten(Sprite playerBacteria, Sprite aiBacteria) {
    for (GameObjectEatenListener gameObjectEatenListener : gameObjectEatenListeners) {
      gameObjectEatenListener.bacteriaEaten(playerBacteria, aiBacteria);
    }

  }
}
