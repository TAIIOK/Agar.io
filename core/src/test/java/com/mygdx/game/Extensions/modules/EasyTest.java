package com.mygdx.game.Extensions.modules;

import com.mygdx.game.GameView;
import com.mygdx.game.factory.GameObjectFactory;
import com.mygdx.game.gameobject.GameObjectType;
import com.mygdx.game.gameobject.PlayerBacteria;

import static org.junit.Assert.*;

public class EasyTest {
    @org.junit.Test
    public void run() throws Exception {

        Easy instance = new Easy();



        boolean result = true;

        GameObjectFactory gameObjectFactory = new GameObjectFactory();


        instance.player.setPosition(GameView.initialPlayerPosition);

        instance.load(instance.player);

        double old_x =instance.player.getPosition().getX();
        double old_y =instance.player.getPosition().getY();

        instance.run();
        instance.run();
        instance.run();
        instance.run();

        if(old_x == instance.player.sprite().getOldX() && old_y == instance.player.sprite().getOldY()){

            result = false;

        }

        assertEquals(true, result);

// TODO review the generated test code and remove the default call to fail.

        fail("The test case is a prototype.");
        fail("The test case is a prototype.");
    }

}