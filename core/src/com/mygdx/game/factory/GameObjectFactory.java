package com.mygdx.game.factory;


import com.mygdx.game.gameobject.AIBacteria;
import com.mygdx.game.gameobject.Agar;
import com.mygdx.game.gameobject.DishObject;
import com.mygdx.game.gameobject.GameObjectType;
import com.mygdx.game.gameobject.Obstacle;
import com.mygdx.game.gameobject.PlayerBacteria;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import com.mygdx.game.utils.PositionRandomizer;
import com.mygdx.game.Sprite;

/**
 * Фабрика объектов игры
 */
public class GameObjectFactory {

  /**
   * Путь к изображению бактерии Игрока
   */
  private final static String PLAYER_SPRITE_IMAGE_PATH = "sprites/player/cell.png";

  /**
   * Путь к изображению Препятствия
   */
  private final static String OBSTACLE_SPRITE_IMAGE_PATH = "sprites/obstacle/obstacle";

  /**
   * Количество изображений для Препятствия
   */
  private final static int OBSTACLE_IMAGES_COUNT = 3;

  /**
   * Путь к изображению Агара
   */
  private final static String AGAR_SPRITE_IMAGE_PATH = "sprites/agar/agar.png";

  /**
   * Путь к изображению ИИБактерии
   */
  private final static String AI_SPRITE_IMAGE_PATH = "sprites/enemy/bacteria";

  /**
   * Количество изображений для ИИБактерии
   */
  private final static int AIBACTERIA_IMAGES_COUNT = 4;

  /**
   * Изображение объекта игры
   */
  protected BufferedImage gameObjectImage;

  /**
   * Спрайт объекта игры
   */
  protected Sprite gameObjectSprite;


  /**
   * Создает объект игры
   *
   * @return объект игры
   */
  public DishObject createGameObject(GameObjectType objectToCreate) throws IOException {

    Point position = PositionRandomizer.getRandomPosition();



    switch (objectToCreate) {
      case PLAYER_BACTERIA: {

        // Загрузить изображение Бактерии игрока



        gameObjectImage = ImageIO.read(new File(PLAYER_SPRITE_IMAGE_PATH));

        // Установить загруженное изображение для спрайта Бактерии игрока

        gameObjectSprite = new Sprite(gameObjectImage, position.x, position.y);

        return new PlayerBacteria(gameObjectSprite);

      }

      case AI_BACTERIA_AGRO: {

        // Загрузить случайное изображение ИИБактерии

        int imageIndex = ThreadLocalRandom.current().nextInt(1, AIBACTERIA_IMAGES_COUNT + 1);
        gameObjectImage = ImageIO.read(new File(AI_SPRITE_IMAGE_PATH + 3 + ".png"));

        // Установить загруженное изображение для спрайта ИИБактерии

        gameObjectSprite = new Sprite(gameObjectImage, position.x, position.y);

        return new AIBacteria(gameObjectSprite);
      }

      case AI_BACTERIA_PASSIVE: {

        // Загрузить случайное изображение ИИБактерии

        int imageIndex = ThreadLocalRandom.current().nextInt(1, AIBACTERIA_IMAGES_COUNT + 1);
        gameObjectImage = ImageIO.read(new File(AI_SPRITE_IMAGE_PATH + 2 + ".png"));

        // Установить загруженное изображение для спрайта ИИБактерии

        gameObjectSprite = new Sprite(gameObjectImage, position.x, position.y);

        return new AIBacteria(gameObjectSprite);
      }

      case AGAR: {

        // Загрузить изображение Агара

        gameObjectImage = ImageIO.read(new File(AGAR_SPRITE_IMAGE_PATH));

        // Установить загруженное изображение для спрайта Агара

        gameObjectSprite = new Sprite(gameObjectImage, position.x, position.y);

        return new Agar(gameObjectSprite);

      }

      case OBSTACLE: {

        // Загрузить случайное изображение Препятствия

        gameObjectImage = ImageIO.read(new File(OBSTACLE_SPRITE_IMAGE_PATH + 1 + ".png"));

        gameObjectSprite = new Sprite(gameObjectImage, position.x, position.y);

        Obstacle temp = new Obstacle(gameObjectSprite);
        temp.setType(1);
        return temp;

      }

      case OBSTACLE_UP: {


        gameObjectImage = ImageIO.read(new File(OBSTACLE_SPRITE_IMAGE_PATH + 2 + ".png"));

        gameObjectSprite = new Sprite(gameObjectImage, position.x, position.y);

        Obstacle temp = new Obstacle(gameObjectSprite);
        temp.setType(2);
        return temp;

      }

      case OBSTACLE_DOWN: {


        gameObjectImage = ImageIO.read(new File(OBSTACLE_SPRITE_IMAGE_PATH + 3 + ".png"));

        gameObjectSprite = new Sprite(gameObjectImage, position.x, position.y);

        Obstacle temp = new Obstacle(gameObjectSprite);
        temp.setType(3);
        return temp;

      }

      case OBSTACLE_LEFT: {

        gameObjectImage = ImageIO.read(new File(OBSTACLE_SPRITE_IMAGE_PATH + 4 + ".png"));

        gameObjectSprite = new Sprite(gameObjectImage, position.x, position.y);

        Obstacle temp = new Obstacle(gameObjectSprite);
        temp.setType(4);
        return temp;

      }

      case OBSTACLE_RIGHT: {

        gameObjectImage = ImageIO.read(new File(OBSTACLE_SPRITE_IMAGE_PATH + 5 + ".png"));

        gameObjectSprite = new Sprite(gameObjectImage, position.x, position.y);

        Obstacle temp = new Obstacle(gameObjectSprite);
        temp.setType(5);
        return temp;

      }

      case OBSTACLE_SLOW: {

        gameObjectImage = ImageIO.read(new File(OBSTACLE_SPRITE_IMAGE_PATH + 7 + ".png"));

        gameObjectSprite = new Sprite(gameObjectImage, position.x, position.y);

        Obstacle temp = new Obstacle(gameObjectSprite);
        temp.setType(7);
        return temp;

      }

      case OBSTACLE_FAST: {

        gameObjectImage = ImageIO.read(new File(OBSTACLE_SPRITE_IMAGE_PATH + 6 + ".png"));

        gameObjectSprite = new Sprite(gameObjectImage, position.x, position.y);

        Obstacle temp = new Obstacle(gameObjectSprite);
        temp.setType(6);
        return temp;

      }





    }

    return null;
  }
}
