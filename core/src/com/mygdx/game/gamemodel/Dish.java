package com.mygdx.game.gamemodel;


import com.mygdx.game.gameobject.AIBacteria;
import com.mygdx.game.gameobject.Agar;
import com.mygdx.game.gameobject.Obstacle;
import com.mygdx.game.gameobject.PlayerBacteria;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import com.mygdx.game.Sprite;
import com.mygdx.game.utils.PositionRandomizer;

/**
 * Чашка Петри
 */
public class Dish {

  public int count_agar = 0;
  /**
   * Бактерия игрока
   */
  private PlayerBacteria playerBacteria;

  /**
   * Препятствия
   */
  private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

  /**
   * Агар
   */
  private ArrayList<Agar> agars = new ArrayList<Agar>();

  /**
   * ИИБактерии
   */
  private ArrayList<AIBacteria> aiBacterias = new ArrayList<AIBacteria>();

  /**
   * Добавляет Препятствие в Чашку Петри
   *
   * @param obstacle Препятствие
   */
  public void addObstacle(Obstacle obstacle) {

    Random rnd = new Random(System.currentTimeMillis());
// Получаем случайное число в диапазоне от min до max (включительно)
    int x = 0 + rnd.nextInt(6 - 0 + 1);
    obstacle.setType(x);
    obstacles.add(obstacle);
  }

  /**
   * Возвращает Препятствия хранимые в Чашке Петри
   *
   * @return Препятствия
   */
  public ArrayList<Obstacle> obstacles() {

    return obstacles;
  }

  /**
   * Добавляет Агар в Чашку Петри
   *
   * @param agar Агар
   */
  public void addAgar(Agar agar) {
    agar.sprite().setId(count_agar);
    count_agar++;
    for(Agar d: agars) {
      if (d.sprite().getX() != agar.sprite().getX() && d.sprite().getY() != agar.sprite().getY()) {
        Point position = PositionRandomizer.getRandomPosition();

        agar.sprite().setX(position.x);
        agar.sprite().setY(position.y);
      }
    }
    agars.add(agar);
  }

  /**
   * Возвращает Агар хранимый в Чашке Петри
   *
   * @return Агар
   */
  public ArrayList<Agar> agar() {

    return agars;
  }

  /**
   * Добавляет Бактерию игрока в Чашку Петри
   */
  public void addPlayerBacteria(PlayerBacteria playerBacteria) {

    this.playerBacteria = playerBacteria;
  }

  /**
   * Возвращает хранимую в Чашке Петри Бактерию игрока
   *
   * @return Бактерия игрока
   */
  public PlayerBacteria playerBacteria() {

    return playerBacteria;
  }

  /**
   * Добавляет ИИБактерию в Чашку Петри
   *
   * @param aiBacteria ИИБактерия
   */
  public void addAIBacteria(AIBacteria aiBacteria) {
    aiBacterias.add(aiBacteria);
  }

  /**
   * Возвращает хранимые в Чашке Петри ИИБактерии
   *
   * @return ИИБактерии
   */
  public ArrayList<AIBacteria> aiBacterias() {
    return aiBacterias;
  }

  /**
   * Возвращает определенную ИИБактерию по её спрайту
   *
   * @param aiBacteriaSprite спрайт ИИБактерии
   * @return ИИБактерия
   */
  public AIBacteria aiBacteria(Sprite aiBacteriaSprite) {

    for (AIBacteria aiBacteria : aiBacterias) {
      if (aiBacteria.sprite().getId() == aiBacteriaSprite.getId()) {
        return aiBacteria;
      }
    }
    return null;
  }

  /**
   * Удаляет определенный Агар из Чашки Петри
   *
   * @param agarSprite спрайт Агара
   */
  public void removeAgar(Sprite agarSprite) {

    for (Agar aiBacterias : agars) {
      if(aiBacterias.sprite().getId() == agarSprite.getId()) {
        agars.remove(aiBacterias);

        break;
      }
    }

    //agars.clear();
    //agars.removeIf(agar -> agar.sprite() == agarSprite);
  }

  /**
   * Удаляет определенную ИИБактерию из Чашки Петри
   *
   * @param aiBacteriaSprite спрайт ИИБактерии
   */
  public void removeAIBacteria(Sprite aiBacteriaSprite) {
    aiBacterias.removeIf(aiBacteria -> aiBacteria.sprite() == aiBacteriaSprite);
  }


}
