package com.mygdx.game;


        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Input;
        import com.mygdx.game.collision.*;
        import com.mygdx.game.controller.BacteriaController;
        import com.mygdx.game.gamemodel.Dish;
        import com.mygdx.game.gamemodel.GameModel;
        import com.mygdx.game.gameobject.AIBacteria;
        import com.mygdx.game.gameobject.Agar;
        import com.mygdx.game.gameobject.Obstacle;
        import java.awt.Color;
        import java.awt.Dimension;
        import java.awt.Font;
        import java.awt.Point;
        import java.awt.event.KeyEvent;
        import java.awt.image.BufferedImage;
        import java.io.File;
        import java.io.IOException;
        import java.util.Timer;
        import java.util.TimerTask;
        import javax.imageio.ImageIO;
        import com.mygdx.game.listeners.GameObjectEatenListener;
        import com.mygdx.game.listeners.GameOverListener;
        import com.mygdx.game.listeners.LevelUpListener;
        import com.mygdx.game.listeners.SpawnGameObjectListener;
        import com.mygdx.game.utils.ImageScaler;


/**
 * Представление игры (отображение игры и её результатов)
 */
public class GameView extends MyGdxGame implements SpawnGameObjectListener,
        GameObjectEatenListener, LevelUpListener, GameOverListener {

    /**
     * Размер игрового поля
     */
    public static final Dimension GAME_FIELD_SIZE = new Dimension(2000, 2000);

    /**
     * Размер окна с игрой
     */
    public static final Dimension GAME_WINDOW = new Dimension(1280, 1024);

    /**
     * Начальная позиция игрока
     */
    public static final Point initialPlayerPosition = new Point((int) (GAME_WINDOW.getWidth() / 2),
            (int) (GAME_WINDOW.getHeight() / 2));

    /**
     * Путь к изображению фона игрового поля
     */
    private final static String DISH_BACKGROUND_IMAGE_PATH = "bg/bg.png";


    /**
     * Коэффициент масштабирования изображения по X при повышении уровня
     */
    private final static int SCALE_IMAGE_X = 30;

    /**
     * Коэффициент масштабирования изображения по Y при повышении уровня
     */
    private final static int SCALE_IMAGE_Y = 30;

    /**
     * Изображения игрового поля
     */
    private ImageBackground bg;

    /**
     * Игровое поле
     */
    private PlayField pf;

    /**
     * Модель игры
     */
    private GameModel gm;

    /**
     * Чашка Петри
     */
    private Dish dish;

    /**
     * Группы спрайтов:  Агара, ИИБактерий
     */
    private SpriteGroup agarGroup;

    private SpriteGroup aiBacteriaGroup;

    /**
     * Шрифт для надписей на игровом поле
     */
    private SystemFont gameFont;

    private int id = 0;
    /**
     * Конструктор класса с параметром
     *
     * @param gameEngine игровой движок
     */
 /* public GameView() {
        if(this.id == 0){

        }
  }*/

    /**
     * Инициализирует ресурсы
     */
    @Override
    public void initResources() {

        SpriteGroup playerGroup;

        SpriteGroup obstacleGroup,obstacleGroup1,obstacleGroup2,obstacleGroup3,obstacleGroup4,obstacleGroup5,obstacleGroup6;

        PlayerAgarCollision playerAgarCollision;

        AIAgarCollision aiAgarCollision;


        PlayerAICollision playerAICollision;


        AIObstacleCollision aiObstacleCollision;

        try {

            // Создать Чашку Петри

            dish = new Dish();

            // Создать Модель игры

            gm = new GameModel(this.dish);

            // Добавить слушателей для сигналов посылаемых Моделью игры

            gm.addSpawnGameObjectListener(this);
            gm.addLevelUpListener(this);
            gm.addGameOverListener(this);

            // Создать фон для поля игры

            this.bg = new ImageBackground(ImageIO.read(new File(DISH_BACKGROUND_IMAGE_PATH)));
            this.bg.setClip(0, 0, (int) GAME_WINDOW.getWidth(), (int) GAME_WINDOW.getHeight());
            this.bg.setTotalClip(GAME_FIELD_SIZE.width, GAME_FIELD_SIZE.height);

            // Создать поле игры

            pf = new PlayField(bg);

            // Добавить на игровое поле группы спрайтов

            obstacleGroup = pf.addGroup(new SpriteGroup("Obstacle WALL Group"));
            obstacleGroup1 = pf.addGroup(new SpriteGroup("Obstacle UP Group"));
            obstacleGroup2 = pf.addGroup(new SpriteGroup("Obstacle DOWN Group"));
            obstacleGroup3 = pf.addGroup(new SpriteGroup("Obstacle LEFT Group"));
            obstacleGroup4 = pf.addGroup(new SpriteGroup("Obstacle RIGHT Group"));
            obstacleGroup5 = pf.addGroup(new SpriteGroup("Obstacle SLOW Group"));
            obstacleGroup6 = pf.addGroup(new SpriteGroup("Obstacle FAST Group"));

            agarGroup = pf.addGroup(new SpriteGroup("Agar Group"));
            aiBacteriaGroup = pf.addGroup(new SpriteGroup("AI Bacteria Group"));
            playerGroup = pf.addGroup(new SpriteGroup("Player Group"));
            playerGroup.add(dish.playerBacteria().sprite());

            // Заполнить группы спрайтами


            obstacleGroup.add(dish.obstacles().get(0).sprite());
            obstacleGroup1.add(dish.obstacles().get(1).sprite());
            obstacleGroup2.add(dish.obstacles().get(2).sprite());
            obstacleGroup3.add(dish.obstacles().get(3).sprite());
            obstacleGroup4.add(dish.obstacles().get(4).sprite());
            obstacleGroup5.add(dish.obstacles().get(5).sprite());
            obstacleGroup6.add(dish.obstacles().get(6).sprite());

            for (Agar agar : dish.agar()) {
                agar.sprite().setImmutable(true);
                agar.sprite().setActive(false);
                agarGroup.add(agar.sprite());
            }
            for (AIBacteria aiBacteria : dish.aiBacterias()) {
                aiBacteria.sprite().setImmutable(true);
                aiBacteria.sprite().setActive(false);
                aiBacteriaGroup.add(aiBacteria.sprite());
            }

            aiObstacleCollision = new AIObstacleCollision();

            for (BacteriaController aiBacteriaController : gm.getAiBacteriaControllers()) {
                aiObstacleCollision.addAiObstacleCollisionListener(aiBacteriaController);
            }

            pf.addCollisionGroup(aiBacteriaGroup, obstacleGroup, aiObstacleCollision);

            // Добавить на игровое поле коллизию Бактерия игрока - Препятствие

            pf.addCollisionGroup(playerGroup, obstacleGroup, new PlayerObstacleCollision());

            pf.addCollisionGroup(playerGroup, obstacleGroup1, new PlayerObstacleCollisionUP());
            pf.addCollisionGroup(playerGroup, obstacleGroup2, new PlayerObstacleCollisionDOWN());
            pf.addCollisionGroup(playerGroup, obstacleGroup3, new PlayerObstacleCollisionLEFT());
            pf.addCollisionGroup(playerGroup, obstacleGroup4, new PlayerObstacleCollisionRIGHT());
            pf.addCollisionGroup(playerGroup, obstacleGroup5, new PlayerObstacleCollisionSLOW());
            pf.addCollisionGroup(playerGroup, obstacleGroup6, new PlayerObstacleCollisionFAST());

            // Добавить на игровое поле коллизию Бактерия игрока - ИИБактерия

            playerAICollision = new PlayerAICollision();

            // Добавить слушателей для сигналов посылаемых коллизией Бактерия игрока - ИИБактерия

            playerAICollision.addPlayerEatenListener(this);
            playerAICollision.addPlayerEatenListener(gm);

            pf.addCollisionGroup(playerGroup, aiBacteriaGroup, playerAICollision);

            // Добавить на игровое поле коллизию Бактерия игрока - Агар

            playerAgarCollision = new PlayerAgarCollision();

            // Добавить слушателей для сигналов посылаемых коллизией Бактерия игрока - Агар

            playerAgarCollision.addAgarEatenListener(this);
            playerAgarCollision.addAgarEatenListener(gm);

            pf.addCollisionGroup(playerGroup, agarGroup, playerAgarCollision);

            // Добавить на игровое поле коллизию ИИБактерия - Агар

            aiAgarCollision = new AIAgarCollision();

            // Добавить слушателей для сигналов посылаемых коллизией ИИБактерия - Агар

            aiAgarCollision.addAgarEatenListener(this);
            aiAgarCollision.addAgarEatenListener(gm);

            pf.addCollisionGroup(aiBacteriaGroup, agarGroup, aiAgarCollision);


            // Добавить на игровое поле коллизию ИИБактерия - ИИБактерия

            AIAICollision col = new AIAICollision();

            // Добавить слушателей для сигналов посылаемых коллизией Бактерия игрока - ИИБактерия

            col.addPlayerEatenListener(this);
            col.addPlayerEatenListener(gm);

            pf.addCollisionGroup(aiBacteriaGroup, aiBacteriaGroup, col);

            // Добавить шрифт для надписей на игровом поле

            gameFont = new SystemFont("Roboto Mono", Font.BOLD, 20, Color.BLACK);

            // Заспавнить ИИБактерии

            spawnAI();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void update(long elapsedTime) {
        pf.update(elapsedTime);
        gm.update(mousePosition());

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            initResources();
        }


    }

    @Override
    public void ctxRender(com.mygdx.game.Graphics2D g) {
        pf.render(g);

        bg.setToCenter(dish.playerBacteria().sprite());

        gameFont.drawString(g, "AGAR COUNT: " + dish.playerBacteria().agarEatenCount() +
                "  PLAYER LEVEL: " + dish.playerBacteria().level(), 9, 9);
    }

    @Override
    public void spawnAgar(Agar agarSprite) {

        if(agarGroup.getArrayListSprites().size() < 20) {
            agarSprite.sprite().setImmutable(true);
            agarSprite.sprite().setActive(false);
            agarGroup.add(agarSprite.sprite());
        }
    }

    @Override
    public void spawnAI() {

        int spawnedAI = 0;

        for (Sprite aiSprite : aiBacteriaGroup.getSprites()) {


            if (aiSprite != null && !aiSprite.isActive()) {


                aiSprite.setActive(true);
                spawnedAI++;

                if (spawnedAI == GameModel.MAX_AIBACTERIA_SPAWN_PORTION) {
                    break;
                }
            }
        }
    }

    @Override
    public void agarEaten(Sprite bacteriaSprite, Sprite agarSprite) {

        agarSprite.setImmutable(false);
        for (Sprite a :agarGroup.sprites) {
            if(a.getId() == agarSprite.getId()) {
                agarGroup.remove(a);
                break;
            }
        }
    }

    @Override
    public void levelIncreased(Sprite bacteriaSprite) {

        BufferedImage currentSpriteImage = bacteriaSprite.getImage();
        bacteriaSprite
                .setImage(ImageScaler.scaleImage(currentSpriteImage, SCALE_IMAGE_X, SCALE_IMAGE_Y));
    }

    @Override
    public void bacteriaEaten(Sprite playerBacteria, Sprite aiBacteria) {

        if (dish.aiBacteria(playerBacteria).level() > dish.aiBacteria(aiBacteria).level()) {

            aiBacteria.setImmutable(false);
            aiBacteriaGroup.remove(aiBacteria);

        }

        if (dish.playerBacteria().level() > dish.aiBacteria(aiBacteria).level()) {

            aiBacteria.setImmutable(false);
            aiBacteriaGroup.remove(aiBacteria);

        }


    }


    @Override
    public void gameOver() {


        try {
            this.bg = new ImageBackground(ImageIO.read(new File("screens/gameover_screen.png")));
            this.bg.setClip(0, 0, (int) GAME_WINDOW.getWidth(), (int) GAME_WINDOW.getHeight());
            this.bg.setTotalClip(1280, 1024);
            pf = new PlayField(bg);

        }

     catch (IOException e) {
        e.printStackTrace();
    }





    }


    private Point mousePosition() {
        Point p = new Point(this.getMouseX(), this.getMouseY());
        p.x += bg.getX();
        p.y += bg.getY();
        return p;
    }
}
