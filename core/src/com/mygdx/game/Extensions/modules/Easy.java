/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Extensions.modules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.PlayField;
import com.mygdx.game.gameobject.PlayerBacteria;

import com.mygdx.game.Extensions.Module;

import java.awt.*;
import java.util.Random;

public class Easy implements Module {

    private long _lastActionTime = 0;
    private PlayField _ls = null;
    public PlayerBacteria player;



    @Override
    public int run(/*platformer game*/) {
        System.out.println("run");
;

        Random mRand = new Random();

        int num = mRand.nextInt(4) +1;
        player.sprite().setX(player.sprite().getOldX() + num);
            player.sprite().setY(player.sprite().getOldY()+ num);

        return 0;
    }
    

    @Override
    public void unload() {
        System.out.println("unload");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load(PlayerBacteria player) {
        this.player = player;
    }

}
