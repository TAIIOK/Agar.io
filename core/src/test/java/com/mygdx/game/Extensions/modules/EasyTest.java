package com.mygdx.game.Extensions.modules;

import com.mygdx.game.Extensions.ModuleEngine;
import com.mygdx.game.gameobject.GameObjectType;
import com.mygdx.game.gameobject.PlayerBacteria;

import static org.junit.Assert.*;

public class EasyTest {
    @org.junit.Test
    public void run() throws Exception {

        PlayerBacteria playerBacteria = (PlayerBacteria) gameObjectFactory .createGameObject(GameObjectType.PLAYER_BACTERIA);
        ModuleEngine.main(arr, playerBacteria);

        player.sprite().setX(player.sprite().getOldX() + num);
        player.sprite().setY(player.sprite().getOldY()+ num);
        

    }

    @org.junit.Test
    public void load() throws Exception {
    }

}