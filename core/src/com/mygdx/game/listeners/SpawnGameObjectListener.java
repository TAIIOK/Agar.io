package com.mygdx.game.listeners;


import com.mygdx.game.Sprite;
import com.mygdx.game.gameobject.Agar;

/**
 * Слушатель сигнала спавна объектов игры
 */
public interface SpawnGameObjectListener {

  /**
   * Сигнал спавна Агара на игровом поле
   *
   * Отправляет:
   * - GameModel
   *
   * Слушает:
   * - GameView
   */
  void spawnAgar(Agar agarSprite);

  /**
   * Сигнал спавна ИИБактерии на игровом поле
   *
   * Отправляет:
   * - GameModel
   *
   * Слушает:
   * - GameView
   */
  void spawnAI();
}
