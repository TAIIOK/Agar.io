package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import java.awt.Point;
import java.awt.image.BufferedImage;
import com.mygdx.game.collision.Ellipse;
import com.mygdx.game.utils.TextureManager;

public class Sprite {

  int id ;

  Texture texture;
  /**
   * Форма для отображения спрайта
   */
  Ellipse shape;
  /**
   * X-координата центра спрайта
   */
  double _x = 0;
  /**
   * Y-координата центра спрайта
   */
  double _y = 0;
  /**
   * "Старая" X-координата спрайта
   */
  double oldX = 0;
  /**
   * "Старая" Y-координата спрайта
   */
  double oldY = 0;

  /**
   * Горизональная скорость спрайта
   */
  double horizontalSpeed = 0;
  /**
   * Вертикальная скорость спрайта
   */
  double verticalSpeed = 0;

  boolean isImmutable = false;

  boolean isActive = true;

  private BufferedImage bufferedImage;

  public Sprite(BufferedImage bi, int x, int y) {
    _x = x;
    oldX = x;
    _y = y;
    oldY = y;
    bufferedImage = bi;

    setImage(bufferedImage);
  }

  public double getX() {
    return _x;
  }

  public void setId(int agarid) {
    id = agarid;
  }

  public int getId() {
    return id ;
  }

  public void setX(double x) {
    _x = x;
    shape.x0 = x + texture.getWidth() / 2;
  }


  public double getY() {
    return _y;
  }


  public void setY(double y) {
    _y = y;
    shape.y0 = _y - texture.getHeight() / 2;
  }


  public double getOldX() {
    return oldX;
  }


  public double getOldY() {
    return oldY;
  }


  public int getWidth() {
    return texture.getWidth();
  }


  public int getHeight() {
    return texture.getHeight();
  }


  public BufferedImage getImage() {
    return bufferedImage;
  }


  public void setImage(BufferedImage image) {

    bufferedImage = image;
    texture = TextureManager.getTexture(image);
    shape = new Ellipse(
        _x + texture.getWidth() / 2,
        _y - texture.getHeight() / 2,
        texture.getWidth() / 2,
        texture.getHeight() / 2
    );
  }


  public double getVerticalSpeed() {
    return verticalSpeed;
  }


  public void setVerticalSpeed(double v) {
    verticalSpeed = v;
  }


  public double getHorizontalSpeed() {
    return horizontalSpeed;
  }


  public void setHorizontalSpeed(double v) {
    horizontalSpeed = v;
  }


  public void setImmutable(boolean b) {
    isImmutable = b;

  }

  public boolean isImmutable() {
    return isImmutable;
  }


  public boolean isActive() {
    return isActive;
  }


  public void setActive(boolean b) {
    isActive = b;
   }

  public Point getCenter() {
    return new Point((int) shape.x0, (int) shape.y0);
  }

  public Ellipse getCollisionShape() {
    return shape;
  }

  /**
   * Обновление состояния спрайта
   * @param elapsed прошедшее время
   */
  public void update(long elapsed) {
    double nx = _x + horizontalSpeed * elapsed;
    double ny = _y + verticalSpeed * elapsed;

    oldX = _x;
    oldY = _y;

    _x = nx;
    _y = ny;

    shape.x0 = _x +  texture.getWidth() / 2;
    shape.y0 = _y -  texture.getHeight() / 2;
  }

  /**
   * Рисует спрайт
   * @param g контекст
   */
  public void render(Graphics2D g) {
    if (texture != null)  {
      g.getBatch().draw(
          texture,
          (float)_x,
          (float)(_y - texture.getHeight())
      );

    }
  }
}
