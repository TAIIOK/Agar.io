package com.mygdx.game.utils;


import com.mygdx.game.MyGdxGame;
import com.mygdx.game.GameView;
import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
/**
 * Генератор случайной позиции объектов на игровом поле
 */
public class PositionRandomizer {

  /**
   * Генерирует случайную позицию на поле
   *
   * @return случайная позиция на поле
   */
  public static Point getRandomPosition() {

    Point randomPosition = new Point();

    // Инициализируем генератор
    Random rnd = new Random(System.currentTimeMillis());
// Получаем случайное число в диапазоне от min до max (включительно)
    int x = 1 + rnd.nextInt(1280 - 1 + 1);

    int y = 1 + rnd.nextInt(1024 - 1 + 1);

    randomPosition.x = x;
    randomPosition.y = y;

    // Отодвинуть позицию спавна какого-либо объекта игры от позиции спавна Бактерии игрока

    if (randomPosition.x == GameView.initialPlayerPosition.x) {
      randomPosition.x += 500;
    }
    if (randomPosition.y == GameView.initialPlayerPosition.y) {
      randomPosition.y -= 500;
    }

    return randomPosition;
  }

  /**
   * Генерирует случайную позицию на поле в заданных пределах
   *
   * @param min верхний предел
   * @param max нижний предел
   * @return случайная позиция на поле
   */
  public static Point getRandomPosition(int min, int max) {

    Point randomPosition = new Point();

    randomPosition.x = ThreadLocalRandom.current().nextInt(min, max);
    randomPosition.y = ThreadLocalRandom.current().nextInt(min, max);

    return randomPosition;
  }

}
