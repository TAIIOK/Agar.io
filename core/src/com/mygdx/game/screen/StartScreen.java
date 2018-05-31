package com.mygdx.game.screen;

import java.awt.event.KeyEvent;
import com.mygdx.game.GameObject;
import com.mygdx.game.ImageBackground;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.GameView;

/**
 * Стартовый экран игры
 */
public class StartScreen extends GameObject {

  /**
   * Путь к фоновому изображению стартового экрана игры
   */
  private final static String SCREEN_BG = "agario/assets/screens/start_screen.png";

  /**
   * Фон стартового экрана игры
   */
  private ImageBackground bg;

  /**
   * Конструктор класса с параметром
   *
   * @param gameEngine игровой движок
   */
  public StartScreen(MyGdxGame gameEngine) {
    super(gameEngine);
    bg = new ImageBackground(getImage(SCREEN_BG));
  }

  /**
   * Инициализирует ресурсы
   */
  @Override
  public void initResources() {

    // Убрать курсор

    hideCursor();
  }

  /**
   * Обновляет ресурсы
   *
   * @param elapsedTime время, прошедшее с момента последнего обновления
   */
  @Override
  public void update(long elapsedTime) {
    bg.update(elapsedTime);

    // Если нажат Enter ...

    if (keyPressed(KeyEvent.VK_ENTER)) {

      // ... переключится на экран с игрой

      parent.nextGameID = 1;

      // ... закрыть текущий экран
      finish();
    }

  }

  /**
   * Рендерит ресурсы
   *
   * @param graphics2D графический контекст
   */

    public void ctxRender(com.mygdx.game.Graphics2D graphics2D) {
      bg.render(graphics2D);
    }
}
