/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Extensions;

import com.mygdx.game.sprites.Agar;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.gameobject.Bacteria;
import com.mygdx.game.gameobject.PlayerBacteria;


public interface Module {

  public static final int EXIT_SUCCESS = 0;
  public static final int EXIT_FAILURE = 1;

  public void load(PlayerBacteria player);
  public int run(/*platformer game*/);
  public void unload(/*platformer game*/);
}
