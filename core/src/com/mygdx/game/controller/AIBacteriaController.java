package com.mygdx.game.controller;


import com.mygdx.game.gameobject.AIBacteria;
import com.mygdx.game.gameobject.PlayerBacteria;
import java.awt.Point;
import com.mygdx.game.listeners.AIObstacleCollisionListener;
import com.mygdx.game.utils.GameMath;
import com.mygdx.game.utils.PositionRandomizer;
import com.mygdx.game.Sprite;

/**
 * Контроллер для управления движением ИИБактерии на игровом поле
 */
public class AIBacteriaController extends BacteriaController implements
    AIObstacleCollisionListener {

  /**
   * Максимальное расстояние, при котором ИИБактерия начинает следовать за Бактерией игрока
   * (агрессивно себя вести)
   */
  private final static int AGGRO_DISTANCE = 300;

  /**
   * Максимальное расстояние до конечной точки движения, при достижении которого
   * ИИБактерия меняет свою конечную точку движения
   * Сделано для случаев, когда конечная точка попадает на непроходимое Препятствие
   */
  private final static int DESIRED_POSITION_BOUND = 50;

  /**
   * Выбранная конченая точка движения ИИБактерии
   */
  private Point desiredPosition;

  private Point obstaclePosition;

  private boolean isCollided = false;


  /**
   * Конструктор класса с параметрами
   *
   * @param playerBacteria Бактерия игрока
   * @param aiBacteria ИИБактерия
   */
  public AIBacteriaController(PlayerBacteria playerBacteria, AIBacteria aiBacteria) {
    otherBacteria = playerBacteria;
    bacteria = aiBacteria;

    // Случайно выбранная конечная точка движения

    desiredPosition = PositionRandomizer.getRandomPosition();
  }
  public AIBacteriaController(AIBacteria playerBacteria, AIBacteria aiBacteria) {
    otherBacteria = playerBacteria;
    bacteria = aiBacteria;

    // Случайно выбранная конечная точка движения

    desiredPosition = PositionRandomizer.getRandomPosition();
  }

  /**
   * Обновляет контроллер ИИБактерии
   *
   * @param mousePosition позиция курсора на игровом поле
   * @return успех выполнения движения в определенную точку
   */
  @Override
  public boolean update(Point mousePosition) {

    // Находится ли ИИБактерия на крае игрового поля

    boolean isOnEdge = super.update(mousePosition);

    // Позиции Бактерии игрока и ИИБактерии на игровом поле

    Point playerPos = otherBacteria.getPosition();
    Point aiPos = bacteria.getPosition();

    // Расстояние от ИИБактерии до Бактерии игрока

    double distanceToPlayer = GameMath.distance(aiPos, playerPos);

    // Если Бактерия игрока находится достаточно близко ...

    if (distanceToPlayer < AGGRO_DISTANCE) {

      int angle;

      // ... и уровень Бактерии игрока меньше или равен уровню ИИБактерии ...

      if (otherBacteria.level() <= bacteria.level()) {

        // ... следовать за игроком

        angle = GameMath.angle(aiPos, playerPos);

      } else {

        // ... иначе, уходить от игрока

        angle = GameMath.angle(aiPos, GameMath.getOppositePoint(playerPos, aiPos));

        desiredPosition = PositionRandomizer.getRandomPosition();
      }

      // Следовать к выбранной конечной точке движения
      bacteria.setDirection(angle);
    }

    // .. иначе

    else {

      if (isCollided) {

        desiredPosition = PositionRandomizer.getRandomPosition();
        isCollided = false;

      }

      // Если расстояние до конечной точки движения менее определенной границы ...

      if (GameMath.distance(aiPos, desiredPosition) <= DESIRED_POSITION_BOUND) {

        // ... выбрать новую конечную точку движения

        desiredPosition = PositionRandomizer.getRandomPosition();
      }

      // Если ИИБактерия достигла края игрового поля ...

      if (isOnEdge) {

        // ... выбрать новую конечную точку движения
        desiredPosition = PositionRandomizer.getRandomPosition();
      }

      // Следовать к выбранной конечной точке движения
      bacteria.setDirection(GameMath.angle(aiPos, desiredPosition));
    }

    // Dummy-значение, которое не используется

    return true;
  }

  @Override
  public void aiCollidedWithObstacle(Sprite aiBacteria, Sprite obstacle) {

    if (bacteria.sprite().equals(aiBacteria)) {
      isCollided = true;
      obstaclePosition = new Point((int) obstacle.getX(), (int) obstacle.getY());
    }
  }
}