package com.mygdx.game.gamemodel;

import com.mygdx.game.controller.AIBacteriaController;
import com.mygdx.game.controller.BacteriaController;
import com.mygdx.game.controller.PlayerBacteriaController;
import com.mygdx.game.factory.GameObjectFactory;
import com.mygdx.game.GameView;
import com.mygdx.game.gameobject.*;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import com.mygdx.game.listeners.GameObjectEatenListener;
import com.mygdx.game.listeners.GameOverListener;
import com.mygdx.game.listeners.LevelUpListener;
import com.mygdx.game.listeners.SpawnGameObjectListener;
import com.mygdx.game.Sprite;
import com.mygdx.game.utils.PositionRandomizer;

import com.mygdx.game.Extensions.*;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;


import javax.swing.*;

/**
 * Модель игры (содержит правила игры и её ход)
 */

/**
 * Модель игры (содержит правила игры и её ход)
 */
public class GameModel implements GameObjectEatenListener {

  /**
   * Скорость Бактерии Игрока
   */
  public final static double PLAYER_SPEED = 0.3;
  /**
   * Количество Агара заспавненного за 1 раз
   */
  public final static int AGAR_SPAWN_PORTION = 1;
  /**
   * Количество ИИБактерий заспавненных за 1 раз
   */
  public final static int MAX_AIBACTERIA_SPAWN_PORTION = 2;
  /**
   * Максимальное количество Препятствий
   */
  private final static int MAX_OBSTACLES_COUNT = 7;
  /**
   * Максимальное количество Агара
   */
  private final static int MAX_AGAR_COUNT = 20;
  /**
   * Промежуток в секундах, по истечении которого Агар спавнится на поле
   */
  private final static int AGAR_SPAWN_TIMEOUT = 10;
  /**
   * Максимальное количество ИИБактерий
   */
  private final static int MAX_AIBACTERIA_AGRESSIVE_COUNT = 2;

  private final static int MAX_AIBACTERIA_PASSIVE_COUNT = 2;
  /**
   * Скорость ИИБактерии
   */
  private final static double AI_SPEED = 0.1;

  /**
   * Количество Агара, которое нужно съесть Бактерии для достижения следующего уровня
   */
  private final static int AGAR_EATEN_COUNT_TO_NEXT_LEVEL = 3;

  /**
   * Количество ИИБактерий, которое нужно съесть Бактерии игрока для достижения следующего уровня
   */
  private final static int AIBACTERIA_EATEN_COUNT_TO_NEXT_LEVEL = 3000000;

  /**
   * Количество ИИБактерий, которые нужно съесть, чтобы заспавнились нвоые ИИБактерии
   */
  private final static int EATEN_AIBACTERIA_TO_NEXT_SPAWN = 1;

  /**
   * Слушатели сигнала SpawnGameObject, говорящего о том, что нужно заспавнить определенное
   * количество игровых объектов
   */
  private ArrayList<SpawnGameObjectListener> spawnGameObjectListeners = new ArrayList<>();

  /**
   * Слушатели сигнала LevelUp, говорящего о том, что нужно повысить уровень определенным объектам
   */
  private ArrayList<LevelUpListener> levelUpListeners = new ArrayList<>();

  /**
   * Слушатели сигнала GameOver, говорящего о том, нужно закончить игру
   */
  private ArrayList<GameOverListener> gameOverListeners = new ArrayList<>();

  /**
   * Фабрика объектов игры
   */
  private GameObjectFactory gameObjectFactory = new GameObjectFactory();

  /**
   * Контроллеры Бактерий
   */
  private ArrayList<BacteriaController> aiBacteriaControllers = new ArrayList<>();

  /**
   * Чашка Петри
   */
  private Dish dish;

  private Boolean isBot = false;

  /**
   * Конструктор класса с параметрами
   *
   * @param dish Чашка Петри
   */
  public GameModel(Dish dish) throws IOException {

    this.dish = dish;

    // Создать Бактерию игрока ...

    PlayerBacteria playerBacteria = (PlayerBacteria) gameObjectFactory
            .createGameObject(GameObjectType.PLAYER_BACTERIA);


    String[] arr = new String[1];
    JFileChooser fileopen = new JFileChooser();
    fileopen.setCurrentDirectory(new File("../out/production/classes/com/mygdx/game/Extensions/modules"));
    int ret = fileopen.showDialog(null, "Открыть файл");
    String filePath="";
    if(ret == JFileChooser.APPROVE_OPTION)
    {
      filePath =  fileopen.getSelectedFile().getPath();
      System.out.println(filePath);
      isBot = true;
    }
    arr[0] = filePath;
    ModuleEngine.main(arr, playerBacteria);

    // ... добавить ей контроллер

    aiBacteriaControllers.add(new PlayerBacteriaController(playerBacteria));

    // ... установить скорость

    playerBacteria.setSpeed(PLAYER_SPEED);

    // ... установить начальную позицию

    playerBacteria.setPosition(GameView.initialPlayerPosition);

    // ... добавить в Чашку Петри
    playerBacteria.status = 0;

    dish.addPlayerBacteria(playerBacteria);

    // Создать Агар и добавить его в Чашку Петри

    for (int i = 0; i < MAX_OBSTACLES_COUNT; i = i + 7) {
      dish.addObstacle((Obstacle) gameObjectFactory.createGameObject(GameObjectType.OBSTACLE));
      dish.addObstacle((Obstacle) gameObjectFactory.createGameObject(GameObjectType.OBSTACLE_UP));
      dish.addObstacle((Obstacle) gameObjectFactory.createGameObject(GameObjectType.OBSTACLE_DOWN));
      dish.addObstacle((Obstacle) gameObjectFactory.createGameObject(GameObjectType.OBSTACLE_LEFT));
      dish.addObstacle((Obstacle) gameObjectFactory.createGameObject(GameObjectType.OBSTACLE_RIGHT));
      dish.addObstacle((Obstacle) gameObjectFactory.createGameObject(GameObjectType.OBSTACLE_SLOW));
      dish.addObstacle((Obstacle) gameObjectFactory.createGameObject(GameObjectType.OBSTACLE_FAST));
    }

    // Создать препятствия и добавить их в Чашку Петри

    for (int i = 0; i < MAX_AGAR_COUNT; i++) {
      dish.addAgar(((Agar) gameObjectFactory.createGameObject(GameObjectType.AGAR)));
    }

    // Для ИИБактерии выполнить аналогичную Бактерии Игрока процедуру



      for (int i = 0; i < MAX_AIBACTERIA_PASSIVE_COUNT; i++) {
        AIBacteria aiBacteria = (AIBacteria) gameObjectFactory
                .createGameObject(GameObjectType.AI_BACTERIA_PASSIVE);

        aiBacteria.setSpeed(AI_SPEED);

        aiBacteria.setDirection(ThreadLocalRandom.current().nextInt(0, 360));

        dish.addAIBacteria(aiBacteria);


        aiBacteriaControllers.add(new AIBacteriaController(playerBacteria, aiBacteria));

    }

    for (int i = 0; i < MAX_AIBACTERIA_AGRESSIVE_COUNT; i++) {
      AIBacteria aiBacteria = (AIBacteria) gameObjectFactory
              .createGameObject(GameObjectType.AI_BACTERIA_AGRO);

      aiBacteria.setSpeed(AI_SPEED);

      aiBacteria.setDirection(ThreadLocalRandom.current().nextInt(0, 360));

      dish.addAIBacteria(aiBacteria);


      aiBacteriaControllers.add(new AIBacteriaController(playerBacteria, aiBacteria));


      for (AIBacteria b : dish.aiBacterias()){

        aiBacteriaControllers.add(new AIBacteriaController(b,aiBacteria ));

      }

    }

   // aiBacteriaControllers.add(new AIBacteriaController(dish.aiBacterias().get(dish.aiBacterias().size() - 1), aiBacteria));
    // Отправить сигнал спавна Агара

    fireSpawnAgar();
  }

  public ArrayList<BacteriaController> getAiBacteriaControllers() {
    return aiBacteriaControllers;
  }

  /**
   * Обновляет все контроллеры игры
   *
   * @param mousePosition позиция мыши на поле
   */
  public void update(Point mousePosition) {

    if (isBot == false) {
      for (BacteriaController bacteriaController : aiBacteriaControllers) {
        bacteriaController.update(mousePosition);
      }
    }
    else{
        ModuleEngine._execute.run();
    }
  }

  /**
   * Добавляет слушателя сигнала LevelUp
   *
   * @param levelUpListener слушатель сигнала LevelUp
   */
  public void addLevelUpListener(LevelUpListener levelUpListener) {
    levelUpListeners.add(levelUpListener);
  }

  /**
   * Добавляет слушателя сигнала SpawnGameObject
   *
   * @param spawnGameObjectListener слушатель сигнала SpawnGameObject
   */
  public void addSpawnGameObjectListener(SpawnGameObjectListener spawnGameObjectListener) {
    spawnGameObjectListeners.add(spawnGameObjectListener);
  }

  /**
   * Добавляет слушателя сигнала GameOver
   *
   * @param gameOverListener слушатель сигнала GameOver
   */
  public void addGameOverListener(GameOverListener gameOverListener) {
    gameOverListeners.add(gameOverListener);
  }

  /**
   * Принимает сигнал AgarEaten (Агар съеден Бактерией)
   *
   * @param bacteriaSprite спрайт Бактерии игрока
   * @param agarSprite спрайт Агара
   */
  @Override
  public void agarEaten(Sprite bacteriaSprite, Sprite agarSprite) {

    // Если Бактерия игрока съела Агар ...



    if (dish.playerBacteria().sprite() == bacteriaSprite) {

      // ... увеличить кол-во съеденного Бактерией игрока Агара

      dish.playerBacteria().increaseEatenAgarAmount();

      // ... если Бактерия игрока съела достаточно Агара для перехода на следующий уровень ...

      if (dish.playerBacteria().agarEatenCount() % AGAR_EATEN_COUNT_TO_NEXT_LEVEL == 0) {

        // ... повысить уровень Бактерии игрока и отправить об этом сигнал

        dish.playerBacteria().levelUp();
        fireLevelUp(bacteriaSprite);
      }
    }

    // ... иначе ...

    else {

      AIBacteria aiBacteria = dish.aiBacteria(bacteriaSprite);

      // ... увеличить кол-во съеденного ИИБактерией Агара

      aiBacteria.increaseEatenAgarAmount();

      // ... если ИИБактерия съела достаточно Агара для перехода на следующий уровень ...

      if (aiBacteria.agarEatenCount() % AGAR_EATEN_COUNT_TO_NEXT_LEVEL == 0) {

        // ... повысить уровень Бактерии игрока и отправить об этом сигнал

        aiBacteria.levelUp();
        fireLevelUp(bacteriaSprite);
      }
    }

    // Удалить съеденный Агар из Чашки Петри

    dish.removeAgar(agarSprite);
  }

  /**
   * Принимает сигнал BacteriaEaten (одна Бактерия съела другую Бактерию)
   *
   * @param playerBacteria спрайт Бактерии игрока
   * @param aiBacteria спрайт ИИБактерии
   */
  @Override
  public void bacteriaEaten(Sprite playerBacteria, Sprite aiBacteria) {

    // Если Бактерия игрока съела ИИБактерию (уровень Бактерии игрока больше
    // уровня ИИБактерии) ...


     if(dish.aiBacteria(playerBacteria).level() > dish.aiBacteria(aiBacteria).level() )
    {
      dish.aiBacteria(playerBacteria).increaseEatenAgarAmount();
      dish.aiBacteria(playerBacteria).levelUp();
      dish.removeAIBacteria(aiBacteria);
    }
    else if(dish.aiBacteria(playerBacteria).level() > dish.aiBacteria(aiBacteria).level() )
    {
      dish.aiBacteria(aiBacteria).increaseEatenAgarAmount();
      dish.aiBacteria(aiBacteria).levelUp();
      dish.removeAIBacteria(playerBacteria);
    }
    if (dish.playerBacteria().sprite() == playerBacteria) {
      if (dish.playerBacteria().level() > dish.aiBacteria(aiBacteria).level()) {

        // ... увеличиить кол-во съеденных Бактерией игрока ИИБактерий

        dish.playerBacteria().increaseEatenAICount();

        // ... если Бактерия игрока съела достаточно ИИБактерий для перехода на следующий уровень ...

        if (dish.playerBacteria().getEatenAiCount() % AIBACTERIA_EATEN_COUNT_TO_NEXT_LEVEL == 0) {

          // ... повысить уровень Бактерии игрока и отправить об этом сигнал

          dish.playerBacteria().levelUp();
          fireLevelUp(playerBacteria);
        }

        // ... если Бактерия игрока съела достаточно ИИБактерий для следующего спавна ...
        if (dish.playerBacteria().getEatenAiCount() % EATEN_AIBACTERIA_TO_NEXT_SPAWN == 0) {

          // ... отправить сигнал спавна ИИБактерий

          fireSpawnAI();
        }

        // Удалить съеденную ИИБактерию из Чашки Петри

        dish.removeAIBacteria(aiBacteria);
      }

      // ... иначе ...
      else if (dish.playerBacteria().level() < dish.aiBacteria(aiBacteria).level()) {

        // ... бактерию игрока съели, поэтому пускам сигнал завершения игры
        if (dish.playerBacteria().getSpeed() == 0.3 && dish.playerBacteria().status != -1)
          fireGameOver();
      }
    }
  }

  /**
   * Периодически (каждые 2 секунды) отправляет сигнал спавна Агара
   */
  private void fireSpawnAgar() {

    ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
    exec.scheduleAtFixedRate(new Runnable() {

      int agarSpawnedCount = 0;



      @Override
      public void run() {

          if (dish.agar().size() < MAX_AGAR_COUNT) {


              for (int i = 0; i < AGAR_SPAWN_PORTION && dish.agar().size() < MAX_AGAR_COUNT; i++) {


                Agar temp = dish.agar().get(0);

                temp.sprite().setId(temp.sprite().getId() * 100 );
                dish.addAgar(temp);

                  for (SpawnGameObjectListener l : spawnGameObjectListeners) {
                    l.spawnAgar(temp);
                  }


              }

          }


      }
    },1, AGAR_SPAWN_TIMEOUT, TimeUnit.SECONDS);


  }

  /**
   * Отправляет сигнал спавна ИИБактерий
   */
  private void fireSpawnAI() {
    for (SpawnGameObjectListener l : spawnGameObjectListeners) {
      l.spawnAI();
    }

  }

  /**
   * Отправляет сигнал повышения уровня Бактерии
   *
   * @param bacteriaSprite спрайт Бактерии, дял которой следует повысить уровень
   */
  private void fireLevelUp(Sprite bacteriaSprite) {
    for (LevelUpListener levelUpListener : levelUpListeners) {
      levelUpListener.levelIncreased(bacteriaSprite);
    }
  }

  /**
   * Отправляет сигнал завершения игры
   */
  private void fireGameOver() {
    for (GameOverListener gameOverListener : gameOverListeners) {
      gameOverListener.gameOver();
    }
  }

}



