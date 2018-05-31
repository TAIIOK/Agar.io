package com.mygdx.game;



        import java.awt.Graphics2D;
        import java.awt.image.BufferedImage;
        import java.io.File;
        import java.io.IOException;
        import javax.imageio.ImageIO;

public class GameObject {

    public final MyGdxGame parent = new MyGdxGame();

    public GameObject(MyGdxGame gameEngine) {

    }

    public void initResources() {

    }

    public void update(long l) {

    }

    public void render(Graphics2D graphics2D) {

    }

    public BufferedImage getImage(String s) {
        try {
            return ImageIO.read(new File(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void hideCursor() {

    }

    public boolean keyPressed(int i) {
        return false;
    }

    public void finish() {

    }



}
