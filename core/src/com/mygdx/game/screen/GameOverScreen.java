package com.mygdx.game.screen;


import java.awt.event.KeyEvent;
import com.mygdx.game.GameObject;
import com.mygdx.game.ImageBackground;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.GameView;

/**
 * Экран окончания игры
 */
public class GameOverScreen extends GameObject {


  /**
   * Путь к фоновому изображению экрана окончания игры
   */
  private final static String SCREEN_BG = "screens/gameover_screen.png";

  /**
   * Фон экрана окончания игры
   */
  private ImageBackground bg;


  /**
   * Конструктор класса с параметром
   *
   * @param gameEngine игровой движок
   */
  public GameOverScreen(MyGdxGame gameEngine) {
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
